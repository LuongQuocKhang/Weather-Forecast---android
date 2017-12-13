package com.example.luongquockhang.weatherforecast.Model;

import android.graphics.Bitmap;

/**
 * Created by Luong Quoc Khang on 12/8/2017.
 */

public class DailyWeather
{
    private long datetime;
    private double Temp;
    private double MaxTemp;
    private double MinTemp;
    private double Humidity;
    private String icon;
    private Bitmap weatherIcon;
    private String Desciption;

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Bitmap getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(Bitmap weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public double getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        MaxTemp = maxTemp;
    }

    public double getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(double minTemp) {
        MinTemp = minTemp;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }
}
