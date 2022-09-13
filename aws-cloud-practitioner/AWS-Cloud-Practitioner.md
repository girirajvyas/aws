# Amazon Web Services

**What is AWS:**  
 "Amazon Web Services (AWS) is the world’s most comprehensive and broadly adopted cloud platform, offering over 175 fully featured services from data centers globally. Millions of customers—including the fastest-growing startups, largest enterprises, and leading government agencies—are using AWS to lower costs, become more agile, and innovate faster."
 
 Source: https://aws.amazon.com/what-is-aws/

# 1. Cloud Computing:

## 1.1 Traditional Setup:
**Normal Server composition:**  
 - Power
   - Compute: CPU
   - Memory: RAM
 - Storage:
   - Data
 - Database: 
   - Store data in a structured way
   - Network: Routers, Switch, DNS Servers

**Terminologies:**  
`Network:` cables, routers and servers connected with each other  
`Router:` A networking device that forwards data packets between computer networks. They know where to send your packets on the internet!  
`Switch:` Takes a packet and send it to the correct server / client on your network  

**Problems With traditional IT:**  
 - Pay for rent, power supply, cooling, maintenance
 - Adding and replacing hardware takes times
 - Scaling is limited 
 - Hire 24/7 team for monitoring infrastructure/
 - Disaster/Natural calamities are not in control

**Solution for all above problems is : CLOUD**

## 1.2 Cloud setup

**What is Coud computing**
Cloud computing is the on-demand delivery of compute power, database storage,
applications, and other IT resources:
 - Through a cloud services platform with pay-as-you-go pricing
 - You can provision exactly the right type and size of computing resources you need
 - You can access as many resources as you need, almost instantly
 - Simple way to access servers, storage, databases and a set of application services
 - Amazon Web Services owns and maintains the network-connected hardware required for these application services, while you provision and use what you need via a web application.

**Deployment models of the cloud:**

|  Private cloud     | Public cloud | Hybrid cloud |
| ---------------- | -------------------- | --------------------- |
| Cloud services used by a single organization, not exposed to the public. | Cloud resources owned and operated by a thirdparty cloud service provider delivered over the Internet. | Keep some servers on premises and extend some capabilities to the Cloud              |
| Complete control over the infrastructure |                      | Control over sensitive assets in your private infrastructure |
| Security for sensitive applications |                      | Flexibility and costeffectiveness of the public cloud |
| Example: Rackspace| Example: Microsoft Azure, Google cloud platform (GCP), Amazon Web Services (AWS) | Example: private infra + any public cloud provider|

Characterstics of Cloud computing

Advantages:

Problems solved

## 1.2 Types of Cloud Computing:
 - On demand Self service
 - Broad network access
 - Multi-tenancy and resource pooling
 - Rapid elasticity and scalability
 - Measured service




# 8. Databases Introduction

**Basics**  
 - Storing data on disk (EFS, EBS, EC2 instance Store, S3) can have its limits
 - Sometimes, you want to store data in a database, you can structure the data
 - You build indexes to efficientlt quey/search through the datasets
 - Databases are optimized for a purpose and come with different features, shapes and constraints

**Relational Databases**  
 - Looks just like excel spreadsheets, with links between them

**NoSQL Databases**
 - NoSQL - non-SQL = non relational databases
 - NoSQL databases are purpose built for specific data models and have flexible schemes for building modern applications.
 - Benefits:
   - Flexibility: Easy to evolve data model
   - Scalability: designed to scale-out by using distributed clusters
   - High-performance: optimized for a specific data model
   - Higly functional: types optimized for the dta model
 - Examples:
   - key-value
   - documents
   - graph
   - in-memory
   - search databases
 - data example: JSON
   - JSON = JavaScript Object Notation
   - JSON is a common for of data that fits into a NoSQL model
   - Data can be nested
   - Fields can change over time
   - Support for new types: arrays, etc

**Databases and shared Responsibility on AWS**  
 - AWS offers use to manage different databases
 - Benefits:
   - Quick provisioning, High Availability, Vertical and Horizontal Scaling
   - Automated Backup and Restore, Operations, Upgrades
  - Operating system patching is handled by AWS**
  - Monitoring, alerting

Note: Many database technologies could be run on EC2, but you must handle yourself the resiliency, backup, patching, high availability, fault tolerance, scaling,..

## 8.1 AWS RDS Overview

**basics**  
 - RDS stands for Relational Database Service
 - Its a managed DB Service for DB using SQL as a query language
 - It allows you to create databases in the cloud that are managed by AWS
   - Postgres
   - MySQL
   - MariaDB
   - Oracle
   - Microsoft SQL Server
 - Aurora (AWS Proprietary database)

**Advantage of using RDS over deploying DB on EC2**  
 - RDS is a managed service:
   - Automated provisioning, OS patching
   - Continous backups and restore to specific timestamp (point in time Restore)
   - Monitoring dashboards
   - Read replicas for improved read performance
   - Multi AZ setup for Disaster Recovery
   - Maintenance windows for upgrades
   - Scaing capability (Vertical and horizontal)
   - Storage backed by EBS (type may be gp2 or io1)
 - **You cant SSH into your instances**

**Amazon Aurora**  
 - Aurora is a proprietary technology from AWS (not open sourced)
 - PostgreSQL and MySQL are both supported as Aurora DB
 - Aurora is "AWS cloud optimized" and claims 5x performance improvement over MySQL on RDS. over 3x the performance of Postgres on RDS
 - Aurora storage automatically grows in increments of 1-GB, up to 64 TB
 - Aurora costs more than RDS (20% more) - but is more efficient
 - Not in the free tier

**hands on**  
 - RDS -> Databases -> create databases 
   - creation method: standard create and easy create, we will select standard
   - Engine options: MySQL
   - Templates: Free tier
   - DB interface identidier: provide name for eg: databasedemo
   - Credentials settings: 
     - Master username: admin
     - Master password: 
     - Confirm password
   - Instance type: t2.micro
   - All other default values
   - Create database

## 8.2 Amazon ElasticCache 

**basics**  
 - The same way RDS is to get managed Relational Databases, ElasticCache is to get managed Redis or Memcached
 - Caches are in-memory databases with **high performance, low latency**
 - Helps reduce load off databases for read intensive workloads
 - AWS takes care of OS maintenance/patching, optimizations, setup, configuration, monitoring, failure recovery and backups

## 8.3 DynamoDB

**basics**  
 - Fully managed highly available with replication across 3 Azure
 - No SQL database - not a relational database
 - Scales to massive workloads, distributes **"serverless"** database
 - Millions of requests per seconds, trillions of row, 100s of TB of storage
 - Fast and consistent in performance
 - **Single-digit millisecond latency - low latency retrieval**
 - Integrated with IAM for security, authorization and administration
 - Low cost and auto scaling capabilities
 - Type of data:
   - key/value database
   - https://aws.amazon.com/nosql/key-value/

**hands on**  
 - DynamoDB -> Create table
   - tablename: 
   - primary key: 

## 8.4 Redshift Overview

 - Redshift is based on PostgresSQL, but its not used for Online transaction processing (OLTP)
 - Its used for OLAP - Online Analytical Processing (analytics and data warehousing)
 - Load data once every hour, not every second
 - 10x better performance than other data warehouses, scales to PBs of data
 - Columnar storage of data (instead of row based)
 - Massively Parallel Query Execution (MPP), highly available
 - Pay as you go based on the instances provisioned
 - Has a SQL interface for performing the queries
 - BI tools such as AWS Quicksight or Tableau integrate with it

## 8.5 AWS EMR

 - EMR stands for "Elastic MapReduce"
 - EMR helps creating Hadoop clusters (Big Data) to analyze and process vast amount of data
 - The clusters can be made of hundreds of EC2 instances
 - Also supports Apache Spark, HBase, Presto, Flink
 - EMR takes care of all the provisioning and configuration
 - Auto scaling and integrated with spot instances
 - Use Cases:
   - Data Processing
   - Machine Learning
   - Web Indexing
   - Big Data

## 8.6 AWS Athena

 - Fully Serverless database with SQL capabilities
 - Used to query data in S3
 - Pay per query
 - Output results back to S3
 - Secured through IAM
 - Use Case: 
   - one time SQL queries
   - queries
   - serverless queries on S3
   - log analytics

## 8.7 AWS DMS

 - Data Migration Service (Source DB ---> (EC2 Instance Running DMS) ---> Target DB)
 - Quickly and securelt migrate databases to AWS, resilient, self healing
 - The Source database remains available during the migrations
 - Supports:
   - Homogeneous migrations, eg: Oracle to Oracle
   - Heterogenous migrations: eg: SQL Server to Aurora

## 8.8 AWS Glue

 - Manages extract, transform, and Load (ETL) service
 - Useful to prepare and transform data for analytics
 - Fully Serverless service
 - Glue Data Catalog: catalog of datasets
   - Can be used by Athena, Redshift, EMR to provide metadata

## 8.9 Summary

 - Relational Database - OLTP: RDS and Aurora (SQL)
 - In-memory database: ElasticCache
 - Key/Value Databse: DynamoDB (serverless)
 - Warehouse - OLAP: Redshift (SQL)
 - Hadoop Cluster: EMR
 - Athena: query data on Amazon S3 (Serverless and SQL)
 - Glue: Managed ETL (extract Transform Load) and Data Catalog service
 - Database Migration: DMS

Quiz:

# 9. Compute Services Overview

## 9.1 What is Docker

**basics**  
 - Docker is a software development platform to deploy apps
 - Apps are packaged in containers that can be run on any OS
 - Apps run the same, regardless of where they're run
   - Any machine
   - No Compliance issues
   - Predictable behaviour
   - Less work
   - Easier to maintain and deploy
   - Works with any language, any OS, any technology

**Docker on an OS**  
 - SIngle EC2 instance will have docker running Java, docker runnning MySql

**Where docker images are stored**  
 - Docker images are stored in Docker repositories
 - Public repository: Docker Hub (https://hub.docker.com)
   - Find base images for may technologies or OS:
     - Ubuntu
     - MySQL
     - NodeJS
     - Java
 - Private Repository: Amazon ECR (Elastic COntainer Registry)  

**Docker vs Virtual machines**
 - Docker is "sort of" a virtualization technology, but not exactly
 - Resources are shared with host -> many containers on one server

## 9.2 ECS, Fargate and ECR 

**ECS**  
 - ECS stands for Elastic Container Service
 - Launch Docker containers on AWS
 - **You must provision and maintain the infrastructure (the EC2 instances)**
 - AWS takes care of starting/stopping containers
 - Has integrations with the Application Load Balancer

**Fargate**  
 - Launch Docker containers on AWS
 - You **do not provision the infrastructure (no EC2 instances to manage)** - simpler than ECS
 - Serverless Offering
 - AWS just runs containers for you based in the CPU/RAM you need

**ECR**
 - Elastic Container Registry
 - Private Docker Registry on AWS
 - This is where you store your Docker images so they can be run by ECS or Fargate

## 9.3 Serverless introduction
 - Serverless is a new paradigm in which the developers dont have to manage servers anymore
 - they just deploy code, functions
 - Initially Serverless == Faas (Function as a Service)
 - Serverless was pioneered by AWS Lambda but now also includes anything that's managed: "databases, messaging, Storage, etc"
 - Serverless does not mean there are no servers, it just means as a end user you just dont manage/provision/see them
 - Serverless services we saw till now:
   - S3
   - DynamoDB
   - Fargate
   - Lambda

## 9.4 AWS Lambda

**Why Lambda**  
| AWS EC2                                           | AWS Lambda                               |
| -------------                                     |:-------------:                           |
| Virtual servers in the cloud                      | Virtual Functions - no servers to manage |
| Limited by RAM and CPU                            | Limited by time - short executions       |
| Continously Running                               | Run on demand                            |
| Scaling means intervention to add/remove servers  | Scaling is automated                     |

**Benefits**  
 - Easy pricing:
   - Pay per request and compute time
   - Free tier of 10,000,00 AWS Lambda requests and 4,000,00 GBs of compute time
 - Integrated with the whole AWS suite of services
 - Event-Driven: functions get invokded by AWS when needed
 - Integrated with many programming languages
 - Easy monitoring through AWS Cloudwatch
 - Easy to get more resources per functions (upto 3GB of RAM)
 - Increasing RAM will also improve CPU and Network

**Languages supported**  
 - Node.js (JavaScript)
 - Python
 - Java (Java 8/11 compatible)
 - C# (.NET Core)
 - Golang
 - C#/Powershell
 - Ruby
 - Custom Runtime API (Community supported example Rust)
 - **Important: Docker is not for AWS Lambda, its for ECS/Fargate**

**Examples:**  
 - Serverless thumbnail creation
 - Serverless CRON job

**Pricing Example**  
 - Detailed pricing information: https://aws.amazon.com/lambda/pricing/
 - Pay per calls:
   - First 10,000,00 requests are free (in free tier)
   - $0,20 per 1 million requests thereafter ($0.0000002 per request)
 - Pay per duration: (in increment of 1ms)
   - 4,000,00 GB seconds of compute time per month if Free
   - == 4,000,00 seconds if function is 1 GB RAM
   - == 32,000,00 seconds if function is 128 MB RAM
 - After this $1.00 for 6,000,00 GB-seconds
 - It is usually very cheap to run AWS Lambda so its very popular

**hands on**  
 - Lambda -> you do not get the begin screen, to get that replace the last  word in URL with begin 
   - eg: https://us-east-2.console.aws.amazon.com/lambda/home?region=us-east-2#/begin
   - here you can explore the Lambda via going Next
 - Lambda -> Functions -> Create Function
   - Select blueprint -> hello-world-python blueprint -> configure
   - Basic information -> Function name: demo-lambda
   - Keep evrything default -> Create function -> Lambda Function created
 - To test the created lambda: Test -> provide name -> create -> test
 - Basic settings -> configure memory(max 3008 MB) and timeout(max 15 mins) setting
 - Monitoring tab provides you the place to monitor

## 9.5 AWS Batch

**basics**  
 - Fully Managed batch processing at any scale
 - Efficint run 1,000,00s of Computeing batch jobs on AWS
 - A "batch" job is a jo with a start and an end (opposed to continous)
 - Batch will dynamically launch EC2 instances or SPOT instances
 - AWS Batch provisions the righ amount of compute/memory
 - You submit or schedule batch jobs and AWS batch does the rest
 - Batch Jobs are defined as Docker images adn run on ECS
 - Helpful for cost optimizations and focusing less on the infrastructure

**Batch vs Lambda**  
 - Lambda:
   - Time limit
   - Limited runtimes ex java, python..
   - Limited temporary disk space
   - Serverless
 - Batch:
   - No time limit
   - Any runtime as long as its packages as Docker image
   - rely on EBS/instance store for disk space
   - Relies on EC2 (can be managed by AWS)

**Amazon LightSail**  

**basics**  
 - Virtual Servers, Storage, databases, and networking
 - Low and predicatble pricing
 - Simpler alternative to using EC2, RDS, ELB, EBS, Route 53
 - Great for people with little cloud experience
 - Can setup notifications and monitoring of your lightsail resources
 - Use cases:
   - Simple web applications (has templates for LAMP, Nginx, MEAN, Node.js, etc)
   - Websites (templates for Wordpress, Magento, Plesk, Joomla)
   - Dev/Test environment
 - Has High availability but no auto-scaling, limited aws integrations

## 9.6 Summary

**Other Compute Services**  
 - Docker: Container technology to run applications
 - ECS: Run Docker containers on EC2 instances
 -- Fargate:
   - Run Docker containers without provisioning the infrastructure
   - Serverless offering (No EC2 instances)
 - ECR: Private Docker images repository
 - Batch: Run Batch jobs on AWS across managed EC2 instances
 - LightSail: Predictable and low pricing for simple application and DB stacks

**Lambda Summary**
 - Lambda is Serverless, Function as a Service, Seamless scaling, reactive
 - Lambda billing:
   - By the time run **X** by the RAM provisioned
   - By the number of invocations
 - Language Support: Many programming languages except docker
 - Invocation Time: upto 15 minutes
 - Use Cases:
   - Create thumbnails for images uploaded into S3
   - Run a Serverless Cron job

# 10. Deployments and managing infrastructure at scale

## 10.1 Cloud Formation

**basics**  
 - CloudFormation is a declarative way of outlining your AWS infrastructure, for any resources (Most of them are supported).
 - For Example: Within a CloudFormation template, you say:
   - I want a security group
   - I want two EC2 instances using this security group
   - I want an S3 bucket
   - I want a load Balancer (ELB) in front of these machines
 - Then CloudFormation creates those for you, in the right order, with the exact configuration that you specify

**benefits**  
 - Infrastructure as a Code:
   - No resources are manually created, which is excellent for control
   - Changes to the infrastructure are reviewed through code
 - Cost:
   - Each resources within the stack is tagged with an identifier so you can easily see how much a stack costs you
   - you can estimate the costs of your resources using the ClopudFormation template
   - Savings strategy: In Dev, you could automate deletion of templates at 5 pm and recreated at 8 am safely
 - Productivity:
   - Ability to destroy and re-create an infrastructure on the cloud in the fly
   - Automated generation of Diagram for your templates
   - Declarative programming (no need to figure out ordering and orchestration)
 - Dont reinvent the wheel:
   - Leverage existing templates on the web
   - Leverage the documentation
 - Supports (almost) all AWS resources
   - Everything we will see here is supported
   - You can use "Custom resources" for resources that are not supported

**CloudFormation Stack Designer**  
 - We can see all the resources
 - We can see the relationships between the components

**hands on**  
 - CloudFormation -> Create Stack -> multiple options and can be created via designer

## 10.3 Beanstalk Overview

**basics**  
 - Typical Architecture: Webapp 3 tier
   - ELB (load balancer) ---- Auto Scaling Group of EC2 instances ---- ElasticCache/RDS

**developer problems on AWS**  
 - Dont want to manage infrastructure, just want to Deploy code
 - Configureing all the databases, load balancers, etc
 - Scaling concerns
 - Most web apps have the same architecture (ALB + ASG)
 - All developers want is for their code to run
 - Possibly, consistently across different applications and environments

**Elastic Beanstalk**  
 - Elastic Beanstalk is a developer centric view of deploying an application on AWS
 - It uses all the component's we have seen before: EC2, ASG, ELB, RDS, etc..
 - But its all in one view thats easy to make sense of
 - we still have full control over the configuration
 - Beanstalk = Platform as a Service (PaaS)
 - Beanstalk is free but you pay for the underlying instances
 - Managed Service
   - Instance configuration/ OS is handled by Beanstalk
   - Deployment strategy is configurable but performed by Elastic Beanstalk
 - Just the application code is the responsibility of the developer
 - Three architecture models:
   - Single instance deployment: good for dev
   - LB + ASG: great for production or pre-production web applications
   - ASG only: great for non-web apps in production (workers, etc)
 - Support for many platforms, 2 eg below:
   - Java Se
   - Java with Tomcat

**hands on**  
 - Beanstalk -> Create Application -> 
   - Name: DemoApp
   - Platform:
     - platform: Java
     - recommended branch and version are autopoulated
   - Application code
     - Sample application
   - Create Application
 - This internally uses CloudFormation to create the complete environment, this can be verified with 
   - CloudFormation -> Stacks -> you will see a stack with "CREATE_IN_PROGRESS"
   - If you select this stack 
     - you can see in tags beanstalk 
     - events has all the events occured
     - Resources tells you about all the resources created
     - Template: it has the template used by BeanStalk
   - EC2 -> instances - yu will find the instance created by BeanStalk
 - It takes almost 5 minutes to create this. once done, you have an url where you can acces the same
 - <yourAppName> -> Configuration

**CloudFormation vs BeanStalk**  

**Resources**  
 - https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/Welcome.html
 - https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/concepts.html 

## 10.4 AWS CodeDeploy
 - We want to deploy our application automatically
 - Works with EC2 instances
 - Works with On-premises servers
 - Hybrid service
 - Servers/Instances must be provisioned and configured ahead of time with the CodeDeploy Agent

## 10.5 AWS Systems Manager (SSM)
**basics**  
 - Helps you manage your EC2 and On-Premises systems at scale, Hence Hybrid AWS Service
 - Get Operational insights about the state of your infrastructure
 - Suite of 10+ products
 - Most important features are:
   - Patching automation for enhanced compliance
   - Run commands across an entire fleet of servers
   - Store parameter configuration with the SSM Parameter Store
 - Works for both Windows and Linux OS

**How Systems Manager works**  
 - We need to install the SSM agent onto the systems we control
 - Installed by default on Amazon Linux AMI and some Ubuntu AMI
 - If an instance cant be controlled with SSM, its probably and issue with the SSM agent
 - Thanks to the SSM Agent, we can run commands, patch and configure our servers

## 10.6 OpsWorks Overview
**basics**  
 - Chef and Puppet help you perform server configuration automatically, or repetitive actions
 - They work great with EC2 and On-Premises VM
 - AWS OpsWorks = Managed Chef and Puppet
 - Its and alternative to AWS SSM
 - Only provision standard AWS resources:
   - EC2 instances, Databases, Load Balancers, EBS volumes..
 - In the exam: Chef or Puppet needed => AWS OpsWorks

## 10.7 Summary 
 - CloudFormation (AWS Only):
   - Infrastructure as a Code, works with almost all of AWS resources
   - Repeat across Regions and Accounts
 - BeanStalk (AWS Only): 
   - Platform as a Service (PaaS), limited to certain programing languages or Docker
   - Deploy code consistently with a known architecture, eg: ALB+EC2+RDS
 - CodeDeploy (Hybrid): Deploy and Upgrade any application onto servers
 - Systems Manager (Hybrid): Patch, Configure and run commands at scale
 - OpsWorks (Hybrid): Managed Chef and Puppet in AWS

Quiz

## 11 Global Applications 

**Why make a Global Application?**  
 - A global application is an application deployed in multiple geographies
 - On AWS: this could be Regions and/or Edge locations
 - **Decreased Latency:**  
   - Latency is the time it takes for a network packet to reach a server
   - It takes time for a packet rom Asia to reach the US
   - Deploy your applications closer to your users to decrease latency, better experience.
 - **Disaster Recovery (DR)**  
   - If an AWS region goes down (earthquake, storms, power shutdown, politics), you can failover to another region and have your application still working
   - A DR plan is important to increase the availability of your application
 - **Attack Protection:** distributed global infrastructure is harder to attack

**Global AWS Infrastructure**  
 - Regions: For deploying applications and infrastructure
 - Availability zones: Made of multiple data centers
 - Edge Locations (Points of Presence): For content delivery as close as possible to users
 - More at: https://infrastructure.aws/

**Global Apps**  
 - **Global DNS: Route 53**
   - Great to eoute users to the closest deployment with least latency
   - Great for disaster recovery strategies
 - **Global Content Delivery Network (CDN): CloudFront**
   - Replicate part of your application to AWS Edge Locations - decrease latency
   - Cache common requests - improved user experience and decreased latency
 - **S3 Transfer Acceleration**
   - Accelerate global uploads and downloads into Amazon S3
 - **AWS Global Accelerator**
   - Improve global application availability and performance using the AWS global network

## 11.1 Amazon Route 53
**basics**  
 - Route53 is a managed DNS (Domain Name System)
 - DNS is a collection of rules and records which helps clients understand how to reach a server through URLs
 - In AWS, the most common records are:
   - www.google.com => 12.34.56.78 = A record (IPv4)
   - www.google.com => 2001:0db9:99a9:0000:0000:9a9e:9999:9889 == AAAA IPv6
   - search.google.com => www.google.com == CNAME: hostname to hostname
   - example.com => AWS resource == Alias (eg: ELB, CloudFront, S3, RDS, etc)
 - **Routing Policies**
   - Simple Routing Policy (No Health checks)
   - Weighted Routing Policy (Distribute traffic across multiple instances)
   - Latency Routing Policy (Minimize Latency)
   - Failover Routing Policy (helps in Disaster Recover)
**hands on**  
 - Create 2 EC2 instances in 2 different regions (Note the ip for easy access later)
 - Route 53 -> Domain registration -> provide details -> register
 - Route 53 -> Hosted zones -> Create Record Set
   - Name: www (and the domain you bought above is already available)
   - Type: A - ipv4 address
   - Value: provide ip of any one instance
   - Routing Policy: Latency
   - Region: select region
   - Set ID: my region <name > access
 - Repeat the process  with another ip

## 11.3 CloudFront
**basics**  
 - Content Delivery Network (CDN)
 - Improves read performance, content is cached at the edge
 - Improves users experience
 - 216 Points of Presence globally (edge locations)
 - DDoS protection (because worldwide), integration with Shield, AWS Web Application Firewall
 - https://aws.amazon.com/cloudfront/featuresp

**CloudFront Origins**  
 - From S3 bucket:
   - For distributing files and caching them at the edge
   - Enhanced security with CloudFront Origin Access Identity (OAI)
   - CloudFront can be used as an ingrass (to upload files to S3)
 - From Custom Origin (HTTP):
   - Application Load balancer
   - EC2 Instance
   - S3 website (must first enable the bucket as a static S3 webisite)
   - Any Http backend you want

**CloudFront vs S3 Cross region Replication**  
 - CloudFront:
   - GlobalEdge network
   - Files are cached for a TTL (may be a day)
   - Great for static content that muste be available everywhere
 - S3 Cross Region Replication
   - Must be setup for each region you want replication to happen
   - Files are updated in near real-time
   - Read only
   - Great for dynamic content that needs to be available at low-latency in few regions

**hands on**  
 - Create S3 bucket without bucket policies to expose publicly
 - Cloudfront -> Create Distribution -> Web distribution -> Get started

## 11.4 S3 Transfer acceleration

 - Increase transfer speed by transferring file onto an AWS edge location which will forward the data to the S3 bucket in the target region
 - Tool: https://s3-accelerate-speedtest.s3-accelerate.amazonaws.com/en/accelerate-speed-compaison.html

## 11.5 AWS Global Accelerator**

 - Improve global application availability and performance using the aws global network
 - Leverage the AWS internal network to optimize the route to your application (60% improvement)


