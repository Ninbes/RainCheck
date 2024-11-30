package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.controller.WeatherController;
import hu.unideb.inf.RainCheck.service.CachedWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.never;
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

        when(cachedWeatherService.getWeatherDataForOneWeek(location)).thenReturn(mockWeatherData);

        mockMvc.perform(get("/forecast/one-week")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mockWeatherData));

        verify(cachedWeatherService).getWeatherDataForOneWeek(location);
    }

    @Test
    void testGetWeatherData_BadRequest() throws Exception {
        String location = "";

        mockMvc.perform(get("/forecast/one-week")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid location parameter"));

        verify(cachedWeatherService, never()).getWeatherDataForOneWeek(location);
    }

    @Test
    void testGetWeatherData_NoHandlerFound() throws Exception {
        mockMvc.perform(get("/forecast/non-existent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
