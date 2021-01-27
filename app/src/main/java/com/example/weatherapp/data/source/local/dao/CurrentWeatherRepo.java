package com.example.weatherapp.data.source.local.dao;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.data.source.local.DatabaseClient;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CurrentWeatherRepo {

    private CurrentWeatherDao currentWeatherDao;
    private String TAG = CurrentWeatherRepo.class.getName();
    private MutableLiveData newlyDotId = new MutableLiveData<>();

    /**
     * @param application
     */
    public CurrentWeatherRepo(Context application) {
        DatabaseClient db = DatabaseClient.getInstance(application);
        currentWeatherDao = db.getAppDatabase().currentWeatherDao();
    }

    /**
     * Insert or Update current weather
     * @param currentWeather
     */
    public void insert(CurrentWeather currentWeather) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<CurrentWeather> itemsFromDB =  currentWeatherDao.getCityById(currentWeather.getId());
            if (itemsFromDB.isEmpty())
                currentWeatherDao.insert(currentWeather);
            else{
                currentWeatherDao.update(currentWeather);
            }
        });

    }

    /**
     * @return Fetch saved weather
     */
    public LiveData<List<CurrentWeather>> fetchSavedWeather() {
        return currentWeatherDao.getCurrentSavedWeather();
    }

    /**
     * Fetch all saved city on sorting order
     * @return
     */
    public LiveData<List<CurrentWeather>> getFavouriteCitiesList(){
        return currentWeatherDao.getFavCities(true);
    }

    /**
     * Add city as a favourite
     * @param id
     */
    public void setCityAsFav(long id) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            List<CurrentWeather> cwList =  currentWeatherDao.getCityById(id);
            if (!cwList.isEmpty()) {
                CurrentWeather currentWeather = cwList.get(0);
                currentWeather.setFavourite(!currentWeather.isFavourite());
                currentWeatherDao.update(currentWeather);
            }
        });
    }
}
