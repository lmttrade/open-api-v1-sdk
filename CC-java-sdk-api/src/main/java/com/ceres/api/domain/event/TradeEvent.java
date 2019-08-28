package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author LMT
 * @date 2019/01/30
 * e.g
 * {"symbol":"BTC/USDT","exchange":"LMT","msg_type":"push-trade","price":"3610.3002","side":"s","time":"1550114101032",
 * "volume":"0.03456403"}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeEvent {

    private String symbol;

    private String exchange;

    @JsonProperty("msg_type")
    private String msgType;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    private String price;

    private String side;

    private String time;

    private String volume;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("symbol", symbol)
                .append("exchange", exchange)
                .append("msgType", msgType)
                .append("price", price)
                .append("side",side)
                .append("time", time)
                .append("volume",volume)
                .toString();
    }
}
