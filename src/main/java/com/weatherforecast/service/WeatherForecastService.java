package com.weatherforecast.service;

import com.sun.javafx.binding.StringFormatter;
import com.weatherforecast.model.WeatherForecastResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.*;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

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
        return processResponse(city, retrieveData(city));
    }

    public String retrieveData(String city){
        return restTemplate.getForObject(getApiUrl(city), String.class);
    }

    public WeatherForecastResponse processResponse(String city, String response){
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDate.now().plusDays(4).atStartOfDay();

        double totalTmpDay = 0;
        double totalTmpNight = 0;
        double totalPressure = 0;

        int forecastsAmount = 0;
        int dayTempAmount = 0;

        JSONObject responseJSON = new JSONObject(response);

        JSONArray forecastList = responseJSON.getJSONArray("list");
        for(int i=0; i<forecastList.length(); i++){
            JSONObject forecastMilestone = forecastList.getJSONObject(i);
            int milestoneDateValue = (Integer)forecastMilestone.get("dt");
            LocalDateTime milestoneDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(milestoneDateValue), TimeZone.getDefault().toZoneId());
            if(milestoneDate.getDayOfMonth() == currentDate.getDayOfMonth()) continue;
            if(milestoneDate.compareTo(endDate)>0) break;

            forecastsAmount++;
            JSONObject main = forecastMilestone.getJSONObject("main");
            //yeah-yeah, I know, but main.get("temp") returns Double or Integer object depending on . presence
            double tmp = Double.valueOf(String.valueOf(main.get("temp")));
            totalPressure += Double.valueOf(String.valueOf(main.get("pressure")));

            if(milestoneDate.getHour()>=6 && milestoneDate.getHour()<18) {
                totalTmpDay += tmp;
                dayTempAmount++;
            }
            else
                totalTmpNight += tmp;
        }

        return new WeatherForecastResponse(city,
                totalTmpNight/(forecastsAmount-dayTempAmount),
                totalTmpDay/dayTempAmount,
                totalPressure/forecastsAmount);

    }
}
