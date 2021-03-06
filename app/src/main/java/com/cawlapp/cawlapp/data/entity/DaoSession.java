package com.cawlapp.cawlapp.data.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.cawlapp.cawlapp.data.entity.DealEntity;
import com.cawlapp.cawlapp.data.entity.StoreEntity;

import com.cawlapp.cawlapp.data.entity.DealEntityDao;
import com.cawlapp.cawlapp.data.entity.StoreEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dealEntityDaoConfig;
    private final DaoConfig storeEntityDaoConfig;

    private final DealEntityDao dealEntityDao;
    private final StoreEntityDao storeEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dealEntityDaoConfig = daoConfigMap.get(DealEntityDao.class).clone();
        dealEntityDaoConfig.initIdentityScope(type);

        storeEntityDaoConfig = daoConfigMap.get(StoreEntityDao.class).clone();
        storeEntityDaoConfig.initIdentityScope(type);

        dealEntityDao = new DealEntityDao(dealEntityDaoConfig, this);
        storeEntityDao = new StoreEntityDao(storeEntityDaoConfig, this);

        registerDao(DealEntity.class, dealEntityDao);
        registerDao(StoreEntity.class, storeEntityDao);
    }
    
    public void clear() {
        dealEntityDaoConfig.clearIdentityScope();
        storeEntityDaoConfig.clearIdentityScope();
    }

    public DealEntityDao getDealEntityDao() {
        return dealEntityDao;
    }

    public StoreEntityDao getStoreEntityDao() {
        return storeEntityDao;
    }

}
