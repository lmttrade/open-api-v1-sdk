package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author LMT
 */
public class CloseOrderReq {

    private String symbol;

    @JsonProperty("price_type")
    private String priceType;

    @JsonProperty("entrust_price")
    private String entrustPrice;

    @JsonProperty("entrust_vol")
    private String entrustVol;

    @JsonProperty("entrust_bs")
    private String entrustBs;

    @JsonProperty("deal_id")
    private String dealId;

    @JsonProperty("client_oid")
    private String clientOid;

    @JsonProperty("close_rule")
    private String closeRule;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("asset_code")
    private Long assetCode = 0L;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getEntrustVol() {
        return entrustVol;
    }

    public void setEntrustVol(String entrustVol) {
        this.entrustVol = entrustVol;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public String getCloseRule() {
        return closeRule;
    }

    public void setCloseRule(String closeRule) {
        this.closeRule = closeRule;
    }

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    public String getSymbol() {
        return symbol!=null ? symbol.replace('/','_'):null;
    }
}
