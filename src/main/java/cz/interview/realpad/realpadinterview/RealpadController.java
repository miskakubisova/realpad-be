package cz.interview.realpad.realpadinterview;

import cz.interview.realpad.realpadinterview.config.ApiUrls;
import cz.interview.realpad.realpadinterview.domain.Cities;
import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.service.RealpadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class RealpadController {

    private final RealpadService realpadService;

    public RealpadController(final RealpadService realpadService) {
        this.realpadService = realpadService;
    }

    @RequestMapping(value = ApiUrls.URL_CITIES, method = RequestMethod.GET)
    public List<Cities> getCities() {
        return List.of(Cities.values());
    }

    @RequestMapping(value = ApiUrls.URL_FORECAST_CITY, method = RequestMethod.GET)
    public ResponseEntity<Forecast> getForecastForCity(@PathVariable String cityName) {
        try {
            var city = Cities.valueOf(cityName.toUpperCase());
            return ResponseEntity.ok(realpadService.getForecastForCity(city));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
