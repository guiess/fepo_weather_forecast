package com.weatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WeatherForecastApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(new Class[]{WeatherForecastApplication.class}, args);
    }
}
