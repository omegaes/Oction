package com.mega4tech.oction.mvp.upcoming.auctions.presenter;

import com.mega4tech.oction.mvp.upcoming.auctions.view.UpcomingAuctionsView;
import com.mega4tech.oction.presenter.BasePresenter;

public interface UpcomingAuctionsPresenter extends BasePresenter<UpcomingAuctionsView> {
    void getAuctions();
}