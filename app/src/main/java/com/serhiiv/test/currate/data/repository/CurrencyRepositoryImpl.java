package com.serhiiv.test.currate.data.repository;

import com.serhiiv.test.currate.core.repository.CurrencyRepository;
import com.serhiiv.test.currate.data.remote.CurrateService;
import com.serhiiv.test.currate.data.remote.response.BaseResponse;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final CurrateService service;

    @Inject
    public CurrencyRepositoryImpl(CurrateService service) {
        this.service = service;
    }

    @Override
    public Observable<List<String>> getCurrencyPairs() {
        return service.currencyPairs()
                .map(BaseResponse::getData)
                .toObservable();
    }


    @Override
    public Observable<Map<String, String>> getRates(List<String> pairs) {
        return service.currencyRates(joinPairs(pairs))
                .map(BaseResponse::getData)
                .toObservable();
    }

    private String joinPairs(List<String> pairs) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pairs.size(); i++) {
            builder.append(pairs.get(i));
            if (i < pairs.size() - 1) {
                builder.append(',');
            }
        }
        return builder.toString();
    }
}
