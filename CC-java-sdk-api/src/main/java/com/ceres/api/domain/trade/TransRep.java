package com.ceres.api.domain.trade;

import java.util.List;

/**
 * @author LMT
 */
public class TransRep {

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
