package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.CachedWeatherService;
import hu.unideb.inf.RainCheck.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.net.URISyntaxException;

@RestController
public class WeatherController {
    private final CachedWeatherService cachedWeatherService;

    @Autowired
    public WeatherController(CachedWeatherService cachedWeatherService) {
        this.cachedWeatherService = cachedWeatherService;
    }

    @GetMapping("/forecast/today")
    public String getTodayWeather(@RequestParam String location) throws URISyntaxException {
        return cachedWeatherService.getWeatherDataForOneDay(location);
    }

    @GetMapping("/forecast/one-week")
    public String getOneWeekForecast(@RequestParam String location) throws URISyntaxException {
        return cachedWeatherService.getWeatherDataForOneWeek(location);
    }

    @GetMapping("/forecast/two-week")
    public String getTwoWeekForecast(@RequestParam String location) throws URISyntaxException {
        return cachedWeatherService.getWeatherDataForTwoWeek(location);
    }
}
