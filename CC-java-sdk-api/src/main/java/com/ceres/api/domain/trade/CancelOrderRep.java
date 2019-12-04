package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author: wulei
 * @date: 2019/12/2
 * @description:
 */
public class CancelOrderRep implements Serializable {
    @JsonProperty("cancel_success_list")
    private List<SystemOidRecord> cancelSuccessList;
    @JsonProperty("cancel_fail_list")
    private List<SystemOidRecord> cancelFailList;

    public List<SystemOidRecord> getCancelSuccessList() {
        return cancelSuccessList;
    }

    public void setCancelSuccessList(List<SystemOidRecord> cancelSuccessList) {
        this.cancelSuccessList = cancelSuccessList;
    }

    public List<SystemOidRecord> getCancelFailList() {
        return cancelFailList;
    }

    public void setCancelFailList(List<SystemOidRecord> cancelFailList) {
        this.cancelFailList = cancelFailList;
    }
}
