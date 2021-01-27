package com.example.weatherapp.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.data.source.local.dao.CurrentWeatherDao;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;

@Database(entities = {CurrentWeather.class}, version = 1, exportSchema =  false)
public abstract class WeatherAppDatabase extends RoomDatabase {
    public abstract CurrentWeatherDao currentWeatherDao();
}
