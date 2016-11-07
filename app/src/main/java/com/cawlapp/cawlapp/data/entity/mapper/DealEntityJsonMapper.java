/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cawlapp.cawlapp.data.entity.mapper;

import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.daoconverter.DealType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class DealEntityJsonMapper {
    private final Gson gson;

    @Inject
    public DealEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link DealEntity}.
     *
     * @param dealJsonResponse A json representing a user profile.
     * @return {@link DealEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public DealEntity transformDealEntity(String dealJsonResponse) throws JSONException {
        try {
            DealEntity dealEntity = new DealEntity();
            JSONObject jsonObject = new JSONObject(dealJsonResponse);
            dealEntity.setCouponId(jsonObject.getLong("coupon_id"));
            dealEntity.setVendorId(jsonObject.getLong("vendor_id"));
            dealEntity.setCouponType(jsonObject.getString("coupon_type"));
            dealEntity.setRestrictions(jsonObject.getString("restrictions"));
            dealEntity.setCouponCode(jsonObject.getString("coupon_code"));
            dealEntity.setExpirationDate(jsonObject.getString("expiration_date"));
            dealEntity.setAffiliateUrl(jsonObject.getString("affiliate_url"));
            dealEntity.setVendorLogoUrl(jsonObject.getString("vendor_logo_url"));
            dealEntity.setVendorCommission(jsonObject.getDouble("vendor_commission"));
            dealEntity.setLabel(jsonObject.getString("label"));
            dealEntity.setOwnersBenefit(jsonObject.getBoolean("owners_benefit"));
            dealEntity.setDealType(DealType.TEXT);
            return dealEntity;
        } catch (JSONException jsonException) {
            throw jsonException;
        }
//        try {
//            Type dealEntityType = new TypeToken<DealEntity>() {
//            }.getType();
//            DealEntity storeEntity = this.gson.fromJson(dealJsonResponse, dealEntityType);
//
//            return storeEntity;
//        } catch (JsonSyntaxException jsonException) {
//            throw jsonException;
//        }
    }

    /**
     * Transform from valid json string to List of {@link DealEntity}.
     *
     * @param dealListJsonResponse A json representing a collection of stores.
     * @return List of {@link DealEntity}.
     * @throws JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<DealEntity> transformDealEntityCollection(String dealListJsonResponse) throws JSONException {

        List<DealEntity> dealEntityCollection = new ArrayList<>();
        DealEntity dealEntity;
        try {
            JSONArray jsonArray = new JSONArray(dealListJsonResponse);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                dealEntity = new DealEntity();
                dealEntity.setCouponId(jsonObject.getLong("coupon_id"));
                dealEntity.setVendorId(jsonObject.getLong("vendor_id"));
                dealEntity.setCouponType(jsonObject.getString("coupon_type"));
                dealEntity.setRestrictions(jsonObject.getString("restrictions"));
                dealEntity.setCouponCode(jsonObject.getString("coupon_code"));
                dealEntity.setExpirationDate(jsonObject.getString("expiration_date"));
                dealEntity.setAffiliateUrl(jsonObject.getString("affiliate_url"));
                dealEntity.setVendorLogoUrl(jsonObject.getString("vendor_logo_url"));
                dealEntity.setVendorCommission(jsonObject.getDouble("vendor_commission"));
                dealEntity.setLabel(jsonObject.getString("label"));
                dealEntity.setOwnersBenefit(jsonObject.getBoolean("owners_benefit"));
                dealEntity.setDealType(DealType.TEXT);
                dealEntityCollection.add(dealEntity);
            }
            return dealEntityCollection;
        } catch (JSONException jsonException) {
            throw jsonException;
        }
//    try {
//      Type listOfUserEntityType = new TypeToken<List<DealEntity>>() {}.getType();
//      dealEntityCollection = this.gson.fromJson(dealListJsonResponse, listOfUserEntityType);
//
//      return dealEntityCollection;
//    } catch (JsonSyntaxException jsonException) {
//      throw jsonException;
//    }
    }
}
