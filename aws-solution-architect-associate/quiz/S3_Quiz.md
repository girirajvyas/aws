1. You have a 25 GB file that you're trying to upload to S3 but you're getting errors. What is a possible solution for this?
a. The file size limit on S3 is 5GB
b. Update your bucket policy to allow the large file
c. Use Multi-part upload when uploading files larger than 5GB
d. Encrypt the file
Ans:
c, Correct, Multi-Part Upload is recommended as soon as the file is over 100 MB.

2. You're getting errors while trying to create a new S3 bucket named "dev". You're using a new AWS Account with no S3 buckets created before. What is a possible cause for this?
a. You're missing IAM permissions to create an S3 bucket
b. S3 bucket names must be globally unique and "dev" is already taken
Ans: 
b, Correct, 

3. You have enabled versioning in your S3 bucket which already contains a lot of files. Which version will the existing files have?
a. 1
b. 0
c. -1
d. null
Ans:
d, Correct

4. Your client wants to make sure that file encryption is happening in S3, but he wants to fully manage the encryption keys and never store them in AWS. You recommend him to use ............................
a. SSE-S3
b. SSE-KMS
c. SSE-C
d. Client-side Encryption
Ans:
d, Incorrect, With Client-Side Encryption, you have to do the encryption yourself and you have full control over the encryption keys.
c, Correct, With SSE-C, the encryption happens in AWS and you have full control over the encryption keys.

5. A company you're working for wants their data stored in S3 to be encrypted. They don't mind the encryption keys stored and managed by AWS, but they want to maintain control over the rotation policy of the encryption keys. You recommend them to use ....................
a. SSE-S3
b. SSE-KMS
c. SSE-C
d. Client-side Encryption
Ans:
b, Correct, With SSE-KMS, the encryption happens in AWS, and the encryption keys are managed by AWS but you have full control over the rotation policy of the encryption key. Encryption keys stored in AWS.

6. Your company does not trust AWS for the encryption process and wants it to happen on the application. You recommend them to use ....................
a. SSE-S3
b. SSE-KMS
c. SSE-C
d. Client-side Encryption
Ans:
d, Correct, With Client-Side Encryption, you have to do the encryption yourself and you have full control over the encryption keys. You perform the encryption yourself and send the encrypted data to AWS. AWS does not know your encryption keys and cannot decrypt your data.

7. You have updated an S3 bucket policy to allow IAM users to read/write files in the S3 bucket, but one of the users complain that he can't perform a PutObject API call. What is a possible cause for this?
a. The S3 bucket policy must be wrong
b. The user is lacking permissions
c. The IAM user must have an explicit DENY in the attached IAM policy
d. You need to contact AWS support to lift this limit
Ans:
c, Correct, Explicit DENY in an IAM Policy will take precedence over an S3 bucket policy.

8. You have a website that loads files from an S3 bucket. When you try the URL of the files directly in your Chrome browser it works, but when the website you're visiting tries to load these files it doesn't. What's the problem?
a. The bucket policy is wrong
b. The IAM policy is wrong
c. CORS is wrong
d. Encryption is wrong
Ans:
c, Correct, Cross-Origin Resource Sharing (CORS) defines a way for client web applications that are loaded in one domain to interact with resources in a different domain. To learn more about CORS, go here: https://docs.aws.amazon.com/AmazonS3/latest/dev/cors.html

---------------------------------------------------------

1. You have enabled versioning and want to be extra careful when it comes to deleting files on an S3 bucket. What should you enable to prevent accidental permanent deletions?
a. Use a bucket policy
b. Enable MFA delete
c. Encrypt the files
d. Disable versioning
Ans:
b, Correct, MFA Delete forces users to use MFA codes before deleting S3 objects. It's an extra level of security to prevent accidental deletions.

2. You would like all your files in an S3 bucket to be encrypted by default. What is the optimal way of achieving this?
a. Use the bucket policy that forces HTTPS connections
b. Enable Default encryption
c. Enable versioning
Ans: 
d, Correct

3. You suspect that some of your employees try to access files in an S3 bucket that they don't have access to. How can you verify this is indeed the case without them noticing?
a. Enable S3 access logs and analyze them using Athena
b. Restrict their IAM policies and look at CloudTail logs
c. Use a Bucket policy
Ans:
a, Correct, S3 Access Logs log all the requests made to S3 buckets and Amazon Athena can then be used to run serverless analytics on top of the log files.

4. You want the content of an S3 bucket to be fully available in different AWS Regions. That will help your team perform data analysis at the lowest latency and cost possible. What S3 feature should you use?
a. Amazon Cloudfront Distributions
b. S3 versioning
c. S3 static website hosting
d. S3 Replication
Ans:
d, Correct, 
S3 Replication allows you to replicate data from an S3 bucket to another in the same/different AWS Region

5. You have 3 S3 buckets. One source bucket A, and two destination buckets B and C in different AWS Regions. You want to replicate objects from bucket A to both bucket B and C. How would you achieve this?
a. Configure replication from bucket A to bucket B, then from bucket A to Bucket C
b. Configure replication from bucket A to bucket B, then from bucket B to Bucket C
a. Configure replication from bucket A to bucket C, then from bucket C to Bucket B
Ans:
a, Correct

6. Which of the following is NOT a Glacier Deep Archive retrieval mode?
a. Expedited (1-5 minutes)
b. Standard (12 hours)
c. Bulk (48 hours)
Ans: 
a, Correct

7. How can you be notified when there's an object uploaded to your S3 bucket?
a. S3 Select 
b. S3 Access Logs
c. S3 Event Notifications
d. S3 Analytics
Ans:
c, Correct

8. You are looking to provide temporary URLs to a growing list of federated users to allow them to perform a file upload on your S3 bucket to a specific location. What should you use?
a. S3 CORS
b. S3 pre-signed URLs
c. S3 bucket policies
d. IAM Users
Ans:
b, Correct, S3 Pre-Signed URLs are temporary URLs that you generate to grant time-limited access to some actions in your S3 bucket.

9. You have an S3 bucket that has S3 Versioning enabled. This S3 bucket has a lot of objects, and you would like to remove old object versions to reduce costs. What's the best approach to automate the deletion of these old object versions?
a. S3 Lifecycle rules - Transition Actions
b. S3 Lifecycle rules - Expiration Actions
c. S3 Access Logs
Ans:
b, Correct

10. How can you automate the transition of S3 objects between their different tiers?
a. Aws Lambda
b. CloudWatch Events
c. S3 Lifecycle Rules
Ans:
c, Correct

11. Which of the following is NOT a Glacier Flexible retrieval mode?
a. Instant (10 Seconds)
b. Expedited (1-5 minutes)
c. Standard (3-5 hours)
d. Bulk (5-12 hours)
Ans:
a, Correct

12. While you're uploading large files to an S3 bucket using Multi-part Upload, there are a lot of unfinished parts stored in the S3 bucket due to network issues. You are not using these unfinished parts and they cost you money. What is the best approach to remove these unfinished parts?
a. Use AWS Lambda to loop on each old/unfinished part and delete them
b. Request AWS support to help you delete old/unfinished parts
c. Use an S3 Lifecycle policy to automate old/unfinished parts deletion
Ans:
a, Inorrect, This would work but it includes a lot of manual work and will cost you more money.
b, Correct

13. You are looking to get recommendations for S3 Lifecycle Rules. How can you analyze the optimal number of days to move objects between different storage tiers?
a. S3 Inventory
b. S3 Analytics
c. S3 Lifecycle Rules Advisor
Ans:
b, Correct

14. You are looking to build an index of your files in S3, using Amazon RDS PostgreSQL. To build this index, it is necessary to read the first 250 bytes of each object in S3, which contains some metadata about the content of the file itself. There are over 100,000 files in your S3 bucket, amounting to 50 TB of data. How can you build this index efficiently?
a. Use the RDS import feature to load the data from S3 to PostgreSQL, and run a SQL query to build the index
b. Create an application that will traverse the S3 bucket, read all the files one by one, extract the first 250 bytes, and store that information in RDS
c. Create an application that will traverse the S3 bucket, issue a byte range fetch for the first 250 bytes, and store that information in RDS
d. Create an application that will traverse the S3 bucket, use S3 select to get the first 250 bytes, and store that information in RDS
Ans:
c, Correct

15. For compliance reasons, your company has a policy mandate that database backups must be retained for 4 years. It shouldn't be possible to erase them. What do you recommend?
a. Glacier vaults with Vault lock periods
b. EFS network drive with restrictive Linux permissions
c. S3 with bucket policies
Ans:
a, Correct

16. You have a large dataset stored on-premises that you want to upload to the S3 bucket. The dataset is divided into 10 GB files. You have good bandwidth but your Internet connection isn't stable. What is the best way to upload this dataset to S3 and ensure that the process is fast and avoid any problems with the Internet connection?
a. Use Multi-Part upload only
b. Use S3 Select and use S3 Transfer acceleration
c. Use S3 Multi-part upload and S3 tranfer acceleration
Ans:
c, Correct

17. You would like to retrieve a subset of your dataset stored in S3 with the .csv format. You would like to retrieve a month of data and only 3 columns out of 10, to minimize compute and network costs. What should you use?
a. S3 Analytics
b. S3 Access logs
c. S3 Select
d. S3 Inventory
Ans:
c, Correct


