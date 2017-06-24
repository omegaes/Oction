package com.mega4tech.oction.mvp.upcoming.auctions.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.Application;
import com.mega4tech.oction.model.repository.Repository;
import com.mega4tech.oction.mvp.upcoming.auctions.interactor.UpcomingAuctionsInteractor;
import com.mega4tech.oction.mvp.upcoming.auctions.interactor.impl.UpcomingAuctionsInteractorImpl;
import com.mega4tech.oction.mvp.upcoming.auctions.presenter.UpcomingAuctionsPresenter;
import com.mega4tech.oction.mvp.upcoming.auctions.presenter.impl.UpcomingAuctionsPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class UpcomingAuctionsViewModule {
    @Provides
    public UpcomingAuctionsInteractor provideInteractor(@NonNull final Repository repository,
                                                        @NonNull final Application application) {
        return new UpcomingAuctionsInteractorImpl(repository, application);
    }

    @Provides
    public PresenterFactory<UpcomingAuctionsPresenter> providePresenterFactory(@NonNull final UpcomingAuctionsInteractor interactor) {
        return new PresenterFactory<UpcomingAuctionsPresenter>() {
            @NonNull
            @Override
            public UpcomingAuctionsPresenter create() {
                return new UpcomingAuctionsPresenterImpl(interactor);
            }
        };
    }
}
