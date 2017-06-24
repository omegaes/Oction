package com.mega4tech.oction.mvp.upcoming.auctions.interactor;

import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.interactor.BaseInteractor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface UpcomingAuctionsInteractor extends BaseInteractor {

    boolean isOnline();

    Observable<List<Auction>> getAuctions();

}