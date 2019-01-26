package com.serhiiv.test.currate.ui.main.viewmodel;

import android.util.Log;

import com.serhiiv.test.currate.core.base.BaseViewModel;
import com.serhiiv.test.currate.core.entity.CurrencyPair;
import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;
import com.serhiiv.test.currate.core.tools.SingleLiveEvent;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {
    private static String TAG = "MainViewModel";

    private final CurrencyInteractor currencyInteractor;

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<List<CurrencyPair>> pairs = new MutableLiveData<>();
    private final MutableLiveData<Boolean> empty = new MutableLiveData<>();
    private final SingleLiveEvent<Void> showInfo = new SingleLiveEvent<>();

    private Set<CurrencyPair> cashedCheckedPairs = Collections.emptySet();

    public MainViewModel(CurrencyInteractor currencyInteractor) {
        this.currencyInteractor = currencyInteractor;

        empty.setValue(false);

        loadCurrencyPairs();
    }

    public void loadCurrencyPairs() {
        track(currencyInteractor.getPairs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doOnComplete(() -> loading.setValue(false))
                .doOnError(error -> loading.setValue(false))
                .subscribe(this::processingData, this::processingError));
    }

    public void checkedPairs(Set<CurrencyPair> checkedPairs) {
        cashedCheckedPairs = checkedPairs;
        currencyInteractor.checkedPairs(checkedPairs);
    }

    public void prepareInfo() {
        if (currencyInteractor.isAnyChecked()) {
            showInfo.call();
        } else {
            showToastMessage("No pairs checked");
        }
    }

    public LiveData<Boolean> isLoading() {
        return loading;
    }

    public LiveData<List<CurrencyPair>> getPairs() {
        return pairs;
    }

    public LiveData<Boolean> isEmpty() {
        return empty;
    }

    public LiveData<Void> showInfo() {
        return showInfo;
    }

    public Set<CurrencyPair> getCheckedPairs() {
        return cashedCheckedPairs;
    }

    private void processingData(List<CurrencyPair> pairs) {
        this.pairs.setValue(pairs);
        this.empty.setValue(pairs.isEmpty());
    }

    private void processingError(Throwable error) {
        Log.e(TAG, "Load currency pairs error", error);
        showToastMessage("Error");
    }
}
