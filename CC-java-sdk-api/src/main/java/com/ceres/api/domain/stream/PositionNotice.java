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
public class PositionNotice {

    /** 委托单号 */
    @JsonProperty("system_oid")
    private String systemOid;

    /** 强平价 */
    private String forcedPrice;

    /** 总保证金 */
    private String totalMargin;

    /** 止盈 */
    private String profit;

    /** 止损 */
    private String stop;

    /** 持仓号 */
    private String dealId;

    /** 挂单或者持仓 */
    private String type;

    /** 通知类型 */
    private int messageType;

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
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

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
