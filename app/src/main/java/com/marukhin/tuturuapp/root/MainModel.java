package com.marukhin.tuturuapp.root;


import com.marukhin.tuturuapp.core.db.models.City;
import com.marukhin.tuturuapp.core.db.models.CityDao;
import com.marukhin.tuturuapp.core.db.models.Destination;
import com.marukhin.tuturuapp.core.db.models.DestinationDao;
import com.marukhin.tuturuapp.core.db.models.Station;
import com.marukhin.tuturuapp.core.layers.model.Model;
import com.marukhin.tuturuapp.core.network.res.DestinationRes;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import rx.Observable;

public class MainModel extends Model {

    public Observable<DestinationRes> getCities() {
        return mDataManager.getCitiesFromNetwork();
    }


    public void saveAllStations(List<Station> stations) {
        mDataManager.getDaoSession().getStationDao().insertOrReplaceInTx(stations);
    }


    public void setDbComplete() {
        mDataManager.setDbComplete();
    }

    public boolean isDbComplete() {
        return mDataManager.isDbComplete();
    }

    public List<City> getCities(String query, int state) {
        QueryBuilder<City> queryBuilder = mDataManager.getDaoSession().queryBuilder(City.class);
        if (!query.isEmpty())
            queryBuilder = queryBuilder.where(CityDao.Properties.SearchTitle.like("%" + query.toUpperCase() + "%"));
        queryBuilder.join(Destination.class, DestinationDao.Properties.CityRemoteId)
                .where(DestinationDao.Properties.State.eq(state));
        return queryBuilder.list();
    }


    Station getStation(Long id) {
        return mDataManager.getDaoSession().getStationDao().load(id);
    }

    public void saveCities(List<City> cities) {
        mDataManager.getDaoSession().getCityDao().insertOrReplaceInTx(cities);
    }

    public void saveDestinations(List<Destination> destinations) {
        mDataManager.getDaoSession().getDestinationDao().insertOrReplaceInTx(destinations);
    }
}
