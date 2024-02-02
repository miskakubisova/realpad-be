package cz.interview.realpad.realpadinterview.mapper;

import cz.interview.realpad.realpadinterview.domain.Forecast;
import cz.interview.realpad.realpadinterview.domain.ForecastResponse;
import cz.interview.realpad.realpadinterview.domain.ForecastItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ForecastMapper {

    @Mapping(target = "counter", source = "cnt")
    @Mapping(target = "responseItems", source = "list")
    Forecast jsonToForecastResponse(ForecastResponse response);

    @Mapping(target = "date", expression = "java(new Date(listItem.dt*1000))")
    @Mapping(target = "temperature", source = "main.temp")
    @Mapping(target = "feelsLikeTemperature", source = "main.feels_like")
    @Mapping(target = "minTemperature", source = "main.temp_min")
    @Mapping(target = "maxTemperature", source = "main.temp_max")
    @Mapping(target = "pressure", source = "main.pressure")
    @Mapping(target = "humidity", source = "main.humidity")
    ForecastItem listItemToForecastItem(ForecastResponse.ListItem listItem);

}
