# Exam Preparation

# Compute

## EC2

### Types

### AutoScaling:

## Lambda

# Storage

## S3
 
### Bucket policies
 
##EFS
 
## EBS

### Volume types

### Snapshots


# Databases

## RDS:

Aurora
 - MySQL
 - PostgreSQL

DynamoDB
 - DAX


# Caches
ElastiCache
 - Redis
 - MemCached


# Networking

## ELB (Elastic Load Balancer)
 - Classic Load balancer
 - Application Load Balancer
   - What happens when EC2 instance fails health check
 - Network Load Balancer
 - https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/how-elastic-load-balancing-works.html

## VPC

## VPN

## Route 53

### Routing Policies
 - 

## Global Accelerator
 

## ENI

# Monitoring

## CloudWatch
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/viewing_metrics_with_cloudwatch.html

# Security

## NACL

## Security Groups
 - Can be attached to running or stopped EC2 instance 

## IAM Roles

## Policies
https://docs.aws.amazon.com/waf/latest/developerguide/what-is-aws-waf.html

# Comparisons
 
# Trash
Exam Alert:
Please note that AWS recommends using OAC because it supports:
All Amazon S3 buckets in all AWS Regions, including opt-in Regions launched after December 2022
Amazon S3 server-side encryption with AWS KMS (SSE-KMS)
Dynamic requests (POST, PUT, etc.) to Amazon S3
OAI doesn't work for the scenarios in the preceding list, or it requires extra workarounds in those scenarios. However, you will continue to see answers enlisting OAI as the preferred option in the actual exam as it takes about 6 months/1 year for a new feature to appear in the exam.


AWS STS: Short term security tokens
petabyte scale storage and instant retrieval and perform realt time analytics: DynamoDB
OpsWorks for puppet and chef

https://docs.aws.amazon.com/whitepapers/latest/disaster-recovery-workloads-on-aws/disaster-recovery-options-in-the-cloud.html

S3:
SSE-S3
SSE-KMS
SSE-C
Client side encryption

Retrieve sensitive data from S3:
 - Use S3 Object Lambda to change objects, before they are retrieved by report generator application

Verify if some of your employees are accessing S3 buckets that they dont have access to?
 - Use S3 Access Logs 
 - It logs all the requests made to S3 buckets and Amazon Athena can then be used to run serverless analytics on top of the log files.

Limited time access to S3:
S3 Pre-Signed URLs are temporary URLs that you generate to grant time-limited access to some actions in your S3 bucket.

Database backups must be retained for 4 years
Glacier vaults with Vault lock policies

# Schedule Exam

## Create training account

ID requirements
https://aws.amazon.com/certification/policies/during-testing/#ID_Requirements
Pearson Vue Exam Center guide:
 - https://wsr.pearsonvue.com/testtaker/registration/ExamPoliciesPage/AWS?conversationId=151388



