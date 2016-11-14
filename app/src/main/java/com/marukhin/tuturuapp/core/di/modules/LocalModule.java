package com.marukhin.tuturuapp.core.di.modules;

import android.content.Context;

import com.marukhin.tuturuapp.core.db.DatabaseManager;
import com.marukhin.tuturuapp.core.managers.PreferencesManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class LocalModule {
    @Provides
    PreferencesManager providePreferencesManager(Context context) {
        return new PreferencesManager(context);
    }

    @Provides
    DatabaseManager provideDatabaseManager(Context context) {
        return new DatabaseManager(context);
    }
}
