package com.cawlapp.cawlapp.domain.model;

/**
 * Class that represents a Deal in the domain layer.
 */

public class Deal {
    private long couponId;
    private long vendorId;
    private String couponType;
    private String restrictions;
    private String couponCode;
    private String expirationDate;
    private String affiliateUrl;
    private String vendorLogoUrl;
    private double vendorCommission;
    private String label;
    private boolean ownersBenefit;

    public Deal(long couponId) {
        this.couponId = couponId;
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
