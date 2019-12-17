package com.ceres.api.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: wulei
 * @date: 2019/12/16
 * @description:
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEvent implements ProcessEvent{
    private String exchange;
    private String symbol;
    private String time;
    @JsonProperty("dataType")
    private String dataType;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
