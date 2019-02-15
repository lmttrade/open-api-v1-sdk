package com.ceres.api.domain.trade;

public class PositionQueryReq {

    private String exchange;
    private String contract;
    private String positionDir;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPositionDir() {
        return positionDir;
    }

    public void setPositionDir(String positionDir) {
        this.positionDir = positionDir;
    }
}
