package com.serhiiv.test.currate.core.entity;

import android.support.annotation.NonNull;

public class CurrencyPair {

    private final String pair;

    public CurrencyPair(String pair) {
        this.pair = pair;
    }

    public String getPair() {
        return pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyPair that = (CurrencyPair) o;
        return pair.equals(that.pair);
    }

    @Override
    public int hashCode() {
        return pair.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        if (pair.length() == 6) {
            return pair.substring(0, 3) + "/" + pair.substring(3);
        }
        return super.toString();
    }
}
