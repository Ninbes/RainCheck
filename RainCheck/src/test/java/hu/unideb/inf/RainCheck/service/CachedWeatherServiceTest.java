package hu.unideb.inf.RainCheck.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachedWeatherServiceTest {

    private CachedWeatherService cachedWeatherService;
    private WeatherService weatherService;
    private StringRedisTemplate redisTemplate;
    private ValueOperations<String, String> valueOperations;

    @BeforeEach
    void setUp() {
        weatherService = mock(WeatherService.class);
        redisTemplate = mock(StringRedisTemplate.class);
        valueOperations = mock(ValueOperations.class);

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        cachedWeatherService = new CachedWeatherService(weatherService, redisTemplate);
    }

    @Test
    void testGetWeatherDataForOneDay_CacheHit() throws URISyntaxException {
        String location = "Paris";
        String cacheKey = "weather:one-day:paris";
        String cachedData = "Cached Weather Data for Paris";

        when(valueOperations.get(cacheKey)).thenReturn(cachedData);

        String result = cachedWeatherService.getWeatherDataForOneDay(location);

        assertEquals(cachedData, result);
        verify(weatherService, never()).getWeatherDataForOneDay(anyString());
        verify(valueOperations).get(cacheKey);
    }

    @Test
    void testGetWeatherDataForOneDay_CacheMiss() throws URISyntaxException {
        String location = "Paris";
        String cacheKey = "weather:one-day:paris";
        String freshData = "Fresh Weather Data for Paris";

        when(valueOperations.get(cacheKey)).thenReturn(null);
        when(weatherService.getWeatherDataForOneDay(location)).thenReturn(freshData);

        String result = cachedWeatherService.getWeatherDataForOneDay(location);

        assertEquals(freshData, result);
        verify(weatherService).getWeatherDataForOneDay(location);
        verify(valueOperations).set(eq(cacheKey), eq(freshData), any(Duration.class));
    }

    @Test
    void testGetWeatherDataForOneWeek_CacheMiss() throws URISyntaxException {
        String location = "Berlin";
        String cacheKey = "weather:one-week:berlin";
        String freshData = "Fresh Weather Data for Berlin";

        when(valueOperations.get(cacheKey)).thenReturn(null);
        when(weatherService.getWeatherDataForOneWeek(location)).thenReturn(freshData);

        String result = cachedWeatherService.getWeatherDataForOneWeek(location);

        assertEquals(freshData, result);
        verify(weatherService).getWeatherDataForOneWeek(location);
        verify(valueOperations).set(eq(cacheKey), eq(freshData), any(Duration.class));
    }

    @Test
    void testGetWeatherDataForTwoWeek_CacheMiss() throws URISyntaxException {
        String location = "London";
        String cacheKey = "weather:two-week:london";
        String freshData = "Fresh Weather Data for London";

        when(valueOperations.get(cacheKey)).thenReturn(null);
        when(weatherService.getWeatherDataForTwoWeek(location)).thenReturn(freshData);

        String result = cachedWeatherService.getWeatherDataForTwoWeek(location);

        assertEquals(freshData, result);
        verify(weatherService).getWeatherDataForTwoWeek(location);
        verify(valueOperations).set(eq(cacheKey), eq(freshData), any(Duration.class));
    }
}
