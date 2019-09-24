package com.ceres.api.domain.trade;


import java.util.List;

public class ExchangeVO {

    /**
     * 默认交易所
     */
    private String defaultExchange;

    /**
     * 排除默认交易所外,剩余币种对应交易所
     */
    private List<SymbolExcludeExchangeRes> excludeExchangeResList;

    public String getDefaultExchange() {
        return defaultExchange;
    }

    public void setDefaultExchange(String defaultExchange) {
        this.defaultExchange = defaultExchange;
    }

    public List<SymbolExcludeExchangeRes> getExcludeExchangeResList() {
        return excludeExchangeResList;
    }

    public void setExcludeExchangeResList(List<SymbolExcludeExchangeRes> excludeExchangeResList) {
        this.excludeExchangeResList = excludeExchangeResList;
    }

    @Override
    public String toString() {
        return "ExchangeVO{" +
                "defaultExchange='" + defaultExchange + '\'' +
                ", excludeExchangeResList=" + excludeExchangeResList +
                '}';
    }
}
