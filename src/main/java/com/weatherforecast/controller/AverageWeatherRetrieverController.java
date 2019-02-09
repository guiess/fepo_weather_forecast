package com.weatherforecast.controller;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/data")
public class AverageWeatherRetrieverController {


    @Autowired
    WeatherForecastService weatherForecastService;

    @RequestMapping(method= RequestMethod.GET)
    public WeatherForecastResponse getWeatherForecast(@PathVariable(value = "city") @NotEmpty String city){
        return weatherForecastService.getWeatherForecastForCity(city);
    }
}
