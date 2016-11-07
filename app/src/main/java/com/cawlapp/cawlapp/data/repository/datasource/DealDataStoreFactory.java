package com.cawlapp.cawlapp.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cawlapp.cawlapp.data.cache.DealCache;
import com.cawlapp.cawlapp.data.entity.mapper.DealEntityJsonMapper;
import com.cawlapp.cawlapp.data.net.DealRestApi;
import com.cawlapp.cawlapp.data.net.DealRestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link DealDataStore}.
 */
@Singleton
public class DealDataStoreFactory {
    private final Context context;
    private final DealCache cache;

    @Inject
    public DealDataStoreFactory(@NonNull Context context, @NonNull DealCache cache) {
        this.context = context.getApplicationContext();
        this.cache = cache;
    }

    /**
     * Create {@link DealDataStore} from a deal urlKey.
     */
    public DealDataStore create(long couponId) {
        DealDataStore dataStore;

        if (!this.cache.isExpired() && this.cache.isCached(couponId)) {
            dataStore = new DiskDealDataStore(this.cache);
        } else {
            dataStore = createCloudDataStore();
        }

        return dataStore;
    }

    public DealDataStore createList() {
        DealDataStore dataStore;

        if (!this.cache.isExpired() && this.cache.isListCached()) {
            dataStore = new DiskDealDataStore(this.cache);
        } else {
            dataStore = createCloudDataStore();
        }

        return dataStore;
    }

    /**
     * Create {@link DealDataStore} to retrieve data from the Cloud.
     */
    public DealDataStore createCloudDataStore() {
        DealEntityJsonMapper dealEntityJsonMapper = new DealEntityJsonMapper();
        DealRestApi restApi = new DealRestApiImpl(this.context, dealEntityJsonMapper);

        return new CloudDealDataStore(restApi, this.cache);
    }

}
