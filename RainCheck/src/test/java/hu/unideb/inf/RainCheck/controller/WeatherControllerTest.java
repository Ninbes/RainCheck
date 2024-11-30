package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.controller.WeatherController;
import hu.unideb.inf.RainCheck.service.CachedWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CachedWeatherService cachedWeatherService;

    @Test
    void testGetWeatherData_ExceptionHandling() throws Exception {
        String location = "Invalid Location";
        when(cachedWeatherService.getWeatherDataForOneWeek(location))
                .thenThrow(new RuntimeException("Service failure"));

        mockMvc.perform(get("/forecast/one-week")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An unexpected error occurred. Please try again later."));


        verify(cachedWeatherService).getWeatherDataForOneWeek(location);
    }

    @Test
    void testGetWeatherData_Success() throws Exception {
        String location = "Valid Location";
        String mockWeatherData = "{\"temperature\": 22, \"forecast\": \"Sunny\"}";

        // Mocking the successful response from CachedWeatherService
        when(cachedWeatherService.getWeatherDataForOneWeek(location)).thenReturn(mockWeatherData);

        mockMvc.perform(get("/forecast/one-week")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(content().json(mockWeatherData)); // Check if returned content matches

        // Verifying that the service method was called with the correct location
        verify(cachedWeatherService).getWeatherDataForOneWeek(location);
    }



}
