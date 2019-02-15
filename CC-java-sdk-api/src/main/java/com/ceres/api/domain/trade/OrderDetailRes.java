package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderDetailRes {

    private String contract;
    @JsonProperty("entrust_bs")
    private String entrustBs;
    @JsonProperty("future_dir")
    private String futureDir;
    @JsonProperty("client_oid")
    private String clientOid;
    @JsonProperty("system_oid")
    private String systemOid;
    private Integer status;
    @JsonProperty("entrust_price")
    private String entrustPrice;
    @JsonProperty("entrust_amount")
    private String entrustAmount;
    @JsonProperty("entrust_time")
    private String entrustTime;
    @JsonProperty("average_dealt_price")
    private String averageDealtPrice;
    @JsonProperty("dealt_amount")
    private String dealtAmount;
    private String lever;
    @JsonProperty("profit_value")
    private String profitValue;
    @JsonProperty("stop_value")
    private String stopValue;
    private String commission;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
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

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getAverageDealtPrice() {
        return averageDealtPrice;
    }

    public void setAverageDealtPrice(String averageDealtPrice) {
        this.averageDealtPrice = averageDealtPrice;
    }

    public String getDealtAmount() {
        return dealtAmount;
    }

    public void setDealtAmount(String dealtAmount) {
        this.dealtAmount = dealtAmount;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
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

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("contract", contract)
                .append("entrustBs", entrustBs)
                .append("futureDir", futureDir)
                .append("clientOid", clientOid)
                .append("systemOid", systemOid)
                .append("status", status)
                .append("entrustPrice", entrustPrice)
                .append("entrustAmount", entrustAmount)
                .append("entrustTime", entrustTime)
                .append("averageDealtPrice", averageDealtPrice)
                .append("dealtAmount", dealtAmount)
                .append("lever", lever)
                .append("profitValue", profitValue)
                .append("stopValue", stopValue)
                .append("commission", commission)
                .toString();
    }
}
