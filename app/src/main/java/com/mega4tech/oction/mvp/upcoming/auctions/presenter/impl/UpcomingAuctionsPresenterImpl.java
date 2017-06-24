package com.mega4tech.oction.mvp.upcoming.auctions.presenter.impl;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.upcoming.auctions.interactor.UpcomingAuctionsInteractor;
import com.mega4tech.oction.mvp.upcoming.auctions.presenter.UpcomingAuctionsPresenter;
import com.mega4tech.oction.mvp.upcoming.auctions.view.UpcomingAuctionsView;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public final class UpcomingAuctionsPresenterImpl extends BasePresenterImpl<UpcomingAuctionsView> implements UpcomingAuctionsPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final UpcomingAuctionsInteractor mInteractor;

    private Disposable mGetAuctionsDisposable;


    @Inject
    public UpcomingAuctionsPresenterImpl(@NonNull UpcomingAuctionsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if (viewCreated) {
            mView.initRecyclerView();
            getAuctions();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        if (mGetAuctionsDisposable != null) {
            mGetAuctionsDisposable.dispose();
            mGetAuctionsDisposable = null;
        }
        super.onPresenterDestroyed();
    }

    @Override
    public void getAuctions() {
        if (mView == null)
            return;

        if (!mInteractor.isOnline()) {
            mView.showOffline();
            return;
        }

        mView.reload();

        mGetAuctionsDisposable = mInteractor.getAuctions().observeOn(AndroidSchedulers.mainThread())
                .subscribe(auctions -> {
                    if (mView != null)
                        mView.display(auctions);
                }, throwable -> {
                    if (mView != null)
                        mView.showError(throwable.getMessage());
                });
    }


}