package com.mega4tech.oction.mvp.credit.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mega4tech.oction.R;
import com.mega4tech.oction.impl.BaseFragment;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.credit.injection.DaggerCreditViewComponent;
import com.mega4tech.oction.mvp.credit.view.CreditView;
import com.mega4tech.oction.mvp.credit.presenter.CreditPresenter;
import com.mega4tech.oction.mvp.credit.injection.CreditViewModule;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import javax.inject.Inject;

public final class CreditFragment extends BaseFragment<CreditPresenter, CreditView> implements CreditView {
    @Inject
    PresenterFactory<CreditPresenter> mPresenterFactory;


    public static CreditFragment newInstance() {
        return new CreditFragment();
    }

    public CreditFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_credit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCreditViewComponent.builder()
                .appComponent(parentComponent)
                .creditViewModule(new CreditViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CreditPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }


}
