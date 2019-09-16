package com.ceres.api.domain.data;

/**
 * 闪电交易币对报价消息包体
 *
 * @author LMT
 * @date 2019-09-16 12:47
 */
public class FlashIndicativePriceReq {
    private String exchange;
    private String symbol;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
