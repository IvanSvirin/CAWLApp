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
import com.cawlapp.cawlapp.presentation.internal.di.PerActivity;
import com.cawlapp.cawlapp.presentation.model.DealModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Deal} (in the domain layer) to {@link DealModel} in the
 * presentation layer.
 */
@PerActivity
public class DealModelDataMapper {

  @Inject
  public DealModelDataMapper() {}

  /**
   * Transform a {@link Deal} into an {@link DealModel}.
   *
   * @param deal Object to be transformed.
   * @return {@link DealModel}.
   */
  public DealModel transform(Deal deal) {
    if (deal == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }

    DealModel dealModel = new DealModel(deal.getCouponId());
    dealModel.setCouponId(deal.getCouponId());
    dealModel.setVendorId(deal.getVendorId());
    dealModel.setCouponType(deal.getCouponType());
    dealModel.setRestrictions(deal.getRestrictions());
    dealModel.setCouponCode(deal.getCouponCode());
    dealModel.setExpirationDate(deal.getExpirationDate());
    dealModel.setAffiliateUrl(deal.getAffiliateUrl());
    dealModel.setVendorLogoUrl(deal.getVendorLogoUrl());
    dealModel.setVendorCommission(deal.getVendorCommission());
    dealModel.setLabel(deal.getLabel());
    dealModel.setOwnersBenefit(deal.isOwnersBenefit());
    return dealModel;
  }

  /**
   * Transform a Collection of {@link Deal} into a Collection of {@link DealModel}.
   *
   * @param dealCollection Objects to be transformed.
   * @return List of {@link DealModel}.
   */
  public Collection<DealModel> transform(Collection<Deal> dealCollection) {
    Collection<DealModel> dealModelCollection;

    if (dealCollection != null && !dealCollection.isEmpty()) {
      dealModelCollection = new ArrayList<>();
      for (Deal deal : dealCollection) {
        dealModelCollection.add(transform(deal));
      }
    } else {
      dealModelCollection = Collections.emptyList();
    }
    return dealModelCollection;
  }
}
