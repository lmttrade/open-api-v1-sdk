
# 行情交易 rest和websocket api

market 目录下是行情api
trade 目录下是交易api

- 增加沙箱环境配置URL
- 代码路径 com.ceres.api.constant.Const

#### 沙箱环境
>沙箱环境使用的行情交易 Rest url:   https://open.lmttrade.net

>沙箱环境使用的行情Websocket url:  wss://open.lmttrade.net/ws/market

>沙箱环境使用的交易Websocket url: wss://open.lmttrade.net/ws/trade

#### 生产环境
>正式环境使用的行情交易 Rest url:   https://open.lmt.trade

>正式环境使用的行情Websocket url:  wss://open.lmt.trade/ws/market

>正式环境使用的交易Websocket url: wss://open.lmt.trade/ws/trade

```java
// 行情示例
// httpClient
CoinceresApiClientFactory.newInstance().newDataClient(PRE_API_BASE_URL);
// websocket
CoinceresApiClientFactory.newWebSocketClient(PRE_wsUrl);

// 交易示例
// httpClient
CoinceresApiClientFactory.newInstance("apiKey",
                "secret").newRestClient(Const.PRE_API_BASE_URL);
// websocket
CoinceresApiClientFactory.newInstance("apiKey",
                 "secret").newTradeWebSocketClient(PRE_orderWsUrl)
```
