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
public class TradeEvent extends BaseEvent{

    private String price;

    private Integer type;

    private String volume;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
                .append("symbol", super.getSymbol())
                .append("exchange", super.getExchange())
                .append("dataType", super.getDataType())
                .append("price", price)
                .append("type",type)
                .append("time", super.getTime())
                .append("volume",volume)
                .toString();
    }
}
