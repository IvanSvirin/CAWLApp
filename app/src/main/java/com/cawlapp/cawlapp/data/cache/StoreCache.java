package com.cawlapp.cawlapp.data.cache;

import com.cawlapp.cawlapp.data.entity.StoreEntity;

import java.util.List;

import rx.Observable;

/**
 * An interface representing a StoreCache.
 */
public interface StoreCache {
    Observable<StoreEntity> get(final long vendorId);

    void put(StoreEntity entity);

    boolean isCached(final long vendorId);

    boolean isExpired();

    void evictAll();

    Observable<List<StoreEntity>> getList();

    void putList(List<StoreEntity> storeEntityList);

    boolean isListCached();

}
