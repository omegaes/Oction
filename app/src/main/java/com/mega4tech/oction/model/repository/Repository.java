package com.mega4tech.oction.model.repository;

import com.mega4tech.oction.entity.Auction;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by aboodba on 23/04/2017.
 */

public interface Repository {

    Observable<List<Auction>> getAuctions();

}
