package com.serhiiv.test.currate.ui.main.viewmodel;

import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyInteractor currencyInteractor;

    @Inject
    public MainViewModelFactory(CurrencyInteractor currencyInteractor) {
        this.currencyInteractor = currencyInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(currencyInteractor);
    }
}
