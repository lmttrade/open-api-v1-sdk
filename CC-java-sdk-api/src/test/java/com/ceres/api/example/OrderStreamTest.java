package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.stream.EntrustNotice;
import com.ceres.api.domain.stream.PositionNotice;
import com.ceres.api.service.CoinceresTradeWebSocketClient;
import com.ceres.api.util.JsonUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.ceres.api.client.CoinceresApiClientFactory.MONITOR_MAP;
import static com.ceres.api.constant.Const.MONITOR_TRADE;
import static com.ceres.api.constant.Const.orderWsUrl;

/**
 * 订单状态数据推送
 *
 * @author xiaotian.huang
 * @date 2019/05/05
 */
public class OrderStreamTest {

    private static CoinceresTradeWebSocketClient orderStreamClient;

    private static long lastPingCount = 0;

    static  {
        orderStreamClient = CoinceresApiClientFactory.newInstance("kycxakrfMVpTDYgb",
                "kcOLgiND8dxNfIQ3rymD7BOYv38wZJkW")
                .newTradeWebSocketClient(orderWsUrl);

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
                    orderStreamClient.closeWebSocket();
                    orderStreamTest.connectAndListen();
                }else {
                    if (currentPingCount.longValue() == lastPingCount && lastPingCount != 0) {
                        // 重连
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
