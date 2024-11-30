package hu.unideb.inf.RainCheck.exception;

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

@WebMvcTest(WeatherController.class) // Test for WeatherController
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc; // Auto-wired MockMvc

    // Mock the CachedWeatherService to inject into WeatherController
    @MockBean
    private CachedWeatherService cachedWeatherService;

    @Test
    void testGetWeatherData_ExceptionHandling() throws Exception {
        // Mock the behavior of CachedWeatherService to throw an exception (example: URISyntaxException)
        String location = "Invalid Location";
        when(cachedWeatherService.getWeatherDataForOneWeek(location))
                .thenThrow(new RuntimeException("Service failure"));

        mockMvc.perform(get("/forecast/one-week")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError()) // Expecting internal server error
                .andExpect(content().string("An unexpected error occurred. Please try again later."));


        verify(cachedWeatherService).getWeatherDataForOneWeek(location);
    }


}
