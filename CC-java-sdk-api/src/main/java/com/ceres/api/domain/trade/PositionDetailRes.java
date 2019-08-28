package com.ceres.api.domain.trade;

import com.ceres.api.util.MoneyUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * @author LMT
 */
public class PositionDetailRes {

    String exchange;
    String symbol;
    @JsonProperty("position_dir")
    String positionDir;
    String vol;
    String margin;
    @JsonProperty("close_profit")
    String closeProfit;
    @JsonProperty("position_profit")
    String positionProfit;
    @JsonProperty("open_price")
    String openPrice;
    @JsonProperty("position_value")
    String positionValue;
    @JsonProperty("force_price")
    String forcePrice;
    String lever;
    private String dealId;

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

    public String getPositionDir() {
        return positionDir;
    }

    public void setPositionDir(String positionDir) {
        this.positionDir = positionDir;
    }

    public String getVol() {
        return MoneyUtil.covertString(vol);
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getMargin() {
        return MoneyUtil.covertString(margin);
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCloseProfit() {
        return MoneyUtil.covertString(closeProfit);
    }

    public void setCloseProfit(String closeProfit) {
        this.closeProfit = closeProfit;
    }

    public String getPositionProfit() {
        return positionProfit;
    }

    public void setPositionProfit(String positionProfit) {
        this.positionProfit = positionProfit;
    }

    public String getOpenPrice() {
        return MoneyUtil.covertString(openPrice);
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(String positionValue) {
        this.positionValue = positionValue;
    }

    public String getForcePrice() {
        return MoneyUtil.covertString(forcePrice);
    }

    public void setForcePrice(String forcePrice) {
        this.forcePrice = forcePrice;
    }

    public String getLever() {
        return MoneyUtil.covertString(lever);
    }

    public void setLever(String lever) {
        this.lever = lever;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("exchange", exchange)
                .append("symbol", symbol)
                .append("positionDir", positionDir)
                .append("vol", vol)
                .append("margin", margin)
                .append("closeProfit", closeProfit)
                .append("positionProfit", positionProfit)
                .append("openPrice", openPrice)
                .append("positionValue", positionValue)
                .append("forcePrice", forcePrice)
                .append("lever", lever)
                .append("dealId", dealId)
                .toString();
    }
}
