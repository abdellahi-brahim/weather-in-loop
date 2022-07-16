package com.weatherinloop.frontend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weatherinloop.frontend.Model.Forecast;
import com.weatherinloop.frontend.Model.Weather;
import com.weatherinloop.frontend.Proxy.WeatherProxy;

@Service
public class HomeService {

    @Autowired
    WeatherProxy weatherProxy;

    public Weather getWeather(String name) {
        JsonNode weatherJSON = weatherProxy.getWeather(name);

        // New weather object
        Weather newWeather = new Weather();
        newWeather.setName(weatherJSON.get("location").get("name").toString().replaceAll("\"", ""));
        newWeather.setCountry(weatherJSON.get("location").get("country").toString().replaceAll("\"", ""));
        newWeather.setTemp_c(weatherJSON.get("current").get("temp_c").toString().replaceAll("\"", ""));
        newWeather.setIcon(weatherJSON.get("current").get("condition").get("icon").toString().replaceAll("\"", ""));

        // New forecast object
        List<Forecast> listForecast = new ArrayList<Forecast>();
        JsonNode days = weatherJSON.get("forecast").get("forecastday");
        for (JsonNode day : days) {
            Forecast newForecast = new Forecast();
            newForecast.setDate(day.get("date").toString().replaceAll("\"", ""));
            newForecast.setMaxtemp_c(day.get("day").get("maxtemp_c").toString().replaceAll("\"", ""));
            newForecast.setMintemp_c(day.get("day").get("mintemp_c").toString().replaceAll("\"", ""));
            newForecast.setIcon(
                    day.get("day").get("condition").get("icon").toString().replaceAll("\"", ""));
            listForecast.add(newForecast);
        }
        newWeather.setForecast(listForecast);

        return newWeather;
    }
}
