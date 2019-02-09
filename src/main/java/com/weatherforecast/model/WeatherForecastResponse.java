package com.weatherforecast.model;

public class WeatherForecastResponse {

    private String cityName;
    private double nightTemp;
    private double dayTemp;
    private double pressure;

    public WeatherForecastResponse(String cityName, double nightTemp, double dayTemp, double pressure) {
        this.cityName = cityName;
        this.nightTemp = nightTemp;
        this.dayTemp = dayTemp;
        this.pressure = pressure;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

}
