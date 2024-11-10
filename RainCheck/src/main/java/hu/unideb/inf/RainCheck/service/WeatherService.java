package hu.unideb.inf.RainCheck.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utils.DateUtil;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherDataForOneDay(String location) {
        String today = DateUtil.getToday();

        String url = UriComponentsBuilder.fromHttpUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + location + "/" + today + "/" + today)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,temp,precip,preciptype,precipcover,windspeedmax")
                .queryParam("include", "hours")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
    public String getWeatherDataForOneWeek(String location) {
        String today = DateUtil.getToday();
        String oneWeek = DateUtil.getSevenDaysFromToday();

        String url = UriComponentsBuilder.fromHttpUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + location + "/" + today + "/" + oneWeek)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,precip,preciptype,windspeedmax")
                .queryParam("include", "days")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
    public String getWeatherDataForTwoWeek(String location) {
        String today = DateUtil.getToday();
        String twoWeek = DateUtil.getFourteenDaysFromToday();

        String url = UriComponentsBuilder.fromHttpUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + location + "/" + today + "/" + twoWeek)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,precip,preciptype,windspeedmax")
                .queryParam("include", "days")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}


