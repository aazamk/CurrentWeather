package com.example.weatherapp.data.source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weatherapp.data.source.local.entity.CurrentWeather;

import java.util.List;

@Dao
public interface CurrentWeatherDao {
    @Insert
    public void insert(CurrentWeather currentWeather);

    @Update
    void update(CurrentWeather currentWeather);

    @Query("DELETE FROM currentWeather_table")
    public int clearDotTable();

    @Query("SELECT * from currentWeather_table WHERE id= :id")
    List<CurrentWeather> getCityById(long id);


    @Query("SELECT * from currentWeather_table ORDER BY storeTimestamp DESC")
    LiveData<List<CurrentWeather>> getCurrentSavedWeather();

    @Query("SELECT * from currentWeather_table WHERE isFavourite =:isFav ORDER BY storeTimestamp DESC")
    LiveData<List<CurrentWeather>> getFavCities(boolean isFav);

}
