package com.ceres.api.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 闪电交易币对报价消息包体
 *
 * @author LMT
 * @date 2019-09-16 12:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlashIndicativePriceData {
    private String exchange;
    private String symbol;
    @JsonProperty("sell_price")
    private String sellPrice;
    @JsonProperty("buy_price")
    private String buyPrice;
    private Long time;

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

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
