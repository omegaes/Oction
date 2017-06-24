package com.mega4tech.oction.presenter.loader;

import android.support.annotation.NonNull;

import com.mega4tech.oction.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
