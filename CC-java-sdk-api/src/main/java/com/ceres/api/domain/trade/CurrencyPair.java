package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CurrencyPair {

    private String exchange;

    private String contract;

    @JsonProperty("tick_size")
    private String tickSize;

    @JsonProperty("volume_increment")
    private String volumeIncrement;

    @JsonProperty("min_price")
    private String minPrice;

    public String getTickSize() {
        return tickSize;
    }
    @JsonProperty("min_volume")
    private String minVolume;

    private String kind;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public void setTickSize(String tickSize) {
        this.tickSize = tickSize;
    }

    public String getVolumeIncrement() {
        return volumeIncrement;
    }

    public void setVolumeIncrement(String volumeIncrement) {
        this.volumeIncrement = volumeIncrement;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(String minVolume) {
        this.minVolume = minVolume;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("exchange", exchange)
                .append("contract", contract)
                .append("tickSize", tickSize)
                .append("volumeIncrement", volumeIncrement)
                .append("minPrice", minPrice)
                .append("minVolume", minVolume)
                .append("kind", kind)
                .toString();
    }

}