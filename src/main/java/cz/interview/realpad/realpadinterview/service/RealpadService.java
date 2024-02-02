package cz.interview.realpad.realpadinterview.service;

import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import org.springframework.stereotype.Service;

@Service
public class RealpadService {

    private final ForecastProvider forecastProvider;

    public RealpadService(final ForecastProvider forecastProvider) {
        this.forecastProvider = forecastProvider;
    }

    public Forecast getForecastForCity(Cities city) {
        return forecastProvider.getForecastForCity(city);
    }
}
