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
import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.net.DealRestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * {@link DealDataStore} implementation based on connections to the api (Cloud).
 */
class CloudDealDataStore implements DealDataStore {

  private final DealRestApi restApi;
  private final DealCache cache;

  private final Action1<DealEntity> saveToCacheAction = dealEntity -> {
    if (dealEntity != null) {
      CloudDealDataStore.this.cache.put(dealEntity);
    }
  };

  private final Action1<List<DealEntity>> saveListToCacheAction = dealEntities -> {
    if (dealEntities != null) {
      CloudDealDataStore.this.cache.putList(dealEntities);
    }
  };

  /**
   * Construct a {@link DealDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link DealRestApi} implementation to use.
   * @param cache A {@link DealCache} to cache data retrieved from the api.
   */
  CloudDealDataStore(DealRestApi restApi, DealCache cache) {
    this.restApi = restApi;
    this.cache = cache;
  }

  @Override
  public Observable<List<DealEntity>> dealEntityList() {
    return this.restApi.dealEntityList().doOnNext(saveListToCacheAction);
  }

  @Override
  public Observable<DealEntity> dealEntity(long couponId) {
    return this.restApi.dealEntityByCouponId(couponId).doOnNext(saveToCacheAction);
  }
}
