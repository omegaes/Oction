package com.mega4tech.oction.mvp.main.presenter.impl;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.mega4tech.oction.R;
import com.mega4tech.oction.mvp.main.interactor.MainInteractor;
import com.mega4tech.oction.mvp.main.presenter.MainPresenter;
import com.mega4tech.oction.mvp.main.view.MainView;
import com.mega4tech.oction.presenter.impl.BasePresenterImpl;

import javax.inject.Inject;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final MainInteractor mInteractor;


    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if (mView != null && viewCreated) {
            mView.initBottomBar();
            mView.initViewPager();
            mView.goToAuctions();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
    }


    @Override
    public void onTabSelect(@IdRes int tabId) {
        if (mView != null) {
            switch (tabId) {
                case R.id.tab_auction:
                    mView.goToAuctions();
                    break;
                case R.id.tab_account:
                    mView.goToAccount();
                    break;
                case R.id.tab_credits:
                    mView.goToCredits();
                    break;
            }
        }
    }
}