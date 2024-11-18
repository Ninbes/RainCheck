package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.StringHandlingUtil;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast/today")
    public String getTodayWeather(@RequestParam String location) {
        return weatherService.getWeatherDataForOneDay(StringHandlingUtil.stripAccents(location));
    }
    @GetMapping("/forecast/one-week")
    public String getOneWeekForecast(@RequestParam String location) {
        return weatherService.getWeatherDataForOneWeek(StringHandlingUtil.stripAccents(location));
    }
    @GetMapping("/forecast/two-week")
    public String getTwoWeekForecast(@RequestParam String location) {
        return weatherService.getWeatherDataForTwoWeek(StringHandlingUtil.stripAccents(location));
    }

}
