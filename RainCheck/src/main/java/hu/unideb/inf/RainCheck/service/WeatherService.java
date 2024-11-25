package hu.unideb.inf.RainCheck.service;

import hu.unideb.inf.RainCheck.config.VisualCrossingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import utils.DateUtil;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

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

        logger.info("Fetching weather data for one day. Location: {}, Date: {}", location, today);
        logger.debug("Constructed URL: {}", url);

        apiCallCounter.incrementAndGet();
        String response = restTemplate.getForObject(new URI(url), String.class);

        logger.info("Weather data for one day retrieved successfully.");
        logger.debug("Response: {}", response);

        return response;
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

        logger.info("Fetching weather data for one week. Location: {}, Date range: {} to {}", location, today, oneWeek);
        logger.debug("Constructed URL: {}", url);

        String response = restTemplate.getForObject(new URI(url), String.class);

        logger.info("Weather data for one week retrieved successfully.");
        logger.debug("Response: {}", response);

        return response;
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

        logger.info("Fetching weather data for two weeks. Location: {}, Date range: {} to {}", location, today, twoWeek);
        logger.debug("Constructed URL: {}", url);

        String response = restTemplate.getForObject(new URI(url), String.class);

        logger.info("Weather data for two weeks retrieved successfully.");
        logger.debug("Response: {}", response);

        return response;
    }
}
