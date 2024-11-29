package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.ApiCallCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallCounterController {

    private static final Logger logger = LoggerFactory.getLogger(ApiCallCounterController.class);
    private final ApiCallCounterService apiCallCounter;

    @Autowired
    public ApiCallCounterController(ApiCallCounterService apiCallCounter) {
        this.apiCallCounter = apiCallCounter;
    }

    @GetMapping("/api-call-count")
    public String getApiCallCount() {
        logger.debug("Received request to fetch API call count and remaining TTL.");

        try {
            long count = apiCallCounter.getCount();
            logger.debug("API call count retrieved: {}", count);

            String ttl = apiCallCounter.getRemainingTTL();
            logger.debug("Remaining TTL retrieved: {}", ttl);

            String response = String.format("Total API calls made: %d. \nTime remaining: %s.", count, ttl);
            logger.info("Successfully fetched API call count and TTL. Response: {}", response);

            return response;
        } catch (Exception e) {
            logger.error("An error occurred while fetching API call count or TTL.", e);
            throw e;
        }
    }
}
