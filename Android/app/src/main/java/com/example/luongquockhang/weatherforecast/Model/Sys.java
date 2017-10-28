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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

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
