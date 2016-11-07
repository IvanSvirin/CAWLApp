package com.cawlapp.cawlapp.domain.repository;

import com.cawlapp.cawlapp.domain.model.Deal;
import com.cawlapp.cawlapp.domain.model.Store;

import java.util.List;

import rx.Observable;

public interface StoreRepository {
    Observable<List<Store>> stores();

    Observable<Store> store(final long vendorId);

}
