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
package com.cawlapp.cawlapp.domain.interactor;

import com.cawlapp.cawlapp.domain.executor.PostExecutionThread;
import com.cawlapp.cawlapp.domain.executor.ThreadExecutor;
import com.cawlapp.cawlapp.domain.repository.StoreRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetStoreList extends UseCase {
  private final StoreRepository storeRepository;

  @Inject
  public GetStoreList(StoreRepository storeRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.storeRepository = storeRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.storeRepository.stores();
  }
}
