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
    private static String API = "&appid=1426db5a6318fd0f2f2ee70e69e6fa86";
    public String getWeatherData(String location)
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
//        finally {
//            try {
//                is.close();
//                connection.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return null;
    }
}
