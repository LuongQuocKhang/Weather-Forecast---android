package com.example.luongquockhang.weatherforecast.WeatherPrediction;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Luong Quoc Khang on 11/13/2017.
 */

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {
    private Activity context;
    private Marker marker = null;
    private OpenWeatherJSON openWeatherJSON = null;
    Bitmap bitmap = null;
    NumberFormat format = new DecimalFormat("#0.0");
    private double latitude;
    private double longitude;

    public InfoAdapter(Activity activity)
    {
        this.context = activity;
    }

    public InfoAdapter(Activity context, Marker marker, OpenWeatherJSON weather, Bitmap bitmap) {
        this.context = context;
        this.marker = marker;
        this.openWeatherJSON = weather;
        this.bitmap = bitmap;
    }

    public InfoAdapter(Activity context, Marker marker, OpenWeatherJSON weather, Bitmap bitmap, double latitude, double longitude) {
        this.context = context;
        this.marker = marker;
        this.openWeatherJSON = weather;
        this.bitmap = bitmap;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = this.context.getLayoutInflater().inflate(R.layout.info_window,null);
        TextView CurrentLocation,Temperature,Max_temp,Min_temp,Wind,Pressure,Humidity,Clouds,Sunrise,Description,SunSet;
        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        CurrentLocation = (TextView)view.findViewById(R.id.textViewLocation);
        Temperature = (TextView)view.findViewById(R.id.textViewTemp);
        Max_temp = (TextView)view.findViewById(R.id.textViewMaxtemp);
        Min_temp = (TextView)view.findViewById(R.id.textViewMinTemp);
        Wind = (TextView)view.findViewById(R.id.textViewWind);
        Pressure = (TextView)view.findViewById(R.id.textViewPressure);
        Humidity = (TextView)view.findViewById(R.id.textViewHumidity);
        Clouds = (TextView)view.findViewById(R.id.textViewClouds);
        Sunrise = (TextView)view.findViewById(R.id.textViewSunrise);
        Description = (TextView)view.findViewById(R.id.textviewDescription);
        SunSet = (TextView)view.findViewById(R.id.txtSunset);

        CurrentLocation.setText(openWeatherJSON.getName());
        Temperature.setText( String.valueOf(openWeatherJSON.getMain().getTemp()) + "°C");
        Max_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_max()) + "°C");
        Min_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_min()) + "°C");
        Wind.setText(String.valueOf(openWeatherJSON.getWind().getSpeed()) + "m/s");
        Pressure.setText(String.valueOf(openWeatherJSON.getMain().getPressure()) + "hpa");
        Humidity .setText(String.valueOf(openWeatherJSON.getMain().getHumidity()) + "%");
        Clouds.setText(openWeatherJSON.getListWeather().get(0).getMain());

        Date TimeSunrise = new Date((long) (openWeatherJSON.getSys().getSunrise()*1000));
        Sunrise.setText(TimeSunrise.getHours() + " : " + TimeSunrise.getMinutes());

        Date TimeSunSet = new Date((long) openWeatherJSON.getSys().getSunset()*1000);
        String TimeSS = TimeSunSet.getHours() + " : " + TimeSunSet.getMinutes();
        SunSet.setText(TimeSS);


        Description.setText(openWeatherJSON.getListWeather().get(0).getDesciption());

        image.setImageBitmap(bitmap);

        try
        {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this.context, Locale.getDefault());
            addresses = geocoder.getFromLocation(latitude,longitude,1);

            if ( addresses.size() > 0)
            {
                Address address = addresses.get(0);
                if ( address != null )
                {
                    CurrentLocation.setText(address.getAddressLine(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.setBackgroundColor(Color.WHITE);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
