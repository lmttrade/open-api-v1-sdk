package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TransRecord {
    @JsonProperty("entrust_bs")
    private String entrustBs;
    @JsonProperty("future_dir")
    private String futureDir;
    @JsonProperty("client_oid")
    private String clientOid;
    private String commission;
    @JsonProperty("commission_currency")
    private String commissionCurrency;
    private String contract;
    @JsonProperty("dealt_amount")
    private String dealtAmount;
    @JsonProperty("dealt_price")
    private String dealtPrice;
    @JsonProperty("dealt_time")
    private String dealtTime;
    @JsonProperty("system_oid")
    private String systemOid;
    @JsonProperty("system_tid")
    private String systemTid;

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

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getCommissionCurrency() {
        return commissionCurrency;
    }

    public void setCommissionCurrency(String commissionCurrency) {
        this.commissionCurrency = commissionCurrency;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getDealtAmount() {
        return dealtAmount;
    }

    public void setDealtAmount(String dealtAmount) {
        this.dealtAmount = dealtAmount;
    }

    public String getDealtPrice() {
        return dealtPrice;
    }

    public void setDealtPrice(String dealtPrice) {
        this.dealtPrice = dealtPrice;
    }

    public String getDealtTime() {
        return dealtTime;
    }

    public void setDealtTime(String dealtTime) {
        this.dealtTime = dealtTime;
    }

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public String getSystemTid() {
        return systemTid;
    }

    public void setSystemTid(String systemTid) {
        this.systemTid = systemTid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("entrustBs", entrustBs)
                .append("futureDir", futureDir)
                .append("clientOid", clientOid)
                .append("commission", commission)
                .append("commissionCurrency", commissionCurrency)
                .append("contract", contract)
                .append("dealtAmount", dealtAmount)
                .append("dealtPrice", dealtPrice)
                .append("dealtTime", dealtTime)
                .append("systemOid", systemOid)
                .append("systemTid", systemTid)
                .toString();
    }
}
