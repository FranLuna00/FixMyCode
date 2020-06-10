package es.albarregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootConfiguration
@EnableAutoConfiguration
@SpringBootApplication
public class FixMyCodeApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FixMyCodeApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FixMyCodeApplication.class, args);
	}

}
