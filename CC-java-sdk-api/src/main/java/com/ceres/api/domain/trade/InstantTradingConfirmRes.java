package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LMT
 * @date: 19/9/4
 * @description:
 */
public class InstantTradingConfirmRes {
    @JsonProperty("confirm_id")
    private String confirmId;
    @JsonProperty("client_oid")
    private String clientOid;
    @JsonProperty("system_oid")
    private String systemOid;

    public String getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }

    public String getClientOid() {
        return clientOid;
    }

    public void setClientOid(String clientOid) {
        this.clientOid = clientOid;
    }

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    @Override
    public String toString() {
        return "InstantTradingConfirmRes{" +
                "confirmId='" + confirmId + '\'' +
                ", clientOid='" + clientOid + '\'' +
                ", systemOid='" + systemOid + '\'' +
                '}';
    }
}
