package com.weatherforecast.controller;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.service.WeatherForecastService;
import com.weatherforecast.validator.ValidCityName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/data")
@Validated
public class AverageWeatherRetrieverController {


    @Autowired
    WeatherForecastService weatherForecastService;

    @RequestMapping(method= RequestMethod.GET)
    public WeatherForecastResponse getWeatherForecast(
            @RequestParam(value = "city") @NotEmpty @ValidCityName String city){
        return weatherForecastService.getWeatherForecastForCity(city);
        //return new WeatherForecastResponse(0,0,0);
    }



}
