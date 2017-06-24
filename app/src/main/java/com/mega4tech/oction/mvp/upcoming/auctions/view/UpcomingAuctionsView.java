package com.mega4tech.oction.mvp.upcoming.auctions.view;

import android.support.annotation.UiThread;
import android.view.View;

import com.mega4tech.oction.entity.Auction;

import java.util.List;

@UiThread
public interface UpcomingAuctionsView {
    void display(List<Auction> auctions);

    void open(Auction auction, View sharedView);

    void initRecyclerView();

    void showError(String error);

    void showOffline();

    void reload();
}