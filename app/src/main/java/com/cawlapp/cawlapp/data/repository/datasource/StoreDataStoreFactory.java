package com.cawlapp.cawlapp.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cawlapp.cawlapp.data.cache.StoreCache;
import com.cawlapp.cawlapp.data.entity.mapper.DealEntityJsonMapper;
import com.cawlapp.cawlapp.data.entity.mapper.StoreEntityJsonMapper;
import com.cawlapp.cawlapp.data.net.DealRestApi;
import com.cawlapp.cawlapp.data.net.DealRestApiImpl;
import com.cawlapp.cawlapp.data.net.StoreRestApi;
import com.cawlapp.cawlapp.data.net.StoreRestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StoreDataStoreFactory {
    private final Context context;
    private final StoreCache cache;

    @Inject
    public StoreDataStoreFactory(@NonNull Context context, @NonNull StoreCache cache) {
        this.context = context.getApplicationContext();
        this.cache = cache;
    }
    public StoreDataStore create(long vendorId) {
        StoreDataStore dataStore;

        if (!this.cache.isExpired() && this.cache.isCached(vendorId)) {
            dataStore = new DiskStoreDataStore(this.cache);
        } else {
            dataStore = createCloudDataStore();
        }

        return dataStore;
    }

    public StoreDataStore createList() {
        StoreDataStore dataStore;

        if (!this.cache.isExpired() && this.cache.isListCached()) {
            dataStore = new DiskStoreDataStore(this.cache);
        } else {
            dataStore = createCloudDataStore();
        }

        return dataStore;
    }

    public StoreDataStore createCloudDataStore() {
        StoreEntityJsonMapper storeEntityJsonMapper = new StoreEntityJsonMapper();
        StoreRestApi restApi = new StoreRestApiImpl(this.context, storeEntityJsonMapper);

        return new CloudStoreDataStore(restApi, this.cache);
    }
}
