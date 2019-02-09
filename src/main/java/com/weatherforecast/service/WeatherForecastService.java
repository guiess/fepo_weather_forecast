package com.weatherforecast.service;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.processor.WeatherForecastProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
        return new WeatherForecastProcessor().process(city, retrieveData(city));
    }

    private String retrieveData(String city){
        try{
            return restTemplate.getForObject(getApiUrl(city), String.class);
        }
        catch (HttpClientErrorException e){
            System.out.println(e.getMessage());
            if(e.getMessage().contains("404")) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "City not found", e);
            }
            else if(e.getMessage().contains("401")){
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Unauthorised exception. Please check appId", e);
            }
            else{
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Something bad has happened when retrieving information from the weather service"+e.getMessage(), e);
            }
        }
    }
}
