package com.serhiiv.test.currate.ui.info.viewmodel;

import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class InfuViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyInteractor currencyInteractor;

    @Inject
    public InfuViewModelFactory(CurrencyInteractor currencyInteractor) {
        this.currencyInteractor = currencyInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new InfoViewModel(currencyInteractor);
    }
}
