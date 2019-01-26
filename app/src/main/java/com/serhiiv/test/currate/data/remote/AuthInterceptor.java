package com.serhiiv.test.currate.data.remote;

import com.serhiiv.test.currate.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Inject
    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        HttpUrl url = originRequest.url().newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build();
        Request request = originRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
