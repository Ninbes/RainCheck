package hu.unideb.inf.RainCheck.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class ApiCallCounterService {
    private final StringRedisTemplate redisTemplate;
    private static final String COUNTER_KEY = "api-call-counter";

    public ApiCallCounterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long incrementAndGet() {
        Long count = redisTemplate.opsForValue().increment(COUNTER_KEY);
        if (count == 1) {
            redisTemplate.expire(COUNTER_KEY, Duration.ofDays(1));
        }
        return count;
    }

    public long getCount() {
        String count = redisTemplate.opsForValue().get(COUNTER_KEY);
        return count != null ? Long.parseLong(count) : 0;
    }

    public String getRemainingTTL() {
        Long ttlSeconds = redisTemplate.getExpire(COUNTER_KEY, TimeUnit.SECONDS);
        if (ttlSeconds == null || ttlSeconds < 0) {
            return "-1";
        }

        long hours = ttlSeconds / 3600;
        long minutes = (ttlSeconds % 3600) / 60;

        return String.format("%d hours and %d minutes", hours, minutes);
    }
}
