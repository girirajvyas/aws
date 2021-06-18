package io.github.girirajvyas.aws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;

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

  public void deleteBucket(String bucketName) {
    amazonS3.deleteBucket(bucketName);
  }

  public ObjectListing listObjects(String bucketName) {
    return amazonS3.listObjects(bucketName);
  }

  public S3Object getObject(String bucketName, String objectKey) {
    return amazonS3.getObject(bucketName, objectKey);
  }

  public CopyObjectResult copyObject(String sourceBucketName, String sourceKey,
      String destinationBucketName, String destinationKey) {
    return amazonS3.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
  }
}
