# Exam Preparation

https://aws.amazon.com/compliance/shared-responsibility-model/

# Compute

## EC2

 
### Types

### AutoScaling:


## Lambda



# Storage

## S3
 - Macie vs Guard Duty
 - Event notification destinations (SNS, SQS, Lambda, EventBridge)
 
 
### Bucket policies

 
EFS
 - 

## EBS

### Volume types

### Snapshots


# Databases

## RDS:
 - Multi-AZ replication vs Multi-Region vs read replicas
 - https://cloud.in28minutes.com/aws-certification-multi-az-vs-multi-region-vs-read-replicas
 - Cross Region Multi-AZ replication
 - https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.MultiAZ.html

Aurora
 - MySQL
 - PostgreSQL

DynamoDB
 - DAX


https://aws.amazon.com/rds/features/multi-az/

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

 - Negative points
   - While relying on the DNS service is a great option for blue/green deployments, it may not fit use-cases that require a fast and controlled transition of the traffic. Some client devices and internet resolvers cache DNS answers for long periods

### Routing Policies
 - https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/routing-policy.html

## Global Accelerator
 - AWS Global Accelerator is a network layer service that directs traffic to optimal endpoints over the AWS global network, this improves the availability and performance of your internet applications.

## ENI
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/best-practices-for-configuring-network-interfaces.html

# Monitoring

## CloudWatch
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/viewing_metrics_with_cloudwatch.html

# Security

## NACL

 - NACL Rules
   - asterisk (\*) rule cannot be changed or deleted, it is default deny for all 
   - https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html

## Security Groups
 - Can be attached to running or stopped EC2 instance 

## IAM Roles

## Policies


https://docs.aws.amazon.com/waf/latest/developerguide/what-is-aws-waf.html














# Comparisons

## CloudWatch vs CloudTrail vs Config. 
 - Think resource performance monitoring, events, and alerts; think CloudWatch.
 - Think account-specific activity and audit; think CloudTrail.
 - Think resource-specific history, audit, and compliance; think Config.

# Scalability

## Types
 - Vertical scalability means increasing the size of the instance. 
   - For example, your application runs on a t2.micro. 
   - *Scaling up* that application vertically means running it on a larger instance such as t2.large. 
   - *Scaling down* that application vertically means running it on a smaller instance such as t2.nano. 
   - Scalability is very common for non-distributed systems, such as a database. There’s usually a limit to how much you can vertically scale (hardware limit). 
   - Example can be the instance type was upgraded from t2.nano to u-12tb1.metal, this is a scale-up example of vertical scalability.
 - Horizontal Scalability means increasing the number of instances/systems for your application.
   - When you increase the number of instances, it's called *scale-out*, whereas if you decrease the number of instances, it's called *scale-in*. 
   - Scale-up is used in conjunction with vertical scaling and not with horizontal scaling. 

# Availability
 - High availability means running your application/system in at least 2 data centers (== Availability Zones). 
 - The goal of high availability is to survive a data center loss. An example of High Availability is when you run instances for the same application across multi AZ. 
 
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