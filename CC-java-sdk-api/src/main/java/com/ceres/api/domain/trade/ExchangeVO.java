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
    private List<SymbolExcludeExchangeRes> symbolExcludeExchangeRes;

    public String getDefaultExchange() {
        return defaultExchange;
    }

    public void setDefaultExchange(String defaultExchange) {
        this.defaultExchange = defaultExchange;
    }

    public List<SymbolExcludeExchangeRes> getSymbolExcludeExchangeRes() {
        return symbolExcludeExchangeRes;
    }

    public void setSymbolExcludeExchangeRes(List<SymbolExcludeExchangeRes> symbolExcludeExchangeRes) {
        this.symbolExcludeExchangeRes = symbolExcludeExchangeRes;
    }

    @Override
    public String toString() {
        return "ExchangeVO{" +
                "defaultExchange='" + defaultExchange + '\'' +
                ", symbolExcludeExchangeRes=" + symbolExcludeExchangeRes +
                '}';
    }
}
