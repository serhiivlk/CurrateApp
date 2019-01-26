package com.serhiiv.test.currate.core.interactor;

import com.serhiiv.test.currate.core.entity.CurrencyPair;
import com.serhiiv.test.currate.core.entity.CurrencyRate;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface CurrencyInteractor {
    Observable<List<CurrencyPair>> getPairs();

    void checkedPairs(Set<CurrencyPair> pairs);

    boolean isAnyChecked();

    Observable<List<CurrencyRate>> getRateForCheckedPairs();
}
