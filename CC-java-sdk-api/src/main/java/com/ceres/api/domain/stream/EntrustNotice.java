package com.ceres.api.domain.stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntrustNotice {
    private long productId;
    private String entrustId;
    private String avgDealPrice;
    private String totalDealVol;
    private int entrustStatus;
    private int messageType;

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
