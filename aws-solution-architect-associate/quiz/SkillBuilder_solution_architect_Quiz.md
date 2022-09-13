19. A company has an application that runs on a large general purpose 


20. A company needs to maintain data records for a minimum of 5 years. Thedata is rarely accessed after it is stored. The data must be accessible withing 2 hours.
Which solution will meet these requirements MOST cost effectively?

A) Store the data in Amazon Elastic File System (EFS) file system. Access the data by using AWS Direct Connect.
Incorrect: The solution is not the most cost effective solution. Amazon S3 is a more cost-effective  solution if there is not a requiremet for a file system.
For more information about Amazon EFS and Direct connect, See https://docs.aws.amazon.com/efs/latest/ug/how-it-works.html#how-it-works-direct-connect

B) (Amazon EBS) volume. Create snapshots. Store the snap shots in an Amazon S3 bucket
Incorrect: The solution is not the most cost-effective solution because it requires the use of Amazon EBS in addition to Amazon S3

For more information about EBS snapshots, See https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSSnapshots.html

C) Store the data in an Amazon S3 bucket. Use an S3 Lifecycle policy to move the data to S3 standard Infrequent Access (Standard-IA) 
Incorrect: The storage if the data in an S3 bucket provides a cost effective initial location for the data. However, S3 Standard-IA is not the most cost-effective storage class to meet the requirements in the scenario. The S3 Glacier storage class is designed for low-cost data archiving.
For more information about S3 storage classes, see https://docs.aws.amazon.com/AmazonS3/latest/userguide/storage-class-intro.html

D) Store the data ina an Amazon S3 bucket. Use an S3 Lifecycle policy to move the data to S3 Glacier Instant Retrieval.
Correct: The storage of the data in an S3 bucket provides a cost-effective initial location for the data S3 Glacier Instant retrieval is the most cost-effective archival storage Solution that meets the requirement of a 2 hour retrieval time.

For more information about how to move data between S3 storage classes automatically, see https://docs.aws.amazon.com/AmazonS3/latest/userguide/object-lifecycle-mgmt.html

For more information about S3 storage classes, See https://docs.aws.amazon.com/AmazonS3/latest/userguide/storage-class-intro.html