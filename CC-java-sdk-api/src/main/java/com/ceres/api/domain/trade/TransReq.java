package com.ceres.api.domain.trade;
/**
 * @author LMT
 */
public class TransReq {

    private String exchange;

    private String symbol;

    private String count;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSymbol() {
        if (symbol != null) {
            return symbol.replace('/', '_');
        }
        return null;
    }
}
