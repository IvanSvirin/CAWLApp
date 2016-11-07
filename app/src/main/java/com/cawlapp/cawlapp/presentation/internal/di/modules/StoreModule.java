/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cawlapp.cawlapp.presentation.internal.di.modules;


import com.cawlapp.cawlapp.domain.executor.PostExecutionThread;
import com.cawlapp.cawlapp.domain.executor.ThreadExecutor;
import com.cawlapp.cawlapp.domain.interactor.GetDealDetails;
import com.cawlapp.cawlapp.domain.interactor.GetDealList;
import com.cawlapp.cawlapp.domain.interactor.GetStoreDetails;
import com.cawlapp.cawlapp.domain.interactor.GetStoreList;
import com.cawlapp.cawlapp.domain.interactor.UseCase;
import com.cawlapp.cawlapp.domain.repository.DealRepository;
import com.cawlapp.cawlapp.domain.repository.StoreRepository;
import com.cawlapp.cawlapp.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class StoreModule {
    private long vendorId = -1;

    public StoreModule() {
    }

    public StoreModule(long vendorId) {
        this.vendorId = vendorId;
    }

    @Provides
    @PerActivity
    @Named("storeList")
    UseCase provideGetStoreListUseCase(GetStoreList getStoreList) {
        return getStoreList;
    }

    @Provides
    @PerActivity
    @Named("storeDetails")
    UseCase provideGetStoreDetailsUseCase(StoreRepository storeRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetStoreDetails(vendorId, storeRepository, threadExecutor, postExecutionThread);
    }
}