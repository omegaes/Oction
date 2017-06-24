package com.mega4tech.oction.mvp.product.view;

import android.support.annotation.UiThread;

import com.mega4tech.oction.entity.Auction;

@UiThread
public interface ProductView {
    void showAuction(Auction auction);
}