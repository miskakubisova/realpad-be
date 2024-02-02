package cz.interview.realpad.realpadinterview.domain;

import java.util.Date;

public class ForecastItem {
    private final Date date;
    private final long temperature;
    private final long feelsLikeTemperature;
    private final long minTemperature;
    private final long maxTemperature;
    private final long pressure;
    private final long humidity;

    public ForecastItem(Date date, long temperature, long feelsLikeTemperature, long minTemperature, long maxTemperature, long pressure, long humidity) {
        this.date = date;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Date getDate() {
        return this.date;
    }

    public long getTemperature() {
        return this.temperature;
    }

    public long getFeelsLikeTemperature() {
        return this.feelsLikeTemperature;
    }

    public long getMinTemperature() {
        return this.minTemperature;
    }

    public long getMaxTemperature() {
        return this.maxTemperature;
    }

    public long getPressure() {
        return this.pressure;
    }

    public long getHumidity() {
        return this.humidity;
    }
}
