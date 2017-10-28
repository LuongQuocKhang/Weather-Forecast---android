package com.example.luongquockhang.weatherforecast;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.utils.OpenWeatherMapAPI;
import com.example.luongquockhang.weatherforecast.utils.WeatherHttpClient;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView Temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Temp = (TextView)findViewById(R.id.TextViewtemp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONweatherTask Task = new JSONweatherTask();
        Task.execute("hồ chí minh");
    }

    private class JSONweatherTask extends AsyncTask<String,String,OpenWeatherJSON>
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
            Temp.setText(openWeatherJSON.getWeather().getDesciption().toString());
        }
    }
}
