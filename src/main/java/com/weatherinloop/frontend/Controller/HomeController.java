package com.weatherinloop.frontend.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.weatherinloop.frontend.Model.Favorites;
import com.weatherinloop.frontend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weatherinloop.frontend.Model.Weather;
import com.weatherinloop.frontend.Service.HomeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    HomeService homeService;
    @Autowired
    UserService userService;

    @GetMapping("/error")
    public String error(Model model) {
        return "error.html";
    }

    @RequestMapping(value = { "", "/", "/home" })
    public String displayHomePage(Model model) {
        String[] biggestCities = { "New York City", "London", "Hong Kong", "Paris", "Tokyo" };
        List<Weather> cities = requestWeatherList(Arrays.asList(biggestCities));
        model.addAttribute("cities", cities);
        return "home.html";
    }

    @GetMapping(value = { "/forecast/{cityName}" })
    public String displayForecast(@PathVariable(value = "cityName") String cityName, Model model) {
        Weather city = homeService.getWeather(cityName);
        model.addAttribute("city", city);
        return "forecast.html";
    }

    @GetMapping(value = { "/favorites" })
    public String displayFavorites(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Weather> favorites = requestWeatherList(userService.getFavorites(username));
        model.addAttribute("favorites", favorites);
        return "favorites.html";
    }
    @PostMapping(value = { "/search" })
    public String searchForecast(@RequestParam(name = "cityName") String cityName) {
        return "redirect:/forecast/" + cityName;
    }

    @PostMapping(value = { "/addFavorite"})
    public String addFavorite(@PathVariable(value = "cityName") String cityName, Model model){
        userService.addFavorite(cityName);
        return displayFavorites(model);
    }

    public List<Weather> requestWeatherList(List<String> cityList){
        List<Weather> weatherList = new ArrayList<Weather>();

        for (String city : cityList) {
            Weather weather = homeService.getWeather(city);
            weatherList.add(weather);
        }

        return weatherList;
    }
}
