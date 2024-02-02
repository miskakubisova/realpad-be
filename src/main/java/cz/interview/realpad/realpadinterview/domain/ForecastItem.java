package cz.interview.realpad.realpadinterview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastItem {
    private Date date;
    private long temperature;
    private long feelsLikeTemperature;
    private long minTemperature;
    private long maxTemperature;
    private long pressure;
    private long humidity;
}
