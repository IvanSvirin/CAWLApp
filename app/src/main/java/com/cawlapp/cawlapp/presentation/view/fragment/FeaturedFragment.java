package com.cawlapp.cawlapp.presentation.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.presentation.internal.di.HasComponent;
import com.cawlapp.cawlapp.presentation.internal.di.components.DealComponent;
import com.cawlapp.cawlapp.presentation.model.DealModel;
import com.daimajia.slider.library.SliderLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FeaturedFragment extends BaseFragment {
    @Nullable
    @Bind(R.id.fragment_feature_carousel_viewpager)
    SliderLayout mSlider;
    @Bind(R.id.fragment_feature_tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.fragment_feature_pager)
    ViewPager mTabPager;
    TabsPagerAdapter adapter;
    private List<DealModel> mCarouselStores = new ArrayList<>();


    public FeaturedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        ButterKnife.bind(this, view);

        if (!mCarouselStores.isEmpty()) {
            render(mCarouselStores);
        }

        adapter = new TabsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(HotDealsTabFragment.newInstance(), getActivity().getString(R.string.featured_tab_hot_deals).toUpperCase());
        adapter.addFragment(FavoritesTabFragment.newInstance(), getActivity().getString(R.string.featured_tab_favorites).toUpperCase());
        mTabPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mTabPager);

        return view;
    }

    private void render(List<DealModel> mCarouselStores) {
    }

    private class TabsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }
    }

}
