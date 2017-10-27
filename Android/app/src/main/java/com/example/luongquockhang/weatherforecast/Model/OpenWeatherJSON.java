package com.example.luongquockhang.weatherforecast.Model;

import java.util.List;

/**
 * Created by Luong Quoc Khang on 10/27/2017.
 */

public class OpenWeatherJSON
{
    private String base;
    private long dt;
    private long id;
    private String name;
    private int cod;

    private Coord coord;
    private Sys sys;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public List<Weatheritem> getWeather() {
        return weather;
    }

    public void setWeather(List<Weatheritem> weather) {
        this.weather = weather;
    }

    private Main main;
    private  Wind wind;
    private Clouds clouds;
    private List<Weatheritem> weather;
}
