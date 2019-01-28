package com.serhiiv.test.currate.ui.info.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;

import javax.inject.Inject;


public class InfoViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyInteractor currencyInteractor;

    @Inject
    public InfoViewModelFactory(CurrencyInteractor currencyInteractor) {
        this.currencyInteractor = currencyInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new InfoViewModel(currencyInteractor);
    }
}
