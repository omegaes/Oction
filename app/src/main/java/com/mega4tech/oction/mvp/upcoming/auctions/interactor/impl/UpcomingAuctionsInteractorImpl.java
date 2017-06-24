package com.mega4tech.oction.mvp.upcoming.auctions.interactor.impl;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.mega4tech.oction.Application;
import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.model.Utils;
import com.mega4tech.oction.model.repository.Repository;
import com.mega4tech.oction.mvp.upcoming.auctions.interactor.UpcomingAuctionsInteractor;

import java.util.List;

import io.reactivex.Observable;

public final class UpcomingAuctionsInteractorImpl implements UpcomingAuctionsInteractor {


    @NonNull
    private final Repository mRepository;

    @NonNull
    private final Application mApplication;


    @Inject
    public UpcomingAuctionsInteractorImpl(@NonNull final Repository repository, @NonNull final Application application) {
        mRepository = repository;
        mApplication = application;
    }

    @Override
    public boolean isOnline() {
        return Utils.isOnline(mApplication);
    }

    @Override
    public Observable<List<Auction>> getAuctions() {
        return mRepository.getAuctions();
    }

}