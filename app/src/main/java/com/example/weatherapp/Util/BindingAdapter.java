package com.example.weatherapp.Util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.weatherapp.R;
import com.github.pwittchen.weathericonview.WeatherIconView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BindingAdapter {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    @androidx.databinding.BindingAdapter({"setIcon"})
    public static void addObservationsItems(WeatherIconView weatherIconView, String condition) {
        getIconResources(weatherIconView.getContext(), weatherIconView, condition);
    }

    @androidx.databinding.BindingAdapter({"setFavIcon"})
    public static void addObservationsItems(ImageView favIcon, boolean isFav) {
        favIcon.setImageResource(isFav ? R.drawable.ic_favorite_fill : R.drawable.ic_favorite);
    }

    @androidx.databinding.BindingAdapter({"setTemperature"})
    public static void setTemp(TextView tvTemp, Double temp) {
        DecimalFormat df = new DecimalFormat("#");
        tvTemp.setText(df.format(temp) + "" + tvTemp.getContext().getString(R.string.temp_symbol_celsius));
    }

    @androidx.databinding.BindingAdapter({"setSunriseTime"})
    public static void setSunrise(TextView tvTemp, long time) {

        if (time > 0)
            tvTemp.setText("Rise : " + getTime(time));
    }

    @androidx.databinding.BindingAdapter({"setSunsetTime"})
    public static void setSunset(TextView tvTemp, long time) {

        if (time > 0)
            tvTemp.setText("Set :  " + getTime(time));
    }


    @androidx.databinding.BindingAdapter({"setTime"})
    public static void setTime(TextView tvTemp, long timeStamp) {
        tvTemp.setText("Updated: " + getTimeAgo(timeStamp));
    }


    public static String getTime(long date) {
        Log.d("TimeUtils", "getTime: " + date);
        long milliseconds = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();

            /* debug: is it local time? */
            Log.d("Time zone: ", tz.getDisplayName());

            /* date formatter in local timezone */
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            sdf.setTimeZone(tz);

            /* print your timestamp and double check it's the date you expect */

            String localTime = sdf.format(new Date(date * 1000)); // I assume your timestamp is in seconds and you're converting to milliseconds?
            Log.d("Time: ", localTime);
            return localTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTimeAgo(long date) {
        Log.d("TimeUtils", "getTimeAgo: " + date);
        long milliseconds = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            String localTime = df.format(new Date(date));
            Date d = df.parse(localTime);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("TimeUtils", "getTimeAgo: " + milliseconds);

        long now = System.currentTimeMillis();
        if (milliseconds > now || milliseconds <= 0) {
            return "sec ago";
        }


        final long diff = now - milliseconds;
        if (diff < MINUTE_MILLIS) {
            return diff / SECOND_MILLIS + " seconds ago";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1 minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else {
            return df.format(date);
        }


    }

    public static void getIconResources(@NonNull Context context, @NonNull WeatherIconView iconView, @NonNull String condition) {

        if (condition != null) {
            if (condition.equalsIgnoreCase("rain")) {
                iconView.setIconResource(context.getString(R.string.wi_rain));
            } else if (condition.equalsIgnoreCase("snow")) {
                iconView.setIconResource(context.getString(R.string.wi_snow));
            } else if (condition.equalsIgnoreCase("sun")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sunny));
            } else if (condition.equalsIgnoreCase("cloud")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_cloudy));
            } else if (condition.equalsIgnoreCase("Clear")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_clear));
            } else if (condition.equalsIgnoreCase("Overcast")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sunny_overcast));
            } else if (condition.equalsIgnoreCase("sleet")) {
                iconView.setIconResource(context.getString(R.string.wi_day_sleet_storm));
            } else if (condition.equalsIgnoreCase("Mist")) {
                iconView.setIconResource(context.getString(R.string.wi_fog));
            } else if (condition.equalsIgnoreCase("drizzle")) {
                iconView.setIconResource(context.getString(R.string.wi_raindrops));
            } else if (condition.equalsIgnoreCase("thunderstorm")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_tstorms));
            } else if (condition.equalsIgnoreCase("Thunder")) {
                iconView.setIconResource(context.getString(R.string.wi_thunderstorm));
            } else if (condition.equalsIgnoreCase("Cloudy")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_cloudy));
            } else if (condition.equalsIgnoreCase("Fog")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_fog));
            } else if (condition.equalsIgnoreCase("Sunny")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_mostlysunny));
            } else if (condition.equalsIgnoreCase("Blizzard")) {
                iconView.setIconResource(context.getString(R.string.wi_snow_wind));
            } else if (condition.equalsIgnoreCase("Ice")) {
                iconView.setIconResource(context.getString(R.string.wi_wu_chancesnow));
            } else if (condition.equalsIgnoreCase("ice")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_snow));
            } else if (condition.equalsIgnoreCase("Rain")) {
                iconView.setIconResource(context.getString(R.string.wi_rain_wind));
            } else if (condition.equalsIgnoreCase("wind")) {
                iconView.setIconResource(context.getString(R.string.wi_windy));
            } else if (condition.equalsIgnoreCase("Wind")) {
                iconView.setIconResource(context.getString(R.string.wi_strong_wind));
            } else if (condition.equalsIgnoreCase("storm")) {
                iconView.setIconResource(context.getString(R.string.wi_storm_warning));
            } else if (condition.equalsIgnoreCase("Storm")) {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_thunderstorm));
            } else if (condition.equalsIgnoreCase("thunder")) {
                iconView.setIconResource(context.getString(R.string.wi_day_snow_thunderstorm));
            } else {
                iconView.setIconResource(context.getString(R.string.wi_forecast_io_partly_cloudy_day));
            }
        }
    }


}
