package com.mega4tech.oction.mvp.product.injection;

import android.support.annotation.NonNull;

import com.mega4tech.oction.mvp.product.interactor.ProductInteractor;
import com.mega4tech.oction.mvp.product.interactor.impl.ProductInteractorImpl;
import com.mega4tech.oction.mvp.product.presenter.ProductPresenter;
import com.mega4tech.oction.mvp.product.presenter.impl.ProductPresenterImpl;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import dagger.Module;
import dagger.Provides;

@Module
public final class ProductViewModule {
    @Provides
    public ProductInteractor provideInteractor() {
        return new ProductInteractorImpl();
    }

    @Provides
    public PresenterFactory<ProductPresenter> providePresenterFactory(@NonNull final ProductInteractor interactor) {
        return new PresenterFactory<ProductPresenter>() {
            @NonNull
            @Override
            public ProductPresenter create() {
                return new ProductPresenterImpl(interactor);
            }
        };
    }
}
