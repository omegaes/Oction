package com.mega4tech.oction.mvp.product.presenter;

import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.mvp.product.view.ProductView;
import com.mega4tech.oction.presenter.BasePresenter;

public interface ProductPresenter extends BasePresenter<ProductView> {
    void setAuction(Auction auction);
}