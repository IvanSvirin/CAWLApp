package com.cawlapp.cawlapp.data.repository.datasource;

import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.StoreEntity;

import java.util.List;

import rx.Observable;

public interface StoreDataStore {
    Observable<List<StoreEntity>> storeEntityList();

    Observable<StoreEntity> storeEntity(final long vendorId);

}
