package com.mega4tech.oction.mvp.product.presenter.impl;

import android.support.annotation.NonNull;

import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.mvp.product.interactor.ProductInteractor;
import com.mega4tech.oction.mvp.product.presenter.ProductPresenter;
import com.mega4tech.oction.mvp.product.view.ProductView;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

public final class ProductPresenterImpl extends BasePresenterImpl<ProductView> implements ProductPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final ProductInteractor mInteractor;

    private Auction mAuction;

    @Inject
    public ProductPresenterImpl(@NonNull ProductInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if (mAuction != null)
            mView.showAuction(mAuction);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        mAuction = null;
        super.onPresenterDestroyed();
    }

    @Override
    public void setAuction(@NonNull Auction auction) {
        mAuction = auction;
        if (mView != null) mView.showAuction(mAuction);
    }
}