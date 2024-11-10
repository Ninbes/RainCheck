package hu.unideb.inf.RainCheck.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherData(String location) {
        String url = UriComponentsBuilder.fromHttpUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + location)
                .queryParam("unitGroup", "us")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}

