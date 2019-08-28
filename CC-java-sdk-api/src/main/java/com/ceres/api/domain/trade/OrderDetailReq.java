package com.ceres.api.domain.trade;
/**
 * @author LMT
 */
public class OrderDetailReq {
    private String systemOid;
    private Long timestamp;

    public String getSystemOid() {
        return systemOid;
    }

    public void setSystemOid(String systemOid) {
        this.systemOid = systemOid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
