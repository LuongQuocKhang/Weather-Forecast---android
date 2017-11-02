package com.example.luongquockhang.weatherforecast.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.R;

import org.json.JSONException;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */

public class WeatherAsyncTask extends AsyncTask<String,String,OpenWeatherJSON>
{

    @Override
    protected OpenWeatherJSON doInBackground(String... params) {

        WeatherHttpClient http = new WeatherHttpClient();
        String data = http.getWeatherData(params[0]);

        OpenWeatherMapAPI object = new OpenWeatherMapAPI();
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

    }
}
