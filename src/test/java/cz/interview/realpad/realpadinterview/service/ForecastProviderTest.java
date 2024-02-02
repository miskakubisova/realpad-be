package cz.interview.realpad.realpadinterview.service;

import cz.interview.realpad.realpadinterview.config.ForecastProviderProperties;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.mapper.ForecastMapper;
import cz.interview.realpad.realpadinterview.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ForecastProviderTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ForecastProviderProperties properties;
    private ForecastProvider forecastProvider;

    @BeforeEach
    public void setUp() {
        forecastProvider = new ForecastProvider(restTemplate, Jackson2ObjectMapperBuilder.json().build(), Mappers.getMapper(ForecastMapper.class), properties);
        when(properties.getUrl()).thenReturn("http://api.openweathermap.org");
    }

    @ParameterizedTest
    @EnumSource(Cities.class)
    void getForecastForCity_SuccessScenario(Cities city) {
        var responseString = TestUtils.readResourceFileAsString("test_file.json");
        doReturn(responseString).when(restTemplate).getForObject(anyString(), eq(String.class));

        var response = forecastProvider.getForecastForCity(city);

        assertThat(response).isNotNull();
        assertThat(response.getResponseItems()).hasSize(40);
    }

    @Test
    void getForecastForCity_InvalidApiKey() {
        doThrow(RestClientException.class).when(restTemplate).getForObject(anyString(), eq(String.class));

        assertThrows(RestClientException.class, () -> forecastProvider.getForecastForCity(Cities.PRAGUE));
    }

    @Test
    void getForecastForCity_InvalidResponse() {
        var responseString = TestUtils.readResourceFileAsString("invalid_test_file.txt");
        doReturn(responseString).when(restTemplate).getForObject(anyString(), eq(String.class));

        assertThrows(IllegalArgumentException.class, () -> forecastProvider.getForecastForCity(Cities.PRAGUE));
    }
}
