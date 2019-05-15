# 交易websocket



## 1.说明：

> 在CoinCeres交易时，获取通知是非常方便的，使用SDK中的客户端工厂，获取webSocket客户端，与服务端保持连接即可获取与该apiKey对应产品的相关通知。
>
> 通知类型包括：下单通知、撤单通知、平仓通知、状态变更通知、保证金追加通知、止盈止损通知、成交通知。



## 2.示例

```java
public class OrderStreamTest {
    private static CoinceresTradeWebSocketClient orderStreamClient;
    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("dWbkgDeLIzLavnYs","dePW2XslyzFYnTuc41yRhqHIUWEVco4W").newTradeWebSocketClient();
    }

    public static void main(String[] args) {
        try {
            orderStreamClient.onOrderStreamEvent(response -> {
                String json = JsonUtils.serialize(response);

                int messageType = response.getMessageType();
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
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
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
   > ws://open.coinceres.com:19003
   >
   >
   >
   >订阅完整路径
   >
   > ws://open.coinceres.com:19003?api_key=**********&sign=**********
   >
   >按照签名说明文档中描述的规则进行加密得到sign



## 4. 通知数据结构

#### A.成交推送，状态推送的websocket信息

```json
{
	"product":"testbc-btc", # 产品名称
    "exchange":"OKEX", # 交易所名称
	"contract":"LTC/BTC", # 交易对
    "entrustId":"181829153963257883", # 委托单号
	"avgDealPrice":"0", # 成交均价
	"entrustTime":"2019-02-16 19:38:31", # 委托时间
	"entrustStatus":"SUBMITTING", # 委托状态 
	"entrustDir":"BUY", # 委托方向
	"entrustType":"LIMIT", # 价格类型
	"lever":"null", # 杠杆倍数
	"entrustPrice":"0.01" # 委托价格
    "entrustVol":"1", # 委托数量
	"dealVol":"0", # 成交数量
	"totalDealVol":"null", # 总成交数量
	"messageType":"1" # 通知类型 1: 状态变更通知  2: 保证金追加
}
```

补充说明:

```
entrustStatus取值:
未报 "NO_REPORT", 正报 "SUBMITTING", 已报 "SUBMITTED", 部分成交 "PART_FILLED", 完全成交 "FILLED",
废单 "FAILURE", 撤单 "CANCEL",正撤 "CANCELING", 待撤 "PENDING", 部分撤单 "PART_CANCELED",
已撤 "CANCELED"
entrustDir取值:
买 BUY, 卖 SELL, 开多 OPEN_BUY, 开空 OPEN_SELL,平多 CLOSE_SELL, 平空CLOSE_BUY
entrustType取值:
限价 LIMIT , 市价 MARKET
```

#### B.保证金变化, 止盈止损

```json
{    
    "product":"testbc-btc", # 产品名称
    "entrustId":"181829153963257883", # 委托id
    "forcedPrice":"12", # 强平价
    "totalMargin":"73", # 总保证金
    "profit":"30", # 止盈
    "stop":"10", # 止损
    "dealId":"", # 持仓号
    "type":"PENDING", # 挂单或者持仓
    "messageType":"2" # 通知类型 1: 状态变更通知  2: 保证金追加
}
```

补充说明

```
type取值:
挂单 PENDING, 持仓 POSITION
```
