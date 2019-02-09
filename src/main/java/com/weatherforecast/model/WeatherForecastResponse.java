package com.weatherforecast.model;

public class WeatherForecastResponse {

    private double nightTemp;
    private double dayTemp;
    private double pressure;

    public WeatherForecastResponse(double nightTemp, double dayTemp, double pressure) {
        this.nightTemp = nightTemp;
        this.dayTemp = dayTemp;
        this.pressure = pressure;
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
