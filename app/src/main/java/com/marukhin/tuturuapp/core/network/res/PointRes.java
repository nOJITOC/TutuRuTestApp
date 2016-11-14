package com.marukhin.tuturuapp.core.network.res;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointRes {

    @SerializedName("longitude")
    @Expose
    public Float longitude;
    @SerializedName("latitude")
    @Expose
    public Float latitude;

}