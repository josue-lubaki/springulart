package ca.ghostteam.springulart.config.bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Configuration
@Data
@Slf4j
public class S3Config {
    private final JwtConfig jwtConfig;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsSecretKey;

    @Value("${cloud.aws.s3.region.name}")
    private String awsRegion;

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    @Bean(name="amazonS3Client")
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.fromName(awsRegion)));
        return amazonS3Client;
    }
}
