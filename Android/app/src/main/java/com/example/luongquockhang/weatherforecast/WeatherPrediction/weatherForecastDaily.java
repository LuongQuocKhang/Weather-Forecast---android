package com.example.luongquockhang.weatherforecast.WeatherPrediction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.luongquockhang.weatherforecast.Model.DailyWeather;
import com.example.luongquockhang.weatherforecast.R;
import com.example.luongquockhang.weatherforecast.utils.OpenWeatherMapAPI;
import com.example.luongquockhang.weatherforecast.utils.WeatherHttpClient;
import com.example.luongquockhang.weatherforecast.utils.weatherAdapter;

import org.json.JSONException;

import java.util.List;

public class weatherForecastDaily extends AppCompatActivity {

    ListView listview;
    weatherAdapter adapter;
    List<DailyWeather> WeatherArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast_daily);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String location = intent.getStringExtra("data");

        ListWeatherAsyncTask listweather = new ListWeatherAsyncTask();
        listweather.execute(location);
    }

    private class ListWeatherAsyncTask extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... location) {


            listview = (ListView) findViewById(R.id.listViewitem);

            String data = (new WeatherHttpClient()).getDailyweather(location[0]);

            try {
                WeatherArray = OpenWeatherMapAPI.getWeatheritem(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new weatherAdapter(weatherForecastDaily.this,R.layout.dailyforecast,WeatherArray);
            listview.setAdapter(adapter);
        }
    }
}
