package com.mega4tech.oction.mvp.account.view.impl;

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
import com.mega4tech.oction.mvp.account.injection.DaggerAccountViewComponent;
import com.mega4tech.oction.mvp.account.view.AccountView;
import com.mega4tech.oction.mvp.account.presenter.AccountPresenter;
import com.mega4tech.oction.mvp.account.injection.AccountViewModule;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import javax.inject.Inject;

public final class AccountFragment extends BaseFragment<AccountPresenter, AccountView> implements AccountView {
    @Inject
    PresenterFactory<AccountPresenter> mPresenterFactory;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    public AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerAccountViewComponent.builder()
                .appComponent(parentComponent)
                .accountViewModule(new AccountViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<AccountPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

}
