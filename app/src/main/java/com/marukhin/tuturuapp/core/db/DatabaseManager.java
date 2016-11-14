package com.marukhin.tuturuapp.core.db;



import android.content.Context;

import com.marukhin.tuturuapp.core.db.models.DaoMaster;
import com.marukhin.tuturuapp.core.db.models.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    private final DaoSession sDaoSession;

    public DatabaseManager(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "tuturu = db");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession(){
        return sDaoSession;
    }

}
