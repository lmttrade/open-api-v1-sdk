package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LMT
 * @date: 19/9/4
 * @description:
 */
public class InstantTradingAskPriceReq {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("entrust_type")
    private int entrustType;

    @JsonProperty("entrust_amount")
    private String entrustAmount;

    @JsonProperty("entrust_bs")
    private String entrustBs;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(int entrustType) {
        this.entrustType = entrustType;
    }

    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    @Override
    public String toString() {
        return "InstantTradingAskPriceReq{" +
                "symbol='" + symbol + '\'' +
                ", entrustType='" + entrustType + '\'' +
                ", entrustAmount='" + entrustAmount + '\'' +
                ", entrustBs='" + entrustBs + '\'' +
                '}';
    }
}
