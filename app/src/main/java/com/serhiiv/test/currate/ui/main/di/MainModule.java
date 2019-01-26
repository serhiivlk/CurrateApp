package com.serhiiv.test.currate.ui.main.di;

import androidx.lifecycle.ViewModelProviders;
import com.serhiiv.test.currate.di.scope.PerActivity;
import com.serhiiv.test.currate.di.scope.PerFragment;
import com.serhiiv.test.currate.ui.main.MainActivity;
import com.serhiiv.test.currate.ui.main.MainFragment;
import com.serhiiv.test.currate.ui.main.viewmodel.MainViewModel;
import com.serhiiv.test.currate.ui.main.viewmodel.MainViewModelFactory;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {

    @Provides
    @PerActivity
    static MainViewModel provideMainViewModel(MainActivity activity, MainViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }

    @PerFragment
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();
}
