package dev.nano.awsupload.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "your access key",
                "your secret key"
        );
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("region of your bucket") // specify the region of yr bucket
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
