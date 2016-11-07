package com.cawlapp.cawlapp.data.cache;

import com.cawlapp.cawlapp.data.entity.DealEntity;

import java.util.List;

import rx.Observable;

/**
 * An interface representing a DealCache.
 */
public interface DealCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link DealEntity}.
     *
     * @param couponId  It's id to retrieve data.
     */
    Observable<DealEntity> get(final long couponId);

    /**
     * Puts and element into the cache.
     *
     * @param entity Element to insert in the cache.
     */
    void put(DealEntity entity);

    /**
     * Checks if an element exists in the cache.
     *
     * @param couponId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final long couponId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();



    Observable<List<DealEntity>> getList();

    void putList(List<DealEntity> dealEntityList);

    boolean isListCached();

}
