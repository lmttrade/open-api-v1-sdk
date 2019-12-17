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
public class TickEvent extends BaseEvent{

    private String change;
    @JsonProperty("changeRate")
    private String changeRate;
    private String high;
    private String last;
    private String low;
    private String open;
    private String volume;

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
                .append("symbol", super.getSymbol())
                .append("exchange", super.getExchange())
                .append("high", high)
                .append("last", last)
                .append("low", low)
                .append("dataType",super.getDataType())
                .append("open", open)
                .append("time", super.getTime())
                .append("volume", volume)
                .toString();
    }
}
