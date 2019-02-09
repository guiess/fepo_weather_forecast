package com.weatherforecast.processor;

import com.weatherforecast.model.WeatherForecastResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class WeatherForecastProcessor {

    public WeatherForecastResponse process(String city, String response){
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDate.now().plusDays(4).atStartOfDay();

        double totalTmpDay = 0;
        double totalTmpNight = 0;
        double totalPressure = 0;

        int forecastsAmount = 0;
        int dayTempAmount = 0;

        JSONObject responseJSON = new JSONObject(response);

        try {
            JSONArray forecastList = responseJSON.getJSONArray("list");
            for (int i = 0; i < forecastList.length(); i++) {
                JSONObject forecastMilestone = forecastList.getJSONObject(i);
                int milestoneDateValue = (Integer) forecastMilestone.get("dt");
                LocalDateTime milestoneDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(milestoneDateValue), TimeZone.getDefault().toZoneId());
                if (milestoneDate.getDayOfMonth() == currentDate.getDayOfMonth()) continue;
                if (milestoneDate.compareTo(endDate) > 0) break;

                forecastsAmount++;
                JSONObject main = forecastMilestone.getJSONObject("main");
                //yeah-yeah, I know, but main.get("temp") returns Double or Integer object depending on . presence
                double tmp = Double.valueOf(String.valueOf(main.get("temp")));
                totalPressure += Double.valueOf(String.valueOf(main.get("pressure")));

                if (milestoneDate.getHour() >= 6 && milestoneDate.getHour() < 18) {
                    totalTmpDay += tmp;
                    dayTempAmount++;
                } else
                    totalTmpNight += tmp;
            }

            return new WeatherForecastResponse(city,
                    totalTmpNight / (forecastsAmount - dayTempAmount),
                    totalTmpDay / dayTempAmount,
                    totalPressure / forecastsAmount);
        }
        catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Problems during processing the forecast result: "+e.getMessage(), e);
        }
    }
}
