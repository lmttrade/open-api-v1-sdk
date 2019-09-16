package com.ceres.api.domain.data;
/**
 * @author LMT
 * @date 2019/01/30
 */
public class CycleReq {

//    释义	值	释义	值
//    一分钟K线	1	日K线	8
//    三分钟K线	2	5日K线	9
//    五分钟K线	3	周K线	10
//    十五分钟K线	4	月K线	11
//    三十分钟K线	5	年K线	12
//    一小时K线	6	两小时K线	40
//    四小时K线	7	六小时K线	50
//    十二小时K线	60

    private String exchange;
    private String symbol;
    private Long begin;
    private Long end;
    private Integer duration;

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

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
