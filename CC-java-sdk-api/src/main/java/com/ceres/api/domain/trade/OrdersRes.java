package com.ceres.api.domain.trade;

import java.util.List;

/**
 * @author: wulei
 * @date: 19/6/12
 * @description:
 */
public class OrdersRes {
    /**
     * 订单列表
     */
    private List<OrderDetailRes> orderDetailRes;

    /**
     * 分页下一页起始id
     */
    private String nextFrom;

    public List<OrderDetailRes> getOrderDetailRes() {
        return orderDetailRes;
    }

    public void setOrderDetailRes(List<OrderDetailRes> orderDetailRes) {
        this.orderDetailRes = orderDetailRes;
    }

    public String getNextFrom() {
        return nextFrom;
    }

    public void setNextFrom(String nextFrom) {
        this.nextFrom = nextFrom;
    }
}
