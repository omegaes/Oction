package com.mega4tech.oction.mvp.current.auctions.interactor;

import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.interactor.BaseInteractor;

import java.util.List;

import io.reactivex.Observable;

public interface CurrentAuctionsInteractor extends BaseInteractor {

    boolean isOnline();

    Observable<List<Auction>> getAuctions();
}