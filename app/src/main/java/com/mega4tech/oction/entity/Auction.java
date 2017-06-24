package com.mega4tech.oction.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by aboodba on 15/06/2017.
 */


public class Auction implements Parcelable {

    @SerializedName("auction")
    @Expose
    private AuctionTextData auctionTextData;
    @SerializedName("media")
    @Expose
    private List<AuctionMedia> media = null;

    public AuctionTextData getAuctionTextData() {
        return auctionTextData;
    }

    public void setAuctionTextData(AuctionTextData auctionTextData) {
        this.auctionTextData = auctionTextData;
    }

    public List<AuctionMedia> getMedia() {
        return media;
    }

    public void setMedia(List<AuctionMedia> media) {
        this.media = media;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.auctionTextData, flags);
        dest.writeList(this.media);
    }

    public Auction() {
    }

    protected Auction(Parcel in) {
        this.auctionTextData = in.readParcelable(AuctionTextData.class.getClassLoader());
        this.media = new ArrayList<AuctionMedia>();
        in.readList(this.media, AuctionMedia.class.getClassLoader());
    }

    public static final Parcelable.Creator<Auction> CREATOR = new Parcelable.Creator<Auction>() {
        @Override
        public Auction createFromParcel(Parcel source) {
            return new Auction(source);
        }

        @Override
        public Auction[] newArray(int size) {
            return new Auction[size];
        }
    };
}
