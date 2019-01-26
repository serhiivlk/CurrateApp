package com.serhiiv.test.currate.di;

import android.app.Application;
import com.serhiiv.test.currate.BaseApplication;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;
import com.serhiiv.test.currate.di.scope.PerApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        DataModule.class,
        ActivityBindingModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    CurrencyRepository currencyRepository();

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder app(Application application);

        AppComponent build();
    }
}
