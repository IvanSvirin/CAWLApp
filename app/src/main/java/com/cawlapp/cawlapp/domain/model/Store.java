package com.cawlapp.cawlapp.domain.model;

import com.google.gson.annotations.SerializedName;

public class Store {
    private long vendorId;
    private String name;
    private double commission;
    private String exceptionInfo;
    private String description;
    private boolean giftCard;
    private boolean isFavorite;
    private String affiliateUrl;
    private String logoUrl;
    private boolean ownersBenefit;

    public Store(long vendorId) {
        this.vendorId = vendorId;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGiftCard() {
        return giftCard;
    }

    public void setGiftCard(boolean giftCard) {
        this.giftCard = giftCard;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAffiliateUrl() {
        return affiliateUrl;
    }

    public void setAffiliateUrl(String affiliateUrl) {
        this.affiliateUrl = affiliateUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public boolean isOwnersBenefit() {
        return ownersBenefit;
    }

    public void setOwnersBenefit(boolean ownersBenefit) {
        this.ownersBenefit = ownersBenefit;
    }
}
