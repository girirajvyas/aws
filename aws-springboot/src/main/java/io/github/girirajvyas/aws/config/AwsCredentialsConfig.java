package io.github.girirajvyas.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class AwsCredentialsConfig {

  @Value("${amazon.aws.accesskey}")
  private String amazonAwsAccessKey;
  
  @Value("${amazon.aws.secretkey}")
  private String amazonAwsSecretKey;
  
  
  private AWSCredentials amazonAWSCredentials() {
      return new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey);
  }
  
  @Bean
  public AWSCredentialsProvider awsStaticCredentialsProvider() {
    return new AWSStaticCredentialsProvider(amazonAWSCredentials());
  }
}
