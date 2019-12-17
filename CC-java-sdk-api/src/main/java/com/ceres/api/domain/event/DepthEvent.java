package com.ceres.api.domain.event;

import com.ceres.api.domain.market.OrderBookEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author LMT
 * @date 2019/01/30
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthEvent extends BaseEvent{

  private List<OrderBookEntry> bids;

  private List<OrderBookEntry> asks;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("exchange", super.getExchange())
        .append("symbol", super.getSymbol())
        .append("dataType", super.getDataType())
        .append("bids", bids)
        .append("asks", asks)
            .append("time", super.getTime())
        .toString();
  }
}
