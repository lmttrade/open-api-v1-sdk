package com.ceres.api.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketArea {
    private Long marketId;
    private String marketName;
    private Integer tradeStatus;
    private List<AreaInfo> areaInfoList;

    public List<AreaInfo> getAreaInfoList() {
        return areaInfoList;
    }

    public void setAreaInfoList(List<AreaInfo> areaInfoList) {
        this.areaInfoList = areaInfoList;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        MarketArea that = (MarketArea) o;
        return Objects.equals(marketId, that.marketId) &&
                Objects.equals(marketName, that.marketName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketId, marketName);
    }

    @SuppressWarnings("all")
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("marketId", marketId)
                .append("marketName", marketName)
                .append("tradeStatus", tradeStatus)
                .append("areaInfoList", areaInfoList)
                .toString();
    }
}
