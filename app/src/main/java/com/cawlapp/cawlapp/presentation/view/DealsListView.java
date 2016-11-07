/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.cawlapp.cawlapp.presentation.view;


import com.cawlapp.cawlapp.presentation.model.DealModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link DealModel}.
 */
public interface DealsListView extends LoadDataView {
  /**
   * Render a deal list in the UI.
   *
   * @param dealModelCollection The collection of {@link DealModel} that will be shown.
   */
  void renderDealsList(Collection<DealModel> dealModelCollection);

  /**
   * View a {@link DealModel} profile/details.
   *
   * @param dealModel The deal that will be shown.
   */
  void viewDeal(DealModel dealModel);
}
