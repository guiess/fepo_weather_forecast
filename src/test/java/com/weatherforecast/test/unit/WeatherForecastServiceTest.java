package com.weatherforecast.test.unit;

import com.weatherforecast.model.WeatherForecastResponse;
import com.weatherforecast.service.WeatherForecastService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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

        WeatherForecastResponse weatherForecastResponse = weatherForecastService.processResponse("city", stubResponse);

        assertThat(weatherForecastResponse.getCityName().equals("city"));
        assertThat(weatherForecastResponse.getDayTemp() ==12);
        assertThat(weatherForecastResponse.getNightTemp() ==6);
        assertThat(weatherForecastResponse.getPressure() == 970);

    }

}
