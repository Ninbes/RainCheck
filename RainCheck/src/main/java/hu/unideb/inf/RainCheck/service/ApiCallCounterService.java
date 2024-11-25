package hu.unideb.inf.RainCheck.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import utils.DateUtil;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class ApiCallCounterService {
    private static final Logger logger = LoggerFactory.getLogger(ApiCallCounterService.class);
    private final StringRedisTemplate redisTemplate;
    private static final String COUNTER_KEY = "api-call-counter";

    public ApiCallCounterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long incrementAndGet() {
        logger.info("Incrementing API call counter.");
        Long count = redisTemplate.opsForValue().increment(COUNTER_KEY);

        if (count == 1) {
            long secondsUntilMidnight = DateUtil.getSecondsUntilApiReset();
            logger.info("First API call of the day. Setting expiration for counter to {} seconds.", secondsUntilMidnight);
            redisTemplate.expire(COUNTER_KEY, Duration.ofSeconds(secondsUntilMidnight));
        } else {
            logger.debug("API call counter incremented to {}", count);
        }

        return count;
    }

    public long getCount() {
        String count = redisTemplate.opsForValue().get(COUNTER_KEY);
        long result = count != null ? Long.parseLong(count) : 0;

        logger.info("Retrieved API call counter: {}", result);
        return result;
    }

    public String getRemainingTTL() {
        logger.info("Fetching remaining TTL for the API call counter.");
        Long ttlSeconds = redisTemplate.getExpire(COUNTER_KEY, TimeUnit.SECONDS);

        if (ttlSeconds == null || ttlSeconds < 0) {
            logger.warn("TTL is unavailable or expired for the API call counter.");
            return "-1";
        }

        long hours = ttlSeconds / 3600;
        long minutes = (ttlSeconds % 3600) / 60;
        String ttl = String.format("%d hours and %d minutes", hours, minutes);

        logger.info("Remaining TTL for the API call counter: {}", ttl);
        return ttl;
    }
}
