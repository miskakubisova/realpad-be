package cz.interview.realpad.realpadinterview;

import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.domain.ForecastItem;
import cz.interview.realpad.realpadinterview.service.RealpadService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for {@link RealpadController} focusing on the REST API layer.
 */
@WebMvcTest(RealpadController.class)
public class RealpadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RealpadService realpadService;

    /**
     * Tests successful retrieval of weather forecast for a valid city.
     * @throws Exception when mockMvc.perform operations fail
     */
    @Test
    public void getWeatherForecast_SuccessScenario() throws Exception {
        Forecast mockForecast = getMockForecast();
        given(realpadService.getForecastForCity(ArgumentMatchers.eq(Cities.PRAGUE))).willReturn(mockForecast);

        mockMvc.perform(get("/forecast/Prague"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.counter").value(mockForecast.getCounter())) // Example JSON path assertion
                .andExpect(jsonPath("$.responseItems[0].temperature").value(mockForecast.getResponseItems().get(0).getTemperature()));
    }

    /**
     * Tests the API response for a request with an invalid city name.
     * @throws Exception when mockMvc.perform operations fail
     */
    @Test
    public void getWeatherForecast_InvalidInput() throws Exception {
        mockMvc.perform(get("/forecast/blabla"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Creates a mock {@link Forecast} object for testing purposes.
     * @return A populated {@link Forecast} instance.
     */
    private Forecast getMockForecast() {
        Forecast mockForecast = new Forecast();
        mockForecast.setCounter(2);
        mockForecast.setResponseItems(List.of(
                ForecastItem.builder().date(Date.from(Instant.now().minus(1, ChronoUnit.DAYS))).temperature(12).feelsLikeTemperature(15).pressure(1234).humidity(67).build(),
                ForecastItem.builder().date(Date.from(Instant.now().minus(2, ChronoUnit.DAYS))).temperature(9).feelsLikeTemperature(10).pressure(1235).humidity(71).build()
        ));
        return mockForecast;
    }
}
