package com.ceres.api.domain.stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author LMT
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderNotice {
    /** 委托单号 */
    @JsonProperty("system_oid")
    private String systemOid;

    private int messageType;
    /** 成交均价 */
    @JsonProperty("average_dealt_price")
    private String averageDealtPrice;

    /** 成交数量 */
    @JsonProperty("dealt_amount")
    private String dealtAmount;

    /** 委托状态 */
    @JsonProperty("status")
    private String status;

    private Integer type;
    private String dealId;
    private String profit;
    private String stop;
    private String forcedPrice;
    private String totalMargin;

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
