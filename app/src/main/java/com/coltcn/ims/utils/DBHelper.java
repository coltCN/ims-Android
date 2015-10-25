package com.coltcn.ims.utils;

import android.content.Context;

import com.coltcn.ims.dao.DaoMaster;
import com.coltcn.ims.dao.DaoSession;

import de.greenrobot.dao.async.AsyncSession;

/**
 * Created by majfc on 2015/10/24.
 */
public class DBHelper {
    private static DBHelper INSTANCE = null;

    private static final String DB_NAME = "ims.db";
    private DaoSession daoSession;
    private AsyncSession asyncSession;

    private DBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
    }

    public static DBHelper getInstanc(Context context){
        if (INSTANCE == null){
            INSTANCE = new DBHelper(context);
        }
        return INSTANCE;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public AsyncSession getAsyncSession() {
        return asyncSession;
    }
}
