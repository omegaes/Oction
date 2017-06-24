package com.mega4tech.oction.mvp.current.auctions.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.mega4tech.oction.R;
import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.impl.BaseFragment;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.current.auctions.adapter.CurrentAuctionsAdapter;
import com.mega4tech.oction.mvp.current.auctions.injection.CurrentAuctionsViewModule;
import com.mega4tech.oction.mvp.current.auctions.injection.DaggerCurrentAuctionsViewComponent;
import com.mega4tech.oction.mvp.current.auctions.presenter.CurrentAuctionsPresenter;
import com.mega4tech.oction.mvp.current.auctions.view.CurrentAuctionsView;
import com.mega4tech.oction.mvp.product.view.impl.ProductActivity;
import com.mega4tech.oction.presenter.loader.PresenterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public final class CurrentAuctionsFragment extends BaseFragment<CurrentAuctionsPresenter, CurrentAuctionsView> implements CurrentAuctionsView {
    @Inject
    PresenterFactory<CurrentAuctionsPresenter> mPresenterFactory;
    @BindView(R.id.list)
    SuperRecyclerView list;
    @BindView(R.id.offline_view_stub)
    ViewStub offlineViewStub;
    @BindView(R.id.error_view_stub)
    ViewStub errorViewStub;

    private ErrorViewStub mErrorViewStub;
    private OfflineViewStub mOfflineViewStub;


    public static CurrentAuctionsFragment newInstance() {
        return new CurrentAuctionsFragment();
    }

    public CurrentAuctionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_auctions, container, false);
        ButterKnife.bind(this, view);
        View viewFromStub = errorViewStub.inflate();
        mErrorViewStub = new ErrorViewStub(viewFromStub);
        viewFromStub = offlineViewStub.inflate();
        mOfflineViewStub = new OfflineViewStub(viewFromStub);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerCurrentAuctionsViewComponent.builder()
                .appComponent(parentComponent)
                .currentAuctionsViewModule(new CurrentAuctionsViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<CurrentAuctionsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void display(List<Auction> auctions) {
        CurrentAuctionsAdapter adapter = new CurrentAuctionsAdapter(getContext(), auctions);
        adapter.setOnItemClickListener((view, position) -> open(adapter.getItemAtPosition(position), view.findViewById(R.id.offer_iv)));
        list.setVisibility(View.VISIBLE);
        errorViewStub.setVisibility(View.GONE);
        offlineViewStub.setVisibility(View.GONE);
        list.setAdapter(adapter);
        list.getProgressView().setVisibility(View.GONE);
        list.getMoreProgressView().setVisibility(View.GONE);
        list.setRefreshListener(() ->
                Observable.just(true).delay(3, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aBoolean -> list.setRefreshing(false))
        );
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
