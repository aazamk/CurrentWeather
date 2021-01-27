package com.example.weatherapp.data.source.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currentWeather_table")
public class CurrentWeather {
    @PrimaryKey
    private long id;

    @ColumnInfo(name = "temp")
    private double temp;

    @ColumnInfo(name = "humidity")
    private int humidity;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "main")
    private String main;

    @ColumnInfo(name = "weatherId")
    private int weatherId;

    @ColumnInfo(name = "windDeg")
    private double windDeg;

    @ColumnInfo(name = "windSpeed")
    private double windSpeed;

    @ColumnInfo(name = "storeTimestamp")
    private long storeTimestamp;

    @ColumnInfo(name = "pressure")
    private double pressure;
    @ColumnInfo(name = "cityName")
    private String cityName;

    @ColumnInfo(name = "sunrise")
    private long sunrise;

    @ColumnInfo(name = "sunset")
    private long sunset;

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @ColumnInfo(name = "isFavourite")
    private boolean isFavourite;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @ColumnInfo(name = "countryName")
    private String countryName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
