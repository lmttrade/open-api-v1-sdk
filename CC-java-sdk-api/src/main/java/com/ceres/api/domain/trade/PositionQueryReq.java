package com.ceres.api.domain.trade;
/**
 * @author LMT
 */
public class PositionQueryReq {

    private String exchange;
    private String symbol;
    private String positionDir;

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

    public String getPositionDir() {
        return positionDir;
    }

    public void setPositionDir(String positionDir) {
        this.positionDir = positionDir;
    }
}
