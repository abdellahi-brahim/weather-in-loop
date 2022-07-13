package com.weatherinloop.frontend.Model;

import lombok.Data;

@Data
public class Forecast {
    private String maxtemp_c;
    private String mintemp_c;
    private String date;
    private String icon;
}
