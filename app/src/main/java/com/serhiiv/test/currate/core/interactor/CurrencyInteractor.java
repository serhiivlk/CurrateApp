package com.serhiiv.test.currate.core.interactor;

import com.serhiiv.test.currate.core.entity.CurrencyPair;

import java.util.List;

import io.reactivex.Observable;

public interface CurrencyInteractor {
    Observable<List<CurrencyPair>> getPairs();
}
