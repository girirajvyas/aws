package io.github.girirajvyas.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

@Configuration
public class AwsCredentialsConfig {

  @Value("${amazon.aws.accesskey}")
  private String amazonAwsAccessKey;
  
  @Value("${amazon.aws.secretkey}")
  private String amazonAwsSecretKey;
  
  @Bean
  public AWSCredentials amazonAWSCredentials() {
      return new BasicAWSCredentials(amazonAwsAccessKey, amazonAwsSecretKey);
  }
  
}
