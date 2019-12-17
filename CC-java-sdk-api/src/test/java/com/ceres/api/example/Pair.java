package com.ceres.api.example;

import java.io.Serializable;

/**
 * @author: wulei
 * @date: 2019/12/16
 * @description:
 */
public class Pair<T1,T2> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T1 key = null;
    private T2 value = null;

    public Pair(T1 t1, T2 t2) {
        this.key = t1;
        this.value = t2;
    }

    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }
}
