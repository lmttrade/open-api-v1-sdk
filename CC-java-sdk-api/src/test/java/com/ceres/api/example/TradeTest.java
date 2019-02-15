package com.ceres.api.example;

import com.ceres.api.client.CoinceresApiClientFactory;
import com.ceres.api.domain.trade.ContractReq;
import com.ceres.api.domain.trade.CurrencyPair;
import com.ceres.api.domain.trade.ResultsVO;
import com.ceres.api.service.CoinceresApiRestClient;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019/02/11
 * 关于交易的示例
 */
public class TradeTest {

    private static CoinceresApiRestClient restClient;

    static {
        restClient = CoinceresApiClientFactory.newInstance("jfAFaUgGPexhzsnJ","jDW3sYWwV6LZRo3plnrpJmK9EkJrwjZM").newRestClient();
    }

    public static void main(String[] args) {
        // 1.查询交易对
        ContractReq req = new ContractReq();
        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
        ResultsVO<List<CurrencyPair>> result = restClient.getContractList(req);
        System.out.println(result);

        // 2.下单
//        InputOrderReq req = new InputOrderReq();
//        req.setExchange("OKEX");
//        req.setContract("ADA_BTC");
//        req.setPriceType("limit");
//        req.setEntrustPrice("0.000011");
//        req.setProfitValue("0.00000997");
//        req.setEntrustVol("10");
//        req.setEntrustBs("buy");
//        req.setFutureDir("open");
//        req.setLever("10");
//        req.setClientOid("12345");
//        ResultsVO<InputOrderRes> result = restClient.input(req);
//        System.out.println(result);

        // 3.撤单 (path参数签名处理，进行了临时处理 todo）
//        ResultsVO<Map<String, List<SystemOidRecord>>> result = restClient.cancel("149150845501059075");
//        System.out.println(result);

        // 4. 查询账户信息
//        ResultsVO<List<AccountInfoRes>> result = restClient.getAccountInfo("OKEX");
//        System.out.println(result);

        // 5. 查询订单信息
//        OrderDetailReq req = new OrderDetailReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        ResultsVO<List<OrderDetailRes>> result = restClient.getOrderInfo(req);
//        System.out.println(result);

        // 6. 查询合约持仓
//        PositionQueryReq req = new PositionQueryReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        ResultsVO result = restClient.getPosition(req);
//        System.out.println(result);

        // 7. 查询成交
//        TransReq req = new TransReq();
//        req.setExchange("OKEX");
//        req.setContract("BTC/USDT");
//        req.setCount("10");
//        ResultsVO<List<TransRecord>> result = restClient.queryTransRecord(req);
//        System.out.println(result);
    }
}
