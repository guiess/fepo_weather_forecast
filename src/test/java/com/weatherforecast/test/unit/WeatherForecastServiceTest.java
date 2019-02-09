package com.weatherforecast.test.unit;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.processor.WeatherForecastProcessor;
import com.weatherforecast.service.WeatherForecastService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WeatherForecastServiceTest {

    @TestConfiguration
    static class WeatherForecastServiceTextContextConfiguration{
        @Bean
        public WeatherForecastService weatherForecastService(){return new WeatherForecastService();}
    }

    @Autowired
    WeatherForecastService weatherForecastService;

    @Test
    public void getWeatherForecastForCity_Test(){
        String stubResponse = "{\"list\":[{\"dt\":1549767600,\"main\":{\"temp\":5,\"pressure\":900}},{\"dt\":1549810800,\"main\":{\"temp\":11,\"pressure\":1000}},{\"dt\":1549854000,\"main\":{\"temp\":7,\"pressure\":940}},{\"dt\":1549897200,\"main\":{\"temp\":13,\"pressure\":1040}}]}";

        WeatherForecastResponse weatherForecastResponse = new WeatherForecastProcessor().process("city", stubResponse);

        assertThat(weatherForecastResponse.getCityName().equals("city"));
        assertThat(weatherForecastResponse.getDayTemp() ==12);
        assertThat(weatherForecastResponse.getNightTemp() ==6);
        assertThat(weatherForecastResponse.getPressure() == 970);

    }

}
