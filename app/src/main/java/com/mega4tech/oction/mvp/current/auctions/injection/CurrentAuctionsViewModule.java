package com.mega4tech.oction.mvp.current.auctions.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.Application;
import com.mega4tech.oction.model.repository.Repository;
import com.mega4tech.oction.mvp.current.auctions.interactor.CurrentAuctionsInteractor;
import com.mega4tech.oction.mvp.current.auctions.interactor.impl.CurrentAuctionsInteractorImpl;
import com.mega4tech.oction.mvp.current.auctions.presenter.CurrentAuctionsPresenter;
import com.mega4tech.oction.mvp.current.auctions.presenter.impl.CurrentAuctionsPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class CurrentAuctionsViewModule {
    @Provides
    public CurrentAuctionsInteractor provideInteractor(@NonNull final Repository repository,
                                                       @NonNull final Application application) {
        return new CurrentAuctionsInteractorImpl(repository, application);
    }

    @Provides
    public PresenterFactory<CurrentAuctionsPresenter> providePresenterFactory(@NonNull final CurrentAuctionsInteractor interactor) {
        return new PresenterFactory<CurrentAuctionsPresenter>() {
            @NonNull
            @Override
            public CurrentAuctionsPresenter create() {
                return new CurrentAuctionsPresenterImpl(interactor);
            }
        };
    }
}
