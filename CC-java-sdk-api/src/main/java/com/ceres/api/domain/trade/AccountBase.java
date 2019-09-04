package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: LMT
 * @date: 19/9/4
 * @description:
 */
public class AccountBase {
    @JsonProperty("asset_code")
    private Long assetCode;
    private int status;
    @JsonProperty("is_sub_account")
    private int isSubAccount;

    public Long getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(Long assetCode) {
        this.assetCode = assetCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsSubAccount() {
        return isSubAccount;
    }

    public void setIsSubAccount(int isSubAccount) {
        this.isSubAccount = isSubAccount;
    }
}
