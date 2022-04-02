package ca.ghostteam.springulart.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getMailer() {
        return new JavaMailSenderImpl();
    }
}
