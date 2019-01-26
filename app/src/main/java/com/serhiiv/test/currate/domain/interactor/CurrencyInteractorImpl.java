package com.serhiiv.test.currate.domain.interactor;

import com.serhiiv.test.currate.core.entity.CurrencyPair;
import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrencyInteractorImpl implements CurrencyInteractor {
    private final CurrencyRepository currencyRepository;

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
}
