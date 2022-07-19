package com.weatherinloop.frontend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weatherinloop.frontend.Model.Weather;
import com.weatherinloop.frontend.Service.HomeService;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/error")
    public String error(Model model) {
        return "error.html";
    }

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

    @GetMapping(value = { "/forecast/{cityName}" })
    public String displayForecast(@PathVariable(value = "cityName") String cityName, Model model) {
        Weather city = homeService.getWeather(cityName);
        model.addAttribute("city", city);
        return "forecast.html";
    }

    @PostMapping(value = { "/search" })
    public String searchForecast(@RequestParam(name = "cityName") String cityName) {
        return "redirect:/forecast/" + cityName;
    }
}
