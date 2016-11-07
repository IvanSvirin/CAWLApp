package com.cawlapp.cawlapp.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cawlapp.cawlapp.R;
import com.cawlapp.cawlapp.data.entity.StoreEntity;
import com.cawlapp.cawlapp.data.entity.mapper.StoreEntityJsonMapper;
import com.cawlapp.cawlapp.data.exception.NetworkConnectionException;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;

public class StoreRestApiImpl implements StoreRestApi {
    private final Context context;
    private final StoreEntityJsonMapper entityJsonMapper;

    public StoreRestApiImpl(Context context, StoreEntityJsonMapper storeEntityJsonMapper) {
        if (context == null || storeEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.entityJsonMapper = storeEntityJsonMapper;
    }

    @RxLogObservable
    @Override
    public Observable<List<StoreEntity>> storeEntityList() {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseStoreEntities = getStoreEntitiesFromApi();
                    if (responseStoreEntities != null) {
                        subscriber.onNext(entityJsonMapper.transformStoreEntityCollection(responseStoreEntities));
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
    public Observable<StoreEntity> storeEntityByVendorId(long vendorId) {
        return Observable.create(subscriber -> {
            if (isThereInternetConnection()) {
                try {
                    String responseStoreByVendorId = getStoreByVendorIdFromApi(vendorId);
                    if (responseStoreByVendorId != null) {
                        subscriber.onNext(entityJsonMapper.transformStoreEntity(responseStoreByVendorId));
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

    private String getStoreEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(context.getString(R.string.api_base_url) + API_URL_GET_STORE_LIST).requestSyncCall();
    }

    //not used
    private String getStoreByVendorIdFromApi(long couponId) throws MalformedURLException {
        return ApiConnection.createGET(context.getString(R.string.api_base_url)).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}
