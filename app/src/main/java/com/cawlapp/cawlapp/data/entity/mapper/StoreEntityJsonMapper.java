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
import com.cawlapp.cawlapp.data.entity.StoreEntity;
import com.cawlapp.cawlapp.data.entity.daoconverter.DealType;
import com.cawlapp.cawlapp.data.entity.daoconverter.StoreType;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StoreEntityJsonMapper {
    private final Gson gson;

    @Inject
    public StoreEntityJsonMapper() {
        this.gson = new Gson();
    }

    public StoreEntity transformStoreEntity(String storeJsonResponse) throws JSONException {
        try {
            StoreEntity storeEntity = new StoreEntity();
            JSONObject jsonObject = new JSONObject(storeJsonResponse);
            storeEntity.setVendorId(jsonObject.getLong("vendor_id"));
            storeEntity.setName(jsonObject.getString("name"));
            storeEntity.setCommission(jsonObject.getDouble("commission"));
            storeEntity.setExceptionInfo(jsonObject.getString("exception_info"));
            storeEntity.setDescription(jsonObject.getString("description"));
            storeEntity.setGiftCard(jsonObject.getBoolean("gift_card"));
            storeEntity.setFavorite(jsonObject.getBoolean("is_favorite"));
            storeEntity.setAffiliateUrl(jsonObject.getString("affiliate_url"));
            storeEntity.setLogoUrl(jsonObject.getString("logo_url"));
            storeEntity.setOwnersBenefit(jsonObject.getBoolean("owners_benefit"));
            storeEntity.setStoreType(StoreType.TEXT);
            return storeEntity;
        } catch (JSONException jsonException) {
            throw jsonException;
        }
    }

    public List<StoreEntity> transformStoreEntityCollection(String storeListJsonResponse) throws JSONException {

        List<StoreEntity> dealEntityCollection = new ArrayList<>();
        StoreEntity storeEntity;
        try {
            JSONArray jsonArray = new JSONArray(storeListJsonResponse);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                storeEntity = new StoreEntity();
                storeEntity.setVendorId(jsonObject.getLong("vendor_id"));
                storeEntity.setName(jsonObject.getString("name"));
                storeEntity.setCommission(jsonObject.getDouble("commission"));
                storeEntity.setExceptionInfo(jsonObject.getString("exception_info"));
                storeEntity.setDescription(jsonObject.getString("description"));
                storeEntity.setGiftCard(jsonObject.getBoolean("gift_card"));
                storeEntity.setFavorite(jsonObject.getBoolean("is_favorite"));
                storeEntity.setAffiliateUrl(jsonObject.getString("affiliate_url"));
                storeEntity.setLogoUrl(jsonObject.getString("logo_url"));
                storeEntity.setOwnersBenefit(jsonObject.getBoolean("owners_benefit"));
                storeEntity.setStoreType(StoreType.TEXT);
                dealEntityCollection.add(storeEntity);
            }
            return dealEntityCollection;
        } catch (JSONException jsonException) {
            throw jsonException;
        }
    }
}
