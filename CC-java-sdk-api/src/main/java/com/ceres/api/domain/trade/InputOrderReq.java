package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * @author LMT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InputOrderReq {

    private String symbol;

    @JsonProperty("price_type")
    private String priceType;

    @JsonProperty("entrust_price")
    private String entrustPrice;

    @JsonProperty("profit_value")
    private String profitValue;

    @JsonProperty("stop_value")
    private String stopValue;

    @JsonProperty("entrust_amount")
    private String entrustAmount;

    @JsonProperty("entrust_bs")
    private String entrustBs;

    @JsonProperty("future_dir")
    private String futureDir;

    @JsonProperty("deal_id")
    private String dealId;

    private String lever;

    @JsonProperty("client_oid")
    private String clientOid;

    @JsonProperty("trade_type")
    private String tradeType;

    @JsonProperty("margin_mode")
    private String marginMode;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("program_oid")
    private String programOid;

    @JsonProperty("asset_code")
    private Long assetCode = 0L;

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getMarginMode() {
        return marginMode;
    }

    public void setMarginMode(String marginMode) {
        this.marginMode = marginMode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public String getProfitValue() {
        return profitValue;
    }

    public void setProfitValue(String profitValue) {
        this.profitValue = profitValue;
    }

    public String getStopValue() {
        return stopValue;
    }

    public void setStopValue(String stopValue) {
        this.stopValue = stopValue;
    }

    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getFutureDir() {
        return futureDir;
    }

    public void setFutureDir(String futureDir) {
        this.futureDir = futureDir;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public String getProgramOid() {
        return programOid;
    }

    public void setProgramOid(String programOid) {
        this.programOid = programOid;
    }

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("symbol", symbol)
                .append("priceType", priceType)
                .append("entrustPrice", entrustPrice)
                .append("profitValue", profitValue)
                .append("stopValue", stopValue)
                .append("entrustAmount", entrustAmount)
                .append("entrustBs", entrustBs)
                .append("futureDir", futureDir)
                .append("dealId", dealId)
                .append("lever", lever)
                .append("clientOid", clientOid)
                .append("tradeType", tradeType)
                .append("marginMode", marginMode)
                .append("timestamp", timestamp)
                .append("programOid", programOid)
                .append("assetCode", assetCode)
                .toString();
    }
}
