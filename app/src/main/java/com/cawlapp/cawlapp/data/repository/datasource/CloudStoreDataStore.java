/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cawlapp.cawlapp.data.repository.datasource;



import com.cawlapp.cawlapp.data.cache.DealCache;
import com.cawlapp.cawlapp.data.cache.StoreCache;
import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.StoreEntity;
import com.cawlapp.cawlapp.data.net.DealRestApi;
import com.cawlapp.cawlapp.data.net.StoreRestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

class CloudStoreDataStore implements StoreDataStore {

  private final StoreRestApi restApi;
  private final StoreCache cache;

  private final Action1<StoreEntity> saveToCacheAction = storeEntity -> {
    if (storeEntity != null) {
      CloudStoreDataStore.this.cache.put(storeEntity);
    }
  };

  private final Action1<List<StoreEntity>> saveListToCacheAction = storeEntities -> {
    if (storeEntities != null) {
      CloudStoreDataStore.this.cache.putList(storeEntities);
    }
  };

  CloudStoreDataStore(StoreRestApi restApi, StoreCache cache) {
    this.restApi = restApi;
    this.cache = cache;
  }

  @Override
  public Observable<List<StoreEntity>> storeEntityList() {
    return this.restApi.storeEntityList().doOnNext(saveListToCacheAction);
  }

  @Override
  public Observable<StoreEntity> storeEntity(long vendorId) {
    return this.restApi.storeEntityByVendorId(vendorId).doOnNext(saveToCacheAction);
  }
}
