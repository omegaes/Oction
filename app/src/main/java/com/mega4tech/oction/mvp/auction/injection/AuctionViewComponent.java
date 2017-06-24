package com.mega4tech.oction.mvp.auction.injection;

import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.FragmentScope;
import com.mega4tech.oction.mvp.auction.view.impl.AuctionFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = AuctionViewModule.class)
public interface AuctionViewComponent {
    void inject(AuctionFragment fragment);
}