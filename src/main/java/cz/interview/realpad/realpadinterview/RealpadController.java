package cz.interview.realpad.realpadinterview;

import cz.interview.realpad.realpadinterview.config.ApiUrls;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.service.RealpadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing weather forecast data.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RealpadController {

    private static final Logger log = LoggerFactory.getLogger(RealpadController.class);
    private final RealpadService realpadService;

    public RealpadController(final RealpadService realpadService) {
        this.realpadService = realpadService;
    }

    /**
     * Endpoint to retrieve all available cities.
     *
     * @return a list of cities.
     */
    @GetMapping(ApiUrls.URL_CITIES)
    public List<String> getCities() {
        return Arrays.stream(Cities.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    /**
     * Endpoint to retrieve the forecast for a specified city.
     *
     * @param cityName the city name for which to retrieve the forecast.
     * @return a {@link ResponseEntity} containing the forecast data or an error message.
     */
    @GetMapping(ApiUrls.URL_FORECAST_CITY)
    public ResponseEntity<Forecast> getForecastForCity(@PathVariable String cityName) {
        try {
            Cities city = Cities.valueOf(cityName.toUpperCase());
            Forecast forecast = realpadService.getForecastForCity(city);
            return ResponseEntity.ok(forecast);
        } catch (IllegalArgumentException e) {
            log.error("Invalid city name provided: {}", cityName, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid city name: " + cityName);
        }
    }
}
