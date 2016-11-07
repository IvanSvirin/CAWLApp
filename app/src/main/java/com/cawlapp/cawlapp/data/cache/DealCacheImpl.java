package com.cawlapp.cawlapp.data.cache;

import android.content.Context;

import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.DealEntityDao;
import com.cawlapp.cawlapp.data.exception.DealNotFoundException;
import com.cawlapp.cawlapp.domain.executor.ThreadExecutor;

import org.greenrobot.greendao.query.Query;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link DealCache} implementation.
 */
@Singleton
public class DealCacheImpl implements DealCache {
    private static final String SETTINGS_FILE_NAME = "deal_settings";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "deal_last_cache_update";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;
    private final Context context;
    private final File cacheDir;
    private final PreferencesManager fileManager;
    private final GetDaoSession getDaoSession;
    private final ThreadExecutor threadExecutor;

    /**
     * Constructor of the class {@link DealCacheImpl}.
     * @param context
     */
    @Inject
    public DealCacheImpl(Context context, PreferencesManager fileManager, GetDaoSession getDaoSession, ThreadExecutor executor) {
        if (context == null || fileManager == null || getDaoSession == null || executor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.fileManager = fileManager;
        this.getDaoSession = getDaoSession;
        this.threadExecutor = executor;
    }

    @Override
    public Observable<DealEntity> get(long couponId) {
        return Observable.create(subscriber -> {
            DealEntityDao dealEntityDao = getDaoSession.getDaoSession().getDealEntityDao();
            Query<DealEntity> query = dealEntityDao.queryBuilder().where(DealEntityDao.Properties.CouponId.eq(couponId)).build();
            DealEntity dealEntity = query.list().get(0);
            if (dealEntity != null) {
                subscriber.onNext(dealEntity);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new DealNotFoundException());
            }
        });
    }

    @Override
    public void put(DealEntity entity) {
        if (entity != null) {
            if (!isCached(entity.getCouponId())) {
                DealEntityDao dealEntityDao = getDaoSession.getDaoSession().getDealEntityDao();
                dealEntityDao.insert(entity);
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(long couponId) {
        if (getDaoSession.getDaoSession().getDealEntityDao().count() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();
        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);
        if (expired) {
            this.evictAll();
        }
        return expired;
    }

    @Override
    public void evictAll() {
        this.executeAsynchronously(new DealCacheImpl.CacheEvictor(this.fileManager, this.cacheDir));
    }

    @Override
    public Observable<List<DealEntity>> getList() {
        return Observable.create(subscriber -> {
            DealEntityDao dealEntityDao = getDaoSession.getDaoSession().getDealEntityDao();
            Query<DealEntity> query = dealEntityDao.queryBuilder().orderAsc(DealEntityDao.Properties.CouponId).build();
            List<DealEntity> dealEntityList = query.list();
            if (dealEntityList != null) {
                subscriber.onNext(dealEntityList);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new DealNotFoundException());
            }
        });
    }

    @Override
    public void putList(List<DealEntity> dealEntityList) {
        if (dealEntityList != null) {
            if (!isListCached()) {
                DealEntityDao dealEntityDao = getDaoSession.getDaoSession().getDealEntityDao();
                for (DealEntity dealEntity : dealEntityList) {
                    dealEntityDao.insert(dealEntity);
                }
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isListCached() {
        if (getDaoSession.getDaoSession().getDealEntityDao().count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
                SETTINGS_KEY_LAST_CACHE_UPDATE);
    }

    /**
     * Executes a {@link Runnable} in another Thread.
     *
     * @param runnable {@link Runnable} to execute
     */
    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }

    /**
     * {@link Runnable} class for evicting all the cached files
     */
    private static class CacheEvictor implements Runnable {
        private final PreferencesManager fileManager;
        private final File cacheDir;

        CacheEvictor(PreferencesManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
        }
    }
}
