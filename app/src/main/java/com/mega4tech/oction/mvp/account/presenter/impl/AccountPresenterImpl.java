package com.mega4tech.oction.mvp.account.presenter.impl;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.account.presenter.AccountPresenter;
import com.mega4tech.oction.mvp.account.view.AccountView;
import com.mega4tech.oction.mvp.account.interactor.AccountInteractor;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

public final class AccountPresenterImpl extends BasePresenterImpl<AccountView> implements AccountPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final AccountInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public AccountPresenterImpl(@NonNull AccountInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}