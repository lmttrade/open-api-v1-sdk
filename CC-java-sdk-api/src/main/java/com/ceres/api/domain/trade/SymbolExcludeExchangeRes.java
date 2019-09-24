package com.ceres.api.domain.trade;

import java.io.Serializable;


public class SymbolExcludeExchangeRes implements Serializable {

    /***
     * 交易所名称
     */
    private String exchange;

    /***
     * 币对
     */
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

    @Override
    public String toString() {
        return "ContractExcludeExchangeRes{" +
                "exchange='" + exchange + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
