package com.marukhin.tuturuapp.core.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager {
    static final String DB_COMPLETE = "DB_COMPLETE";
    private SharedPreferences mSharedPreferences;
    public PreferencesManager(Context context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}









