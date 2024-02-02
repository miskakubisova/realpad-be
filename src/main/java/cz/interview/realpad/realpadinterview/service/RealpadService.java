package cz.interview.realpad.realpadinterview.service;

import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service layer responsible for handling business logic related to real estate forecasts.
 * (Might be pointless in this little scenario, but I wanted to show that it is good practice
 * to wrap provider logic into separate service, where more business logic can be placed)
 */
@Service
public class RealpadService {

    private static final Logger log = LoggerFactory.getLogger(RealpadService.class);

    private final ForecastProvider forecastProvider;

    public RealpadService(final ForecastProvider forecastProvider) {
        this.forecastProvider = forecastProvider;
    }

    /**
     * Retrieves the weather forecast for a given city.
     *
     * @param city The city for which to retrieve the forecast.
     * @return The forecast for the given city.
     */
    public Forecast getForecastForCity(Cities city) {
        log.debug("Getting forecast for city: {}", city);
        return forecastProvider.getForecastForCity(city);
    }
}

