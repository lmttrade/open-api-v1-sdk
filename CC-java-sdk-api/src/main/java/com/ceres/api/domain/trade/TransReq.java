package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author LMT
 */
public class TransReq {

    private String exchange;

    private String symbol;

    @JsonProperty("system_oid")
    private String systemOid;

    private Long from;

    private Integer count;

    @JsonProperty("asset_code")
    private Long assetCode = 0L;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
