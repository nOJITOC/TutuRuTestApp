package com.marukhin.tuturuapp.core.network.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StationRes {

    @SerializedName("countryTitle")
    @Expose
    public String countryTitle;
    @SerializedName("point")
    @Expose
    public PointRes mPointRes;
    @SerializedName("districtTitle")
    @Expose
    public String districtTitle;
    @SerializedName("cityId")
    @Expose
    public Long cityId;
    @SerializedName("cityTitle")
    @Expose
    public String cityTitle;
    @SerializedName("regionTitle")
    @Expose
    public String regionTitle;
    @SerializedName("stationId")
    @Expose
    public Long stationId;
    @SerializedName("stationTitle")
    @Expose
    public String stationTitle;

}