package com.mega4tech.oction.mvp.main.view;

import android.support.annotation.UiThread;

@UiThread
public interface MainView {

    void initBottomBar();
    void initViewPager();
    void goToAuctions();
    void goToCredits();
    void goToAccount();

}