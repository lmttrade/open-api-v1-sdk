package com.ceres.api.domain.trade;

import java.io.Serializable;


public class ContractExcludeExchangeRes implements Serializable {

    /***
     * 交易所名称
     */
    private String marketName;

    /***
     * 合约代码
     */
    private String contractCode;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    @Override
    public String toString() {
        return "ContractExcludeExchangeRes{" +
                "marketName='" + marketName + '\'' +
                ", contractCode='" + contractCode + '\'' +
                '}';
    }
}
