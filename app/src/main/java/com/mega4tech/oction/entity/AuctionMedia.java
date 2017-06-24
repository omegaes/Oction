package com.mega4tech.oction.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by aboodba on 15/06/2017.
 */


public class AuctionMedia implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("product_id")
    @Expose
    private String productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.media);
        dest.writeString(this.title);
        dest.writeString(this.productId);
    }

    public AuctionMedia() {
    }

    protected AuctionMedia(Parcel in) {
        this.id = in.readString();
        this.media = in.readString();
        this.title = in.readString();
        this.productId = in.readString();
    }

    public static final Parcelable.Creator<AuctionMedia> CREATOR = new Parcelable.Creator<AuctionMedia>() {
        @Override
        public AuctionMedia createFromParcel(Parcel source) {
            return new AuctionMedia(source);
        }

        @Override
        public AuctionMedia[] newArray(int size) {
            return new AuctionMedia[size];
        }
    };
}
