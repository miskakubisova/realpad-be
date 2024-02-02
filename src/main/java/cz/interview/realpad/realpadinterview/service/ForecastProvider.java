package cz.interview.realpad.realpadinterview.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.interview.realpad.realpadinterview.config.ForecastProviderProperties;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.domain.ForecastResponse;
import cz.interview.realpad.realpadinterview.mapper.ForecastMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class ForecastProvider {

    private static final Logger log = LoggerFactory.getLogger(ForecastProvider.class);
    public static final String CITY_PARAM_PLACEHOLDER = "{city}";

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final ForecastMapper forecastMapper;
    private final ForecastProviderProperties properties;

    public ForecastProvider(final RestTemplate restTemplate,
                            final ObjectMapper mapper,
                            final ForecastMapper forecastMapper,
                            final ForecastProviderProperties properties) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.forecastMapper = forecastMapper;
        this.properties = properties;
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public Forecast getForecastForCity(Cities city) {
        String url = properties.getUrl().replace(CITY_PARAM_PLACEHOLDER, city.name());
        try {
            var response = restTemplate.getForObject(url, String.class);
            var forecastResponse = mapper.readValue(response, ForecastResponse.class);
            return forecastMapper.jsonToForecastResponse(forecastResponse);
        } catch (RestClientException e) {
            log.error("Unable to retrieve forecast data.");
            throw e;
        } catch (IOException e) {
            log.error("Unable to parse response.");
            throw new IllegalArgumentException("Invalid JSON format or unexpected data structure.");
        }
    }
}
