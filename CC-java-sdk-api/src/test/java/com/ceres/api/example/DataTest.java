package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.data.MarketArea;
import com.ceres.api.domain.data.TickData;
import com.ceres.api.domain.data.TickDataReq;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.service.CoinceresDataRestClient;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019/02/14
 * 关于行情数据请求的示例
 */
public class DataTest {

    private static CoinceresDataRestClient dataRestClient;

    static {
        dataRestClient = CoinceresApiClientFactory.newInstance().newDataClient();
    }

    public static void main(String[] args) {

        // 1.查询所有交易所的所有交易对信息
        ResultsVO<List<MarketArea>> result = dataRestClient.queryAllMarketArea();
        System.out.println(result);

        // 2. 请求历史分钟线数据
//        CycleReq req = new CycleReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        req.setEnd(System.currentTimeMillis() + "");
//        System.out.println(req.getEnd());
//        req.setSize(5);
//        req.setDuration("1h");
//        OpenResp<List<CycleData>> result = dataRestClient.queryHistoryCycleData(req);
//        System.out.println(result);

        // 3. 请求成交数据
//        TradeDataReq req = new TradeDataReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        req.setEnd(System.currentTimeMillis()+"");
//        req.setSize(5);
//        OpenResp<List<TradeData>> result = dataRestClient.queryTradeData(req);
//        System.out.println(result);

        // 4. 请求depth10数据
//        DepthDataReq req = new DepthDataReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        OpenBookData result = dataRestClient.queryBookData(req);
//        System.out.println(result);

        // 5. 请求tick数据
//        TickDataReq req = new TickDataReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        TickData result = dataRestClient.queryTickData(req);
//        System.out.println(result);
    }

}
