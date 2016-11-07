package com.cawlapp.cawlapp.data.repository;

import com.cawlapp.cawlapp.data.entity.mapper.DealEntityDataMapper;
import com.cawlapp.cawlapp.data.repository.datasource.DealDataStore;
import com.cawlapp.cawlapp.data.repository.datasource.DealDataStoreFactory;
import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.domain.repository.DealRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link DealRepository} for retrieving data.
 */
@Singleton
public class DealDataRepository implements DealRepository {
    private final DealDataStoreFactory dataStoreFactory;
    private final DealEntityDataMapper entityDataMapper;

    /**
     * Constructs a {@link DealRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param entityDataMapper {@link DealEntityDataMapper}.
     */
    @Inject
    public DealDataRepository(DealDataStoreFactory dataStoreFactory, DealEntityDataMapper entityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.entityDataMapper = entityDataMapper;
    }

    @Override
    public Observable<List<Deal>> deals() {
        final DealDataStore dealDataStore = this.dataStoreFactory.createList();
        return dealDataStore.dealEntityList().map(this.entityDataMapper::transform);
    }

    @Override
    public Observable<Deal> deal(long couponId) {
        final DealDataStore dealDataStore = this.dataStoreFactory.create(couponId);
        return dealDataStore.dealEntity(couponId).map(this.entityDataMapper::transform);
    }
}
