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
package com.cawlapp.cawlapp.presentation.internal.di.components;



import com.cawlapp.cawlapp.presentation.internal.di.PerActivity;
import com.cawlapp.cawlapp.presentation.internal.di.modules.ActivityModule;
import com.cawlapp.cawlapp.presentation.internal.di.modules.DealModule;
import com.cawlapp.cawlapp.presentation.internal.di.modules.StoreModule;
import com.cawlapp.cawlapp.presentation.view.fragment.AllStoresFragment;
import com.cawlapp.cawlapp.presentation.view.fragment.HotDealsTabFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, StoreModule.class})
public interface StoreComponent extends ActivityComponent {
  void inject(AllStoresFragment allStoresFragment);
}
