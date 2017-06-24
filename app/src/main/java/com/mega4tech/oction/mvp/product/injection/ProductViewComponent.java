package com.mega4tech.oction.mvp.product.injection;

import com.mega4tech.oction.injection.ActivityScope;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.product.view.impl.ProductActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ProductViewModule.class)
public interface ProductViewComponent {
    void inject(ProductActivity activity);
}