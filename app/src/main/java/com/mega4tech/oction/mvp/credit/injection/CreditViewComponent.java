package com.mega4tech.oction.mvp.credit.injection;

import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.FragmentScope;
import com.mega4tech.oction.mvp.credit.view.impl.CreditFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = CreditViewModule.class)
public interface CreditViewComponent {
    void inject(CreditFragment fragment);
}