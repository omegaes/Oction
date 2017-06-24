package com.mega4tech.oction.mvp.account.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.account.interactor.AccountInteractor;
import com.mega4tech.oction.mvp.account.interactor.impl.AccountInteractorImpl;
import com.mega4tech.oction.mvp.account.presenter.AccountPresenter;
import com.mega4tech.oction.mvp.account.presenter.impl.AccountPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class AccountViewModule {
    @Provides
    public AccountInteractor provideInteractor() {
        return new AccountInteractorImpl();
    }

    @Provides
    public PresenterFactory<AccountPresenter> providePresenterFactory(@NonNull final AccountInteractor interactor) {
        return new PresenterFactory<AccountPresenter>() {
            @NonNull
            @Override
            public AccountPresenter create() {
                return new AccountPresenterImpl(interactor);
            }
        };
    }
}
