package com.mega4tech.oction.mvp.current.auctions.injection;

import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.FragmentScope;
import com.mega4tech.oction.mvp.current.auctions.view.impl.CurrentAuctionsFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CurrentAuctionsViewModule.class)
public interface CurrentAuctionsViewComponent {
    void inject(CurrentAuctionsFragment fragment);
}