package ca.ghostteam.springulart.config.bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.awspring.cloud.ses.SimpleEmailServiceJavaMailSender;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Configuration
public class MailConfig {

//    private final S3Config s3Config;
//    private final AWSCredentials awsCredentials;
//
//    public MailConfig(S3Config s3Config,
//                      AWSCredentials awsCredentials) {
//        this.s3Config = s3Config;
//        this.awsCredentials = awsCredentials;
//    }

//    @Bean(name = "mailSender")
//    public SimpleEmailServiceJavaMailSender getMailer(AmazonSimpleEmailService amazonSimpleMailService) {
//        return new SimpleEmailServiceJavaMailSender(amazonSimpleMailService);
//    }

//    @Bean
//    public AmazonSimpleEmailService amazonSimpleMailService() {
//        return AmazonSimpleEmailServiceClientBuilder
//                .standard()
//                .withRegion(s3Config.getAwsRegion())
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .build();
//    }
//
//    @Bean
//    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleMailService) {
//        return new SimpleEmailServiceMailSender(amazonSimpleMailService);
//    }


    @Bean(name = "myMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("josuelubaki@gmail.com");
        mailSender.setPassword("pbmotbufkcplsvmi");
        mailSender.setJavaMailProperties(javaMailProperties());
        return mailSender;
    }

    private Properties javaMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        return properties;
    }
}