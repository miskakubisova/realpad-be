package cz.interview.realpad.realpadinterview.domain;

import java.util.List;

public final class ForecastResponse {
    public int cnt;
    public List<ListItem> list;

    public ForecastResponse() {
    }

    public ForecastResponse(int cnt, List<ListItem> list) {
        this.cnt = cnt;
        this.list = list;
    }

    public int getCnt() {
        return this.cnt;
    }

    public List<ListItem> getList() {
        return this.list;
    }

    public static class ListItem {
        public long dt;
        public Main main;

        public ListItem() {
        }

        public ListItem(long dt, Main main) {
            this.dt = dt;
            this.main = main;
        }

        public long getDt() {
            return this.dt;
        }

        public Main getMain() {
            return this.main;
        }
    }

    public static class Main {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;

        public Main() {
        }

        public Main(double temp, double feels_like, double temp_min, double temp_max, int pressure, int humidity) {
            this.temp = temp;
            this.feels_like = feels_like;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public double getTemp() {
            return this.temp;
        }

        public double getFeels_like() {
            return this.feels_like;
        }

        public double getTemp_min() {
            return this.temp_min;
        }

        public double getTemp_max() {
            return this.temp_max;
        }

        public int getPressure() {
            return this.pressure;
        }

        public int getHumidity() {
            return this.humidity;
        }
    }
}
