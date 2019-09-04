package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LMT
 * @date: 19/9/4
 * @description:
 */
public class InstantTradingAskPriceRes {
    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("dealt_price")
    private String dealtPrice;

    @JsonProperty("dealt_amount")
    private String dealtAmount;

    @JsonProperty("dealt_time")
    private String dealtTime;

    @JsonProperty("confirm_id")
    private String confirmId;

    @JsonProperty("time_out")
    private int timeOut;

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

    public String getDealtPrice() {
        return dealtPrice;
    }

    public void setDealtPrice(String dealtPrice) {
        this.dealtPrice = dealtPrice;
    }

    public String getDealtAmount() {
        return dealtAmount;
    }

    public void setDealtAmount(String dealtAmount) {
        this.dealtAmount = dealtAmount;
    }

    public String getDealtTime() {
        return dealtTime;
    }

    public void setDealtTime(String dealtTime) {
        this.dealtTime = dealtTime;
    }

    public String getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public String toString() {
        return "InstantTradingAskPriceRes{" +
                "exchange='" + exchange + '\'' +
                ", symbol='" + symbol + '\'' +
                ", dealtPrice='" + dealtPrice + '\'' +
                ", dealtAmount='" + dealtAmount + '\'' +
                ", dealtTime='" + dealtTime + '\'' +
                ", confirmId='" + confirmId + '\'' +
                ", timeOut=" + timeOut +
                '}';
    }
}
