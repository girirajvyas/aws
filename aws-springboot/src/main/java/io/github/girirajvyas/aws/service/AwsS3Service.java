package io.github.girirajvyas.aws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

@Service
public class AwsS3Service {

  @Autowired
  public AmazonS3 amazonS3;
  
  public boolean doesBucketExist(String bucketName) {
    return amazonS3.doesBucketExistV2(bucketName);
  }
  
  public Bucket createBucket(String bucketName) {
    return amazonS3.createBucket(bucketName);
  }
  
  public List<Bucket> listBuckets() {
    return amazonS3.listBuckets();
  }
  
}
