package cz.interview.realpad.realpadinterview.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@Configuration
@PropertySource("classpath:application.properties")
public class ForecastProviderProperties {

    @NotEmpty
    @Value("${forecast.url}")
    private String url;
}
