package com.mega4tech.oction.mvp.main.view.impl;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mega4tech.oction.R;
import com.mega4tech.oction.impl.BaseActivity;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.main.adapter.MainScreenViewPagerAdapter;
import com.mega4tech.oction.mvp.main.injection.DaggerMainViewComponent;
import com.mega4tech.oction.mvp.main.injection.MainViewModule;
import com.mega4tech.oction.mvp.main.presenter.MainPresenter;
import com.mega4tech.oction.mvp.main.view.MainView;
import com.mega4tech.oction.presenter.loader.PresenterFactory;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.contentContainer)
    ViewPager viewPagerContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    // Your presenter is available using the mPresenter variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        viewPagerContainer.setOffscreenPageLimit(3);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void initBottomBar() {

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (mPresenter != null)
                    mPresenter.onTabSelect(tabId);
            }
        });
    }

    @Override
    public void initViewPager() {
        viewPagerContainer.setAdapter(new MainScreenViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void goToAuctions() {
        viewPagerContainer.setCurrentItem(0);
    }

    @Override
    public void goToCredits() {
        viewPagerContainer.setCurrentItem(1);
    }

    @Override
    public void goToAccount() {
        viewPagerContainer.setCurrentItem(2);
    }
}
