# fepo_weather_forecast
Weather forecast test study application.

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


#Reasoning and Motivations behind the code
Actually, I'm not sure what is expected to be written here.  
I'm trying to use the most common approaches that are enough for the given task without any additional complexity (at least on the way I understand this =))
Also used Spring Boot in order to simplify the application by not necessary for the current task configuration classes
