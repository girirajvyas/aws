# aws
Summary and resources for certifications

# 1. Overview

This repository contains the path and resources for learning and passing AWS certifications. This place is just to consolidate the learnings I had from various learning resources and act as a reference for me or any one who wants to pursue this track.

If you see below, you will have common data that will be common across the three certifications and for specific certifications you can see below:
 - [Cloud Practitioner](https://github.com/girirajvyas/aws/blob/main/AWS-Cloud-Practitioner.md)
 - [Solutions Architect](https://github.com/girirajvyas/aws/blob/main/AWS-Solution-Architect-Associate.md)
 - [Developer - Associate (DVA -C01)](https://github.com/girirajvyas/aws/blob/main/AWS-Developer-Associate.md) 

# 2. Certifications

## 2.1 Types 
We have different levels of Certifications in AWS, depending on the level you are you can choose it accordingly

 - *Foundation Level AWS Certifications*
   - **[Cloud Practitioner](https://aws.amazon.com/certification/certified-cloud-practitioner)** - Learn cloud fundamentals and best practices
 - *Associate Level AWS Certifications*
   - **[Solutions Architect (SAA-C02) ](https://aws.amazon.com/certification/certified-solutions-architect-associate)** - Associate: Learn to design highly available systems
   - **[Developer - Associate (DVA-C01)](https://aws.amazon.com/certification/certified-developer-associate/)** - Learn to develop applications for the cloud
   - **SysOps Administrator** - Associate: Learn to automate applications, networks, and systems
 - *Professional Level AWS Certifications*
   - **Solutions Architect - Professional:** Build complex solutions with data services, governance, and security
   - **DevOps Engineer - Professional:** Use DevOps practices to develop and maintain AWS applications
 - *Speciality Level AWS Certifications*
   - Advanced Networking - Speciality
   - Alexa Skill Builder - Speciality
   - Security - Speciality
   - Machine Learning - Speciality
   - Data Analytics - Speciality
   - Database - Speciality
 - For details like below, follow link https://aws.amazon.com/certification/certification-prep/
   - Download exam guide and sample questions
   - Explore relevant AWS training
   - Read AWS Whitepapers and FAQs
   - Take Exam readiness training
   - Take a pracrice exam
 - Pictorial version is avaialble at: https://aws.amazon.com/certification/
 - ![Certifications link](https://github.com/girirajvyas/aws/blob/main/resources/images/available_certifications.png)

## 2.2 Where to start

This is the question that comes to mind when we plan to take any certification. Ideally if you see the image above, AWS has certain guidelines of how many years of experience one must have to appear that certification. These are just guidelines and your timeline can be different from the one recommended by AWS

**Ideal flow is:**  
 `Certifid Cloud Practitioner` **->** `Certified solutions Architect - Associate` **->** `Certified Developer - Associate`

The flow depicted above is the normal one and not strictly followed you can do them in any order and can do any one directly. This is generally what people do  

Also, their will be some steps and resources that are common across and will mention them in below sections


# 3. Account Setup

## 3.1 Create free Account
Before you start your journey the important step is to create an aws account. This is the prerequisite for learning. You can read a lot of data but without hands on it will all be futile.  

Start by creating a free tier account (Concentrate on free, what is stopping you to create one then ;) )  
Just to give you a headsup this free comes with a limit depending on the services you use and some of the services are not included in this free tier. Please have a quick glance at https://aws.amazon.com/free so as to make correct decisions.

## 3.2 Registration page
 - https://aws.amazon.com/free -> Create a free account
 - Basic details (Email/Password/AWS account id)
 - Account type: (Personal/Business)
 - Billing information (Payment information) (in case you exceed the free tier):
    - Credit/debit card number with expiry
    - Rs 2 were deducted for verification in India
    - $ 1 in US
 - Confirm your identity: OTP/voice call on mobile number
 - Select a support plan:
    - Basic Plan (Recommended for new users just getting started with aws)
      - 24x7 self-service access to AWS resources
      - For account and billing issues only
      - Access to Personal Health Dashboard & Trusted Advisor
    - Developer Plan ( Recommended for developers experimenting with AWS )
      - Email access to AWS Support during business hours
      - 12 (business)-hour response times
    - Business Plan (Recommended for running production workloads on AWS)
      - 24x7 tech support via email, phone, and chat
      - 1-hour response times
      - Full set of Trusted Advisor best-practice recommendations
 - Complete signup
 - Sign in to console, you will have below 2 options:
   - Root User
   - IAM User
 - As we are just starting off we will select root user email id and sign in with email and password

**Additional Details:**  
 - https://aws.amazon.com/premiumsupport/knowledge-center/create-and-activate-aws-account/
 - **For understanding of different plans:** https://aws.amazon.com/premiumsupport/plans/  

https://en.wikipedia.org/wiki/Amazon_Web_Services

# 4. Basics

## 4.1 Why Move to cloud
 - Unlimited Storage space (Almost)
 - Flexible spend instead of setting upfront
 - Downtime mitigation available
 - Disaster recovery options out of the box
 - Reduced Software maintenance
 - Centralized security management
 - Automated provisioning of servers
 - Scalability
 - Decentralized environments
 - Getting close to users (Point of presence, cloudfront)
 - Speed
 - Capital Expenditure(CapEx) reduction

**Good reads:** 
 - https://en.wikipedia.org/wiki/Cloud_computing
 - 

## 4.2 History

[AWS](https://en.wikipedia.org/wiki/Amazon_Web_Services):
 - 2002: Internally launched
 - 2004: Launched publicly with SQS
 - 2006: re-launched with SQS, S3 and EC2
 - 2007: Launched in Europe + simpleDB  
 - Major customers: Netflix, Nasa, Airbnb, Dropbox

**Good Reads:**
 - [AWS announcements](https://aws.amazon.com/new/)

## 4.2 Major competitors:
 - Microsoft Azure cloud
 - Google cloud platform (GCP)
 - Followed by Alibaba, IBM Bluemix, Salesforce, Tencent, VmWare and Oracle

**Good read:**
 - https://en.wikipedia.org/wiki/Category:Cloud_computing_providers

## 4.3 Where to use
 - Building Enterprise IT applications
 - Backup and Storage
 - Big Data Analytics
 - Many services for IOT and ML in case you are doing something in tat field
 - Hosting your website, application 

## 4.4 Infrastructure

The best resource to understand infrastructure is https://www.infrastructure.aws/.  
Here you can click on different sections to understand in detail about them. A brief summary is as below:

**Why cloud infrastructure matters?**   
 - https://aws.amazon.com/about-aws/global-infrastructure

### 4.4.1 Regions and Availability zones  
 - On high level We have Regions:
   - Cluster of data centers
   - Many Avaialbility zones (normally 3, minimum 2, maximum 6)
   - Most of the services are Region scoped. This means that you have to define the region while using that service and it will be available in that region only
 - Each Region has 2 or more Availability zones
   - Each availability zone is one or more discrete data centers with redundant power, Networking and connectivity
   - They are saperated from each other so as to have isolation from disasters
   - connected with high bandwidth, ultra-low latency network
   - Example:
 - Each availability zone has data centers 
 - Finally we have Edge locations/Point of presence
 - You can read about this in detail at: https://aws.amazon.com/about-aws/global-infrastructure/regions_az/

### 4.4.2 AWS global datacentres: 
 - It is important to know how many `regions` and `availability zones` does aws provide
 - You can always get the latest data at: https://aws.amazon.com/about-aws/global-infrastructure/
 - For Instance, India has 1 region and USA has 7 
 - While deploying we can decide which region to deploy our service
 - Each region will have typically 2 or more data centers for high availability of services and hence called `availability zones`
 - `Edge locations are the place where the data is cached to the nearest location` from where it is accessed
 - 200+ services as of June-2021

### 4.4.3 Quick Summary:
 - `Region:` Independent geographic area, typically has 2 or more availability zones
 - `Availability zones:` Multiple isolated locations/data centers within a region
 - `Edge Locations:` place where the data is cached to the nearest location, you cannot directly use but can be used via services like cloudFront

## 4.3 Different Service offerings and its scope
Do not get overwhelmed by the numbers below, it is just to consolidate the mostly used services and once you are familiar with those you will be comfortable to read this again and relate with it.  

*In detail when a service was announced and released in detail:*
 - https://www.awsgeek.com/AWS-History/

### 4.3.1 AWS services by their scope
 
**AWS account (Global)**
 - IAM{Identity and Access Management} users. (User management)
 - Billing
 - Route53 (DNS Service)
 - WAF
 - CloudFront (COntent Delivery Network)

**Region level services**
 - S3 Bucket
 - Dynamo DB (NoSQL db)
 - Rekognition (SaaS)
 - BeanStalk (PaaS)
 - EC2 (IaaS)
 - Lambda (FaaS)

**Availability zones services**
 - EC2 it is vm (IAAS)
 - RDS relational databases
 - EBS Elastic block storage

### 4.3.1 AWS services by Functionality

**Compute services**  
 - Elastic cloud compute (EC2) : Virtual servers in cloud
 - EC2 container service (ECS) : Run and Manage Docker Containers
 - Elastic Container service for Kubernetes (EKS) : Deploy, manage and scale containerized applications using K8s
 - Fargate : Run containers without having to manage servers and clusters
 - Lightsail : Launch and manage Virtual private servers
 - Elastic Beanstalk : Run and Manage webapps
 - Lambda : Run code in response to Events or Run code without thinking about servers
 - Batch : Batch Jobs at scale
 
**Storage Services**  
 - Simple Storage Service (S3) : Storage in cloud
 - Elastic File System (EFS) : Managed file storage for EC2
 - Glacier : Archive storage
 - Storage Gateway : Hybrid storage integration

**Database Services**  
 - Relational Database Services (RDS) : Managed relational database for Amazon Aurora, MySQL, PostgreSQL, SQL server, Oracle and MariaDB
 - DynamoDB : Managed NoSQL database (Equivalent of mongoDB)
 - ElastiCache : In-Memory caching system
 - RedShift : DataWarehousing
 - Elastic Map Reduce (EMR) : Managed hadoop that can be combined with hbase or hive
 
**Networking and content delivery**  
 - Virtual Private Cloud (VPC) : Isolated/saggregated private networks
 - CloudFront : Content Delivery Network
 - Direct Connect : Private Network connection to AWS
 - Route 53 : Domain Name system

**Security, Identity and colplaince**  
 - Identity and Access Management (IAM) : Manage user access and API access keys in AWS  
 - Key management service (KMS) : Managed creation and control of encryption keys   
 - Certificate Manager: Provision, Manage and deploy SSL/TLS certificates
 - Directory Service:  Host and manage active directory
 - WAF and shield - Web application firewalls (Prevents ddos, sql injection, cross site scripting, etc)  
 - Inspector : Analyze application security (PCI DSS compliance, HIPPA compliance, scans for any known vulnerabilities)
 - Artifact :  No cost, self-service portal for on demand access to aws compliance report
 - AWS Config : Assess, Audit and Evaluate the configurations of yor aws resources

**Expose API**  
 - API Gateway - Rest API  
 - Cognito - web and mobile user management  

**Development and Devops services**  
 - CloudFormation: code your infrastructure/Infrastructure as a code (json/yaml)  
 - CodeCommit: Code repo:  (similar to github)  
 - CodeBuild: Build tool (ant/maven)  
 - CodeDeploy: Deployment  
 - CodePipeline: (covers all above 3 service)  
 - CodeStar: Project managing/issue tracking/   

**AWS Networking services**   
**Developer**  
 - VPC basics
    - CIDR
    - Internet Gateway
    - Subnets
    - Route tables
    - NAT
    - IP (Public, private, Elastic), NAT
    - Security Group
    - Network ACl
 - ELB/Cloudfront
 - Route53 (DNS)
 - VPC Endpoint
 - Private Link
 - VPC Peering

**DevOps**  
 - All Services for Developer
 - Transit VPC
 - Transit Gateway
 - Site to Site VPN
 - Client VPN
 - Other Topics
   - Network automation (CloudFormation, CLI)
   - Logging and Monitoring

**Network Administrator**  
 - All Services for Developer and Devops
 - Direct Connect
 - VPC Advanced
 - Other Topics
    - Enhanced Networking
    - hybrid connectivity
    - Network Performance
    - Network Security (Layer 3, Layer 7)

**Example: Video converter from 1 s3 to another**  
option 1:  
 - EC2 instance watches s3 for a new video  
 - downloads - converts it - puts in another s3  
Option 2:  
 - Lambda  
  - specify how to convert  
  - triggered when video uploaded  
  - scales automatically  

**Miscellaneous**  
 - ElasticCache - Redis and memcached  
 - ELB - Elastic load balancer  
 - VPC - Virtual private cloud  
 - Route53 - DNS service  
 - S3 - Simple storage service (External storage)  
 - Rekognition - Content filter  
 - Lambda - Serverless service  
 - Kinesis - Click stream analysis  
 - EMR - aggregation, processig spark/hadoop cluster  
 - GLUE - ETL  
 - Redshift - data warehouse can store petabytes  
 - Quicksight - Bi analysis  
 - Athena - SQL tool for BI  
 - Cloudfront - Content delivery network (cache). It does this with the help of edgelocation  
 - SNS - Simple notification service - for mobile push notifications/SMS  
 - SES - Simple email service - for sending mails/ bulk emails  
 - SQS - Simle queue service - messaging queues or chatting app  
 - Cloudwatch - Monitor all the services, set alarms  

# 5. Manage Billing

## 5.1 Why
To avoid billing shocks while using AWS, you can do below setup as a precautionalry measure.

## 5.2 Pre-requisite
In case you access the billing module from IAM user, you might get below permissions error  
```cmd
You Need Permissions
You don't have permission to access billing information for this account. Contact your AWS administrator if you need help. If you are an AWS administrator, you can provide permissions for your users or groups by making sure that (1) this account allows IAM and federated users to access billing information and (2) you have the required IAM permissions.
```

You have to update the access via root account
 - Account name -> My Account -> IAM User and Role Access to Billing Information -> Edit -> Activate IAM Access -> Update
 - Go to IAM user account and refresh to see it reflects now

## 5.3 Read bills
 - Go to Bills section -> you can see your bill month wise
 - You can expand any section which has amount specified and see the details of the same
 - For free tier services you can also check the details of your usage

## 5.4 Setup a budget
 - Go to Budgets -> Create a Budget -> you have below options
 - Step 1: Select Budget
   - Types:
     - Cost Budget: Monitor your costs against a specified amount and receive alerts when your user defined thresholds are met
     - Usage Budget: Monitor your usage of one or more specified usage types or usage type groups and receive alrets when your user-defined thresholds are met
     - Reservation Budget: Track the RI Utilization or RI Coverage associated with your reservations. Thses budgets supports Amazon EC2, RDS, Redshift, ElasticCache and ElasticSearch reservation models
     - Savings plan budget: Track the utilization and coverage associated with your Savings plans
 - Step 2: Set your budget 
   - Lets create `Cost Budget`
     - Provide name: anything
     - Period: monthly
     - Budget effective date: Recurring type
     - Specify your monthly budget
       - Fixed
       - Budgeted amount: 1$
 - Step 3: Configure Thresholds
   - based on actual cost and forecasted cost
   - provide alert threshold
   - Setup your notifications via providing email
 - Step 4: Confirm Budget


# 7. Connect from Code
You can find a Spring boot project with below examples in this repository
 - Basic connectivity setup
 - Example of S3
 - Example of DynamoDB. 

You can check the code **[here](https://github.com/girirajvyas/aws/tree/main/aws-springboot)**

# Resources
Courses:  
 - [Ultimate AWS Certified Cloud Practitioner - 2022](https://www.udemy.com/course/aws-certified-cloud-practitioner-new/)
 - [Ultimate AWS Certified Solutions Architect Associate 2022
](https://www.udemy.com/course/aws-certified-solutions-architect-associate-saa-c03/)
 - [AWS Solution Architect Practice Exams](https://www.udemy.com/course/practice-exams-aws-certified-solutions-architect-associate/)
 
Official Documentation:  
 - [Official AWS Docs](https://docs.aws.amazon.com/index.html)

Good Reads:  
 - [AWSGeek - Nice pictorial summary for different topics](https://www.awsgeek.com/)
 - [Open guides AWS](https://github.com/open-guides/og-aws)
 - [Resources with most stars on github are listed here](https://github.com/donnemartin/awesome-aws)
 - Notes: https://tutorialsdojo.com/aws-study-path-notes/
 - Repo: https://bitbucket.org/awsdevguru/awsdevassoc/src/master/
 - aws certified solutions architect jon bonso
 - https://docs.aws.amazon.com/AmazonS3/latest/userguide/cors.html
 - https://docs.aws.amazon.com/whitepapers/latest/disaster-recovery-workloads-on-aws/disaster-recovery-options-in-the-cloud.html
 - https://courses.datacumulus.com/downloads/certified-solutions-architect-pn9/

Exam:
 - https://d1.awsstatic.com/training-and-certification/docs-sa-assoc/AWS-Certified-Solutions-Architect-Associate_Exam-Guide_C03.pdf
Practice Exams:  
 - https://www.knowledgehut.com/practice-tests/aws-solutions-architect-associate
 - https://www.awsboy.com/aws-practice-exams/
 - Schedule AWS exam: https://aws.amazon.com/certification/certified-solutions-architect-associate/
 - https://aws.amazon.com/certification/coming-soon/

Youtube:  
 - [Introduction to AWS Services](https://www.youtube.com/watch?v=Z3SYDTMP3ME)
 - [Introduction to AWS Networking](https://www.youtube.com/watch?v=XZbvQWkpJTI)

1. AWS Certified Solutions Architect – Associate Official Practice Question Set (SAA-C02 - English) - old
2. AWS Certified Solutions Architect – Associate Official Practice Question Set (SAA-C03 - English) - new

AWS Certified Solutions Architect - Associate certification exam is changing from August 30, 2022
	
	AWS Certified Solutions Architect - Professional certification exam is changing from November 15, 2022
    
    https://aws.amazon.com/certification/coming-soon/
    