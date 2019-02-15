package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 * e.g
 * {"close":"3616.3721","contract":"BTC/USDT","duration":"1m","exchange":"OKEX",
 * "high":"3616.9806","low":"3616.3507","msg_type":"push-candle","open":"3616.9806","time":"1550126820000","volume":"0.67480051"}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandleEvent {
    private String close;
    private String contract;
    private String duration;
    private String exchange;
    private String high;
    private String low;
    @JsonProperty("msg_type")
    private String msgType;
    private String open;
    private String time;
    private String volume;

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
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
                .append("close", close)
                .append("contract", contract)
                .append("duration", duration)
                .append("exchange", exchange)
                .append("high", high)
                .append("low", low)
                .append("msgType",msgType)
                .append("open", open)
                .append("time", time)
                .append("volume", volume)
                .toString();
    }
}
