package com.weatherforecast.controller;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class AverageWeatherRetrieverController {


    @Autowired
    WeatherForecastService weatherForecastService;

    @RequestMapping(method= RequestMethod.GET)
    public WeatherForecastResponse getWeatherForecast(@RequestParam("city") String city){

        return weatherForecastService.getWeatherForecastForCity(city);
    }
}
