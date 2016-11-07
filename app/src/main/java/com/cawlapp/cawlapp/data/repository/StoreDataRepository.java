package com.cawlapp.cawlapp.data.repository;

import com.cawlapp.cawlapp.data.entity.mapper.DealEntityDataMapper;
import com.cawlapp.cawlapp.data.entity.mapper.StoreEntityDataMapper;
import com.cawlapp.cawlapp.data.repository.datasource.DealDataStore;
import com.cawlapp.cawlapp.data.repository.datasource.DealDataStoreFactory;
import com.cawlapp.cawlapp.data.repository.datasource.StoreDataStore;
import com.cawlapp.cawlapp.data.repository.datasource.StoreDataStoreFactory;
import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.domain.model.Store;
import com.cawlapp.cawlapp.domain.repository.DealRepository;
import com.cawlapp.cawlapp.domain.repository.StoreRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

@Singleton
public class StoreDataRepository implements StoreRepository {
    private final StoreDataStoreFactory dataStoreFactory;
    private final StoreEntityDataMapper entityDataMapper;

    @Inject
    public StoreDataRepository(StoreDataStoreFactory dataStoreFactory, StoreEntityDataMapper entityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.entityDataMapper = entityDataMapper;
    }

    @Override
    public Observable<List<Store>> stores() {
        final StoreDataStore storeDataStore = this.dataStoreFactory.createList();
        return storeDataStore.storeEntityList().map(this.entityDataMapper::transform);
    }

    @Override
    public Observable<Store> store(long vendorId) {
        final StoreDataStore storeDataStore = this.dataStoreFactory.create(vendorId);
        return storeDataStore.storeEntity(vendorId).map(this.entityDataMapper::transform);
    }
}
