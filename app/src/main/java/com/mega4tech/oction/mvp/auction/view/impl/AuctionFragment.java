package com.mega4tech.oction.mvp.auction.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mega4tech.oction.R;
import com.mega4tech.oction.impl.BaseFragment;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.auction.adapter.AuctionScreenViewPagerAdapter;
import com.mega4tech.oction.mvp.auction.injection.AuctionViewModule;
import com.mega4tech.oction.mvp.auction.injection.DaggerAuctionViewComponent;
import com.mega4tech.oction.mvp.auction.presenter.AuctionPresenter;
import com.mega4tech.oction.mvp.auction.view.AuctionView;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AuctionFragment extends BaseFragment<AuctionPresenter, AuctionView> implements AuctionView {
    @Inject
    PresenterFactory<AuctionPresenter> mPresenterFactory;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.auctions_viewpager)
    ViewPager auctionsViewpager;


    public static AuctionFragment newInstance() {
        return new AuctionFragment();
    }

    public AuctionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ouction, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerAuctionViewComponent.builder()
                .appComponent(parentComponent)
                .auctionViewModule(new AuctionViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<AuctionPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }


    @Override
    public void initTabs() {
        auctionsViewpager.setAdapter(new AuctionScreenViewPagerAdapter(getChildFragmentManager(),getContext()));
        tabs.setupWithViewPager(auctionsViewpager);

    }

    @Override
    public void goToUpcomingAuctions() {
        auctionsViewpager.setCurrentItem(1);

    }

    @Override
    public void goToCurrentAuctions() {
        auctionsViewpager.setCurrentItem(0);
    }
}
