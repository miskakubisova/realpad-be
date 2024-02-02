package cz.interview.realpad.realpadinterview.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.interview.realpad.realpadinterview.config.ForecastProviderProperties;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.domain.ForecastResponse;
import cz.interview.realpad.realpadinterview.exception.ForecastRetrievalException;
import cz.interview.realpad.realpadinterview.mapper.ForecastMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * Provides weather forecast information by interacting with an external weather service.
 */
@Component
public class ForecastProvider {

    private final Logger log = LoggerFactory.getLogger(ForecastProvider.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final ForecastMapper forecastMapper;
    private final ForecastProviderProperties properties;

    /**
     * Constructs a ForecastProvider with the required dependencies.
     *
     * @param restTemplate     The RestTemplate used for making HTTP requests.
     * @param mapper           The ObjectMapper for JSON deserialization.
     * @param forecastMapper   The mapper for converting JSON responses to domain objects.
     * @param properties       Configuration properties for the forecast provider.
     */
    public ForecastProvider(final RestTemplate restTemplate,
                            final ObjectMapper mapper,
                            final ForecastMapper forecastMapper,
                            final ForecastProviderProperties properties) {
        this.restTemplate = restTemplate;
        this.mapper = configureObjectMapper(mapper);
        this.forecastMapper = forecastMapper;
        this.properties = properties;
    }

    private ObjectMapper configureObjectMapper(ObjectMapper mapper) {
        return mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Retrieves the forecast for a specified city.
     *
     * @param city The city for which to retrieve the forecast.
     * @return The forecast for the specified city.
     * @throws RestClientException if there is an error during the HTTP request.
     */
    public Forecast getForecastForCity(Cities city) {
        String url = UriComponentsBuilder.fromHttpUrl(properties.getUrl())
                .buildAndExpand(city.name())
                .toUriString();
        try {
            log.info("Fetching forecast data for URL: {}", url);
            String response = restTemplate.getForObject(url, String.class);
            ForecastResponse forecastResponse = mapper.readValue(response, ForecastResponse.class);
            return forecastMapper.jsonToForecastResponse(forecastResponse);
        } catch (RestClientException e) {
            String errorMessage = "Unable to retrieve forecast data for city: " + city.name();
            log.error(errorMessage, e);
            throw new ForecastRetrievalException(errorMessage, e);
        } catch (IOException e) {
            String errorMessage = "Unable to parse response for city: " + city.name();
            log.error(errorMessage, e);
            throw new ForecastRetrievalException(errorMessage, e);
        }
    }
}
