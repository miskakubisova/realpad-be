package cz.interview.realpad.realpadinterview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotEmpty;

@Configuration
@PropertySource("classpath:application.properties")
public class ForecastProviderProperties {

    @NotEmpty
    @Value("${forecast.url}")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
