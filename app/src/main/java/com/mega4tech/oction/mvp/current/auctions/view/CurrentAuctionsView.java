package com.mega4tech.oction.mvp.current.auctions.view;

import android.support.annotation.UiThread;
import android.view.View;

import com.mega4tech.oction.entity.Auction;

import java.util.List;

@UiThread
public interface CurrentAuctionsView {
    void display(List<Auction> auctions);

    void open(Auction auction, View sharedView);

    void initRecyclerView();

    void showError(String error);

    void showOffline();

    void reload();
}