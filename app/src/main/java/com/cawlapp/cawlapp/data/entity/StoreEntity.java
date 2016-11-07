package com.cawlapp.cawlapp.data.entity;

import com.cawlapp.cawlapp.data.entity.daoconverter.DealType;
import com.cawlapp.cawlapp.data.entity.daoconverter.DealTypeConverter;
import com.cawlapp.cawlapp.data.entity.daoconverter.StoreType;
import com.cawlapp.cawlapp.data.entity.daoconverter.StoreTypeConverter;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * StoreEntity used in the data layer.
 */
@Entity(indexes = {
        @Index(value = "name ASC", unique = true)
})
public class StoreEntity {
    @Id
    private Long id;

    @SerializedName("vendor_id")
    private long vendorId;
    @SerializedName("name")
    private String name;
    @SerializedName("commission")
    private double commission;
    @SerializedName("exception_info")
    private String exceptionInfo;
    @SerializedName("description")
    private String description;
    @SerializedName("gift_card")
    private boolean giftCard;
    @SerializedName("is_favorite")
    private boolean isFavorite;
    @SerializedName("affiliate_url")
    private String affiliateUrl;
    @SerializedName("logo_url")
    private String logoUrl;
    @SerializedName("owners_benefit")
    private boolean ownersBenefit;

    @Convert(converter = StoreTypeConverter.class, columnType = String.class)
    private StoreType storeType;

    @Generated(hash = 1935411093)
    public StoreEntity() {
    }

    public StoreEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 23322933)
    public StoreEntity(Long id, long vendorId, String name, double commission, String exceptionInfo, String description, boolean giftCard, boolean isFavorite, String affiliateUrl,
            String logoUrl, boolean ownersBenefit, StoreType storeType) {
        this.id = id;
        this.vendorId = vendorId;
        this.name = name;
        this.commission = commission;
        this.exceptionInfo = exceptionInfo;
        this.description = description;
        this.giftCard = giftCard;
        this.isFavorite = isFavorite;
        this.affiliateUrl = affiliateUrl;
        this.logoUrl = logoUrl;
        this.ownersBenefit = ownersBenefit;
        this.storeType = storeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public boolean getGiftCard() {
        return this.giftCard;
    }

    public boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean getOwnersBenefit() {
        return this.ownersBenefit;
    }
}
