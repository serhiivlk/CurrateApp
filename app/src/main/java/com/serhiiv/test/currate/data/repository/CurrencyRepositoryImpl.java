package com.serhiiv.test.currate.data.repository;

import com.serhiiv.test.currate.core.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Inject
    public CurrencyRepositoryImpl() {
    }

    @Override
    public Observable<List<String>> getCurrencyPairs() {
        return Observable.fromCallable(() -> {
            List<String> list = new ArrayList<>(3);
            list.add("pairrr");
            list.add("pairrr");
            list.add("pairrr");
            return list;
        })
                .delay(3, TimeUnit.SECONDS);
    }

    @Override
    public Observable<Map<String, String>> getRates(List<String> pairs) {
        return null;
    }
}
