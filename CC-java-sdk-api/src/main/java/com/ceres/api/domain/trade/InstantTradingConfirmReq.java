package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LMT
 * @date: 19/9/4
 * @description:
 */
public class InstantTradingConfirmReq {
    @JsonProperty("confirm_id")
    private String confirmId;
    @JsonProperty("client_oid")
    private String clientOid;
    @JsonProperty("asset_code")
    private Long assetCode = 0L;

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

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public String toString() {
        return "InstantTradingConfirmReq{" +
                "confirmId='" + confirmId + '\'' +
                ", clientOid='" + clientOid + '\'' +
                ", assetCode=" + assetCode +
                '}';
    }
}
