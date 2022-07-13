package com.weatherinloop.frontend.Model;

import java.util.List;

import lombok.Data;

@Data
public class Weather {
    private String name;
    private String temp_c;
    private String icon;
    private List<Forecast> forecast;

}
