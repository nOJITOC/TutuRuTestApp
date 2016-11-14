package com.marukhin.tuturuapp.core.network;

import com.marukhin.tuturuapp.core.network.res.DestinationRes;


import retrofit2.http.GET;
import rx.Observable;

public interface RestService {

    @GET("allStations.json")
    Observable<DestinationRes> getCities();
}
