package com.weatherinloop.frontend.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

import com.fasterxml.jackson.databind.JsonNode;
import com.weatherinloop.frontend.Config.FeignConfig;

@FeignClient(name = "weather", url = "${api.baseUrl}", configuration = FeignConfig.class)
public interface WeatherProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    @Headers(value = "Content-Type: application/json")
    public JsonNode getWeather(@PathVariable("name") String name);
}
