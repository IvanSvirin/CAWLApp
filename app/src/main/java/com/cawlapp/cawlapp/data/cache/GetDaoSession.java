package com.cawlapp.cawlapp.data.cache;


import android.content.Context;


import com.cawlapp.cawlapp.data.entity.DaoMaster;
import com.cawlapp.cawlapp.data.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetDaoSession {
    /**
     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
     */
    public static final boolean ENCRYPTED = false;
    private DaoSession daoSession;

    @Inject
    public GetDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, ENCRYPTED ? "cash-db-encrypted" : "cash-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
