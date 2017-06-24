package com.mega4tech.oction.mvp.main.injection;

import com.mega4tech.oction.injection.ActivityScope;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.main.view.impl.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity activity);
}