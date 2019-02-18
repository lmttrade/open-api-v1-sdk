package com.ceres.api.domain.stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderNotice {
    private long productId;
    private String entrustId;
    private int messageType;
    private String avgDealPrice;
    private String totalDealVol;
    private int entrustStatus;
    private Integer type;
    private String dealId;
    private String profit;
    private String stop;
    private String forcedPrice;
    private String totalMargin;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getAvgDealPrice() {
        return avgDealPrice;
    }

    public void setAvgDealPrice(String avgDealPrice) {
        this.avgDealPrice = avgDealPrice;
    }

    public String getTotalDealVol() {
        return totalDealVol;
    }

    public void setTotalDealVol(String totalDealVol) {
        this.totalDealVol = totalDealVol;
    }

    public int getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(int entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getForcedPrice() {
        return forcedPrice;
    }

    public void setForcedPrice(String forcedPrice) {
        this.forcedPrice = forcedPrice;
    }

    public String getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(String totalMargin) {
        this.totalMargin = totalMargin;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
