package com.serhiiv.test.currate.ui.main.viewmodel;

import androidx.lifecycle.ViewModel;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;

public class MainViewModel extends ViewModel {
    private final CurrencyRepository currencyRepository;

    public MainViewModel(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
}
