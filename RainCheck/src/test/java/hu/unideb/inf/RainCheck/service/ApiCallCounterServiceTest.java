package hu.unideb.inf.RainCheck.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

class ApiCallCounterServiceTest {

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    @InjectMocks
    private ApiCallCounterService apiCallCounterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testIncrementAndGet_FirstCall_SetsExpiration() {
        when(valueOperations.increment(ApiCallCounterService.getCounterKey())).thenReturn(1L);

        when(redisTemplate.expire(eq(ApiCallCounterService.getCounterKey()), any(Duration.class))).thenReturn(true);

        long count = apiCallCounterService.incrementAndGet();

        assertEquals(1L, count);

        verify(valueOperations).increment(ApiCallCounterService.getCounterKey());
        verify(redisTemplate).expire(eq(ApiCallCounterService.getCounterKey()), any(Duration.class));
    }

    @Test
    void testIncrementAndGet_SubsequentCall() {
        when(valueOperations.increment(ApiCallCounterService.getCounterKey())).thenReturn(2L);

        long count = apiCallCounterService.incrementAndGet();

        assertEquals(2L, count);
        verify(valueOperations).increment(ApiCallCounterService.getCounterKey());
        verify(redisTemplate, never()).expire(anyString(), any(Duration.class));
    }

    @Test
    void testGetCount_CounterExists() {
        when(valueOperations.get(ApiCallCounterService.getCounterKey())).thenReturn("5");

        long count = apiCallCounterService.getCount();

        assertEquals(5L, count);
        verify(valueOperations).get(ApiCallCounterService.getCounterKey());
    }

    @Test
    void testGetCount_CounterDoesNotExist() {
        when(valueOperations.get(ApiCallCounterService.getCounterKey())).thenReturn(null);

        long count = apiCallCounterService.getCount();

        assertEquals(0L, count);
        verify(valueOperations).get(ApiCallCounterService.getCounterKey());
    }

    @Test
    void testGetRemainingTTL_TTLExists() {
        when(redisTemplate.getExpire(ApiCallCounterService.getCounterKey(), TimeUnit.SECONDS)).thenReturn(7200L); // 2 hours

        String ttl = apiCallCounterService.getRemainingTTL();

        assertEquals("2 hours and 0 minutes", ttl);
        verify(redisTemplate).getExpire(ApiCallCounterService.getCounterKey(), TimeUnit.SECONDS);
    }

    @Test
    void testGetRemainingTTL_TTLExpired() {
        when(redisTemplate.getExpire(ApiCallCounterService.getCounterKey(), TimeUnit.SECONDS)).thenReturn(-1L);

        String ttl = apiCallCounterService.getRemainingTTL();

        assertEquals("-1", ttl);
        verify(redisTemplate).getExpire(ApiCallCounterService.getCounterKey(), TimeUnit.SECONDS);
    }
}
