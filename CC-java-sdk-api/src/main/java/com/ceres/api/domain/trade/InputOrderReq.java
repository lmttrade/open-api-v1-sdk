package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputOrderReq {

    private String exchange;

    private String contract;

    @JsonProperty("price_type")
    private String priceType;

    @JsonProperty("entrust_price")
    private String entrustPrice;

    @JsonProperty("profit_value")
    private String profitValue;

    @JsonProperty("stop_value")
    private String stopValue;

    @JsonProperty("entrust_vol")
    private String entrustVol;

    @JsonProperty("entrust_bs")
    private String entrustBs;

    @JsonProperty("future_dir")
    private String futureDir;

    @JsonProperty("deal_id")
    private String dealId;

    private String lever;

    @JsonProperty("client_oid")
    private String clientOid;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getContract() {
        return contract!=null ? contract.replace('/','_'):null;
    }

    public void setContract(String contract) {
        this.contract = contract;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("exchange", exchange)
                .append("contract", contract)
                .append("priceType", priceType)
                .append("entrustPrice", entrustPrice)
                .append("profitValue", profitValue)
                .append("stopValue", stopValue)
                .append("entrustVol", entrustVol)
                .append("entrustBs", entrustBs)
                .append("futureDir", futureDir)
                .append("dealId", dealId)
                .append("lever", lever)
                .append("clientOid", clientOid)
                .toString();
    }
}
