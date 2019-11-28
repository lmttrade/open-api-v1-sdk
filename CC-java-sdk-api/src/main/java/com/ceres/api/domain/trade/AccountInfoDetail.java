package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author LMT
 */
public class AccountInfoDetail {
    private String balance;
    private String currency;
    @JsonProperty("max_borrow_balance")
    private String maxBorrowBalance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMaxBorrowBalance() {
        return maxBorrowBalance;
    }

    public void setMaxBorrowBalance(String maxBorrowBalance) {
        this.maxBorrowBalance = maxBorrowBalance;
    }
}
