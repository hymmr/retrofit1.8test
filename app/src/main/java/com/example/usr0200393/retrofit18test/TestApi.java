package com.example.usr0200393.retrofit18test;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TestApi {
    @GET("/forecast/webservice/json/v1")
    void getPinpointLocations(
            @Query("city") int cityNumber,
            Callback<Weather> cb
    );

}
