package cz.interview.realpad.realpadinterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RealpadInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealpadInterviewApplication.class, args);
	}

}
