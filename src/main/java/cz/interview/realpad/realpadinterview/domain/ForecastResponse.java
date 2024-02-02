package cz.interview.realpad.realpadinterview.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public final class ForecastResponse {
    private int cnt;
    private List<ListItem> list;

    @Data
    @NoArgsConstructor
    public static class ListItem {
        private long dt;
        private Main main;
    }

    @Data
    @NoArgsConstructor
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;
    }
}
