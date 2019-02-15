# -*- coding: utf-8 -*-
from coinceres.api import APIClient
from coinceres.sign import SignMixin


def test_sign():
    payload = {
        "exchange": "BITMEX"
    }
    sign = SignMixin()
    sign.secret_key = "TQAFrtRS4WrUn1hHKByszao3ozWvIMkF"
    assert sign.sign(payload) == "9173fddc96e445b07a3d11295b52ca45385f96d9a52307da58134a2fd7e239be"


def test_contract():
    r = APIClient(api_key="dWbkgDeLIzLavnYs", secret_key="dePW2XslyzFYnTuc41yRhqHIUWEVco4W",
                  host="192.168.50.172").contract_info("BITMEX")
    print(r)


def test_account():
    r = APIClient(api_key="dWbkgDeLIzLavnYs", secret_key="dePW2XslyzFYnTuc41yRhqHIUWEVco4W",
                  host="192.168.50.172").account()
    print(r)


def test_order():
    r = APIClient(api_key="dWbkgDeLIzLavnYs", secret_key="dePW2XslyzFYnTuc41yRhqHIUWEVco4W",
                  host="192.168.50.172").limit_order("BITMEX", "BTC/USDT", "10", "buy", "open", "10", "0.000011",
                                                     "0.00000997")
    print(r)


def test_depth():
    r = APIClient(host="market.coinceres.com").depth("HUOBI", "BTC/USDT")
    print(r)


def test_kline():
    r = APIClient(host="market.coinceres.com").kline("HUOBI", "BTC/USDT", '1d', '1546985600000', '1560130387000')
    print(r)


def test_tick():
    r = APIClient(host="market.coinceres.com").tick("HUOBI", "BTC/USDT")
    print(r)


def test_trade():
    r = APIClient(host="market.coinceres.com").trade("HUOBI", "BTC/USDT", '1546985600000', '1560130387000')
    print(r)
