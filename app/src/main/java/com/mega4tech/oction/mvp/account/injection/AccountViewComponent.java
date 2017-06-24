package com.mega4tech.oction.mvp.account.injection;

import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.injection.FragmentScope;
import com.mega4tech.oction.mvp.account.view.impl.AccountFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = AccountViewModule.class)
public interface AccountViewComponent {
    void inject(AccountFragment fragment);
}