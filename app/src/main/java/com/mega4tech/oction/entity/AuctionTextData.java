package com.mega4tech.oction.entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aboodba on 15/06/2017.
 */

public class AuctionTextData implements Parcelable {

    @SerializedName("user_name")
    @Expose
    private Object userName;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("profileImage")
    @Expose
    private Object profileImage;
    @SerializedName("userId")
    @Expose
    private Object userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("productPrice")
    @Expose
    private String productPrice;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("productCurrency")
    @Expose
    private String productCurrency;
    @SerializedName("auctionId")
    @Expose
    private String auctionId;
    @SerializedName("startingPrice")
    @Expose
    private String startingPrice;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("start_time_unix")
    @Expose
    private String startTimeUnix;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("end_time_unix")
    @Expose
    private String endTimeUnix;
    @SerializedName("minimumPrice")
    @Expose
    private String minimumPrice;
    @SerializedName("bidCount")
    @Expose
    private String bidCount;
    @SerializedName("auctionsHeldCount")
    @Expose
    private String auctionsHeldCount;
    @SerializedName("bidderCount")
    @Expose
    private String bidderCount;


    transient private boolean notificationOn;

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public void setProductCurrency(String productCurrency) {
        this.productCurrency = productCurrency;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(String startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeUnix() {
        return startTimeUnix;
    }

    public void setStartTimeUnix(String startTimeUnix) {
        this.startTimeUnix = startTimeUnix;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeUnix() {
        return endTimeUnix;
    }

    public void setEndTimeUnix(String endTimeUnix) {
        this.endTimeUnix = endTimeUnix;
    }

    public String getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(String minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getBidCount() {
        return bidCount;
    }

    public void setBidCount(String bidCount) {
        this.bidCount = bidCount;
    }

    public String getAuctionsHeldCount() {
        return auctionsHeldCount;
    }

    public void setAuctionsHeldCount(String auctionsHeldCount) {
        this.auctionsHeldCount = auctionsHeldCount;
    }

    public String getBidderCount() {
        return bidderCount;
    }

    public void setBidderCount(String bidderCount) {
        this.bidderCount = bidderCount;
    }

    public boolean isNotificationOn() {
        return notificationOn;
    }

    public void setNotificationOn(boolean notificationOn) {
        this.notificationOn = notificationOn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.productPrice);
        dest.writeString(this.productId);
        dest.writeString(this.productCurrency);
        dest.writeString(this.auctionId);
        dest.writeString(this.startingPrice);
        dest.writeString(this.currency);
        dest.writeString(this.price);
        dest.writeString(this.uniqueId);
        dest.writeString(this.startTime);
        dest.writeString(this.startTimeUnix);
        dest.writeString(this.endTime);
        dest.writeString(this.endTimeUnix);
        dest.writeString(this.minimumPrice);
        dest.writeString(this.bidCount);
        dest.writeString(this.auctionsHeldCount);
        dest.writeString(this.bidderCount);
    }

    public AuctionTextData() {
    }

    protected AuctionTextData(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.productPrice = in.readString();
        this.productId = in.readString();
        this.productCurrency = in.readString();
        this.auctionId = in.readString();
        this.startingPrice = in.readString();
        this.currency = in.readString();
        this.price = in.readString();
        this.uniqueId = in.readString();
        this.startTime = in.readString();
        this.startTimeUnix = in.readString();
        this.endTime = in.readString();
        this.endTimeUnix = in.readString();
        this.minimumPrice = in.readString();
        this.bidCount = in.readString();
        this.auctionsHeldCount = in.readString();
        this.bidderCount = in.readString();
    }

    public static final Parcelable.Creator<AuctionTextData> CREATOR = new Parcelable.Creator<AuctionTextData>() {
        @Override
        public AuctionTextData createFromParcel(Parcel source) {
            return new AuctionTextData(source);
        }

        @Override
        public AuctionTextData[] newArray(int size) {
            return new AuctionTextData[size];
        }
    };
}