package com.example.weatherapi.service;

import com.example.weatherapi.model.Location;
import com.example.weatherapi.model.Weather;

import java.util.List;

public interface WeatherService {
    public List<Location> findLocationByName(String name);
    public Weather getWeatherById(String woeId);
}
