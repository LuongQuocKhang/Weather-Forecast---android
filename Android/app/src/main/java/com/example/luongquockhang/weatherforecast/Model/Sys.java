package com.example.luongquockhang.weatherforecast.Model;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */

public class Sys
{
    private String message;
    private String country;
    private double sunrise;
    private double sunset;

    public String getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public double getSunrise() {
        return sunrise;
    }

    public double getSunset() {
        return sunset;
    }
}
