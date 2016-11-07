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
package com.cawlapp.cawlapp.data.net;


import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.data.entity.DealEntity;

import java.util.List;

import rx.Observable;

/**
 * DealRestApi for retrieving data from the network.
 */
public interface DealRestApi {
  String API_BASE_URL = "https://app.iconsumer.com/rest/icon/api/v1/";
//  String API_BASE_URL = "http://dev.sitetalkcashback.com/api/v3/6/";

  /** Api url for getting all stores */
  String API_URL_GET_DEAL_LIST = "merchants/coupons/featured/";
//  String API_URL_GET_DEAL_LIST = API_BASE_URL + "get_hot_offers/11/all/999";
  /** Api url for getting a user profile: Remember to concatenate id + 'json' */
  String API_URL_GET_DEAL = "";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link DealEntity}.
   */
  Observable<List<DealEntity>> dealEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link DealEntity}.
   *
   * @param couponId The deal urlKey used to get deal data.
   */
  Observable<DealEntity> dealEntityByCouponId(final long couponId);
}
