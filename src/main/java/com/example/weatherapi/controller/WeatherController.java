package com.example.weatherapi.controller;

import com.example.weatherapi.model.ConsolidatedWeather;
import com.example.weatherapi.model.Location;
import com.example.weatherapi.model.Weather;
import com.example.weatherapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WeatherController {
    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String getWeather(Model model, @RequestParam(required = false, defaultValue = "") String query,
                             @RequestParam(required = false, defaultValue = "") String id) {
        List<Location> locations = new ArrayList<>();
        Weather weather = new Weather();

        if (!query.isEmpty()) {
            locations = weatherService.findLocationByName(query);
        }
        if (!id.isEmpty()) {
            weather = weatherService.getWeatherById(id);
            System.out.println("==================================");
            System.out.println(weather.getConsolidatedWeather().get(0).getMaxTemp());
        }
        model.addAttribute("Loc", locations);
        model.addAttribute("weather", weather);

        return "home";
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String getWeather() {
//        return "home";
//    }
}