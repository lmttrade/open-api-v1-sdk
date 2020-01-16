package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: wulei
 * @date: 2020/1/16
 * @description:
 */
public class AccountBorrowInfoRes {
    private String currency;
    private String borrowed;
    @JsonProperty("lending_fee")
    private String lendingFee;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
    }

    public String getLendingFee() {
        return lendingFee;
    }

    public void setLendingFee(String lendingFee) {
        this.lendingFee = lendingFee;
    }
}
