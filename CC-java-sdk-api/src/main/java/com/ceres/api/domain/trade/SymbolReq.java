package com.ceres.api.domain.trade;
/**
 * @author LMT
 */
public class SymbolReq {

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