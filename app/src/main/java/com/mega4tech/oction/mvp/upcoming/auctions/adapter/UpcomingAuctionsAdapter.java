package com.mega4tech.oction.mvp.upcoming.auctions.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.mega4tech.oction.BuildConfig;
import com.mega4tech.oction.R;
import com.mega4tech.oction.custom.view.RatioImageView;
import com.mega4tech.oction.entity.Auction;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpcomingAuctionsAdapter extends RecyclerView.Adapter<UpcomingAuctionsAdapter.ViewHolder> {
    private RecycleItemClickListener itemClickListener;
    private List<Auction> mObjects = new ArrayList<Auction>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    PrettyTime mPrettyTime = new PrettyTime();
    private int mLastPosition = -1;


    public UpcomingAuctionsAdapter(Context context, List<Auction> items) {
        this.mObjects = items;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return this.mObjects.size();
    }

    @Override
    public void onBindViewHolder(UpcomingAuctionsAdapter.ViewHolder holder, int position) {
        Auction item = this.mObjects.get(position);
        if (item.getMedia() != null && item.getMedia().size() > 0) {
            Picasso.with(mContext).load(BuildConfig.BASEURL + item.getMedia().get(0).getMedia()).placeholder(R.drawable.ic_autorenew_black_24dp).into(holder.offerIv);
        } else {
            Picasso.with(mContext).load(R.drawable.ic_android_black_24dp).into(holder.offerIv);
        }
        holder.auctionItemNameTv.setText(item.getAuctionTextData().getTitle());
        holder.auctionItemRetailPriceTv.setText(mContext.getString(R.string.auction_retail_price, item.getAuctionTextData().getCurrency() + item.getAuctionTextData().getProductPrice()));
        holder.auctionItemStartDateTv.setText(mContext.getString(R.string.auction_start_date, mPrettyTime.format(new Date(
                Long.parseLong(item.getAuctionTextData().getStartTimeUnix() )* 1000))));
        holder.notificationIv.setTag(item);

        Drawable img = mContext.getResources().getDrawable( item.getAuctionTextData().isNotificationOn() ?
                R.drawable.ic_notifications_black_24dp : R.drawable.ic_notifications_none_black_24dp );

        holder.notificationIv.setImageDrawable(img);
        holder.notificationIv.setColorFilter(
                ContextCompat.getColor(mContext,(item.getAuctionTextData().isNotificationOn() ?
                        R.color.colorAccent :R.color.gray)));

        holder.notificationIv.setOnClickListener(v -> {

            Auction auction = (Auction) v.getTag();
            auction.getAuctionTextData().setNotificationOn(!auction.getAuctionTextData().isNotificationOn());
            Drawable img1 = mContext.getResources().getDrawable( auction.getAuctionTextData().isNotificationOn() ?
                    R.drawable.ic_notifications_black_24dp : R.drawable.ic_notifications_none_black_24dp );
            holder.notificationIv.setImageDrawable(img1);
            holder.notificationIv.setColorFilter(
                    ContextCompat.getColor(mContext,(item.getAuctionTextData().isNotificationOn() ?
                            R.color.colorAccent :R.color.gray)));

        });

        setScaleAnimation(holder.itemView, position);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.card_upcoming_auction, parent, false);
        return new UpcomingAuctionsAdapter.ViewHolder(sView, itemClickListener);
        //return new UpcomingAuctionsAdapter.ViewHolder(sView);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecycleItemClickListener mItemClickListener;
        private RatioImageView offerIv;
        private TextView auctionItemNameTv;
        private TextView auctionItemRetailPriceTv;
        private TextView auctionItemStartDateTv;
        private ImageView notificationIv;

        public ViewHolder(View view, RecycleItemClickListener itemClickListener) {
            super(view);
            offerIv = (RatioImageView) view.findViewById(R.id.offer_iv);
            auctionItemNameTv = (TextView) view.findViewById(R.id.auction_item_name_tv);
            auctionItemRetailPriceTv = (TextView) view.findViewById(R.id.auction_item_retail_price_tv);
            auctionItemStartDateTv = (TextView) view.findViewById(R.id.auction_item_start_date_tv);
            notificationIv = (ImageView) view.findViewById(R.id.notification_iv);
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

    private void setScaleAnimation(View view, int position) {
        if (position > mLastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(800);
            view.startAnimation(anim);
            mLastPosition = position;

        }
    }

    public Auction getItemAtPosition(int position){
        return mObjects.get(position);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
