package ca.ghostteam.springulart.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Configuration
public class PasswordConfig {

    /**
     * encryption method used in the application
     * @see BCryptPasswordEncoder function
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
