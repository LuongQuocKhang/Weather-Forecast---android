package com.example.luongquockhang.weatherforecast.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.luongquockhang.weatherforecast.Model.Clouds;
import com.example.luongquockhang.weatherforecast.Model.Coord;
import com.example.luongquockhang.weatherforecast.Model.DailyWeather;
import com.example.luongquockhang.weatherforecast.Model.Main;
import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.Model.Sys;
import com.example.luongquockhang.weatherforecast.Model.Weatheritem;
import com.example.luongquockhang.weatherforecast.Model.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */
// chuyển từ chuỗi JSON về đối tượng weather
public class OpenWeatherMapAPI {
    public static OpenWeatherJSON getWeather(String data) throws JSONException {
        OpenWeatherJSON weather = null;

        if (data != null) {
            weather = new OpenWeatherJSON();
            // tạo đối tượng JSON từ chuỗi JSON
            JSONObject JObject = new JSONObject(data);
            // lấy thông tin tọa độ
            Coord coord = new Coord();
            JSONObject coordObject = getObject("coord", JObject);
            coord.setLatitude(getFloat("lat", coordObject));
            coord.setLongitude(getFloat("lon", coordObject));
            weather.setCoord(coord);

            // lấy thông tin Sys
            Sys sys = new Sys();
            JSONObject sysObject = getObject("sys", JObject);
            if (getInt("sunrise", sysObject) != 0) sys.setSunrise(getInt("sunrise", sysObject));
            if ((getString("country", sysObject)) != null)
                sys.setCountry((getString("country", sysObject)));
            if (getString("message", sysObject) != null)
                sys.setMessage(getString("message", sysObject));
            sys.setSunset(getFloat("sunset", sysObject));
            weather.setSys(sys);

            // lấy thông tin Main
            Main main = new Main();
            JSONObject mainObject = getObject("main", JObject);
            main.setTemp((int) (getFloat("temp", mainObject) - 273.15));
            main.setTemp_max((int) (getFloat("temp_max", mainObject) - 273.15));
            main.setTemp_min((int) (getFloat("temp_min", mainObject) - 273.15));
            main.setPressure(getInt("pressure", mainObject));
            //main.setSea_level(getFloat("sea_level",mainObject));
            main.setHumidity(getFloat("humidity", mainObject));
            //main.setGrnd_level(getFloat("grnd_level",mainObject));
            weather.setMain(main);

            // lấy thông tin Clouds
            JSONObject cloudsObject = getObject("clouds", JObject);
            Clouds clouds = new Clouds();
            clouds.setAll(getInt("all", cloudsObject));
            weather.setClouds(clouds);

            // lấy thông tin wind
            JSONObject windObject = getObject("wind", JObject);
            Wind wind = new Wind();
            //wind.setDegree(getInt("deg",windObject));
            wind.setSpeed(getInt("speed", windObject));
            weather.setWind(wind);

            weather.setBase(getString("base", JObject));
            weather.setName(getString("name", JObject));
            weather.setId(getInt("id", JObject));
            weather.setDt(getInt("dt", JObject));
            weather.setCod(getInt("cod", JObject));

            //lấy thông tin weatherItem
            JSONArray array = JObject.getJSONArray("weather");
            JSONObject weatheritem = array.getJSONObject(0);
            Weatheritem item = new Weatheritem();
            item.setDesciption(getString("description", weatheritem));
            item.setIcon(getString("icon", weatheritem));
            item.setID(getInt("id", weatheritem));
            item.setMain(getString("main", weatheritem));

            weather.getListWeather().add(item);
        }
    return weather;
}
    public static List<DailyWeather> getWeatheritem(String data) throws JSONException {
        List<DailyWeather> list = new ArrayList<>();

        JSONObject JObject = new JSONObject(data);

        JSONArray Array = JObject.getJSONArray("list");

        for ( int  i = 0 ; i < Array.length() ; i++) {
            DailyWeather weather = new DailyWeather();
            JSONObject obj = Array.getJSONObject(i);

            // lấy thông tin nhiệt độ
            JSONObject temp = getObject("temp", obj);
            long datetime = Long.parseLong(getString("dt", obj));
            weather.setDatetime(datetime);
            weather.setMaxTemp(getFloat("max", temp) - 273.15);
            weather.setMinTemp(getFloat("day", temp) - 273.15);
            weather.setTemp(getFloat("min", temp) - 273.15);
            weather.setHumidity(getFloat("humidity", obj));

            // lấy icon

            JSONArray array = obj.getJSONArray("weather");
            JSONObject weatheritem = array.getJSONObject(0);
            String idIcon = getString("icon", weatheritem);
            weather.setIcon(idIcon);
            weather.setDesciption(getString("description",weatheritem));


            String urlicon = "http://openweathermap.org/img/w/" + idIcon + ".png";
            InputStream is = null;
            HttpURLConnection connection = null;
            Bitmap mybitmap = null;
            try {
                URL urlconnection = new URL(urlicon);
                connection = (HttpURLConnection) urlconnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                is = connection.getInputStream();
                mybitmap = BitmapFactory.decodeStream(is);
                weather.setWeatherIcon(mybitmap);
                is.close();
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            list.add(weather);
        }
            return list;
    }
    public static JSONObject getObject(String name , JSONObject JObj) throws JSONException {
        return JObj.getJSONObject(name);
    }
    public static float getFloat(String name , JSONObject jObj) throws JSONException {
        return (float)jObj.getDouble(name);
    }

    public static String getString(String name , JSONObject jObj ) throws JSONException {
        return jObj.getString(name);
    }
    public static int getInt(String name , JSONObject jObj) throws JSONException {
        return jObj.getInt(name);
    }
}
