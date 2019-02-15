package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 * e.g
 * {"change":"7.0741000","change_rate":"0.0020","contract":"BTC/USDT","exchange":"OKEX","high":"3621.2376",
 * "last":"3610.8865","low":"3606.8170","msg_type":"push-tick","open":"3617.9606","time":"1550108930438","volume":"1066.07976710"}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickEvent {

    private String change;
    @JsonProperty("change_rate")
    private String changeRate;
    private String contract;
    private String exchange;
    private String high;
    private String last;
    private String low;
    @JsonProperty("msg_type")
    private String msgType;
    private String open;
    private String time;
    private String volume;

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = changeRate;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @SuppressWarnings("all")
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("change", change)
                .append("changeRate", changeRate)
                .append("contract", contract)
                .append("exchange", exchange)
                .append("high", high)
                .append("last", last)
                .append("low", low)
                .append("msgType",msgType)
                .append("open", open)
                .append("time", time)
                .append("volume", volume)
                .toString();
    }
}
