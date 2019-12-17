package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author LMT
 * @date 2019/01/30
 * e.g
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandleEvent extends BaseEvent{
    private String close;
    private String duration;
    private String high;
    private String low;
    private String open;
    private String openTime;
    private String closeTime;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @SuppressWarnings("all")
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("close", close)
                .append("duration", duration)
                .append("high", high)
                .append("low", low)
                .append("open", open)
                .append("volume", volume)
                .append("closeTime", closeTime)
                .append("openTime", openTime)
                .toString();
    }
}
