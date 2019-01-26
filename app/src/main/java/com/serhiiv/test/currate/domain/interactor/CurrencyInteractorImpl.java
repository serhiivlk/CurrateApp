package com.serhiiv.test.currate.domain.interactor;

import com.serhiiv.test.currate.core.entity.CurrencyPair;
import com.serhiiv.test.currate.core.entity.CurrencyRate;
import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrencyInteractorImpl implements CurrencyInteractor {
    private final CurrencyRepository currencyRepository;

    @NonNull
    private Set<String> checkedPairs = Collections.emptySet();

    @Inject
    public CurrencyInteractorImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Observable<List<CurrencyPair>> getPairs() {
        return currencyRepository.getCurrencyPairs()
                .flatMapIterable(strings -> strings)
                .map(CurrencyPair::new)
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void checkedPairs(Set<CurrencyPair> pairs) {
        Set<String> p = new ArraySet<>();
        for (CurrencyPair pair : pairs) {
            p.add(pair.getPair());
        }
        checkedPairs = p;
    }

    @Override
    public boolean isAnyChecked() {
        return !checkedPairs.isEmpty();
    }

    @Override
    public Observable<List<CurrencyRate>> getRateForCheckedPairs() {
        return currencyRepository.getRates(new ArrayList<>(checkedPairs))
                .map(this::mapRates)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<CurrencyRate> mapRates(Map<String, String> map) {
        List<CurrencyRate> rates = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            //noinspection ConstantConditions
            rates.add(new CurrencyRate(key, Double.parseDouble(map.get(key))));
        }
        return rates;
    }
}
