package com.mega4tech.oction.mvp.current.auctions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mega4tech.oction.BuildConfig;
import com.mega4tech.oction.R;
import com.mega4tech.oction.custom.view.RatioImageView;
import com.mega4tech.oction.entity.Auction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CurrentAuctionsAdapter extends RecyclerView.Adapter<CurrentAuctionsAdapter.ViewHolder> {
    private RecycleItemClickListener itemClickListener;
    private List<Auction> mObjects = new ArrayList<Auction>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mLastPosition = -1;

    public CurrentAuctionsAdapter(Context context, List<Auction> items) {
        this.mObjects = items;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return this.mObjects.size();
    }

    @Override
    public void onBindViewHolder(CurrentAuctionsAdapter.ViewHolder holder, int position) {
        Auction item = this.mObjects.get(position);
        if (item.getMedia() != null && item.getMedia().size() > 0) {
            Picasso.with(mContext).load(BuildConfig.BASEURL + item.getMedia().get(0).getMedia()).placeholder(R.drawable.ic_autorenew_black_24dp).into(holder.offerIv);
        } else {
            Picasso.with(mContext).load(R.drawable.ic_android_black_24dp).into(holder.offerIv);
        }

        holder.auctionItemNameTv.setText(item.getAuctionTextData().getTitle());
        holder.auctionItemRetailPriceTv.setText(mContext.getString(R.string.auction_retail_price, item.getAuctionTextData().getCurrency() + item.getAuctionTextData().getProductPrice()));
        holder.cardCurrentAuctionPriceTv.setText(String.format("%s %s", item.getAuctionTextData().getCurrency(), item.getAuctionTextData().getPrice()));

        long remaining = (Long.parseLong(item.getAuctionTextData().getEndTimeUnix()) * 1000) - System.currentTimeMillis();
        remaining = remaining / 1000;
        holder.endTimeTv.setText(Long.toString(remaining) + "s");

        int max = 100;// mock value
        int progress = new Random().nextInt(100); //mock value
        holder.dealCountdownProgressBar.setMax(max);
        holder.dealCountdownProgressBar.setProgress(progress);
        setAnimation(holder.itemView, position);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.card_current_auction, parent, false);
        return new CurrentAuctionsAdapter.ViewHolder(sView, itemClickListener);
        //return new CurrentAuctionsAdapter.ViewHolder(sView);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecycleItemClickListener mItemClickListener;
        private RatioImageView offerIv;
        private ProgressBar dealCountdownProgressBar;
        private TextView endTimeTv;
        private TextView auctionItemNameTv;
        private TextView auctionItemRetailPriceTv;
        private LinearLayout cardCurrentAuctionPriceLayout;
        private TextView cardCurrentAuctionPriceTv;

        public ViewHolder(View view, RecycleItemClickListener itemClickListener) {
            super(view);
            offerIv = (RatioImageView) view.findViewById(R.id.offer_iv);
            dealCountdownProgressBar = (ProgressBar) view.findViewById(R.id.deal_countdown_progressBar);
            endTimeTv = (TextView) view.findViewById(R.id.end_time_tv);
            auctionItemNameTv = (TextView) view.findViewById(R.id.auction_item_name_tv);
            auctionItemRetailPriceTv = (TextView) view.findViewById(R.id.auction_item_retail_price_tv);
            cardCurrentAuctionPriceLayout = (LinearLayout) view.findViewById(R.id.card_current_auction_price_layout);
            cardCurrentAuctionPriceTv = (TextView) view.findViewById(R.id.card_current_auction_price_tv);
            mItemClickListener = itemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public void setOnItemClickListener(RecycleItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface RecycleItemClickListener {
        public void onItemClick(View view, int position);
    }

    private void setAnimation(View view, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > mLastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        view.startAnimation(animation);
        mLastPosition = position;
    }

    public Auction getItemAtPosition(int position) {
        return mObjects.get(position);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
