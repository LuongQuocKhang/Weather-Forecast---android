package com.example.luongquockhang.weatherforecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView Temp;
    Button btnSearch;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Temp = (TextView)findViewById(R.id.TextViewtemp);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        editText = (EditText)findViewById(R.id.editText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,weather_information.class);
                intent.putExtra("data",editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
