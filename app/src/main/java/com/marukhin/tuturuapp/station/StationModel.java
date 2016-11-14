package com.marukhin.tuturuapp.station;

import com.marukhin.tuturuapp.core.db.models.Station;
import com.marukhin.tuturuapp.core.layers.model.Model;


public class StationModel extends Model {

    public Station getStation(Long stationId){
        return mDataManager.getDaoSession().getStationDao().load(stationId);
    }
}
