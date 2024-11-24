package hu.unideb.inf.RainCheck.service;

import hu.unideb.inf.RainCheck.config.VisualCrossingConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utils.DateUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final ApiCallCounterService apiCallCounter;
    private final VisualCrossingConfig visualCrossingConfig;

    public WeatherService(RestTemplate restTemplate, ApiCallCounterService apiCallCounter, VisualCrossingConfig visualCrossingConfig) {
        this.restTemplate = restTemplate;
        this.apiCallCounter = apiCallCounter;
        this.visualCrossingConfig = visualCrossingConfig;
    }

    public String getWeatherDataForOneDay(String location) throws URISyntaxException {
        String today = DateUtil.getToday();
        String url = UriComponentsBuilder.fromHttpUrl(visualCrossingConfig.getBaseUrl()
                        + location + "/" + today + "/" + today)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,temp,precip,preciptype,precipcover,windspeedmax")
                .queryParam("include", "hours")
                .queryParam("key", visualCrossingConfig.getKey())
                .queryParam("contentType", "json")
                .toUriString();
        apiCallCounter.incrementAndGet();
        return restTemplate.getForObject(new URI(url), String.class);
    }
    public String getWeatherDataForOneWeek(String location) throws URISyntaxException {
        String today = DateUtil.getToday();
        String oneWeek = DateUtil.getSevenDaysFromToday();

        String url = UriComponentsBuilder.fromHttpUrl(visualCrossingConfig.getBaseUrl()
                        + location + "/" + today + "/" + oneWeek)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,precip,preciptype,windspeedmax")
                .queryParam("include", "days")
                .queryParam("key", visualCrossingConfig.getKey())
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(new URI(url), String.class);
    }
    public String getWeatherDataForTwoWeek(String location) throws URISyntaxException {
        String today = DateUtil.getToday();
        String twoWeek = DateUtil.getFourteenDaysFromToday();

        String url = UriComponentsBuilder.fromHttpUrl(visualCrossingConfig.getBaseUrl()
                        + location + "/" + today + "/" + twoWeek)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,tempmax,tempmin,precip,preciptype,windspeedmax")
                .queryParam("include", "days")
                .queryParam("key", visualCrossingConfig.getKey())
                .queryParam("contentType", "json")
                .toUriString();

        return restTemplate.getForObject(new URI(url), String.class);
    }
}


