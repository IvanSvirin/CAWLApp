package com.cawlapp.cawlapp.presentation;

import android.app.Application;

import com.cawlapp.cawlapp.BuildConfig;
import com.cawlapp.cawlapp.data.entity.DaoMaster;
import com.cawlapp.cawlapp.data.entity.DaoSession;
import com.cawlapp.cawlapp.presentation.internal.di.components.ApplicationComponent;
import com.cawlapp.cawlapp.presentation.internal.di.components.DaggerApplicationComponent;
import com.cawlapp.cawlapp.presentation.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import org.greenrobot.greendao.database.Database;

/**
 * Android Main Application
 */

public class AndroidApplication extends Application{
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
 }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
