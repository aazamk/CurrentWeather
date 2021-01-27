package com.example.weatherapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.data.source.local.dao.CurrentWeatherRepo;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;

import java.util.List;

public class FavouriteCitiesViewModel extends AndroidViewModel {

    public static final String TAG = "CurrentWeaterViewModel";
    private final CurrentWeatherRepo currentWeatherRepo;
    public LiveData<List<CurrentWeather>> getCurrentWeatherFromDB = new MutableLiveData<>();


    public FavouriteCitiesViewModel(@NonNull Application application) {
        super(application);
        currentWeatherRepo = new CurrentWeatherRepo(application);
    }

    public void getWeatherLocally() {
        getCurrentWeatherFromDB = currentWeatherRepo.getFavouriteCitiesList();

    }
}
