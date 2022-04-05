package ca.ghostteam.springulart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringulartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringulartApplication.class, args);
    }

}
