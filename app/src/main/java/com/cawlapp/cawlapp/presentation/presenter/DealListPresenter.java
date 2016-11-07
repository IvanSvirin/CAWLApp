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
package com.cawlapp.cawlapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.cawlapp.cawlapp.domain.exception.DefaultErrorBundle;
import com.cawlapp.cawlapp.domain.exception.ErrorBundle;
import com.cawlapp.cawlapp.domain.interactor.DefaultSubscriber;
import com.cawlapp.cawlapp.domain.interactor.UseCase;
import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.presentation.exception.ErrorMessageFactory;
import com.cawlapp.cawlapp.presentation.internal.di.PerActivity;
import com.cawlapp.cawlapp.presentation.mapper.DealModelDataMapper;
import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.cawlapp.cawlapp.presentation.view.DealsListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class DealListPresenter implements Presenter {
    private DealsListView viewListView;

    private final UseCase getDealListUseCase;
    private final DealModelDataMapper dealModelDataMapper;

    @Inject
    public DealListPresenter(@Named("dealList") UseCase getDealListUseCase, DealModelDataMapper dealModelDataMapper) {
        this.getDealListUseCase = getDealListUseCase;
        this.dealModelDataMapper = dealModelDataMapper;
    }

    public void setView(@NonNull DealsListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getDealListUseCase.unsubscribe();
        this.viewListView = null;
    }

    /**
     * Initializes the presenter by start retrieving the deal list.
     */
    public void initialize() {
        this.loadDealList();
    }

    /**
     * Loads all products.
     */
    private void loadDealList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getDealList();
    }

    public void onDealClicked(DealModel dealModel) {
        this.viewListView.viewDeal(dealModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showDealsCollectionInView(Collection<Deal> dealCollection) {
        final Collection<DealModel> dealModelCollection = this.dealModelDataMapper.transform(dealCollection);
        this.viewListView.renderDealsList(dealModelCollection);
    }

    private void getDealList() {
        this.getDealListUseCase.execute(new DealListSubscriber());
    }

    private final class DealListSubscriber extends DefaultSubscriber<List<Deal>> {

        @Override
        public void onCompleted() {
            DealListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            DealListPresenter.this.hideViewLoading();
            DealListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            DealListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Deal> deals) {
            DealListPresenter.this.showDealsCollectionInView(deals);
        }
    }
}
