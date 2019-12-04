package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * @author LMT
 */
public class TransRecord {
    private String exchange;
    @JsonProperty("entrust_bs")
    private String entrustBs;
    @JsonProperty("future_dir")
    private String futureDir;
    private String commission;
    @JsonProperty("commission_currency")
    private String commissionCurrency;
    private String symbol;
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
    @JsonProperty("asset_code")
    private Long assetCode;
    @JsonProperty("flow_id")
    private String flowId;

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

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("entrustBs", entrustBs)
                .append("futureDir", futureDir)
                .append("commission", commission)
                .append("commissionCurrency", commissionCurrency)
                .append("symbol", symbol)
                .append("dealtAmount", dealtAmount)
                .append("dealtPrice", dealtPrice)
                .append("dealtTime", dealtTime)
                .append("systemOid", systemOid)
                .append("systemTid", systemTid)
                .append("flowId", flowId)
                .toString();
    }
}
