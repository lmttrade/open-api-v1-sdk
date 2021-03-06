# 交易websocket



## 1.说明：

> 在LMT交易时，获取通知是非常方便的，使用SDK中的客户端工厂，获取webSocket客户端，与服务端保持连接即可获取与该apiKey对应产品的相关通知。
>
> 通知类型包括：下单通知、撤单通知、状态变更通知、成交通知。



## 2.示例

```java
public class OrderStreamTest {
    private static CoinceresTradeWebSocketClient orderStreamClient;

    private static long lastPingCount = 0;
    static  {
            orderStreamClient = CoinceresApiClientFactory.newInstance("kycxakrfMVpTDYgb",
                    "kcOLgiND8dxNfIQ3rymD7BOYv38wZJkW")
                    .newTradeWebSocketClient(orderWsUrl);// 这里URL 可选沙箱环境进行测试：PRE_orderWsUrl

        }
    private static long testPingCountEqualsAndReconnect = 5;
    public static void main(String[] args) {
        try {
                    OrderStreamTest orderStreamTest = new OrderStreamTest();
                    orderStreamTest.connectAndListen();

                    ScheduledExecutorService reconnectService = new ScheduledThreadPoolExecutor(1,
                            new BasicThreadFactory.Builder().namingPattern("lmt-trade-reconnect-scheduled-%d").daemon(true).build());
                    reconnectService.scheduleAtFixedRate(()->{
                        Long currentPingCount = MONITOR_MAP.getOrDefault(MONITOR_TRADE,0L);
                        if (currentPingCount == testPingCountEqualsAndReconnect){
                            System.out.println("模拟心跳次数相等 主动重连");
                            // 这里为了测试 强制重连
                            // 重连需要注意 先关闭 后重连 否则重新连接上后 消息将重复推送
                            orderStreamClient.closeWebSocket();
                            orderStreamTest.connectAndListen();
                        }else {
                            if (currentPingCount.longValue() == lastPingCount && lastPingCount != 0) {
                                // 重连需要注意 先关闭 后重连 否则重新连接上后 消息将重复推送
                                orderStreamClient.closeWebSocket();
                                orderStreamTest.connectAndListen();
                                lastPingCount = 0;
                            } else {
                                lastPingCount = currentPingCount.longValue();
                            }
                        }

                    },6,15,TimeUnit.SECONDS);

                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
    }

    public  void connectAndListen(){
            orderStreamClient.onOrderStreamEvent(response -> {
                String json = JsonUtils.serialize(response);

                int messageType = response.getMessage_type();
                if (1 == messageType) {
                    // entrustNotice
                    EntrustNotice entrustNotice = JsonUtils.parse(json, EntrustNotice.class);
                    System.out.println(entrustNotice);
                } else {
                    // positionNotice
                    PositionNotice positionNotice = JsonUtils.parse(json, PositionNotice.class);
                    System.out.println(positionNotice);
                }
            });
        }
}
```

## 3. 补充说明:

   >交易Websocket订阅
   >
   >
   >
   >base_url:
   >
   > wss://open.lmt.trade
   >
   >
   >
   >订阅完整路径
   >api_key:apiKey
   >timestamp:时间戳
   >sign:签名结果
   > wss://open.lmt.trade/ws/trade?api_key=**********&timestamp=1568602940731&sign=**********
   >
   >按照签名说明文档中描述的规则进行加密得到sign



## 4. 通知数据结构

#### 订单变更推送信息

```json
{
    "exchange":"LMT", #交易所名称
    "symbol":"LTC_BTC", #交易对
    "system_oid":"181829153963257883", #委托单号
    "average_dealt_price":"0", #成交均价
    "entrust_time":"2019-02-16 19:38:31", #委托时间
    "status":"SUBMITTING", #委托状态
    "entrust_bs":"BUY", #委托方向
    "price_type":"LIMIT", #价格类型
    "lever":"null", #杠杆倍数
    "entrust_price":"0.01" #委托价格
    "entrust_amount":"1", #委托数量
    "dealt_amount":"0", #累计成交数量
    "last_fill_amount":"0",#最新一次成交数量 无成交为0
    "last_fill_price":"0",#最新一次成交价格 无成交为0
    "dealt_time": "2019-02-16 19:38:34", #成交时间
    "message_type": 1, #1:订单通知 2:持仓变化通知
    "client_oid":"12345", #客户端保留字段ID
    "trade_type":"spot",#交易类型 spot:现货交易
    "margin_mode": "none", #保证金模式 暂不提供
    "asset_code":190810234, #账户编码
    "program_oid":"AT281829153963257883" #程序单id
}
```

补充说明:

```
status取值:
正报 "SUBMITTING", 已报 "SUBMITTED", 部分成交 "PART_FILLED", 完全成交 "FILLED",
废单 "FAILURE", 撤单 "CANCEL",正撤 "CANCELING", 待撤 "PENDING", 部分撤单 "PART_CANCELED",
已撤 "CANCELED"
entrust_bs取值:
买 BUY, 卖 SELL, 开多 BUY_OPEN, 开空 SELL_OPEN,平多 SELL_CLOSE, 平空 BUY_CLOSE
price_type取值:
限价 LIMIT , 市价 MARKET
dealt_amount: 单次成交数量
```
