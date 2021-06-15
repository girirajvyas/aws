package io.github.girirajvyas.aws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.s3.model.Bucket;
import io.github.girirajvyas.aws.service.AwsDynamoDbService;
import io.github.girirajvyas.aws.service.AwsS3Service;

@RestController()
@RequestMapping("AwsController")
public class AwsController {

  @Autowired
  private AwsS3Service awsS3Service;
  
  @Autowired
  private AwsDynamoDbService awsDynamoDbService;
  
  @GetMapping("/listBuckets")
  public List<Bucket> getBuckets() {
    List<Bucket> buckets = awsS3Service.listBuckets();
    for(Bucket bucket : buckets) {
        System.out.println(bucket.getName());
    }
    awsDynamoDbService.save();
    return buckets;
  }
}
