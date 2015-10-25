package com.coltcn.ims;

import android.app.Application;

import com.coltcn.ims.dao.DaoMaster;
import com.coltcn.ims.dao.DaoSession;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import de.greenrobot.dao.async.AsyncSession;


/**
 * @author andyiac
 * @date 15-8-4
 * @web http://blog.andyiac.com/
 */
public class ClientApplication extends Application  {

    private static final String LOGGER_TAG = "<<=IMS=>>";
    private static final String DB_NAME = "ims";

    public DaoSession daoSession;
    public AsyncSession asyncSession;

    public void onCreate() {
        super.onCreate();
        initStetho();
        initLogger();
        initDB();
    }


    private void initLogger() {
        Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
                .setMethodCount(3)            // default 2
                .setLogLevel(LogLevel.FULL)   // default LogLevel.FULL
                .setMethodOffset(2);          // default 0
//              .hideThreadInfo()             // default shown
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    private void initDB(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());

        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
    }

}
