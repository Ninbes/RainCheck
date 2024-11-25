package hu.unideb.inf.RainCheck.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import utils.DateUtil;

import java.net.URISyntaxException;
import java.time.Duration;

@Service
public class CachedWeatherService {
    private static final Logger logger = LoggerFactory.getLogger(CachedWeatherService.class);

    private final WeatherService weatherService;
    private final StringRedisTemplate redisTemplate;

    public CachedWeatherService(WeatherService weatherService, StringRedisTemplate redisTemplate) {
        this.weatherService = weatherService;
        this.redisTemplate = redisTemplate;
    }

    public String getWeatherDataForOneDay(String location) throws URISyntaxException {
        String cacheKey = "weather:one-day:" + location;
        logger.info("Requesting one-day weather data for location: {}", location);
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForOneDay(location));
    }

    public String getWeatherDataForOneWeek(String location) throws URISyntaxException {
        String cacheKey = "weather:one-week:" + location;
        logger.info("Requesting one-week weather data for location: {}", location);
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForOneWeek(location));
    }

    public String getWeatherDataForTwoWeek(String location) throws URISyntaxException {
        String cacheKey = "weather:two-week:" + location;
        logger.info("Requesting two-week weather data for location: {}", location);
        return fetchAndCacheData(cacheKey, () -> weatherService.getWeatherDataForTwoWeek(location));
    }

    private String fetchAndCacheData(String cacheKey, DataFetcher fetcher) throws URISyntaxException {
        cacheKey = cacheKey.toLowerCase();
        logger.debug("Generated cache key: {}", cacheKey);

        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        if (cachedData != null) {
            logger.info("Cache hit for key: {}", cacheKey);
            return cachedData;
        }

        logger.info("Cache miss for key: {}. Fetching fresh data.", cacheKey);
        String freshData = fetcher.fetch();

        long secondsUntilMidnight = DateUtil.getSecondsUntilApiReset();
        logger.debug("Caching data for key: {} with TTL of {} seconds.", cacheKey, secondsUntilMidnight);

        redisTemplate.opsForValue().set(cacheKey, freshData, Duration.ofSeconds(secondsUntilMidnight));
        logger.info("Data cached successfully for key: {}", cacheKey);

        return freshData;
    }

    @FunctionalInterface
    interface DataFetcher {
        String fetch() throws URISyntaxException;
    }
}
