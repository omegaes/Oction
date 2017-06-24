package com.mega4tech.oction.mvp.upcoming.auctions.injection;

import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.FragmentScope;
import com.mega4tech.oction.mvp.upcoming.auctions.view.impl.UpcomingAuctionsFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = UpcomingAuctionsViewModule.class)
public interface UpcomingAuctionsViewComponent {
    void inject(UpcomingAuctionsFragment fragment);
}