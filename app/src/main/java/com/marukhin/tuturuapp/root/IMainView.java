package com.marukhin.tuturuapp.root;

import com.marukhin.tuturuapp.core.db.models.City;
import com.marukhin.tuturuapp.core.layers.view.IView;

import java.util.Date;
import java.util.List;


public interface IMainView extends IView {

    void chooseStation(Long stationId);
    void chooseDate(Date time);

    void showCities(List<City> cities);
    void showStation(long stationId);
    void showDatePicker();

    void showMessage(String message);
    void showLoad();
    void updateLoad(Integer currentCityCount, Integer cityCount);



}
