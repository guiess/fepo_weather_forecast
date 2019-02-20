package com.weatherforecast.service;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.processor.WeatherForecastProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherForecastService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.api.endpoint:stub}")
    private String apiEndpoint;

    @Value("${weather.api.appid:stub}")
    private String appid;

    private final String urlFormat = "http://%s/data/2.5/forecast?appid=%s&mode=json&units=metric&q=%s";


    private String getApiUrl(String city){
        return String.format(urlFormat, apiEndpoint, appid, city);
    }

    public WeatherForecastResponse getWeatherForecastForCity(String city){
        return new WeatherForecastProcessor().process(retrieveData(city));
    }

    private String retrieveData(String city){
        return restTemplate.getForObject(getApiUrl(city), String.class);
    }


}
