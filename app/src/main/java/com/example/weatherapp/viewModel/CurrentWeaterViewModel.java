package com.example.weatherapp.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.data.model.CurrentWeatherResponse;
import com.example.weatherapp.data.source.local.dao.CurrentWeatherRepo;
import com.example.weatherapp.data.source.local.entity.CurrentWeather;
import com.example.weatherapp.data.source.repository.WeatherRepository;

import java.util.List;

public class CurrentWeaterViewModel extends AndroidViewModel {

    public static final String TAG = "CurrentWeaterViewModel";
    private final CurrentWeatherRepo currentWeatherRepo;
    public LiveData<CurrentWeatherResponse> currentWeatherResponseMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CurrentWeather>> getCurrentWeatherFromDB = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public CurrentWeaterViewModel(@NonNull Application application) {
        super(application);
         currentWeatherRepo = new CurrentWeatherRepo(application);
    }

    public void getCurrentWeather(String query) {
        currentWeatherResponseMutableLiveData = WeatherRepository.getInstance().getCurrentWeather(query);

    }

    public CurrentWeather preparCurrentWeatherData(CurrentWeatherResponse response) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemp(response.getMain().getTemp());
        currentWeather.setId(response.getId());
        currentWeather.setHumidity(response.getMain().getHumidity());
        currentWeather.setDescription(response.getWeather().get(0).getDescription());
        currentWeather.setMain(response.getWeather().get(0).getMain());
        currentWeather.setWeatherId(response.getWeather().get(0).getId());
        currentWeather.setWindDeg(response.getWind().getDeg());
        currentWeather.setWindSpeed(response.getWind().getSpeed());
        currentWeather.setPressure(response.getMain().getPressure());
        currentWeather.setCountryName(response.getSys().getCountry());
        currentWeather.setCityName((response.getName()));
        currentWeather.setSunrise(response.getSys().getSunrise());
        currentWeather.setSunset(response.getSys().getSunset());
        currentWeather.setFavourite(false);
        currentWeather.setStoreTimestamp(System.currentTimeMillis());


        currentWeatherRepo.insert(currentWeather);
        return currentWeather;

    }

    public void getWeatherLocally(){
        getCurrentWeatherFromDB =  currentWeatherRepo.fetchSavedWeather();
    }

    public void setAsFavourite(long id) {
        Log.d(TAG, "setAsFavourite: ");
        currentWeatherRepo.setCityAsFav(id);
    }
}
