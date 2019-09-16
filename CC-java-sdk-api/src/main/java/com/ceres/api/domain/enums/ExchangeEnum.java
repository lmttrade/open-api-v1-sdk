package com.ceres.api.domain.enums;

/**
 * 交易所枚举
 *
 * @author LMT
 * @date 2019-09-16 13:26
 */
public enum ExchangeEnum {
    //
    LMT("LMT", "默认"),
    BINANCE("BINANCE", "Binance交易所"),
    BITMAX("BITMAX", "BitMax交易所"),
    HUOBI("HUOBI", "火币交易所"),
    OKEX("OKEX", "Ok交易所"),
    INSTANTEX("INSTANTEX", "闪电交易");
    private String name;
    private String value;

    ExchangeEnum(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
