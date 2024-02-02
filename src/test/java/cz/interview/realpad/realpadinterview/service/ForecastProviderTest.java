package cz.interview.realpad.realpadinterview.service;

import cz.interview.realpad.realpadinterview.config.ForecastProviderProperties;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.exception.ForecastRetrievalException;
import cz.interview.realpad.realpadinterview.mapper.ForecastMapper;
import cz.interview.realpad.realpadinterview.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ForecastProvider} class.
 */
@ExtendWith(MockitoExtension.class)
public class ForecastProviderTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ForecastProviderProperties properties;
    private ForecastProvider forecastProvider;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        forecastProvider = new ForecastProvider(restTemplate, objectMapper, Mappers.getMapper(ForecastMapper.class), properties);
        when(properties.getUrl()).thenReturn("http://api.openweathermap.org/data/2.5/forecast?appid=testKey&units=metric&q={city}");
    }

    /**
     * Tests the successful retrieval of the forecast for all cities defined in the Cities enum.
     */
    @ParameterizedTest
    @EnumSource(Cities.class)
    void getForecastForCity_SuccessScenario(Cities city) {
        // Mock the successful response from the external API
        var responseString = TestUtils.readResourceFileAsString("test_file.json");
        doReturn(responseString).when(restTemplate).getForObject(anyString(), eq(String.class));

        var response = forecastProvider.getForecastForCity(city);

        assertThat(response).isNotNull();
        assertThat(response.getResponseItems()).hasSize(40); // Assert based on mocked data
    }

    /**
     * Tests the behavior when the RestTemplate throws a RestClientException, simulating
     * an API call failure due to an invalid API key or other client errors.
     */
    @Test
    void getForecastForCity_InvalidApiKey() {
        // Mock the RestClientException when API key is invalid
        doThrow(RestClientException.class).when(restTemplate).getForObject(anyString(), eq(String.class));

        assertThrows(ForecastRetrievalException.class, () -> forecastProvider.getForecastForCity(Cities.PRAGUE));
    }

    /**
     * Tests the handling of invalid JSON responses that cannot be deserialized into a ForecastResponse object.
     */
    @Test
    void getForecastForCity_InvalidResponse() {
        // Mock an invalid JSON response from the external API
        var responseString = TestUtils.readResourceFileAsString("invalid_test_file.json");
        doReturn(responseString).when(restTemplate).getForObject(anyString(), eq(String.class));

        assertThrows(ForecastRetrievalException.class, () -> forecastProvider.getForecastForCity(Cities.PRAGUE));
    }
}
