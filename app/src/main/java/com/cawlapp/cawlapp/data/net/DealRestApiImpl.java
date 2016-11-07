package com.cawlapp.cawlapp.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.mapper.DealEntityJsonMapper;
import com.cawlapp.cawlapp.data.exception.NetworkConnectionException;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;

/**
 * {@link DealRestApi} implementation for retrieving data from the network.
 */

public class DealRestApiImpl implements DealRestApi {
    private final Context context;
    private final DealEntityJsonMapper entityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context          {@link android.content.Context}.
     * @param entityJsonMapper {@link DealEntityJsonMapper}.
     */
    public DealRestApiImpl(Context context, DealEntityJsonMapper entityJsonMapper) {
        if (context == null || entityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.entityJsonMapper = entityJsonMapper;
    }

    @RxLogObservable
    @Override
    public Observable<List<DealEntity>> dealEntityList() {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseDealEntities = getDealEntitiesFromApi();
                    if (responseDealEntities != null) {
                        subscriber.onNext(entityJsonMapper.transformDealEntityCollection(responseDealEntities));
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<DealEntity> dealEntityByCouponId(long couponId) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseDealByUrlKey = getDealByIDFromApi(couponId);
                    if (responseDealByUrlKey != null) {
                        subscriber.onNext(entityJsonMapper.transformDealEntity(responseDealByUrlKey));
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    subscriber.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                subscriber.onError(new NetworkConnectionException());
            }
        });
    }

    private String getDealEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(context.getString(R.string.api_base_url) + API_URL_GET_DEAL_LIST).requestSyncCall();
    }

    //not used
    private String getDealByIDFromApi(long couponId) throws MalformedURLException {
        return ApiConnection.createGET(context.getString(R.string.api_base_url) + API_URL_GET_DEAL).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}
