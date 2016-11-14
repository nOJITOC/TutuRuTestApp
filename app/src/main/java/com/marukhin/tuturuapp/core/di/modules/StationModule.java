package com.marukhin.tuturuapp.core.di.modules;

import com.marukhin.tuturuapp.station.StationModel;
import com.marukhin.tuturuapp.station.StationPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class StationModule {
    StationPresenter presenter;
    @Provides
    StationModel provideStationModel() {
        return new StationModel();
    }

    @Provides
    StationPresenter provideStationPresenter() {
        if(presenter==null)
            presenter = new StationPresenter();
        return presenter;
    }

}
