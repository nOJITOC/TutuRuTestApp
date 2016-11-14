package com.marukhin.tuturuapp.core.network.res;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DestinationRes {
    @SerializedName("citiesFrom")
    @Expose
    public List<CityRes> from;
    @SerializedName("citiesTo")
    @Expose
    public List<CityRes> to;
}
