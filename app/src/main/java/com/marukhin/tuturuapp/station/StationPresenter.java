package com.marukhin.tuturuapp.station;

import android.databinding.Bindable;

import com.marukhin.tuturuapp.core.App;
import com.marukhin.tuturuapp.core.db.models.Station;
import com.marukhin.tuturuapp.core.layers.presenter.Presenter;

import javax.inject.Inject;

public class StationPresenter extends Presenter<IStationView> {
    @Inject
    StationModel mModel;
    Station station;

    @Override
    public void initView() {
        App.getAppComponent().inject(this);
    }

    public void setStation(long station) {
        this.station = mModel.getStation(station);
        notifyChange();
    }

    @Bindable
    public Station getStation() {
        return station;
    }
}
