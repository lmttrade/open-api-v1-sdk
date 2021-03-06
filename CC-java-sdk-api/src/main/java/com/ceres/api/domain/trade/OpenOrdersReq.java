package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: wulei
 * @date: 19/9/10
 * @description:
 */
public class OpenOrdersReq {
    private String exchange;
    private String symbol;
    private String from;
    private int limit = 100;
    @JsonProperty("asset_code")
    private long assetCode=0;
    private Long timestamp;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(long assetCode) {
        this.assetCode = assetCode;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
