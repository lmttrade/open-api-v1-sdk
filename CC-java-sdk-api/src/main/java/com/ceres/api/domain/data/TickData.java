package com.ceres.api.domain.data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * @author LMT
 * @date 2019/01/30
 */
public class TickData {

    private String exchange;
    private String symbol;
    private String time;
    private String open;
    private String high;
    private String low;
    private String last;
    private String volume;
    private String change;
    private String changeRate;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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

    @SuppressWarnings("all")
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("exchange", exchange)
                .append("symbol", symbol)
                .append("time", time)
                .append("open", open)
                .append("high", high)
                .append("low",low)
                .append("last",last)
                .append("volume",volume)
                .append("change",change)
                .append("changeRate",changeRate)
                .toString();
    }
}
