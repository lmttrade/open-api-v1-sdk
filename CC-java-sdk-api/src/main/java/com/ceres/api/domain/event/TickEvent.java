package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author LMT
 * @date 2019/01/30
 * e.g
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickEvent {

    private String change;
    @JsonProperty("changeRate")
    private String changeRate;
    @JsonProperty("dataType")
    private String dataType;
    private String symbol;
    private String exchange;
    private String high;
    private String last;
    private String low;
    private String open;
    private String time;
    private String volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
                .append("symbol", symbol)
                .append("exchange", exchange)
                .append("high", high)
                .append("last", last)
                .append("low", low)
                .append("dataType",dataType)
                .append("open", open)
                .append("time", time)
                .append("volume", volume)
                .toString();
    }
}
