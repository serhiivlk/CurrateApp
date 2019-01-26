package com.serhiiv.test.currate.di;

import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;
import com.serhiiv.test.currate.data.repository.CurrencyRepositoryImpl;
import com.serhiiv.test.currate.di.scope.PerApplication;
import com.serhiiv.test.currate.domain.interactor.CurrencyInteractorImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    @PerApplication
    abstract CurrencyRepository bindCurrencyRepository(CurrencyRepositoryImpl impl);

    @Binds
    @PerApplication
    abstract CurrencyInteractor bindCurrencyInteractor(CurrencyInteractorImpl impl);
}
