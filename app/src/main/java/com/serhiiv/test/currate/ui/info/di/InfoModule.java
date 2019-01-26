package com.serhiiv.test.currate.ui.info.di;

import com.serhiiv.test.currate.di.scope.PerActivity;
import com.serhiiv.test.currate.di.scope.PerFragment;
import com.serhiiv.test.currate.ui.info.InfoActivity;
import com.serhiiv.test.currate.ui.info.InfoFragment;
import com.serhiiv.test.currate.ui.info.viewmodel.InfoViewModel;
import com.serhiiv.test.currate.ui.info.viewmodel.InfuViewModelFactory;

import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InfoModule {
    @Provides
    @PerActivity
    static InfoViewModel provideInfoViewModel(InfoActivity activity, InfuViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(InfoViewModel.class);
    }

    @PerFragment
    @ContributesAndroidInjector
    abstract InfoFragment contributeInfoFragment();
}
