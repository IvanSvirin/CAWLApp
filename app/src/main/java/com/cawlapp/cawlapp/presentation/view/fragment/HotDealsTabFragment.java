package com.cawlapp.cawlapp.presentation.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.presentation.internal.di.components.DealComponent;
import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.cawlapp.cawlapp.presentation.presenter.DealListPresenter;
import com.cawlapp.cawlapp.presentation.view.DealsListView;
import com.cawlapp.cawlapp.presentation.view.adapter.CommonLayoutManager;
import com.cawlapp.cawlapp.presentation.view.adapter.DealAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HotDealsTabFragment extends BaseFragment implements DealsListView{
    /**
     * Interface for listening deal list events.
     */
    public interface DealListListener {
        void onDealClicked(final DealModel dealModel);
    }

    @Inject
    DealListPresenter dealListPresenter;
    @Inject
    DealAdapter dealAdapter;

    @Bind(R.id.rv_deals)
    RecyclerView rv_deals;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;

    private DealListListener dealListListener;


    public static HotDealsTabFragment newInstance() {
        return new HotDealsTabFragment();
    }

    public HotDealsTabFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DealListListener) {
            this.dealListListener = (DealListListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(DealComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_deal_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.dealListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadDealList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.dealListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.dealListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rv_deals.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.dealListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.dealListPresenter = null;
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderDealsList(Collection<DealModel> dealModelCollection) {
        if (dealModelCollection != null) {
            this.dealAdapter.setDealsCollection(dealModelCollection);
        }
    }

    public void viewDeal(DealModel dealModel) {
        if (this.dealListListener != null) {
            this.dealListListener.onDealClicked(dealModel);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        this.dealAdapter.setOnItemClickListener(onItemClickListener);
        this.rv_deals.setLayoutManager(new CommonLayoutManager(context()));
        this.rv_deals.setAdapter(dealAdapter);
    }

    /**
     * Loads all users.
     */
    private void loadDealList() {
        this.dealListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry) void onButtonRetryClick() {
        HotDealsTabFragment.this.loadDealList();
    }

    private DealAdapter.OnItemClickListener onItemClickListener =
            new DealAdapter.OnItemClickListener() {
                public void onDealItemClicked(DealModel dealModel) {
                    if (HotDealsTabFragment.this.dealListPresenter != null && dealModel != null) {
                        HotDealsTabFragment.this.dealListPresenter.onDealClicked(dealModel);
                    }
                }
            };
}
