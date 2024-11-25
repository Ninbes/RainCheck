package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.CachedWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final CachedWeatherService cachedWeatherService;

    @Autowired
    public WeatherController(CachedWeatherService cachedWeatherService) {
        this.cachedWeatherService = cachedWeatherService;
    }

    @GetMapping("/forecast/today")
    public String getTodayWeather(@RequestParam String location) throws URISyntaxException {
        logger.info("Received request for today's weather. Location: {}", location);
        try {
            String response = cachedWeatherService.getWeatherDataForOneDay(location);
            logger.info("Successfully retrieved today's weather data for location: {}", location);
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while fetching today's weather for location: {}", location, e);
            throw e;
        }
    }

    @GetMapping("/forecast/one-week")
    public String getOneWeekForecast(@RequestParam String location) throws URISyntaxException {
        logger.info("Received request for one-week weather forecast. Location: {}", location);
        try {
            String response = cachedWeatherService.getWeatherDataForOneWeek(location);
            logger.info("Successfully retrieved one-week weather forecast for location: {}", location);
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while fetching one-week weather forecast for location: {}", location, e);
            throw e;
        }
    }

    @GetMapping("/forecast/two-week")
    public String getTwoWeekForecast(@RequestParam String location) throws URISyntaxException {
        logger.info("Received request for two-week weather forecast. Location: {}", location);
        try {
            String response = cachedWeatherService.getWeatherDataForTwoWeek(location);
            logger.info("Successfully retrieved two-week weather forecast for location: {}", location);
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while fetching two-week weather forecast for location: {}", location, e);
            throw e;
        }
    }
}
