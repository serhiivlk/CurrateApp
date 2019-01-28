package com.serhiiv.test.currate.ui.info.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.serhiiv.test.currate.core.base.BaseViewModel;
import com.serhiiv.test.currate.core.entity.CurrencyRate;
import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InfoViewModel extends BaseViewModel {
    private static final String TAG = "InfoViewModel";

    private final CurrencyInteractor currencyInteractor;

    private final MutableLiveData<List<CurrencyRate>> rates = new MutableLiveData<>();
    private final MutableLiveData<Boolean> empty = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public InfoViewModel(CurrencyInteractor currencyInteractor) {
        this.currencyInteractor = currencyInteractor;

        empty.setValue(false);

        loadCurrencyRates();
    }

    public void loadCurrencyRates() {
        track(currencyInteractor.getRateForCheckedPairs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doOnComplete(() -> loading.setValue(false))
                .subscribe(this::processingData, this::processingError));
    }

    public LiveData<List<CurrencyRate>> getRates() {
        return rates;
    }

    public LiveData<Boolean> isEmpty() {
        return empty;
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    private void processingData(List<CurrencyRate> rates) {
        this.rates.setValue(rates);
        this.empty.setValue(rates.isEmpty());
    }

    private void processingError(Throwable error) {
        Log.e(TAG, "loading currency rates error", error);
    }
}
