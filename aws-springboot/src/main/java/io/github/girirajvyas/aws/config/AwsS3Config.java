package io.github.girirajvyas.aws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsS3Config {

  @Autowired
  public AWSCredentials amazonAWSCredentials;
  
  @Bean
  public AmazonS3 amazonS3() {
    return AmazonS3ClientBuilder 
        .standard() 
        .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials))
        .withRegion(Regions.US_EAST_2) 
        .build();
  }
  
}
