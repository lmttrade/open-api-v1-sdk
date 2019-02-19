# 交易websocket



## 1.说明：

> 在CoinCeres交易时，获取通知是非常方便的，使用SDK中的客户端工厂，获取webSocket客户端，与服务端保持连接即可获取与该apiKey对应产品的相关通知。
>
> 通知类型包括：下单通知、撤单通知、平仓通知、状态变更通知、保证金追加通知、止盈止损通知、成交通知。



## 2.示例

```java
public class OrderStreamTest {
    public static void main(String[] args) {
        
        CoinceresTradeWebSocketClient orderStreamClient = CoinceresApiClientFactory.newInstance("jfAFaUgGPexhzsnJ","jDW3sYWwV6LZRo3plnrpJmK9EkJrwjZM").newTradeWebSocketClient();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            orderStreamClient.onOrderStreamEvent(response -> {
                String json = mapper.writeValueAsString(response);
                int messageType = response.getMessageType();
                if (1 == messageType) {
                    // entrustNotice
                    EntrustNotice entrustNotice = mapper.readValue(json, EntrustNotice.class);
                    System.out.println(entrustNotice);
                } else {
                    // positionNotice
                    PositionNotice positionNotice = mapper.readValue(json, PositionNotice.class);
                    System.out.println(positionNotice);
                }
            });
        } catch (Exception e) {
            TestCase.fail(e.getMessage());
        }
    }
}
```

