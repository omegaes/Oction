package com.mega4tech.oction.model.retrofit;


import com.mega4tech.oction.entity.Auction;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by aboodba on 08/05/2017.
 */

public interface AuctionService {
    @GET("api/v1/auctions?")
    Observable<List<Auction>> getAuctions();
}
