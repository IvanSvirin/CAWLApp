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
import com.cawlapp.cawlapp.domain.model.Deal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link DealEntity} (in the data layer) to {@link Deal} in the
 * domain layer.
 */
@Singleton
public class DealEntityDataMapper {

  @Inject
  public DealEntityDataMapper() {}

  /**
   * Transform a {@link DealEntity} into an {@link Deal}.
   *
   * @param dealEntity Object to be transformed.
   * @return {@link Deal} if valid {@link DealEntity} otherwise null.
   */
  public Deal transform(DealEntity dealEntity) {
    Deal deal = null;
    if (dealEntity != null) {
      deal = new Deal(dealEntity.getCouponId());
      deal.setCouponId(dealEntity.getCouponId());
      deal.setVendorId(dealEntity.getVendorId());
      deal.setCouponType(dealEntity.getCouponType());
      deal.setRestrictions(dealEntity.getRestrictions());
      deal.setCouponCode(dealEntity.getCouponCode());
      deal.setExpirationDate(dealEntity.getExpirationDate());
      deal.setAffiliateUrl(dealEntity.getAffiliateUrl());
      deal.setVendorLogoUrl(dealEntity.getVendorLogoUrl());
      deal.setVendorCommission(dealEntity.getVendorCommission());
      deal.setLabel(dealEntity.getLabel());
      deal.setOwnersBenefit(dealEntity.isOwnersBenefit());
    }
    return deal;
  }

  /**
   * Transform a List of {@link DealEntity} into a Collection of {@link Deal}.
   *
   * @param dealEntityCollection Object Collection to be transformed.
   * @return {@link Deal} if valid {@link DealEntity} otherwise null.
   */
  public List<Deal> transform(Collection<DealEntity> dealEntityCollection) {
    List<Deal> dealList = new ArrayList<>(20);
    Deal deal;
    for (DealEntity dealEntity : dealEntityCollection) {
      deal = transform(dealEntity);
      if (deal != null) {
        dealList.add(deal);
      }
    }

    return dealList;
  }
}
