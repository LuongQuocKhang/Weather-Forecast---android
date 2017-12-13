package com.example.luongquockhang.weatherforecast.WeatherPrediction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.luongquockhang.weatherforecast.R;
import com.example.luongquockhang.weatherforecast.utils.WeatherAsyncTask;

public class weather_information extends AppCompatActivity {

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_information);

        btnBack = (Button)findViewById(R.id.button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        WeatherAsyncTask Task = new WeatherAsyncTask(weather_information.this,data);
        Task.execute();
    }
}
