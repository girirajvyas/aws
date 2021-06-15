package io.github.girirajvyas.aws.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import io.github.girirajvyas.aws.repository.UserRepository;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = UserRepository.class)
public class AwsDynamoDbConfig {

  @Autowired
  AwsCredentialsConfig awsCredentialsConfig;

  @Bean
  @Primary
  public DynamoDBMapperConfig dynamoDBMapperConfig() {
    return DynamoDBMapperConfig.DEFAULT;
  }

  @Bean
  @Primary
  public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
    return new DynamoDBMapper(amazonDynamoDB, config);
  }

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder
        .standard()
        .withCredentials(awsCredentialsConfig.awsStaticCredentialsProvider())
        .withRegion(Regions.US_EAST_2)
        .build();
  }
}
