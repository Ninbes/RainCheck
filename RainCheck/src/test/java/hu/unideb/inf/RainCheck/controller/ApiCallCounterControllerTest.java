package hu.unideb.inf.RainCheck.controller;

import hu.unideb.inf.RainCheck.service.ApiCallCounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ApiCallCounterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ApiCallCounterService apiCallCounterService;

    private ApiCallCounterController apiCallCounterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiCallCounterController = new ApiCallCounterController(apiCallCounterService);
        mockMvc = MockMvcBuilders.standaloneSetup(apiCallCounterController).build();
    }

    @Test
    void testGetApiCallCount_Success() throws Exception {
        // Given
        long count = 100;
        String ttl = "1 hour";
        when(apiCallCounterService.getCount()).thenReturn(count);
        when(apiCallCounterService.getRemainingTTL()).thenReturn(ttl);

        // When & Then
        mockMvc.perform(get("/api-call-count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Total API calls made: 100. \nTime remaining: 1 hour."));

        verify(apiCallCounterService, times(1)).getCount();
        verify(apiCallCounterService, times(1)).getRemainingTTL();
    }

    @Test
    void testGetApiCallCount_ServiceThrowsException() throws Exception {
        // Given
        when(apiCallCounterService.getCount()).thenThrow(new RuntimeException("Service failure"));

        // When & Then
        mockMvc.perform(get("/api-call-count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred while fetching API call count or TTL."));

        verify(apiCallCounterService, times(1)).getCount();
    }

}
