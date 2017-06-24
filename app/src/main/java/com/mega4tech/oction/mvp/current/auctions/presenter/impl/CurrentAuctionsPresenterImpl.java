package com.mega4tech.oction.mvp.current.auctions.presenter.impl;

import android.support.annotation.NonNull;
import com.mega4tech.oction.mvp.current.auctions.interactor.CurrentAuctionsInteractor;
import com.mega4tech.oction.mvp.current.auctions.presenter.CurrentAuctionsPresenter;
import com.mega4tech.oction.mvp.current.auctions.view.CurrentAuctionsView;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public final class CurrentAuctionsPresenterImpl extends BasePresenterImpl<CurrentAuctionsView> implements CurrentAuctionsPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CurrentAuctionsInteractor mInteractor;

    private Disposable mGetAuctionsDisposable;


    @Inject
    public CurrentAuctionsPresenterImpl(@NonNull CurrentAuctionsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if(viewCreated) {
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