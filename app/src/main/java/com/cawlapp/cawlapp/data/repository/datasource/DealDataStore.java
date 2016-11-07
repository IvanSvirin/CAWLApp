package com.cawlapp.cawlapp.data.repository.datasource;

import com.cawlapp.cawlapp.data.entity.DealEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface DealDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link DealEntity}.
     */
    Observable<List<DealEntity>> dealEntityList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link DealEntity} by its urlKey.
     *
     * @param couponId The urlKey to retrieve user data.
     */
    Observable<DealEntity> dealEntity(final long couponId);

}
