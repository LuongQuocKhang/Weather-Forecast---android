package com.example.luongquockhang.weatherforecast.Model;

import android.graphics.Bitmap;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */

public class Weatheritem
{
    private long ID;
    private String main;
    private String desciption;
    private String icon;

    public Bitmap getWeathericon() {
        return weathericon;
    }

    public void setWeathericon(Bitmap weathericon) {
        this.weathericon = weathericon;
    }

    private Bitmap weathericon;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    private String datetime;

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getID() {
        return ID;
    }

    public String getMain() {
        return main;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getIcon() {
        return icon;
    }
}
