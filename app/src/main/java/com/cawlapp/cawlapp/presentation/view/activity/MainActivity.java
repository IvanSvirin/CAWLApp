package com.cawlapp.cawlapp.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.data.executor.JobExecutor;
import com.cawlapp.cawlapp.domain.executor.ThreadExecutor;
import com.cawlapp.cawlapp.presentation.internal.di.HasComponent;
import com.cawlapp.cawlapp.presentation.internal.di.components.DaggerDealComponent;
import com.cawlapp.cawlapp.presentation.internal.di.components.DealComponent;
import com.cawlapp.cawlapp.presentation.view.fragment.AllStoresFragment;
import com.cawlapp.cawlapp.presentation.view.fragment.FeaturedFragment;
import com.cawlapp.cawlapp.presentation.view.fragment.HotDealsTabFragment;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HasComponent<DealComponent> {
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.navigator)
    NavigationView navigator;
    public static final int FRAGMENT_FEATURED = 0;
    public static final int FRAGMENT_ALL_STORES = 1;
    public static final int FRAGMENT_CATEGORIES = 2;
    public static final int FRAGMENT_TELL_A_FRIEND = 3;
    public static final int FRAGMENT_ACCOUNT = 4;
    private int currentItemId = 0;
    private ThreadExecutor threadExecutor;
    private DealComponent dealComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigator.setNavigationItemSelectedListener(this);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.content_frame, new FeaturedFragment());
        }
    }

    private void initializeInjector() {
        this.dealComponent = DaggerDealComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        int selectedItem = item.getItemId();

        if (selectedItem == currentItemId) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else {
            new JobExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    switch (item.getItemId()) {
                        case (R.id.item_featured):
                            currentItemId = FRAGMENT_FEATURED;
                            replaceFragment(R.id.content_frame, new FeaturedFragment());
                            break;
                        case (R.id.item_all_stores):
                            currentItemId = FRAGMENT_ALL_STORES;
                            replaceFragment(R.id.content_frame, new AllStoresFragment());
                            break;
                        case (R.id.item_categories):
                            currentItemId = FRAGMENT_CATEGORIES;
                            replaceFragment(R.id.content_frame, new FeaturedFragment());
                            break;
                        case (R.id.item_tell_a_friend):
                            currentItemId = FRAGMENT_TELL_A_FRIEND;
                            replaceFragment(R.id.content_frame, new FeaturedFragment());
                            break;
                        case (R.id.item_account):
                            currentItemId = FRAGMENT_ACCOUNT;
                            replaceFragment(R.id.content_frame, new FeaturedFragment());
                            break;
                    }
                }
            });
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }

    @Override
    public DealComponent getComponent() {
        return dealComponent;
    }
}
