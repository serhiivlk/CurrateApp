package com.serhiiv.test.currate.core.repository;

import io.reactivex.Observable;

import java.util.List;
import java.util.Map;

public interface CurrencyRepository {
    Observable<List<String>> getCurrencyPairs();

    Observable<Map<String, String>> getRates(List<String> pairs);
}
