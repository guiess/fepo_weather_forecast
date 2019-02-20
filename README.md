# fepo_weather_forecast
Weather forecast test study application.

The task:  
Using Java (1.8 or newer), create an API that will retrieve weather metrics of a specific city.
Please use https://openweathermap.org/ to create a free account and retrieve the data for this
case study.

- The API should expose a “/data” endpoint to retrieve the averages  
- The “/data” endpoint should return a JSON object that gives the averages of the
following metrics:
  - Average temperature (in Celsius) of the next 3 days from today’s date for Day
time (06:00 – 18:00) and Night time (18:00 – 06:00).
  - Average of pressure for the next 3 days from today’s date.
- The “/data” endpoint must include a CITY parameter containing the city’s name as the
input for the correct response.

#Prerequisites
in the application.properties file, located in the following folder  
\weather_forecast\src\main\resources  
where you may find the following parameters:  
- server.port - set to non-default
- weather.api.endpoint - endpoint url to weather forecast api
- weather.api.appid - personal API key. should be set in order to retrieve data from the service. Please use your API ID

#How to run the application:
in the command window/far navigate to the root folder and execute the following command
>gradle BootRun  

after the application is started use the following URL
>http://localhost:7777/data?city=city_name

#How to run tests:
in the command window/far navigate to the root folder and execute the following command
>gradle test

