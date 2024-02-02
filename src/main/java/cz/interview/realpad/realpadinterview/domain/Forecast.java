package cz.interview.realpad.realpadinterview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Forecast {
    private int counter;
    private List<ForecastItem> responseItems;
}
