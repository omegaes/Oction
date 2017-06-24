package com.mega4tech.oction.mvp.main.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.main.interactor.MainInteractor;
import com.mega4tech.oction.mvp.main.interactor.impl.MainInteractorImpl;
import com.mega4tech.oction.mvp.main.presenter.MainPresenter;
import com.mega4tech.oction.mvp.main.presenter.impl.MainPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {
    @Provides
    public MainInteractor provideInteractor() {
        return new MainInteractorImpl();
    }

    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return new PresenterFactory<MainPresenter>() {
            @NonNull
            @Override
            public MainPresenter create() {
                return new MainPresenterImpl(interactor);
            }
        };
    }
}
