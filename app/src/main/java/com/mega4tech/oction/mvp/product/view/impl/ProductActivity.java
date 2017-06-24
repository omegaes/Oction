package com.mega4tech.oction.mvp.product.view.impl;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega4tech.oction.BuildConfig;
import com.mega4tech.oction.R;
import com.mega4tech.oction.entity.Auction;
import com.mega4tech.oction.impl.BaseActivity;
import com.mega4tech.oction.injection.AppComponent;
import com.mega4tech.oction.mvp.product.injection.DaggerProductViewComponent;
import com.mega4tech.oction.mvp.product.injection.ProductViewModule;
import com.mega4tech.oction.mvp.product.presenter.ProductPresenter;
import com.mega4tech.oction.mvp.product.view.ProductView;
import com.mega4tech.oction.presenter.loader.PresenterFactory;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class ProductActivity extends BaseActivity<ProductPresenter, ProductView> implements ProductView {

    private static final String AUCTION_PARAM = "auction_param";
    @Inject
    PresenterFactory<ProductPresenter> mPresenterFactory;


    @BindView(R.id.product_image_iv)
    ImageView productImageIv;
    @BindView(R.id.product_name_tv)
    TextView productNameTv;

    Auction mAuction;


    public static void show(Activity activity, Auction auction, View sharedView) {
        Intent intent = new Intent(activity, ProductActivity.class);
        String transitionName = "product_image_trans";
        intent.putExtra(AUCTION_PARAM, auction);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, sharedView, transitionName);
        activity.startActivity(intent, transitionActivityOptions.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(AUCTION_PARAM)) {
            mAuction = getIntent().getParcelableExtra(AUCTION_PARAM);
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setAuction(mAuction);
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerProductViewComponent.builder()
                .appComponent(parentComponent)
                .productViewModule(new ProductViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<ProductPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public void showAuction(Auction auction) {
        productNameTv.setText(auction.getAuctionTextData().getTitle());

        supportPostponeEnterTransition();

        if (auction.getMedia() != null && auction.getMedia().size() > 0) {
            Picasso.with(this).load(BuildConfig.BASEURL + auction.getMedia().get(0).getMedia())
                    // .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .into(productImageIv, new Callback() {
                        @Override
                        public void onSuccess() {
                            supportStartPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            supportStartPostponedEnterTransition();
                        }
                    });
        } else {
            Picasso.with(this).load(R.drawable.ic_android_black_24dp).into(productImageIv, new Callback() {
                @Override
                public void onSuccess() {
                    supportStartPostponedEnterTransition();
                }

                @Override
                public void onError() {
                    supportStartPostponedEnterTransition();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
