package com.marukhin.tuturuapp.root;

import android.content.Context;
import android.databinding.Bindable;

import com.marukhin.tuturuapp.R;
import com.marukhin.tuturuapp.core.App;
import com.marukhin.tuturuapp.core.db.models.City;
import com.marukhin.tuturuapp.core.db.models.Destination;
import com.marukhin.tuturuapp.core.db.models.Station;
import com.marukhin.tuturuapp.core.layers.presenter.Presenter;
import com.marukhin.tuturuapp.core.network.res.CityRes;
import com.marukhin.tuturuapp.core.network.res.StationRes;
import com.marukhin.tuturuapp.core.utils.NetworkStatusChecker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;


public class MainPresenter extends Presenter<IMainView> {
    @Inject
    MainModel mModel;
    @Inject
    Context mContext;
    private int state = ViewState.IDLE;
    private String fromText = "";
    private String toText = "";

    private OnStationClickListener mListener = stationId -> getView().showStation(stationId);


    //region =========================Presenter==============================

    @Override
    public void initView() {
        App.getAppComponent().inject(this);
        if (!mModel.isDbComplete()) {
            if (NetworkStatusChecker.isNetworkAvailable(mContext)) {
                saveCitiesToDb();
                getView().showLoad();
            } else {
                getView().showMessage(mContext.getString(R.string.network_error));
            }
        }
        setCities();

    }

    private void saveCitiesToDb() {
        mModel.getCities()
                .subscribeOn(Schedulers.io())
                .cache()
                .map(destinationRes -> {
                    int cityCount = (destinationRes.from.size() + destinationRes.to.size());
                    int currentCityCount = -1;
                    List<City> cities = new ArrayList<City>();
                    List<Destination> destinations = new ArrayList<Destination>();
                    List<Station> stations = new ArrayList<Station>();
                    for (CityRes cityRes : destinationRes.from) {
                        City city = new City(cityRes);
                        cities.add(city);
                        for (StationRes station : cityRes.mStationRes) {
                            stations.add(new Station(station));
                        }
                        destinations.add(new Destination((long) currentCityCount, ViewState.FROM, city.getId()));
                        getView().updateLoad(++currentCityCount, cityCount);
                    }
                    for (CityRes cityRes : destinationRes.to) {
                        City city = new City(cityRes);
                        cities.add(city);
                        for (StationRes station : cityRes.mStationRes) {
                            stations.add(new Station(station));
                        }
                        destinations.add(new Destination((long) currentCityCount, ViewState.TO, city.getId()));
                        getView().updateLoad(++currentCityCount, cityCount);
                    }
                    mModel.saveAllStations(stations);
                    mModel.saveCities(cities);
                    mModel.saveDestinations(destinations);
                    getView().updateLoad(++currentCityCount, cityCount);
                    return true;
                })
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        mModel.setDbComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getView().updateLoad(2, 1);
                        getView().showMessage(mContext.getString(R.string.save_in_db_error));
                    }

                    @Override
                    public void onNext(Boolean cond) {
                    }
                });
    }

    private void setCities() {
        switch (state) {
            case ViewState.FROM:
                setCities(fromText);
                break;
            case ViewState.TO:
                setCities(toText);
                break;
        }
    }

    private void setCities(String query) {
        getView().showCities(mModel.getCities(query, state));
    }


    public String getStationTitle(Long stationId) {
        return mModel.getStation(stationId).toString();
    }

    public interface OnStationClickListener {
        void onStationClick(Long stationId);
    }

    public OnStationClickListener getStationClickListener() {
        return mListener;
    }

    public String convertDateToFormatedString(Date date) {

        Date now = new Date();
        if (now.getTime() > date.getTime()) {
            date = now;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault());
        return dateFormat.format(date);
    }


    //endregion

    //region =========================DataBinding==============================

    public void onFromClick() {
        setFromState();
        setCities(fromText);
    }

    public void onToClick() {
        setToState();
        setCities(toText);
    }

    public void onDateClick() {
        getView().showDatePicker();
    }

    @Bindable
    public boolean isIdleViewState() {
        return state == ViewState.IDLE;
    }

    @Bindable
    public boolean isFromViewState() {
        return state == ViewState.FROM;
    }

    @Bindable
    public boolean isToViewState() {
        return state == ViewState.TO;
    }

    public void setIdleState() {
        state = ViewState.IDLE;
        notifyChange();
    }


    public void setFromState() {
        state = ViewState.FROM;
        notifyChange();
    }

    public void setToState() {
        state = ViewState.TO;
        notifyChange();
    }

    @Bindable
    public String getFromText() {
        return fromText;
    }

    public void setFromText(String fromText) {
        this.fromText = fromText;
        setCities(fromText);
        notifyChange();
    }

    @Bindable
    public String getToText() {
        return toText;
    }

    public void setToText(String toText) {
        this.toText = toText;
        setCities(toText);
        notifyChange();
    }


    //endregion


}
