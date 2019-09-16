package com.ceres.api.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author LMT
 * @date 2019/01/30
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CycleResData {
    private String exchange;
    private String symbol;
    private Integer duration;
    private List<CycleData> detail;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<CycleData> getDetail() {
        return detail;
    }

    public void setDetail(List<CycleData> detail) {
        this.detail = detail;
    }
}
