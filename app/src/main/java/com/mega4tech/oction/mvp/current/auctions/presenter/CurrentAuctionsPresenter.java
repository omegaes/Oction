package com.mega4tech.oction.mvp.current.auctions.presenter;

import com.mega4tech.oction.mvp.current.auctions.view.CurrentAuctionsView;
import com.mega4tech.oction.presenter.BasePresenter;

public interface CurrentAuctionsPresenter extends BasePresenter<CurrentAuctionsView> {

    void getAuctions();

}