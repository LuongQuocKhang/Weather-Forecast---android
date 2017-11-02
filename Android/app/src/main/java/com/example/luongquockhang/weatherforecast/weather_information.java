package com.example.luongquockhang.weatherforecast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.utils.OpenWeatherMapAPI;
import com.example.luongquockhang.weatherforecast.utils.WeatherHttpClient;

import org.json.JSONException;

import java.util.Date;

public class weather_information extends AppCompatActivity {

    TextView CurrentLocation,Temperature,Max_temp,Min_temp,Wind,Pressure,Humidity,Clouds,Sunrise,Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_information);


        CurrentLocation = (TextView)findViewById(R.id.textViewLocation);
        Temperature = (TextView)findViewById(R.id.textViewTemp);
        Max_temp = (TextView)findViewById(R.id.textViewMaxtemp);
        Min_temp = (TextView)findViewById(R.id.textViewMinTemp);
        Wind = (TextView)findViewById(R.id.textViewWind);
        Pressure = (TextView)findViewById(R.id.textViewPressure);
        Humidity = (TextView)findViewById(R.id.textViewHumidity);
        Clouds = (TextView)findViewById(R.id.textViewClouds);
        Sunrise = (TextView)findViewById(R.id.textViewSunrise);
        Description = (TextView)findViewById(R.id.textviewDescription);
    }
    protected void onResume() {

        super.onResume();
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        WeatherTask Task = new WeatherTask();
        Task.execute(data);
    }

    private class WeatherTask extends AsyncTask<String,String,OpenWeatherJSON>
    {
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

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(OpenWeatherJSON openWeatherJSON) {
            super.onPostExecute(openWeatherJSON);

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
}
