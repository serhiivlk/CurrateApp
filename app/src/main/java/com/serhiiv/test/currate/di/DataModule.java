package com.serhiiv.test.currate.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.serhiiv.test.currate.BuildConfig;
import com.serhiiv.test.currate.core.interactor.CurrencyInteractor;
import com.serhiiv.test.currate.core.repository.CurrencyRepository;
import com.serhiiv.test.currate.data.remote.AuthInterceptor;
import com.serhiiv.test.currate.data.remote.CurrateService;
import com.serhiiv.test.currate.data.repository.CurrencyRepositoryImpl;
import com.serhiiv.test.currate.di.scope.PerApplication;
import com.serhiiv.test.currate.domain.interactor.CurrencyInteractorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class DataModule {

    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClient(AuthInterceptor authInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build();
    }

    @Provides
    @PerApplication
    static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @PerApplication
    static CurrateService provideCurrateService(Retrofit retrofit) {
        return retrofit.create(CurrateService.class);
    }

    @Binds
    @PerApplication
    abstract CurrencyRepository bindCurrencyRepository(CurrencyRepositoryImpl impl);

    @Binds
    @PerApplication
    abstract CurrencyInteractor bindCurrencyInteractor(CurrencyInteractorImpl impl);
}
