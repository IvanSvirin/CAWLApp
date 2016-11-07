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
package com.cawlapp.cawlapp.data.repository.datasource;


import com.cawlapp.cawlapp.data.cache.DealCache;
import com.cawlapp.cawlapp.data.entity.DealEntity;

import java.util.List;

import rx.Observable;

/**
 * {@link DealDataStore} implementation based on file system data store.
 */
class DiskDealDataStore implements DealDataStore {

  private final DealCache cache;

  /**
   * Construct a {@link DealDataStore} based file system data store.
   *
   * @param cache A {@link DealCache} to cache data retrieved from the api.
   */
  DiskDealDataStore(DealCache cache) {
    this.cache = cache;
  }

  @Override
  public Observable<List<DealEntity>> dealEntityList() {
    return this.cache.getList();
  }

  @Override
  public Observable<DealEntity> dealEntity(long couponId) {
    return this.cache.get(couponId);
  }
}