package ca.ghostteam.springulart.config.bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.google.common.net.HttpHeaders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-20
 */
@ConfigurationProperties(prefix = "application.springulart")
@Data
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Long tokenExpirationAfterDays;
    private String applicationName;

    @Bean
    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }

}
