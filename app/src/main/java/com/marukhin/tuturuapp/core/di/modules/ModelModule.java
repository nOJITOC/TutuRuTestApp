package com.marukhin.tuturuapp.core.di.modules;

import com.marukhin.tuturuapp.core.managers.DataManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    DataManager provideDataManager() {
        return new DataManager();
    }
}
