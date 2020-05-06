package es.albarregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootConfiguration
@EnableAutoConfiguration
@SpringBootApplication
public class FixMyCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixMyCodeApplication.class, args);
	}

}
