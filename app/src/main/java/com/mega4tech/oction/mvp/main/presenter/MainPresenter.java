package com.mega4tech.oction.mvp.main.presenter;

import android.support.annotation.IdRes;

import com.mega4tech.oction.mvp.main.view.MainView;
import com.mega4tech.oction.presenter.BasePresenter;

public interface MainPresenter extends BasePresenter<MainView> {

    void onTabSelect(@IdRes int tabId);

}