package cz.interview.realpad.realpadinterview.domain;

import java.util.List;

public final class Forecast {
    private final int counter;
    private final List<ForecastItem> responseItems;

    public Forecast(int counter, List<ForecastItem> responseItems) {
        this.counter = counter;
        this.responseItems = responseItems;
    }

    public int getCounter() {
        return this.counter;
    }

    public List<ForecastItem> getResponseItems() {
        return this.responseItems;
    }
}
