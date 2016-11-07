package com.cawlapp.cawlapp.data.entity;

import com.cawlapp.cawlapp.data.entity.daoconverter.DealType;
import com.cawlapp.cawlapp.data.entity.daoconverter.DealTypeConverter;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * DealEntity used in the data layer.
 */
@Entity(indexes = {
        @Index(value = "couponId ASC", unique = true)
})
public class DealEntity {
    @Id
    private Long id;

    @SerializedName("coupon_id")
    private long couponId;
    @SerializedName("vendor_id")
    private long vendorId;
    @SerializedName("coupon_type")
    private String couponType;
    @SerializedName("restrictions")
    private String restrictions;
    @SerializedName("coupon_code")
    private String couponCode;
    @SerializedName("expiration_date")
    private String expirationDate;
    @SerializedName("affiliate_url")
    private String affiliateUrl;
    @SerializedName("vendor_logo_url")
    private String vendorLogoUrl;
    @SerializedName("vendor_commission")
    private double vendorCommission;
    @SerializedName("label")
    private String label;
    @SerializedName("owners_benefit")
    private boolean ownersBenefit;

    @Convert(converter = DealTypeConverter.class, columnType = String.class)
    private DealType dealType;

    @Generated(hash = 1298433796)
    public DealEntity() {
    }

    public DealEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 1779840070)
    public DealEntity(Long id, long couponId, long vendorId, String couponType, String restrictions, String couponCode, String expirationDate, String affiliateUrl, String vendorLogoUrl,
            double vendorCommission, String label, boolean ownersBenefit, DealType dealType) {
        this.id = id;
        this.couponId = couponId;
        this.vendorId = vendorId;
        this.couponType = couponType;
        this.restrictions = restrictions;
        this.couponCode = couponCode;
        this.expirationDate = expirationDate;
        this.affiliateUrl = affiliateUrl;
        this.vendorLogoUrl = vendorLogoUrl;
        this.vendorCommission = vendorCommission;
        this.label = label;
        this.ownersBenefit = ownersBenefit;
        this.dealType = dealType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAffiliateUrl() {
        return affiliateUrl;
    }

    public void setAffiliateUrl(String affiliateUrl) {
        this.affiliateUrl = affiliateUrl;
    }

    public String getVendorLogoUrl() {
        return vendorLogoUrl;
    }

    public void setVendorLogoUrl(String vendorLogoUrl) {
        this.vendorLogoUrl = vendorLogoUrl;
    }

    public double getVendorCommission() {
        return vendorCommission;
    }

    public void setVendorCommission(double vendorCommission) {
        this.vendorCommission = vendorCommission;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isOwnersBenefit() {
        return ownersBenefit;
    }

    public void setOwnersBenefit(boolean ownersBenefit) {
        this.ownersBenefit = ownersBenefit;
    }

    public boolean getOwnersBenefit() {
        return this.ownersBenefit;
    }

    public DealType getDealType() {
        return this.dealType;
    }

    public void setDealType(DealType dealType) {
        this.dealType = dealType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("***** Deal Model Details *****\n");
        stringBuilder.append("couponId=" + this.getCouponId() + "\n");
        stringBuilder.append("vendorId=" + this.getVendorId() + "\n");
        stringBuilder.append("couponType=" + this.getCouponType() + "\n");
        stringBuilder.append("restrictions=" + this.getRestrictions() + "\n");
        stringBuilder.append("couponCode=" + this.getCouponCode() + "\n");
        stringBuilder.append("expirationDate=" + this.getExpirationDate() + "\n");
        stringBuilder.append("affiliateUrl=" + this.getAffiliateUrl() + "\n");
        stringBuilder.append("vendorLogoUrl=" + this.getVendorLogoUrl() + "\n");
        stringBuilder.append("vendorCommission=" + this.getVendorCommission() + "\n");
        stringBuilder.append("label=" + this.getLabel() + "\n");
        stringBuilder.append("ownersBenefit=" + this.isOwnersBenefit() + "\n");
        stringBuilder.append("*******************************");

        return stringBuilder.toString();
    }


}
