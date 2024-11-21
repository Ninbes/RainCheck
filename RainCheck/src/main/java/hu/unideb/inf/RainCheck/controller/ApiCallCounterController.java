package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.ApiCallCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallCounterController {

    private final ApiCallCounterService apiCallCounter;

    @Autowired
    public ApiCallCounterController(ApiCallCounterService apiCallCounter) {
        this.apiCallCounter = apiCallCounter;
    }

    @GetMapping("/api-call-count")
    public String getApiCallCount() {
        long count = apiCallCounter.getCount();

        String ttl = apiCallCounter.getRemainingTTL();

        return String.format("Total API calls made: %d. \nTime remaining: %s.", count, ttl);
    }
}
