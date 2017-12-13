package com.example.luongquockhang.weatherforecast.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Luong Quoc Khang on 10/28/2017.
 */

// lấy chuỗi JSON
public class WeatherHttpClient
{
    private static String Location_URL="";
    private static String API = "&appid=be8d3e323de722ff78208a7dbb2dcd6f";
    public String getCurrentWeatherData(String location)
    {
        Location_URL ="http://api.openweathermap.org/data/2.5/weather?q=";
        HttpURLConnection connection = null;
        InputStream is = null;
        try
        {
            connection = (HttpURLConnection)(new URL(Location_URL + location + API)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            // đọc dữ liệu trả về
            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null)
            {
                buffer.append(line + "\r\n");
            }
            is.close();
            connection.disconnect();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDailyweather(String location)
    {
        Location_URL ="https://api.openweathermap.org/data/2.5/forecast/daily?q=";
        HttpURLConnection connection = null;
        InputStream is = null;
        try
        {
            connection = (HttpURLConnection)(new URL(Location_URL + location + "&cnt=10" + API)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            // đọc dữ liệu trả về
            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null)
            {
                buffer.append(line + "\r\n");
            }
            is.close();
            connection.disconnect();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getWeatherDataByCoord(double latitude , double longitude)
    {
        Location_URL ="http://api.openweathermap.org/data/2.5/weather?lat=" +latitude + "&" + "lon=" + longitude;
        HttpURLConnection connection = null;
        InputStream is = null;
        try
        {
            connection = (HttpURLConnection)(new URL(Location_URL + API)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            // đọc dữ liệu trả về
            StringBuffer buffer = new StringBuffer();
            is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null)
            {
                buffer.append(line + "\r\n");
            }
            is.close();
            connection.disconnect();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
