package com.cawlapp.cawlapp.domain.repository;

import com.cawlapp.cawlapp.domain.model.Deal;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a DealRepository for getting {} related data.
 */

public interface DealRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Deal}.
     */
    Observable<List<Deal>> deals();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Deal}.
     *
     * @param couponId The deal urlKey used to retrieve deal data.
     */
    Observable<Deal> deal(final long couponId);

}
