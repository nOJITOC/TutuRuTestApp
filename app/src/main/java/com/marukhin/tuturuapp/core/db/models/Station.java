package com.marukhin.tuturuapp.core.db.models;

import com.marukhin.tuturuapp.core.network.res.StationRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "STATION")
public class Station {
    @Id
    Long id;

    private String countryTitle;
    private String regionTitle;
    private String cityTitle;
    private Long cityRemoteId;
    private String stationTitle;
    private Float longitude;
    private Float latitude;

    public Station(StationRes stationRes) {
        id = stationRes.stationId;
        countryTitle = stationRes.countryTitle;
        regionTitle = stationRes.regionTitle;
        cityTitle = stationRes.cityTitle;
        stationTitle = stationRes.stationTitle;
        cityRemoteId = stationRes.cityId;
        longitude = stationRes.mPointRes.longitude;
        latitude = stationRes.mPointRes.latitude;

    }


    @Generated(hash = 1993731686)
    public Station(Long id, String countryTitle, String regionTitle,
                   String cityTitle, Long cityRemoteId, String stationTitle,
                   Float longitude, Float latitude) {
        this.id = id;
        this.countryTitle = countryTitle;
        this.regionTitle = regionTitle;
        this.cityTitle = cityTitle;
        this.cityRemoteId = cityRemoteId;
        this.stationTitle = stationTitle;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    @Generated(hash = 833410198)
    public Station() {
    }


    @Override
    public String toString() {
        return countryTitle + ", " + cityTitle + ", " + stationTitle;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCountryTitle() {
        return this.countryTitle;
    }


    public void setCountryTitle(String countryTitle) {
        this.countryTitle = countryTitle;
    }


    public String getRegionTitle() {
        return this.regionTitle;
    }


    public void setRegionTitle(String regionTitle) {
        this.regionTitle = regionTitle;
    }


    public String getCityTitle() {
        return this.cityTitle;
    }


    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }


    public Long getCityRemoteId() {
        return this.cityRemoteId;
    }


    public void setCityRemoteId(Long cityRemoteId) {
        this.cityRemoteId = cityRemoteId;
    }


    public String getStationTitle() {
        return this.stationTitle;
    }


    public void setStationTitle(String stationTitle) {
        this.stationTitle = stationTitle;
    }


    public Float getLongitude() {
        return this.longitude;
    }


    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }


    public Float getLatitude() {
        return this.latitude;
    }


    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }


}
