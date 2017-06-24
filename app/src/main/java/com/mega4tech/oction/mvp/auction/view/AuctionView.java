package com.mega4tech.oction.mvp.auction.view;

import android.support.annotation.UiThread;

@UiThread
public interface AuctionView {

    void initTabs();
    void goToUpcomingAuctions();
    void goToCurrentAuctions();

}