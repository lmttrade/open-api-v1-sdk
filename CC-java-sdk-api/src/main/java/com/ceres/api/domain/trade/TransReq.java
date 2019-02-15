package com.ceres.api.domain.trade;

public class TransReq {

    private String exchange;

    private String contract;

    private String count;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getContract() {
        if (contract != null) {
            return contract.replace('/', '_');
        }
        return null;
    }
}
