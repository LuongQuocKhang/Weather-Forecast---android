package com.example.luongquockhang.weatherforecast.utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.R;

import org.json.JSONException;

import java.util.Date;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */

public class WeatherAsyncTask extends AsyncTask<String,String,OpenWeatherJSON>
{
    Activity activity;
    public WeatherAsyncTask(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    protected OpenWeatherJSON doInBackground(String... params) {

        WeatherHttpClient http = new WeatherHttpClient();
        String data = http.getWeatherData(params[0]);

        OpenWeatherJSON JObject = null;
        try {
            JObject = OpenWeatherMapAPI.getWeather(data);
            return JObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(OpenWeatherJSON openWeatherJSON) {
        super.onPostExecute(openWeatherJSON);

        TextView CurrentLocation,Temperature,Max_temp,Min_temp,Wind,Pressure,Humidity,Clouds,Sunrise,Description;;
        CurrentLocation = (TextView)activity.findViewById(R.id.textViewLocation);
        Temperature = (TextView)activity.findViewById(R.id.textViewTemp);
        Max_temp = (TextView)activity.findViewById(R.id.textViewMaxtemp);
        Min_temp = (TextView)activity.findViewById(R.id.textViewMinTemp);
        Wind = (TextView)activity.findViewById(R.id.textViewWind);
        Pressure = (TextView)activity.findViewById(R.id.textViewPressure);
        Humidity = (TextView)activity.findViewById(R.id.textViewHumidity);
        Clouds = (TextView)activity.findViewById(R.id.textViewClouds);
        Sunrise = (TextView)activity.findViewById(R.id.textViewSunrise);
        Description = (TextView)activity.findViewById(R.id.textviewDescription);

        CurrentLocation.setText(openWeatherJSON.getName());
        Temperature.setText( String.valueOf(openWeatherJSON.getMain().getTemp()) + "°C");
        Max_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_max()) + "°C");
        Min_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_min()) + "°C");
        Wind.setText(String.valueOf(openWeatherJSON.getWind().getSpeed()) + "m/s");
        Pressure.setText(String.valueOf(openWeatherJSON.getMain().getPressure()) + "hpa");
        Humidity .setText(String.valueOf(openWeatherJSON.getMain().getHumidity()) + "%");
        Clouds.setText(openWeatherJSON.getWeather().getMain());

        Date TimeSunrise = new Date((long) openWeatherJSON.getSys().getSunrise());
        String Time = TimeSunrise.getHours() + " : " + TimeSunrise.getMinutes() + " AM";
        Sunrise.setText(Time);
        Description.setText(openWeatherJSON.getWeather().getDesciption());
    }
}
