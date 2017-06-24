package com.mega4tech.oction.mvp.auction.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.auction.interactor.AuctionInteractor;
import com.mega4tech.oction.mvp.auction.interactor.impl.OuctionInteractorImpl;
import com.mega4tech.oction.mvp.auction.presenter.AuctionPresenter;
import com.mega4tech.oction.mvp.auction.presenter.impl.AuctionPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class AuctionViewModule {
    @Provides
    public AuctionInteractor provideInteractor() {
        return new OuctionInteractorImpl();
    }

    @Provides
    public PresenterFactory<AuctionPresenter> providePresenterFactory(@NonNull final AuctionInteractor interactor) {
        return new PresenterFactory<AuctionPresenter>() {
            @NonNull
            @Override
            public AuctionPresenter create() {
                return new AuctionPresenterImpl(interactor);
            }
        };
    }
}
