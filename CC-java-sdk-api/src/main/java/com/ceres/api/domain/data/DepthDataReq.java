package com.ceres.api.domain.data;
/**
 * @author LMT
 * @date 2019/01/30
 */
public class DepthDataReq {

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
