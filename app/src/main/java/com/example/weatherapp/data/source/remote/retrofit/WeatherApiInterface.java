package com.example.weatherapp.data.source.remote.retrofit;

import com.example.weatherapp.data.model.CurrentWeatherResponse;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {
    @GET("/data/2.5/weather")
    Call<CurrentWeatherResponse> getSpecificWeather(@Query("q") String locationName,
                                                    @Query("units") String units,
                                                    @Query("lang") String lang,
                                                    @Query("appid") String appId);
}
