package com.mega4tech.oction.mvp.credit.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.credit.interactor.CreditInteractor;
import com.mega4tech.oction.mvp.credit.interactor.impl.CreditInteractorImpl;
import com.mega4tech.oction.mvp.credit.presenter.CreditPresenter;
import com.mega4tech.oction.mvp.credit.presenter.impl.CreditPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class CreditViewModule {
    @Provides
    public CreditInteractor provideInteractor() {
        return new CreditInteractorImpl();
    }

    @Provides
    public PresenterFactory<CreditPresenter> providePresenterFactory(@NonNull final CreditInteractor interactor) {
        return new PresenterFactory<CreditPresenter>() {
            @NonNull
            @Override
            public CreditPresenter create() {
                return new CreditPresenterImpl(interactor);
            }
        };
    }
}
