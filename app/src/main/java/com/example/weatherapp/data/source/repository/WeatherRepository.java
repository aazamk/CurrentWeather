package com.example.weatherapp.data.source.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.Util.Constant;
import com.example.weatherapp.data.model.CurrentWeatherResponse;
import com.example.weatherapp.data.source.remote.retrofit.WeatherApiClient;
import com.example.weatherapp.data.source.remote.retrofit.WeatherApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.weatherapp.Util.Constant.APP_ID;

public class WeatherRepository {
    private String TAG = getClass().getName();
    private static WeatherRepository repository;
    private WeatherApiInterface apiInterface ;

    public static WeatherRepository getInstance(){
        if (repository == null){
            repository = new WeatherRepository();
        }
        return repository;
    }

    public WeatherRepository(){
        apiInterface = WeatherApiClient.getClient().create(WeatherApiInterface.class);
    }

    public LiveData<CurrentWeatherResponse> getCurrentWeather(String cityName){
        MutableLiveData<CurrentWeatherResponse> responseMovieMutableLiveData = new MutableLiveData<>();
        apiInterface.getSpecificWeather(cityName, Constant.UNITS, "en",APP_ID).enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                Log.d(TAG, "onResponse: "+response.message());
                if (response.isSuccessful()){
                    responseMovieMutableLiveData.setValue(response.body());
                }else {
                    responseMovieMutableLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                responseMovieMutableLiveData.setValue(null);
            }
        });
        return responseMovieMutableLiveData;
    }
}