package com.ceres.api.domain.event;

import com.ceres.api.domain.market.OrderBookEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author xiaotian.huang
 * @date 2019/01/30
 * e.g
 * {"asks":[{"price":"3608.9238","volume":"0.00100000"},{"price":"3609.0435","volume":"0.50912826"},
 * {"price":"3609.0436","volume":"0.01476721"},{"price":"3609.2870","volume":"0.74600000"},
 * {"price":"3609.2976","volume":"0.40000000"},{"price":"3609.3814","volume":"0.10000000"},
 * {"price":"3609.4330","volume":"0.52000000"},{"price":"3609.4332","volume":"0.09990000"},
 * {"price":"3609.4333","volume":"6.76520000"},{"price":"3609.4334","volume":"0.01653834"}],
 * "bids":[{"price":"3608.4847","volume":"0.03857827"},{"price":"3608.4846","volume":"0.01102398"},
 * {"price":"3608.4845","volume":"0.39900000"},{"price":"3608.2706","volume":"0.25300000"},
 * {"price":"3608.2493","volume":"0.13000000"},{"price":"3608.2197","volume":"0.00571767"},
 * {"price":"3608.2136","volume":"0.00880546"},{"price":"3608.1378","volume":"0.27715127"},
 * {"price":"3608.0801","volume":"0.10000000"},{"price":"3607.9311","volume":"0.00914828"}],
 * "contract":"BTC/USDT","exchange":"OKEX","msg_type":"push-depth10"}
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthEvent {

  private String exchange;

  private String contract;

  @JsonProperty("msg_type")
  private String msgType;

  private List<OrderBookEntry> bids;

  private List<OrderBookEntry> asks;

  public String getContract() {
    return contract;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getExchange() {
    return exchange;
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("exchange", exchange)
        .append("contract", contract)
        .append("msgType", msgType)
        .append("bids", bids)
        .append("asks", asks)
        .toString();
  }
}
