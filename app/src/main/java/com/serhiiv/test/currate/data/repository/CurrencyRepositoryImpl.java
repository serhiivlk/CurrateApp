package com.serhiiv.test.currate.data.repository;

import com.serhiiv.test.currate.core.repository.CurrencyRepository;
import io.reactivex.Observable;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Inject
    public CurrencyRepositoryImpl() {
    }

    @Override
    public Observable<List<String>> getCurrencyPairs() {
        return Observable.fromCallable(() -> {
            List<String> list = new ArrayList<>(3);
            list.add("pair");
            list.add("pair");
            list.add("pair");
            return list;
        });
    }

    @Override
    public Observable<Map<String, String>> getRates(List<String> pairs) {
        return null;
    }
}
