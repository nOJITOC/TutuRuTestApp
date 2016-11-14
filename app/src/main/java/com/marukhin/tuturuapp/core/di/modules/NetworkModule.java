package com.marukhin.tuturuapp.core.di.modules;


import com.marukhin.tuturuapp.core.network.RestService;
import com.marukhin.tuturuapp.core.utils.AppConfig;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


@Module
public class NetworkModule {
    @Provides
    OkHttpClient provideOkHttpClient() {
        return createClient();
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(AppConfig.MAX_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(AppConfig.MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(AppConfig.MAX_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttp) {
        return createRetrofit(okHttp);
    }

    private Retrofit createRetrofit(OkHttpClient okHttp) {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttp)
                .build();
    }

    @Provides
    RestService provideRestService(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }


}
