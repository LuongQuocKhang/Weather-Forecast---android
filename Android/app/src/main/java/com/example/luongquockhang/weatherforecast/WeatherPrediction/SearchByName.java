package com.example.luongquockhang.weatherforecast.WeatherPrediction;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luongquockhang.weatherforecast.R;
import com.example.luongquockhang.weatherforecast.utils.WeatherHttpClient;

public class SearchByName extends AppCompatActivity {

    Button btnSearch;
    EditText edittextName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        btnSearch = (Button)findViewById(R.id.buttonSearch);
        edittextName = (EditText)findViewById(R.id.EditTextSearchName);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAvaiable check = new CheckAvaiable();
                check.execute(edittextName.getText().toString());
            }
        });
    }
    private class CheckAvaiable extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            WeatherHttpClient check = new WeatherHttpClient();
            return check.getCurrentWeatherData(edittextName.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ( s != null )
            {
                Intent intent = new Intent(SearchByName.this,weatherForecastDaily.class);
                intent.putExtra("data",edittextName.getText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(SearchByName.this, "khong tim thay", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
