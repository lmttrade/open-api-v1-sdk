# -*- coding: utf-8 -*-
import json
import time
from logging.handlers import RotatingFileHandler

import aiohttp
import asyncio
import logging

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)

handler = logging.StreamHandler()
handler.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s %(lineno)d - %(message)s"))
handler.setLevel(logging.DEBUG)
logger.addHandler(handler)

file_handler = RotatingFileHandler("./huobi_kline.log", 'a', maxBytes=1024 * 1024 * 20, backupCount=10)
file_handler.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s %(lineno)d - %(message)s))"))
file_handler.setLevel(logging.INFO)
logger.addHandler(file_handler)


class CoinCeresMsgHandler(object):

    def __init__(self, loop=None):
        self.loop = loop

    async def main(self, msg):
        logger.info(msg)


class CoinCeresWSClient(object):

    def __init__(self, api_key: str=None, secret_key: str=None, loop=None, ping_interval=3, ping_timeout=5):
        self.url = "ws://192.168.50.172:18003"
        self.api_key = api_key
        self.secret_key = secret_key
        self.evt_ping = {'msg_type': 'ping'}
        self.session = None
        self.ws = None
        self.ping_interval = ping_interval
        self.ping_timeout = ping_timeout
        self.last_ping_tm = 0
        self.last_pong_tm = 0
        self.channels = []
        self.message_handler = CoinCeresMsgHandler(loop=loop)
        asyncio.ensure_future(self.keep_connection())

    # keep connection
    async def keep_connection(self):
        while True:
            if not self.session or self.session.closed:
                logger.debug('create session 1')
                self.session = aiohttp.ClientSession()
            if self.ws:
                while not self.ws.closed:
                    logger.info('ws connected')
                    try:
                        logger.debug('ping')
                        await self.ws.send_json(self.evt_ping)
                        self.last_ping_tm = time.time()
                        await asyncio.sleep(self.ping_interval)
                    except:
                        logger.warning('ping failed')
                        break
            else:
                await self.ws_connect()
            await asyncio.sleep(1)

    async def check(self):
        has_timeout_expired = time.time() - self.last_ping_tm > self.ping_timeout
        has_pong_not_arrived_after_last_ping = self.last_pong_tm - self.last_ping_tm < 0
        has_pong_arrived_too_late = self.last_pong_tm - self.last_ping_tm > self.ping_timeout

        if (self.last_ping_tm
                and has_timeout_expired
                and (has_pong_not_arrived_after_last_ping or has_pong_arrived_too_late)):
            logger.warning('ping/pong timeout')
            await self.ws.close()
            await self.ws_connect()
            await self.subscribe(channels=self.channels)

    # connect websocket
    async def ws_connect(self):
        while self.session:
            if not self.session.closed:
                if not self.ws or self.ws.closed:
                    try:
                        self.ws = await self.session.ws_connect(self.url)
                        logger.debug('ws connecting')
                    except:
                        await asyncio.sleep(5)
                        logger.warning('ws reconnect 1')
                        continue
                    if self.ws and not self.ws.closed:
                        logger.debug('ws connect success')
                        break
                    else:
                        await asyncio.sleep(5)
                        logger.debug('ws reconnect 2')
                        continue
                else:
                    logger.debug('ws connected')
                    break
            else:
                await self.create_session()

    # create session
    async def create_session(self):
        if not self.session or self.session.closed:
            logger.debug('create session...')
            self.session = aiohttp.ClientSession()

    # 消息订阅
    async def subscribe(self, channels: list):
        """
        :param channels: 所有交易对一起订阅
        :return:
        """
        await self.create_session()
        while True:
            if self.ws and not self.ws.closed:
                await self.ws.send_json(channels)
                try:
                    await self.read_msg(ws=self.ws)
                except Exception as e:
                    # todo 重新连接
                    logger.warning("{}".format(e))
                    await self.ws.close()
                    await self.ws_connect()
            else:
                await self.ws_connect()

    async def read_msg(self, ws):
        """
        :param ws:
        :return:
        """
        while not ws.closed:
            try:
                msg = await ws.receive(timeout=60)
                # logger.debug(msg)
                # return
                if msg.type == aiohttp.WSMsgType.CLOSED:
                    logger.warning("websocket closed")
                    # todo raise exception?
                    await ws.close()
                    await self.ws_connect()
                    await self.subscribe(channels=self.channels)

                elif msg.type == aiohttp.WSMsgType.ERROR:
                    logger.warning("msg type error")
                    continue
                elif msg.type == aiohttp.WSMsgType.TEXT:
                    # todo 消息处理
                    data = json.loads(msg.data)
                    logging.debug(msg)
                    try:
                        pong = data.get('msg_type')
                        if pong == 'pong':
                            logger.debug('pong')
                            self.last_pong_tm = time.time()
                            continue
                    except:
                        pass
                    try:
                        await self.message_handler.main(msg=json.loads(msg.data))
                    except Exception as e:
                        logger.warning("data error:{}".format(e))

            except asyncio.TimeoutError:
                await ws.close()
                await self.ws_connect()
                await self.subscribe(channels=self.channels)
                break

    def _sub(self, sub_type: str, contract: list):
        self.channels += ({'msg_type': sub_type, 'exchange': i[0], 'contract': i[1]} for i in contract)

    async def run(self):
        print(self.channels)
        return await self.subscribe(channels=self.channels)

    def sub_tick(self, contract: list = (('HUOBI', 'BTC/USDT'), ('OKEX', 'BTC/USDT'), )):
        self._sub('subscribe-tick', contract)

    def sub_trade(self, contract: list = (('HUOBI', 'BTC/USDT'), ('OKEX', 'BTC/USDT'), )):
        self._sub('subscribe-trade', contract)

    def sub_depth(self, contract: list = (('HUOBI', 'BTC/USDT'), ('OKEX', 'BTC/USDT'), )):
        self._sub('subscribe-depth', contract)

    def sub_kline(self, contract: list = (('HUOBI', 'BTC/USDT', '1m'), ('OKEX', 'BTC/USDT', '1m'), )):
        self.channels += ({'msg_type': 'subscribe-candle', 'exchange': i[0],
                           'contract': i[1], 'duration': i[2]} for i in contract)


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    c = CoinCeresWSClient(loop=loop)
    c.sub_tick()
    task = c.run()
    loop.run_until_complete(asyncio.wait([task]))
