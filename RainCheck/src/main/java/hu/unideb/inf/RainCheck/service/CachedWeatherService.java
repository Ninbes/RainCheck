package hu.unideb.inf.RainCheck.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import utils.DateUtil;

import java.net.URISyntaxException;
import java.time.Duration;

@Service
public class CachedWeatherService {
    private final WeatherService weatherService;
    private final StringRedisTemplate redisTemplate;

    public CachedWeatherService(WeatherService weatherService, StringRedisTemplate redisTemplate) {
        this.weatherService = weatherService;
        this.redisTemplate = redisTemplate;
    }

    public String getWeatherDataForOneDay(String location) throws URISyntaxException {
        String cacheKey = "weather:one-day:" + location;
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForOneDay(location));
    }

    public String getWeatherDataForOneWeek(String location) throws URISyntaxException {
        String cacheKey = "weather:one-week:" + location;
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForOneWeek(location));
    }

    public String getWeatherDataForTwoWeek(String location) throws URISyntaxException {
        String cacheKey = "weather:two-week:" + location;
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForTwoWeek(location));
    }

    private String fetchAndCacheData(String cacheKey, DataFetcher fetcher) throws URISyntaxException {
        cacheKey = cacheKey.toLowerCase();
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            return cachedData;
        }

        String freshData = fetcher.fetch();

        long secondsUntilMidnight = DateUtil.getSecondsUntilApiReset();

        redisTemplate.opsForValue().set(cacheKey, freshData, Duration.ofSeconds(secondsUntilMidnight));

        return freshData;
    }

    @FunctionalInterface
    interface DataFetcher {
        String fetch() throws URISyntaxException;
    }
}
