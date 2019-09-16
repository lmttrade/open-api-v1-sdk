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
        orderStreamClient = CoinceresApiClientFactory.newInstance("bVHOwaYzkmtfSUXr","b7tKSQahoYzfcI7nwJ0qAgXXuArzTstl")
                .newTradeWebSocketClient();

    }

    public static void main(String[] args) {
        try {

            connectAndListen();

            ScheduledExecutorService reconnectService = new ScheduledThreadPoolExecutor(1,
                    new BasicThreadFactory.Builder().namingPattern("lmt-trade-reconnect-scheduled-%d").daemon(true).build());
            reconnectService.scheduleAtFixedRate(()->{
                Long currentPingCount = MONITOR_MAP.getOrDefault(MONITOR_TRADE,0L);
                if (currentPingCount.longValue() == lastPingCount && lastPingCount != 0){
                    // 重连
                    orderStreamClient = CoinceresApiClientFactory.newInstance("bVHOwaYzkmtfSUXr","b7tKSQahoYzfcI7nwJ0qAgXXuArzTstl")
                            .newTradeWebSocketClient();
                    connectAndListen();
                    lastPingCount = 0;
                }else {
                    lastPingCount = currentPingCount.longValue();
                }

            },6,15,TimeUnit.SECONDS);

        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    private static void connectAndListen(){
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
