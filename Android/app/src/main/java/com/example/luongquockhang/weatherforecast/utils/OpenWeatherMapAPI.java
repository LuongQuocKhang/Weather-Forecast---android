package com.example.luongquockhang.weatherforecast.utils;

import com.example.luongquockhang.weatherforecast.Model.Clouds;
import com.example.luongquockhang.weatherforecast.Model.Coord;
import com.example.luongquockhang.weatherforecast.Model.Main;
import com.example.luongquockhang.weatherforecast.Model.OpenWeatherJSON;
import com.example.luongquockhang.weatherforecast.Model.Sys;
import com.example.luongquockhang.weatherforecast.Model.Weatheritem;
import com.example.luongquockhang.weatherforecast.Model.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */
// chuyển từ chuỗi JSON về đối tượng weather
public class OpenWeatherMapAPI
{
    public static OpenWeatherJSON getWeather(String data) throws JSONException
    {
        OpenWeatherJSON weather = new OpenWeatherJSON();

        // tạo đối tượng JSON từ chuỗi JSON
        JSONObject JObject = new JSONObject(data);

        // lấy thông tin tọa độ
        Coord coord = new Coord();
        JSONObject coordObject = getObject("coord",JObject);
        coord.setLatitude(getFloat("lat",coordObject));
        coord.setLongitude(getFloat("lon",coordObject));
        weather.setCoord(coord);

        // lấy thông tin Sys
        Sys sys = new Sys();
        JSONObject sysObject = getObject("sys",JObject);
        sys.setSunrise(getInt("sunrise",sysObject));
        sys.setCountry((getString("country",sysObject)));
        sys.setMessage(getString("message",sysObject));
        sys.setSunset(getFloat("sunset",sysObject));
        weather.setSys(sys);

        // lấy thông tin Main
        Main main = new Main();
        JSONObject mainObject = getObject("main",JObject);
        main.setTemp((int)(getFloat("temp",mainObject) - 273.15));
        main.setTemp_max((int)(getFloat("temp_max",mainObject) - 273.15));
        main.setTemp_min((int)(getFloat("temp_min",mainObject) - 273.15));
        main.setPressure(getInt("pressure",mainObject));
        //main.setSea_level(getFloat("sea_level",mainObject));
        main.setHumidity(getFloat("humidity",mainObject));
        //main.setGrnd_level(getFloat("grnd_level",mainObject));
        weather.setMain(main);

        // lấy thông tin Clouds
        JSONObject cloudsObject = getObject("clouds",JObject);
        Clouds clouds = new Clouds();
        clouds.setAll(getInt("all",cloudsObject));
        weather.setClouds(clouds);

        // lấy thông tin wind
        JSONObject windObject = getObject("wind",JObject);
        Wind wind = new Wind();
        //wind.setDegree(getInt("deg",windObject));
        wind.setSpeed(getInt("speed",windObject));
        weather.setWind(wind);

        weather.setBase(getString("base",JObject));
        weather.setName(getString("name",JObject));
        weather.setId(getInt("id",JObject));
        weather.setDt(getInt("dt",JObject));
        weather.setCod(getInt("cod",JObject));

        //lấy thông tin weatherItem
        JSONArray Array = JObject.getJSONArray("weather");
        JSONObject weatheritem = Array.getJSONObject(0);
        Weatheritem item = new Weatheritem();
        item.setDesciption(getString("description",weatheritem));
        item.setIcon(getString("icon",weatheritem));
        item.setID(getInt("id",weatheritem));
        item.setMain(getString("main",weatheritem));
        weather.setWeather(item);

        return weather;
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
