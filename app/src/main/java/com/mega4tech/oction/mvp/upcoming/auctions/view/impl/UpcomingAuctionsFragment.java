package com.mega4tech.oction.mvp.upcoming.auctions.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.mega4tech.oction.R;
import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.impl.BaseFragment;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.product.view.impl.ProductActivity;
import com.mega4tech.oction.mvp.upcoming.auctions.adapter.UpcomingAuctionsAdapter;
import com.mega4tech.oction.mvp.upcoming.auctions.injection.DaggerUpcomingAuctionsViewComponent;
import com.mega4tech.oction.mvp.upcoming.auctions.injection.UpcomingAuctionsViewModule;
import com.mega4tech.oction.mvp.upcoming.auctions.presenter.UpcomingAuctionsPresenter;
import com.mega4tech.oction.mvp.upcoming.auctions.view.UpcomingAuctionsView;
import com.mega4tech.oction.presenter.loader.PresenterFactory;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public final class UpcomingAuctionsFragment extends BaseFragment<UpcomingAuctionsPresenter, UpcomingAuctionsView> implements UpcomingAuctionsView {
    @Inject
    PresenterFactory<UpcomingAuctionsPresenter> mPresenterFactory;
    @BindView(R.id.list)
    SuperRecyclerView list;
    @BindView(R.id.offline_view_stub)
    ViewStub offlineViewStub;
    @BindView(R.id.error_view_stub)
    ViewStub errorViewStub;

    private UpcomingAuctionsFragment.ErrorViewStub mErrorViewStub;
    private UpcomingAuctionsFragment.OfflineViewStub mOfflineViewStub;


    public static UpcomingAuctionsFragment newInstance() {
        return new UpcomingAuctionsFragment();
    }

    public UpcomingAuctionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcoming_auctions, container, false);
        ButterKnife.bind(this, view);
        View viewFromStub = errorViewStub.inflate();
        mErrorViewStub = new UpcomingAuctionsFragment.ErrorViewStub(viewFromStub);
        viewFromStub = offlineViewStub.inflate();
        mOfflineViewStub = new UpcomingAuctionsFragment.OfflineViewStub(viewFromStub);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerUpcomingAuctionsViewComponent.builder()
                .appComponent(parentComponent)
                .upcomingAuctionsViewModule(new UpcomingAuctionsViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<UpcomingAuctionsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }


    @Override
    public void display(List<Auction> auctions) {
        UpcomingAuctionsAdapter adapter = new UpcomingAuctionsAdapter(getContext(), auctions);
        adapter.setOnItemClickListener((view, position) -> open(adapter.getItemAtPosition(position), view.findViewById(R.id.offer_iv)));
        list.setVisibility(View.VISIBLE);
        errorViewStub.setVisibility(View.GONE);
        offlineViewStub.setVisibility(View.GONE);
        list.setAdapter(adapter);
        list.getProgressView().setVisibility(View.GONE);
        list.getMoreProgressView().setVisibility(View.GONE);
        list.setRefreshListener(() -> {
            Observable.just(true).delay(3,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> list.setRefreshing(false));
        });
    }

    @Override
    public void open(Auction auction, View sharedView) {
        ProductActivity.show(getActivity(), auction, sharedView);
    }

    @Override
    public void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        list.getProgressView().setVisibility(View.VISIBLE);
        list.getEmptyView().setVisibility(View.GONE);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list.getContext(), DividerItemDecoration.VERTICAL);
        list.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void showError(String error) {
        errorViewStub.setVisibility(View.VISIBLE);
        mErrorViewStub.errorTv.setText(error);
        list.setVisibility(View.GONE);
        offlineViewStub.setVisibility(View.GONE);
    }

    @Override
    public void showOffline() {
        offlineViewStub.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
        errorViewStub.setVisibility(View.GONE);
    }


    @Override
    public void reload() {
        list.setVisibility(View.VISIBLE);
        list.getEmptyView().setVisibility(View.GONE);
        list.getProgressView().setVisibility(View.VISIBLE);
        errorViewStub.setVisibility(View.GONE);
        offlineViewStub.setVisibility(View.GONE);
    }


    class ErrorViewStub {
        @BindView(R.id.error_tv)
        TextView errorTv;

        public ErrorViewStub(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.try_again_btn)
        void onClick(View view) {
            if (mPresenter != null) mPresenter.getAuctions();
        }
    }

    class OfflineViewStub {

        public OfflineViewStub(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.reconnect_btn)
        void onClick(View view) {
            if (mPresenter != null) mPresenter.getAuctions();
        }
    }
}
