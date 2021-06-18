# aws
Summary and resources for certifications

# 1. Overview

This repository contains the path and resources for learning and passing AWS certifications. This place is just to consolidate the learnings I had from various learning resources and act as a reference for me or any one who wants to pursue this track.

If you see below, you will have common data that will be common across the three certifications and for specific certifications you can see below:
 - [Cloud Practitioner]()
 - [Solutions Architect]()
 - [Developer - Associate (DVA -C01)]() 

# 2. Certifications

## 2.1 Types 
We have different levels of Certifications in AWS, depending on the level you are you can choose it accordingly

 - *Foundation Level AWS Certifications*
   - **Cloud Practitioner** - Learn cloud fundamentals and best practices
 - *Associate Level AWS Certifications*
   - **Solutions Architect** - Associate: Learn to design highly available systems
   - **[Developer - Associate (DVA -C01)](https://aws.amazon.com/certification/certified-developer-associate/)** - Learn to develop applications for the cloud
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
 - ![Certifications link](https://github.com/girirajvyas/aws/blob/main/resources/images/Certifications.png)

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

## 4.1 History

[AWS](https://en.wikipedia.org/wiki/Amazon_Web_Services):
 - 2002: Internally launched
 - 2003: 
 - 2004: Launched publicly with SQS
 - 2006: re-launched with SQS, S3 and EC2
 - 2007: Launched in Europe
 - Major customers: Netflix, Nasa, Airbnb, Dropbox

## 4.2 Major competitors:
 - Microsoft Azure cloud
 - Google cloud platform (GCP)
 - Followed by Alibaba, IBM, Salesforce, Tencent and Oracle

## 4.3 Where to use
 - Building Enterprise IT applications
 - Backup and Storage
 - Big Data Analytics
 - Many services for IOT and ML in case you are doing something in tat field
 - Hosting your website, application 

## 4.3 Infrastructure

The best resource to understand infrastructure is https://www.infrastructure.aws/.  
Here you can click on different sections to understand in detail about them. A brief summary is as below:

Why cloud infrastructure matters?   
 - https://aws.amazon.com/about-aws/global-infrastructure

Regions and Availability zones  
 - On high level We have Regions:
   - Cluster of data centers
   - Many Avaialbility zones (normally 3, minimum 2, maximum 6)
   - Most of the services are Region scoped. This means that you have to define the region while using that service and it will be available in that region only
 - Each region has Availability zones
   - Each availability zone is one or more discrete data centers with redundant power, Networking and connectivity
   - They are saperated from each other so as to have isolation from disasters
   - connected with high bandwidth, ultra-low latency network
   - Example:
 - Each availability zone has data centers 
 - Finally we have Edge locations/Point of presence
 - You can read about this in detail at: https://aws.amazon.com/about-aws/global-infrastructure/regions_az/

**AWS global datacentres:**  
 - It is important to know how many `regions` and `availability zones` does aws provide
 - You can always get the latest data at: https://aws.amazon.com/about-aws/global-infrastructure/
 - For Instance, India has 1 region and USA has 7 
 - While deploying we can decide which region to deploy our service
 - Each region will have typically 2 or more data centers for high availability of services and hence called `availability zones`
 - `Edge locations are the place where the data is cached to the nearest location` from where it is accessed
 - 200+ services as of June-2021

**Quick Summary:**  
 - `Region:` Independent geographic area, typically has 2 or more availability zones
 - `Availability zones:` Multiple isolated locations/data centers within a region
 - `Edge Locations:` place where the data is cached to the nearest location

## 4.3 Different Service offerings and its scope
Do not get overwhelmed by the numbers below, it is just to consolidate the mostly used services and once you are familiar with those you will be comfortable to read this again and relate with it.  

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

**Expose API**  
 - API Gateway - Rest API  
 - Cognito - web and mobile user management  

**Security**  
 - IAM - manage all accesses in AWS  
 - KMS - Key management service  
 - ACM - Digital certificates for https ssl connection Amazon certification management  
 - WAF - Web application firewalls (Prevents ddos, sql injection, cross site scripting, etc)  
 - Inspector - PCI DSS compliance, HIPPA compliance: scans for any known vulnerabilities  

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

# 5. Manage Billing
To avoid billing shocks while using AWS, you can do below setup as a precautionalry measure.

In case you access the billing module from IAM user, you might get below permissions error  
```cmd
You Need Permissions
You don't have permission to access billing information for this account. Contact your AWS administrator if you need help. If you are an AWS administrator, you can provide permissions for your users or groups by making sure that (1) this account allows IAM and federated users to access billing information and (2) you have the required IAM permissions.
```

You have to update the access via root account
 - Account name -> My Account -> IAM User and Role Access to Billing Information -> Edit -> Activate IAM Access -> Update
 - Go to IAM user account and refresh to see it refkects now

Now to setup a budgetgo
 - Go to Budgets -> Create a Budget -> you have below options
   - Cost Budget: Monitor your costs against a specified amount and receive alerts when your user defined thresholds are met
   - Usage Budget: Monitor your usage of one or more specified usage types or usage type groups and receive alrets when your user-defined thresholds are met
   - Reservation Budget: Track the RI Utilization or RI Coverage associated with your reservations. Thses budgets supports Amazon EC2, RDS, Redshift, ElasticCache and ElasticSearch reservation models
   - Savings plan budget: Track the utilization and coverage associated with your Savings plans
 - Lets create `Cost Budget`, provide name, recurring type monthly, threshold amount at which alert will be triggered. and create


# 7. Connect from Code
If you see their is spring boot project created with basic connectivity setup done and basic example of S3 and DynamoDB. you can check that **[here](https://github.com/girirajvyas/aws/tree/main/aws-springboot)**

# Resources

 - [Introduction to AWS Services](https://www.youtube.com/watch?v=Z3SYDTMP3ME)
 - [Introduction to AWS Networking](https://www.youtube.com/watch?v=XZbvQWkpJTI)
 - [Open guides AWS](https://github.com/open-guides/og-aws)
 - [Resources with most stars on github are listed here](https://github.com/donnemartin/awesome-aws)
 - [Ultimate AWS Certified Cloud Practitioner - 2021](https://www.udemy.com/course/aws-certified-cloud-practitioner-new/)

