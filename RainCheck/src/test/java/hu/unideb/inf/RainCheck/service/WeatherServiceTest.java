package hu.unideb.inf.RainCheck.service;

import hu.unideb.inf.RainCheck.config.VisualCrossingConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private WeatherService weatherService;
    private RestTemplate restTemplate;
    private ApiCallCounterService apiCallCounterService;
    private VisualCrossingConfig visualCrossingConfig;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        apiCallCounterService = mock(ApiCallCounterService.class);
        visualCrossingConfig = mock(VisualCrossingConfig.class);

        weatherService = new WeatherService(restTemplate, apiCallCounterService, visualCrossingConfig);
    }

    @Test
    void testGetWeatherDataForOneDay() throws URISyntaxException {
        String location = "Paris";
        String today = "2024-11-30";
        String expectedUrl = "http://mocked-url";
        String expectedResponse = "{\"weather\":\"sunny\"}";

        when(visualCrossingConfig.getBaseUrl()).thenReturn("http://mocked-url/");
        when(visualCrossingConfig.getKey()).thenReturn("mocked-api-key");
        when(restTemplate.getForObject(any(URI.class), eq(String.class))).thenReturn(expectedResponse);
        when(apiCallCounterService.incrementAndGet()).thenReturn(1L);

        String result = weatherService.getWeatherDataForOneDay(location);

        assertEquals(expectedResponse, result);
        verify(apiCallCounterService).incrementAndGet();
        verify(restTemplate).getForObject(any(URI.class), eq(String.class));
    }

    @Test
    void testGetWeatherDataForOneWeek() throws URISyntaxException {
        String location = "Berlin";
        String today = "2024-11-30";
        String oneWeek = "2024-12-07";
        String expectedUrl = "http://mocked-url";
        String expectedResponse = "{\"weather\":\"rainy\"}";

        when(visualCrossingConfig.getBaseUrl()).thenReturn("http://mocked-url/");
        when(visualCrossingConfig.getKey()).thenReturn("mocked-api-key");
        when(restTemplate.getForObject(any(URI.class), eq(String.class))).thenReturn(expectedResponse);

        String result = weatherService.getWeatherDataForOneWeek(location);

        assertEquals(expectedResponse, result);
        verify(restTemplate).getForObject(any(URI.class), eq(String.class));
    }

    @Test
    void testGetWeatherDataForTwoWeek() throws URISyntaxException {
        String location = "London";
        String today = "2024-11-30";
        String twoWeek = "2024-12-14";
        String expectedUrl = "http://mocked-url";
        String expectedResponse = "{\"weather\":\"cloudy\"}";

        when(visualCrossingConfig.getBaseUrl()).thenReturn("http://mocked-url/");
        when(visualCrossingConfig.getKey()).thenReturn("mocked-api-key");
        when(restTemplate.getForObject(any(URI.class), eq(String.class))).thenReturn(expectedResponse);

        String result = weatherService.getWeatherDataForTwoWeek(location);

        assertEquals(expectedResponse, result);
        verify(restTemplate).getForObject(any(URI.class), eq(String.class));
    }
}
