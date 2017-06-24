package com.mega4tech.oction.mvp.auction.presenter.impl;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.auction.interactor.AuctionInteractor;
import com.mega4tech.oction.mvp.auction.presenter.AuctionPresenter;
import com.mega4tech.oction.mvp.auction.view.AuctionView;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

public final class AuctionPresenterImpl extends BasePresenterImpl<AuctionView> implements AuctionPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final AuctionInteractor mInteractor;


    @Inject
    public AuctionPresenterImpl(@NonNull AuctionInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if(viewCreated) {
            mView.initTabs();
            mView.goToCurrentAuctions();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
    }
}