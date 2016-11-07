package com.cawlapp.cawlapp.presentation.internal.di.modules;

import android.content.Context;

import com.cawlapp.cawlapp.data.cache.DealCache;
import com.cawlapp.cawlapp.data.cache.DealCacheImpl;
import com.cawlapp.cawlapp.data.executor.JobExecutor;
import com.cawlapp.cawlapp.data.repository.DealDataRepository;
import com.cawlapp.cawlapp.domain.executor.PostExecutionThread;
import com.cawlapp.cawlapp.domain.executor.ThreadExecutor;
import com.cawlapp.cawlapp.domain.repository.DealRepository;
import com.cawlapp.cawlapp.presentation.AndroidApplication;
import com.cawlapp.cawlapp.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    DealCache provideCache(DealCacheImpl cache) {
        return cache;
    }

    @Provides
    @Singleton
    DealRepository provideRepository(DealDataRepository dataRepository) {
        return dataRepository;
    }
}
