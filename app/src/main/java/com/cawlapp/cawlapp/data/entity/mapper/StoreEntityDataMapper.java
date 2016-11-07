/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cawlapp.cawlapp.data.entity.mapper;


import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.StoreEntity;
import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.domain.model.Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StoreEntityDataMapper {

  @Inject
  public StoreEntityDataMapper() {}

  public Store transform(StoreEntity storeEntity) {
    Store store = null;
    if (storeEntity != null) {
      store = new Store(storeEntity.getVendorId());
      store.setVendorId(storeEntity.getVendorId());
      store.setName(storeEntity.getName());
      store.setCommission(storeEntity.getCommission());
      store.setExceptionInfo(storeEntity.getExceptionInfo());
      store.setDescription(storeEntity.getDescription());
      store.setGiftCard(storeEntity.getGiftCard());
      store.setFavorite(storeEntity.isFavorite());
      store.setAffiliateUrl(storeEntity.getAffiliateUrl());
      store.setLogoUrl(storeEntity.getLogoUrl());
      store.setOwnersBenefit(storeEntity.isOwnersBenefit());
    }
    return store;
  }

  public List<Store> transform(Collection<StoreEntity> storeEntityCollection) {
    List<Store> storeList = new ArrayList<>(20);
    Store store;
    for (StoreEntity storeEntity : storeEntityCollection) {
      store = transform(storeEntity);
      if (store != null) {
        storeList.add(store);
      }
    }
    return storeList;
  }
}
