package com.mega4tech.oction.model.repository;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.model.retrofit.AuctionService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by aboodba on 23/04/2017.
 */

@Singleton
public class RepositoryImpl implements Repository {

    Application mApp;
    Retrofit retrofit;
    SharedPreferences preferences;

    @Inject
    public RepositoryImpl(Application application, Retrofit retrofit) {
        mApp = application;
        this.retrofit = retrofit;
        preferences = PreferenceManager.getDefaultSharedPreferences(mApp);
    }



    @Override
    public Observable<List<Auction>> getAuctions() {
        AuctionService auctionService = retrofit.create(AuctionService.class);
        return auctionService.getAuctions()
                .timeout(20, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread());
    }
}
