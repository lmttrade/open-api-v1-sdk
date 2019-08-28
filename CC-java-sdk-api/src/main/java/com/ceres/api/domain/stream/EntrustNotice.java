package com.ceres.api.domain.stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author LMT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntrustNotice {
    /** 交易所名称 */
    private String exchange;

    /** 交易对 */
    private String symbol;

    /** 委托单号 */
    @JsonProperty("system_oid")
    private String systemOid;

    /** 成交均价 */
    @JsonProperty("average_dealt_price")
    private String averageDealtPrice;

    /** 委托时间 */
    @JsonProperty("entrust_time")
    private String entrustTime;

    /** 委托状态 */
    @JsonProperty("status")
    private String status;

    /** 委托方向 */
    @JsonProperty("entrust_bs")
    private String entrustBs;

    /** 价格类型 */
    @JsonProperty("price_type")
    private String priceType;

    /** 杠杆倍数 */
    private String lever;

    /** 委托价格 */
    @JsonProperty("entrust_price")
    private String entrustPrice;

    /** 委托数量 */
    @JsonProperty("entrust_amount")
    private String entrustAmount;

    /** 成交数量 */
    @JsonProperty("dealt_amount")
    private String dealtAmount;

    /** 最后一次成交时间 */
    @JsonProperty("dealt_time")
    private String dealtTime;

    /** 通知类型 1: 状态变更通知  2: 保证金追加 */
    private int messageType;

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

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public String getAverageDealtPrice() {
        return averageDealtPrice;
    }

    public void setAverageDealtPrice(String averageDealtPrice) {
        this.averageDealtPrice = averageDealtPrice;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getLever() {
        return lever;
    }

    public void setLever(String lever) {
        this.lever = lever;
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

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
