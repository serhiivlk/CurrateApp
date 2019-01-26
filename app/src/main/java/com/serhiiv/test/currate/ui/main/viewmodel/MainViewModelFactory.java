package com.serhiiv.test.currate.ui.main.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyRepository currencyRepository;

    @Inject
    public MainViewModelFactory(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(currencyRepository);
    }
}
