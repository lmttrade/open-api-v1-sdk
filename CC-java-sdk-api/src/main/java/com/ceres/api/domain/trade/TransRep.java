package com.ceres.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author LMT
 */
public class TransRep {

    @JsonProperty("next_from")
    private long nextFrom;

    private List<TransRecord> transRecordList;

    public long getNextFrom() {
        return nextFrom;
    }

    public void setNextFrom(long nextFrom) {
        this.nextFrom = nextFrom;
    }

    public List<TransRecord> getTransRecordList() {
        return transRecordList;
    }

    public void setTransRecordList(List<TransRecord> transRecordList) {
        this.transRecordList = transRecordList;
    }
}
