package com.example.luongquockhang.weatherforecast.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.R;
import com.example.luongquockhang.weatherforecast.WeatherPrediction.InfoAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */
public class WeatherAsyncTask extends AsyncTask<Void,Void,OpenWeatherJSON> {
    private Activity activity;
    private Bitmap mybitmap;
    private ProgressDialog dialog;
    private TypePrediction prediction;
    private String location;
    private double latitude;
    private double longitude;
    private NumberFormat format = new DecimalFormat("#0.0");

    Marker marker = null;
    GoogleMap googlemap = null;

    public WeatherAsyncTask(Activity activity, double latitude, double longitude, Marker marker, GoogleMap googlemap) {
        prediction = TypePrediction.LATITUDE_LONGITUDE;
        this.activity = activity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.marker = marker;
        this.googlemap = googlemap;
        dialog = new ProgressDialog(activity);
        dialog.setTitle("Loading data ...");
        dialog.setCancelable(true);
    }

    public WeatherAsyncTask(Activity activity , String location) {
        prediction = TypePrediction.ADDRESS_NAME;
        this.activity = activity;
        this.location = location;
        dialog = new ProgressDialog(activity);
        dialog.setTitle("Loading data ...");
        dialog.setCancelable(true);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //dialog.show();
    }

    @Override
    protected OpenWeatherJSON doInBackground(Void... params)
    {
        // kết nối , lấy thông tin
        String data = "'";
        WeatherHttpClient http = new WeatherHttpClient();
        if ( prediction == TypePrediction.LATITUDE_LONGITUDE)
        {
            data = http.getWeatherDataByCoord(latitude,longitude);
        }
        else
        {
            data = http.getCurrentWeatherData(location);
        }
        OpenWeatherJSON JObject = null;
        try
        {
            JObject = OpenWeatherMapAPI.getWeather(data);

            if (JObject != null)
            {
                String idIcon = JObject.getListWeather().get(0).getIcon().toString();
                String urlicon = "http://openweathermap.org/img/w/" + idIcon + ".png";
                InputStream is = null;
                HttpURLConnection connection = null;
                try {
                    URL urlconnection = new URL(urlicon);
                    connection = (HttpURLConnection) urlconnection.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    is = connection.getInputStream();
                    mybitmap = BitmapFactory.decodeStream(is);

                    is.close();
                    connection.disconnect();

                    return JObject;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(OpenWeatherJSON openWeatherJSON) {
        super.onPostExecute(openWeatherJSON);

        if ( googlemap != null && openWeatherJSON != null)
        {
            googlemap.setInfoWindowAdapter(new InfoAdapter(activity,marker,openWeatherJSON,mybitmap,latitude,longitude));
            marker.showInfoWindow();
            return;
        }

        if ( openWeatherJSON != null ) {
            // cập nhật thông tin lên giao diện
            TextView CurrentLocation, Temperature, Max_temp, Min_temp, Wind, Pressure, Humidity, Clouds, Sunrise, Description, SunSet;
            ImageView image = (ImageView) activity.findViewById(R.id.imageView);
            CurrentLocation = (TextView) activity.findViewById(R.id.textViewLocation);
            Temperature = (TextView) activity.findViewById(R.id.textViewTemp);
            Max_temp = (TextView) activity.findViewById(R.id.textViewMaxtemp);
            Min_temp = (TextView) activity.findViewById(R.id.textViewMinTemp);
            Wind = (TextView) activity.findViewById(R.id.textViewWind);
            Pressure = (TextView) activity.findViewById(R.id.textViewPressure);
            Humidity = (TextView) activity.findViewById(R.id.textViewHumidity);
            Clouds = (TextView) activity.findViewById(R.id.textViewClouds);
            Sunrise = (TextView) activity.findViewById(R.id.textViewSunrise);
            Description = (TextView) activity.findViewById(R.id.textviewDescription);
            SunSet = (TextView) activity.findViewById(R.id.txtSunset);

            CurrentLocation.setText(openWeatherJSON.getName());
            Temperature.setText(String.valueOf(openWeatherJSON.getMain().getTemp()) + "°C");
            Max_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_max()) + "°C");
            Min_temp.setText(String.valueOf(openWeatherJSON.getMain().getTemp_min()) + "°C");
            Wind.setText(String.valueOf(openWeatherJSON.getWind().getSpeed()) + "m/s");
            Pressure.setText(String.valueOf(openWeatherJSON.getMain().getPressure()) + "hpa");
            Humidity.setText(String.valueOf(openWeatherJSON.getMain().getHumidity()) + "%");
            Clouds.setText(openWeatherJSON.getListWeather().get(0).getMain());

            Date TimeSunrise = new Date((long) (openWeatherJSON.getSys().getSunrise() * 1000));
            Sunrise.setText(TimeSunrise.getHours() + " : " + TimeSunrise.getMinutes());

            Date TimeSunSet = new Date((long) openWeatherJSON.getSys().getSunset() * 1000);
            String TimeSS = TimeSunSet.getHours() + " : " + TimeSunSet.getMinutes();
            SunSet.setText(TimeSS);


            Description.setText(openWeatherJSON.getListWeather().get(0).getDesciption());

            image.setImageBitmap(mybitmap);

            try
            {
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this.activity, Locale.getDefault());
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
        }
        dialog.dismiss();
    }
}
