package com.marukhin.tuturuapp.core.managers;





import com.marukhin.tuturuapp.core.db.DatabaseManager;

import com.marukhin.tuturuapp.core.db.models.DaoSession;
import com.marukhin.tuturuapp.core.network.RestService;
import com.marukhin.tuturuapp.core.network.res.DestinationRes;
import com.marukhin.tuturuapp.core.App;

import javax.inject.Inject;

import rx.Observable;

public class DataManager {

    @Inject
    PreferencesManager mPreferencesManager;
    @Inject
    RestService mRestService;
    @Inject
    DatabaseManager mDatabaseManager;




    public DataManager() {
        App.getAppComponent().inject(this);
    }

    public void setDbComplete(){
        mPreferencesManager.getSharedPreferences().edit()
                .putBoolean(PreferencesManager.DB_COMPLETE,true)
                .apply();
    }

    public boolean isDbComplete(){
        return mPreferencesManager.getSharedPreferences().getBoolean(PreferencesManager.DB_COMPLETE,false);
    }

    public DaoSession getDaoSession(){
        return mDatabaseManager.getDaoSession();
    }

    public Observable<DestinationRes> getCitiesFromNetwork(){
        return  mRestService.getCities();
    }

}


