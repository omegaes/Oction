package com.mega4tech.oction.mvp.credit.presenter.impl;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.credit.presenter.CreditPresenter;
import com.mega4tech.oction.mvp.credit.view.CreditView;
import com.mega4tech.oction.mvp.credit.interactor.CreditInteractor;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

public final class CreditPresenterImpl extends BasePresenterImpl<CreditView> implements CreditPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final CreditInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public CreditPresenterImpl(@NonNull CreditInteractor interactor) {
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