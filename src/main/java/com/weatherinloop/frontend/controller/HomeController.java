package com.weatherinloop.frontend.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weatherinloop.frontend.Model.Weather;
import com.weatherinloop.frontend.Service.HomeService;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping(value = { "", "/", "/home" })
    public String displayHomePage(Model model) {
        String[] biggestCities = { "New York City", "London", "Hong Kong", "Paris", "Tokyo" };
        List<Weather> cities = new ArrayList<Weather>();
        for (String city : biggestCities) {
            Weather weather = homeService.getWeather(city);
            cities.add(weather);
        }

        model.addAttribute("cities", cities);
        return "home.html";
    }
}