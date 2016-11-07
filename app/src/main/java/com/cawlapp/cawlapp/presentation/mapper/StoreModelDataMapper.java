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
package com.cawlapp.cawlapp.presentation.mapper;

import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.domain.model.Store;
import com.cawlapp.cawlapp.presentation.internal.di.PerActivity;
import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.cawlapp.cawlapp.presentation.model.StoreModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

@PerActivity
public class StoreModelDataMapper {

  @Inject
  public StoreModelDataMapper() {}

  public StoreModel transform(Store store) {
    if (store == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    StoreModel storeModel = new StoreModel(store.getVendorId());
    storeModel.setVendorId(store.getVendorId());
    storeModel.setName(store.getName());
    storeModel.setCommission(store.getCommission());
    storeModel.setExceptionInfo(store.getExceptionInfo());
    storeModel.setDescription(store.getDescription());
    storeModel.setGiftCard(store.isGiftCard());
    storeModel.setFavorite(store.isFavorite());
    storeModel.setAffiliateUrl(store.getAffiliateUrl());
    storeModel.setLogoUrl(store.getLogoUrl());
    storeModel.setOwnersBenefit(store.isOwnersBenefit());
    return storeModel;
  }

  public Collection<StoreModel> transform(Collection<Store> storeCollection) {
    Collection<StoreModel> storeModelCollection;

    if (storeCollection != null && !storeCollection.isEmpty()) {
      storeModelCollection = new ArrayList<>();
      for (Store store : storeCollection) {
        storeModelCollection.add(transform(store));
      }
    } else {
      storeModelCollection = Collections.emptyList();
    }
    return storeModelCollection;
  }
}
