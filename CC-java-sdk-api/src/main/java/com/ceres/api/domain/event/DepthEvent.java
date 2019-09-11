package com.ceres.api.domain.event;

import com.ceres.api.domain.market.OrderBookEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author LMT
 * @date 2019/01/30
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthEvent {

  private String exchange;

  private String symbol;

  @JsonProperty("dataType")
  private String dataType;

  private String time;

  private List<OrderBookEntry> bids;

  private List<OrderBookEntry> asks;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getExchange() {
    return exchange;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public List<OrderBookEntry> getBids() {
    return bids;
  }

  public void setBids(List<OrderBookEntry> bids) {
    this.bids = bids;
  }

  public List<OrderBookEntry> getAsks() {
    return asks;
  }

  public void setAsks(List<OrderBookEntry> asks) {
    this.asks = asks;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("exchange", exchange)
        .append("symbol", symbol)
        .append("dataType", dataType)
        .append("bids", bids)
        .append("asks", asks)
            .append("time", time)
        .toString();
  }
}
