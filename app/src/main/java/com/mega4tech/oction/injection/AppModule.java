package com.mega4tech.oction.injection;

import android.content.Context;
import android.support.annotation.NonNull;


import com.mega4tech.oction.Application;
import com.mega4tech.oction.BuildConfig;
import com.mega4tech.oction.model.repository.Repository;
import com.mega4tech.oction.model.repository.RepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public final class AppModule {
    @NonNull
    private final Application mApp;

    public AppModule(@NonNull Application app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public Application provideApp() {
        return mApp;
    }

    @Provides
    public Repository provideRepository(Application application, @Named("oction") Retrofit retrofit) {
        return new RepositoryImpl(application, retrofit);
    }

    @Provides
    @Singleton
    @Named("oction")
    Retrofit provideCall() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
