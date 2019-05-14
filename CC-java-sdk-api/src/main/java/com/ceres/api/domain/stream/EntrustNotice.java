package com.ceres.api.domain.stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntrustNotice {
    /** 产品名称 */
    private String product;

    /** 交易所名称 */
    private String exchange;

    /** 交易对 */
    private String contract;

    /** 委托单号 */
    private String entrustId;

    /** 成交均价 */
    private String avgDealPrice;

    /** 委托时间 */
    private String entrustTime;

    /** 委托状态 */
    private String entrustStatus;

    /** 委托方向 */
    private String entrustDir;

    /** 价格类型 */
    private String entrustType;

    /** 杠杆倍数 */
    private String lever;

    /** 委托价格 */
    private String entrustPrice;

    /** 委托数量 */
    private String entrustVol;

    /** 成交数量 */
    private String dealVol;

    /** 总成交数量 */
    private String totalDealVol;

    /** 通知类型 1: 状态变更通知  2: 保证金追加 */
    private int messageType;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getAvgDealPrice() {
        return avgDealPrice;
    }

    public void setAvgDealPrice(String avgDealPrice) {
        this.avgDealPrice = avgDealPrice;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getEntrustDir() {
        return entrustDir;
    }

    public void setEntrustDir(String entrustDir) {
        this.entrustDir = entrustDir;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
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

    public String getEntrustVol() {
        return entrustVol;
    }

    public void setEntrustVol(String entrustVol) {
        this.entrustVol = entrustVol;
    }

    public String getDealVol() {
        return dealVol;
    }

    public void setDealVol(String dealVol) {
        this.dealVol = dealVol;
    }

    public String getTotalDealVol() {
        return totalDealVol;
    }

    public void setTotalDealVol(String totalDealVol) {
        this.totalDealVol = totalDealVol;
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
