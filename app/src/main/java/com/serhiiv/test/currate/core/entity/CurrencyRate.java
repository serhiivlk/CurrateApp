package com.serhiiv.test.currate.core.entity;

public class CurrencyRate {
    private CurrencyPair pair;
    private double rate;

    public CurrencyRate(CurrencyPair pair, double rate) {
        this.pair = pair;
        this.rate = rate;
    }

    public CurrencyPair getPair() {
        return pair;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRate that = (CurrencyRate) o;
        return Double.compare(that.rate, rate) == 0 &&
                pair.equals(that.pair);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (pair == null ? 0 : pair.hashCode());
        hash = 31 * hash + Double.valueOf(rate).hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "pair='" + pair + '\'' +
                ", rate=" + rate +
                '}';
    }
}
