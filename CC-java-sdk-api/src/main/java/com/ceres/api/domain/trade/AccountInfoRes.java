package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LMT
 */
public class AccountInfoRes {
    @JsonProperty("current_lever")
    private String currentLever = "1";
    @JsonProperty("warn_line")
    private String warnLine = "--";
    @JsonProperty("close_line")
    private String closeLine = "--";
    @JsonProperty("risk_rate")
    private String riskRate = "--";
    @JsonProperty("currency_list")
    private List<AccountInfoDetail> currencyList = new ArrayList<>();

    public String getCurrentLever() {
        return currentLever;
    }

    public void setCurrentLever(String currentLever) {
        this.currentLever = currentLever;
    }

    public String getWarnLine() {
        return warnLine;
    }

    public void setWarnLine(String warnLine) {
        this.warnLine = warnLine;
    }

    public String getCloseLine() {
        return closeLine;
    }

    public void setCloseLine(String closeLine) {
        this.closeLine = closeLine;
    }

    public String getRiskRate() {
        return riskRate;
    }

    public void setRiskRate(String riskRate) {
        this.riskRate = riskRate;
    }

    public List<AccountInfoDetail> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<AccountInfoDetail> currencyList) {
        this.currencyList = currencyList;
    }
}
