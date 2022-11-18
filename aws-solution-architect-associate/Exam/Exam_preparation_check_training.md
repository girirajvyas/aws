# Domains
 - Design Resilient architectures
 - Design High-performance architectures
 - Design Secure Applications and Architectures
 - Design cost-optimized Architectures


# Outline
Module 1: Resilient Architectures

Design a multi-tier architecture solution
Design highly available and/or fault-tolerant architectures
Design decoupling mechanisms using AWS services
Choose appropriate resilient storage
Questions walkthrough

Module 2: High-performing Architectures

Identify elastic and scalable compute solutions for a workload
Select high-performing and scalable storage solutions for a workload
Select high-performing networking solutions for a workload
Choose high-performing database solutions for a workload
Questions walkthrough

Module 3: Secure Applications

Design secure access to AWS resources
Design secure application tiers
Select appropriate data security options
Questions walkthrough

Module 4: Cost-optimized Architectures

Identify cost-effective storage solutions
Identify cost-effective compute and database services
Design cost-optimized network architectures
Questions walkthrough


# Module 1: Resilient architectures

## Overview
 - Design Multi-Tier solutions
 - Design Highly Available and/or fault-tolerant architectures
 - Design Decoupling mechanisms for AWS
 - Choose appropriate resilient storage

## Multi-Tier Solutions

### Overview
 - Access Patterns
   - Consider access patterns with regards to who and what needs access, and how they will access the services and architectures
   - Access patterns plays a large role in determining how to design and update your architectures
 - Questions to ask yourself:
   - How access pattern looks like for hour to hour and day to day?
   - Do we have steady connections or do we have peaks to consider?
   - Do requests vary in size or are they consistent?
   - Do you need to be ready for large volumes of unexpected traffic?
 - Scaling
   - Consider ways to implement scaling, how various services handle elasticity and methods to automate scaling needs
   - Horizontal vs vertical scaling
 - Evaluate how architecture requirements will affect the selection of database storage and compute services
   - when to build your own db instance vs managed databases?
   - how instances family affect what servers you'll run, and how you'll deploy them?
   - When to choose instance based sstorage vs Amazon Elastic Block storage?   

### Example Question
1. A company is designing a human genome application using multiple Amazon EC2 Linux instances. The high performance computing (HPC) application requires low latency and high performance network communication between the instances
Which solution provides the Lowest latency between instances?
a. Launch the instances in a cluster placement group
b. Launch the EC2 instances in a spread placement group
c. Launch the EC2 instances in an Auto Scaling group spanning multiple regions
d. Launch the EC2 instances in an Auto scaling group spanning multiple availability zones within a regions
Ans: a

## Design Highly Available and/or fault-tolerant architectures

### Overview
 - Highly Available vs fault-tolerant
   - Designing for high-availability means designing for minimum downtime
   - Designing for fault-tolerance means designing for zero downtime
 - Determine the amount of resources needed
 - Determine which services can be used to improve reliability
 - Select highly available configurations to mitigate single points of failure, and select appropriate disaster recovery strategies
 - Determine single point of failures:
   - When looking at an environment, take each component and assess:
     - What happens when that component fails?
	 - What happens when one of your web servers fail?
	 - What do you do when one of the application servers go down?
	 - How about the database? 
   - Evaluate the failures and then determine how you will mitigate
 - Determine Disaster recovery objectives
   - Evaluate Recovery Time Objectives (RTO)
   - Evaluate Recovery Point Objectives (RPO)   
 - Determine Disaster recovery strategies
   - Active/Passive: Backup and restore, pilot light and warm standby
   - Active/Active: Multi site Active/Active
 - Understand Key performance Indicators (KPIs)
   - Understanding KPIS gives you the best visibility into your architecture's health
   - Also, It is important in determining when to take actions
   - Monitoring and benchmarking are the primary ways to know what normal operations look like
   - It also gives a normal range to utilize in disaster recovery plans

### Example Question
A solutions architect must create a disaster recovery (DR) solution for a company's business critical applications. The DR site must reside in different AWS region than the primary site. The solution requires a recovery point objective (RPO) in seconds and recovery time objective (RTO) in minutes. The solution also requires the deployment of a completely functional but scaled-down version of the applications. Which DR strategy will met these requirements?
a. multi-site active-active
b. backup and restore
c. Pilot light
d. Warm standby
Ans:
a is distractor as it can also provide the solution, but client is looking for scaled-down version of application and in this both the sites are at same level hence wrong
d, correct

## Decoupling mechanisms

### Overview
 - Decoupling refers to components remaining autonomous and unaware of each other as they complete their work as part of larger system
 - Synchronous decoupling involves components that must always be available for proper functionality
   - Example: Elastic Load Balancer to disctribute traffic between instances across multiple Availability zones
 - Asynchronous decoupling involves communication between components through durable components
   - Example: An application processing a lot of mesages and has queue such as Amazon Simple Queue services handling messaging between instances. If one of the queues has to go offline the messages would persist till an instance was available again
 - Knowing how to use decoupling services will be incredibly important
 - Need to understand serverless tools to build and improve decoupling mechanisms 
 
### Example Question
A company is migrating its on-premises application to Amazon Web Services and refactoring its design. The design will consist of frontend Amazon EC2 instances that receive requests, and a message queuing service to address decoupling the application. The solutions architect has been informed that a key aspect of the application is that requests are processed in the order in which they are received.
Which AWS service should the Solutions Architect use to decouple the application?
a. Amazon Simple Queue Service (Amazon SQS) standard queue
b. Amazon Simple Notification Service (Amazon SNS)
c. Amazon Simple Queue Service (Amazon SQS) FIFO queue
d. Amazon Kinesis
Ans:
c,

## Resilient storage

### Overview
 - Define your strategy to ensure the durability of data
 - Identify how data consistency will affect operations
 - Know how to implement across a variety of architectures, including hybrid or non-cloud-native applications
 - Questions to ask
   - How different data services handle durability?
   - What scenarios each of the different services are united towards?
 - How will data consistency will come into play with the operations?
   - Its important to understand not only how the data consistency is handled by services but alse how calls to the services can be impacted
   - With replication for durability, the calls made, and the way these calls are made can impact the type of data your retrieve
   - Also look at the ways you can work with different consistency scenarios
     - Look at PUTs of new data as well as changes to existing data, and methods to best access the most-recent data
 - Now comes the selection of data services that will meet the requirements
   - This area will be where it is critically important to understand the functionality of the services as well as how the access-patterns requirement can be impacted by that functionality
   - Do you have read heavy workload or do you need fast read and write capabilities?
   - Does data needs to be accessible by global user base or is it limited to a single IP range?
   - Are you working with smaller files or do you need to move larger objects?
 - Your access and other requirements will go a long way in determining which services you might use
 - You should be able to identify storage services that can used with hybrid or non-cloud native applications
 - When looking for migarting data when you would look for one of the AWS snow family over the other?
 - What kind of services you will consider for consistent and regular data tranfers between on-premises and AWS environments? 

### Example Question
An organization is planning to migrate from an on-premises data center to an AWS environment that spans multiple Availability zones. A migration engineer has been tasked to plan the transfer of the home directories and other shared, network attached storage from the data center to AWS. Your migration design should support connections from multiple EC2 instances running the Linux operating system to this common, share storage platform
Which storage options best fits your choice?
a. Transfer the files to S3a nd access the data from EC2 instances
b. Transfer the files to the EC2 instance store attached to the EC2 instances
c. Transfer the files to Amazon EFS and mount that file system to the EC2 instances
d. Transfer the files to one EBS volume and mount that volume to the EC2 instances
Ans:
a, incorrect: S3 does not provide file systems such as home directories
b, incorrect: Cannot be moved across multiple ec2 instances
c, correct
d, incorrect: beetrs for data that requires frequent update

## Design Resilient architectures Summary

### Overview
 - What access paths are aailable for instance or database management
 - What services and methods will be available for providing access to the document that needs to be widely distributed and reachable over time
 - Block STorage vs Object storage for storing the above document
 - How you use Availability Zones and Regions, Failover mechanisms, and disaster recovery strategies will be crucial to architecture design
 - Understand services and feature usage for decoupling and storage resources

### Lesson
This credential helps organizations identify and develop talent with critical skills for implementing cloud initiatives. Earning AWS Certified Solutions Architect – Associate validates the ability to design and implement distributed systems on AWS.

To learn more, see: [AWS Certified Solutions Architect - Associate](https://aws.amazon.com/certification/certified-solutions-architect-associate/)

To earn this certification, you will need to take and pass the AWS Certified Solutions Architect – Associate exam. The exam features a combination of two question formats: multiple choice and multiple response. Additional information, such as the exam content outline and passing score, is in the exam guide.

To learn more, see: [AWS Certified Solutions Architect - Associate Exam Guide](https://d1.awsstatic.com/training-and-certification/docs-sa-assoc/AWS-Certified-Solutions-Architect-Associate_Exam-Guide.pdf)

The multi-tier application has been a cornerstone architecture pattern for decades, and remains a popular pattern for user-facing applications. Although the language used to describe a multi-tier architecture varies, a multi-tier application generally consists of a presentation tier, a logic tier, and a data tier.

To learn more, see: [AWS Serverless Multi-Tier Architectures with Amazon API Gateway and AWS Lambda](https://docs.aws.amazon.com/whitepapers/latest/serverless-multi-tier-architectures-api-gateway-lambda/introduction.html)

AWS Well-Architected helps cloud architects build secure, high-performing, resilient, and efficient infrastructure for their applications and workloads. Based on five pillars—operational excellence, security, reliability, performance efficiency, and cost optimization—AWS Well-Architected provides a consistent approach for customers and AWS Partner Network (APN) Partners to evaluate architectures and implement designs that can scale over time.

To learn more, see: [AWS Well-Architected](https://aws.amazon.com/architecture/well-architected/)

Achieving reliability can be challenging in traditional on-premises environments because of single points of failure, lack of automation, and lack of elasticity. By focusing on best practices and recommendations, you will be enabled to build architectures that have strong foundations, resilient architecture, consistent change management, and proven failure recovery processes.

To learn more, see: [Reliability Pillar - AWS Well-Architected Framework](https://docs.aws.amazon.com/wellarchitected/latest/reliability-pillar/welcome.html)

Event-driven architecture can help you decouple services and simplify dependencies as your applications grow. In this session, you learn how Amazon EventBridge provides new options for developers who want to gain the benefits of this approach.

To learn more, see: [Decoupling serverless workloads with Amazon EventBridge (video)](https://www.youtube.com/watch?v=VI79XQW4dIM)

The pattern provided in the following documentation link shows asynchronous communication between microservices by using an asynchronous poll model. A loosely coupled architecture can be built, which avoids bottlenecks caused by synchronous communication, latency, and I/O. In the pattern's use case, Amazon Simple Queue Service (Amazon SQS) and Lambda are used to implement asynchronous communication between different microservices.

To learn more, see: [Decouple messaging pattern](https://docs.aws.amazon.com/prescriptive-guidance/latest/modernization-integrating-microservices/decouple-messaging.html)

Amazon Elastic File System (Amazon EFS) file systems are resilient to one or more Availability Zone failures in an AWS Region. Mount targets themselves are designed to be highly available. As you design for high availability and failover to other Availability Zones, remember that though the IP addresses and DNS for your mount targets in each Availability Zone are static, they are redundant components backed by multiple resources.

To learn more see: [Resilience in Amazon Elastic File System](https://docs.aws.amazon.com/efs/latest/ug/disaster-recovery-resiliency.html)

Each object in Amazon Simple Storage Service (Amazon S3) has a storage class associated with it. For example, if you list the objects in an S3 bucket, the console shows the storage class for all the objects in the list. Amazon S3 offers a range of storage classes for the objects that you store. You choose a class depending on your use-case scenario and your requirements for performance access. All Amazon S3 storage classes are designed to offer high durability.

# Module 2: High-performance architectures

## Elastic and Scalable Compute solutions for a workload

### Overview
 - Some services in AWS are Elastic by nature where we need not do anything. For example: Lambda whereas some serives like EC2 are not inherently scalable
   - Lambda
     - Scalability and Elasticity are built in
     - You can adjust concurrency to meet scaling needs
   - EC2
     - Scalability and Elasticity are not built in
     - You can use Amazon EC2 AUtoScaling to meet scaling needs
     - When choosing EC2 instance, start with which Instance type will you use?
        - To select correct instance type, need to see what are the application needs- Compare types on high level to get overview of them
 - Choose the appropriate architecture and services that scale to meet performance requirements         
 - EC2 vs ECS vs Lambda
 - Know the use cases, benefits, and limitations of AWS compute services
 - Example of limitation: Lambda can run for max of 15 minutes at a time
 - Scaling
   - Scaling event occurs when alarm is created in Amazon CloudWatch
   - These alarms are invoked when a metric crosses a certain threshold for a defined amount of time
   - Knowing how to choose what metrics to monitor for scaling is very important from examm perspective
   - CPU utilization is a common EC2 metric to monitor for scaling, If a CPU utilization is too high for an instance or Instance group it is a sign that instances cannot keep up with current demand and a scaling event should occur
   - Custom Metric can be created based on the demand
   - Metric related to Elastic Load Balancing like `HealthyHostCount` or `SurgeQueueLength`
   - It depends on the situation to identify which scaling metrics makes most sense

### Example Question
A company has developed an application that processes photos and videos. When users upload photos and videos, a job can take upto 1 hour to process long videos. The company is using Amazon EC2 on-demand instances to run web servers and processing jobs.  
The web layer and the processing layer have instances that run in an Auto Scaling group behind an Application load balancer. During peak hours, users report that the application is slow and that the application does not process some request at all. During Evening hours, the systems are idle. What should a solutions architect do so that the application will process all jobs in the Most Cost-effective manner?
a. Use a larger instance size in the Auto scaling groups of the web layer and processing layer
b. Use spot instances for the auto scaling groups of the web layer and the processing layer
c. Use an Amazon simple queue service (Amazon SQS) standard queue between the web layer and the processing layer. Use a custom queue metric to scale the Auto scaling group in the processing layer.
d. Use AWS Lambda functions instead of EC2 instances and Auto scaling groups.Increase the service quota so that sufficient concurrent functions can run out at the same time.
Ans:
 c

## High performing and scalable storage solutions for workload

### Overview
 - Primary question while architecting solution is "How and where to store the data you will be working with?"
 - Picking a right storage solution involves: 
   - evaluating how a solution scales
   - how it performs under a given use case
   - Example 1: When you attach EBS volume to an EC2 instance, you can sacle via:
     - Scale by changing the EBS volumes
     - Scale by attaching more volumes
   - Example 2: When you mount an Elastic File storage (EFS) onto EFS file system, it scales automatically
 - Determine which storage solution is the best fit based on future storage needs
 - Know general upper bounds for the capacity of storage solutions
 - Determine which solution would be the most appropriate for a situation based on performance
 - Be familiar with different Amazon Elastic Block store (Amazon EBS) volume types and their performance implications
   - Which storage service is best suited for low-latency requirements?
   - Amazon EBS volumes provide extremely low latency and degree to ow performant they can be is configurable
 - Should be familiar with major performance configurations for each service
   - Example: How can you improve performance of data upload or download/retrieval with Amazon S3
   - Basic API calls
   - Multiplart uploads: for larger file uploads
   - Amazon S3 Accelerator: for more performant uploads
   - Caching with Amazon CloudFront: for faster data retrieval
 - Focus on:
   - Being able to determine which storage service to use that can scale to accomodate current and future needs
   - Choosing AWS services and configurations that meet performance needs under different circumstances   

### Example Question
A solutions Architect has been given a large number of video files to upload to an Amazon S3 bucket. The file sizes are 100-500 MB. The solutions Architect also wants to Easily resume failed upload attemots. How should the solutions architect perform the uploads in the Least amount of time?
a. Split each file into 5 MB parts. Upload the individual parts normally and use S3 multi-part upload to merge the parts into a complete object
b. Using the AWS CLI copy individual objects into the Amazon S3 bucket with the AWS s3 cp command
c. From the Amazon S3 console, select the s3 bucket. Upload the S3 bucket and drag and drop items ito the bucket
d. Upload the files with SFTP and the AWS transfer family
Ans:
b, when your size reaches 100MB, consider using multipart uploads. Using multipart upload improves throughput and it could also allow quick recovery of restarting objects due to network failure. **Aws cp commands automatically perform multipart uploading and downloading based on the file size**

## High performing Network solutions for a workload

### Overview
 - Select high-performing networking solutions for a workload given a defined set of requirements or circumstances
 - Example: Following a hybrid model is very common while working with AWS, meaning a company has on-premises data center that is operating alongside their solutions hosted in AWS. The on-preises data center and the AWS resources often need a way to privately communicate to transfer data and messages across systems. You cna connect your AWS services to your on-premises, data center resources through VPN connections or the use of services like "AWS Direct connect or "AWS managed VPN"
 - AWS networking connectivity services:
   - AWS Managed VPN
   - AWS Direct connect
   - AWS Transit gateway: can be used with above two to connect multiple VPCs to a remote network. its good to know how it works, its use cases and how it can simplify network peering solutions
   - AWS VPN CloudHUb: It helps you create a hub and spoke models for connecting networks
 - You should be aware about how to create connection between VPCs. You need to do this so that applications in one vpc can receive and send messages, or data with an application being hosted in another VPC or in another VPC account
 - Connectivity between VPCs:
   - VPC peering
   - AWS Transit gateway
   - AWS PrivateLink
   - AWS Managed VPN
 - Select appropriate features to optimize connectivity to AWS for public services   
 - AwS Global Accelerator can improve application network performance
 - COnsider caching assets closer to your end users by using Amazon CLoudFront
 - For directing the user to a website that is deployed in multi region, you can use Route53 with geoproximity routing
 - AWS transfer services:
   - AWS Data sync
   - AWS SNow family
   - AWS transfer family
   - AWS Database migration Service (AWS DMS)
 
### Example Question
A large international company has a management account in AWS Organizations, and over 50 individual accounts for each country they operate in. Each of the country accounts has least 4 VPCs setup for functional divisions. There is ahigh amount of trust across the accounts and communication among all of VPCs should be allowed. Each of the individual VPCs throughout the entire Global organization will need to access an account and VPC that provide shared services to all the other accounts. How can the member accounts access the shared services VPC with the LEAST operational overhead?
a. Create an Application Load balancer, with a target of the private IP addresses of the shared services VPC. Add a Certification Authority Authorizaton (CAA) record for the ALB to Amazon Route 53. Point all requests for shared services in the VPCs routing tables to that CAA record
b. Create a peering connection between each of the VPCs and the shared services VPC
c. Create a Network Load Balancer across the AZs in the shared services VPC. Create Service consumer roles in IAM, and set endpoint connection acceptance to automatically accept. Create consumer endpoints in each division VPC and point to the NLB
d. Create a VPN connection between each of the VPCs and the shared service VPCs
Ans:
c, Correct: AWS PrivateLink is best used when you have client-server setup where you want to allow one or more consumer VPCs unidirectional access to a specific service (or set of instances) in the service provider VPC
b, Incorrect: VPC peering connections has limit on number of peering connections. 1 VPC can accept upto 125 peering connections. 

 
AWS PrivateLink connection setup:
1. Create a load balancer
2. Create a Service-consumer role in IAM 
3. Setup an endpoint connection in the shared services VPC, and set it to automatically accept
4. create Consumer endpoints in each VPC that must access the shared service
5. Point to the network Load balancer in the shared services VPC
  
## High performing "DATABASE" solutions for a workload

### Overview
 - How to choose a database services is important. for example Amazon RDS can be a highly performant database, but if you have a usecase that needs single-digit-milliseconds response times, then Amazon DynamoDB can be a better choice
 - Know the differences between Amazon Relational Database Service (Amazon RDS) and Amazon Aurora
 - Amazon RDS STirage types:
   - General purpose SSD
   - Provisioned IOPS SSD
   - Magnetic
 - Choose appropriate scaling strategies to meet scaling needs
 - Amazon DynamoDB automatically scales storage and you retain control over scaling throughput
 - To scale Amazon RDS, Update the DB instance, use storage auto scaling or use read replicas
 - Scaling appropriate database-scaling strategy depends on type of database you are using and the scenario
 - When database caching is required for performance improvement is also crucial. Some time you dont want to scale database but want performance improvement. This can be done by caching. Different database services have different aching features that you should be familiar with
 - Know when to use Amazon ElastiCache for caching, and the impacts on performance
 
### Example Question
A company is building a distributed application, which will send sensor IoT data - including weather conditions and wind speed from Wind turbines - to the AWS cloud for further processing. As the nature of data is Spiky, the application needs to be able to scale. It is important to store the streaming data in key-value databases and then send it over to a centralized data lake, where it can be transformed, analyzed, and combined with diverse organizational datasets to derive meaningful insights and make predictions. Which combination of solutions would accomplish the business need with minimal operations overhead (Select Two)?
a. Configure Amazon Kinesis delivering streaming data to an Amazon S3 data lake
b. Use Amazon DocumentDB to store IoT sensor data
c. Write AWS Lambda functions delivering streaming data to S3
d. Use AMazon DynamoDB to store the IoT sensor data and enable DynamoDB streams
e. Use Amazon Kinesis delivering streaming data to Amazon Redshift and enable Amazon Redshift spectrum
Ans:
A,D 

## Design High-performance architectures summary

### Summary
 - Solution Layers:
   - Compute
   - Storage
   - Database
   - Networking
   - Others: Security, authetication, etc
 - Compute: 
   - Important to match your compute choice with use case and not vice-versa
   - Example: You are architecting a solution for a company to migrate an existing backend web services to AWS. The Application is currently running on web services in an on-premises data center, and the company needs to migrate the application with the least amount of rework to the code as possible. What kind of solution would you recommend for this?
   - Ans: lift and shift to Amazon EC2 would require the least amount of effort
   - Understand the scaling mechanisms, failover mechanisms and also how to setup for the different compute services
   - For EC2 understanding auto scaling step by step and also how to troubleshoot it is important while working with AwS
   - Container services: Cluster scaling
   - AWS Lambda: Concurrency rates
   - Consider where the service you are using resides in the AWS global infrastructure
 - Storage:
   - often times the type of data you are storing and the performance requirements will determine which service you choose
   - SHould be aware about, which services are for block storage, object storage, file storage
   - Look for how these services would impact performance for reading and writing data, durability of data, and to backup data for each storage service
   - Know how to take and manage EBS snapshots is an important part of setting up block-storage solutions
   - Know when to offload requests to caches to increase performance, read replicas for RDS, DynamoDB accelerator for DynamoDB, Amazon ELastiCache for Redis.Memcached
   -Know which DB allows Read replics across AZs and which do not
 - Networking
   - General steps in setting up and operating the network solutions
   - How to mitigate issues by taking advantage of things like Multi-AZ or Multi-region deployment for failover and disaster recovery
   - Know the AWS global infrastructure well for the exam
     - Some services are available at edge locations while some are not
     - Should know the bounds of services from Infrastructure point of view
   - Understand Amazon Route 53, the different record types, the different routing methods, and how regional failover works
   - Know how to:
     - Create a VPC
     - Create Subnets
     - Use Network ACLs
     - Use Security groups
     - use route tables
     - USe gateways
   - Networking services and features to study
     - AWS managed VPN
     - AWS Direct Connect
     - AWS Transit gateway
     - VPC peering
     - AWS PrivateLink: for connectivity
     - VPC endpoints: for connectivity

### Lesson
Instance types include varying combinations of CPU, memory, storage, and networking. You have the flexibility to choose the appropriate mix of resources for your applications. Each instance type includes one or more instance sizes, which can help you meet the requirements of your target workload.

To learn more, see: [Amazon EC2 Instance Types](https://aws.amazon.com/ec2/instance-types/)

Metrics, or the data about the performance of your systems, are provided by many resources. The ability to activate detailed monitoring for some resources—and publishing your own application metrics—provides additional visibility into your environments. You can gain insight into the functionality and behavior of the resources you use by understanding what metrics are available, how to view them, and how to understand them.

To learn more, see: [Using Amazon CloudWatch metrics](https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/working_with_metrics.html)

The optimal storage solution for a particular system varies, based on the kind of access method (block, file, or object), patterns of access, throughput required, frequency of access, frequency of update, and constraints on availability and durability. To improve performance, well-architected systems use multiple storage solutions and enable different features.

To learn more, see:

 - [Overview of Amazon Web Services - Storage] (https://docs.aws.amazon.com/whitepapers/latest/aws-overview/storage-services.html)
 - [AWS Well-Architected Framework - Performance Efficiency Pillar: Storage Architecture Selection](https://docs.aws.amazon.com/wellarchitected/latest/performance-efficiency-pillar/storage-architecture-selection.html)

Amazon Virtual Private Cloud (Amazon VPC) provides multiple network connectivity options for you to use, depending on your current network designs and requirements. These connectivity options include using either the internet or an AWS Direct Connect connection as the network backbone, and terminating the connection into AWS or user-managed network endpoints.

To learn more, see: [Amazon Virtual Private Cloud Connectivity Options - Introduction](https://docs.aws.amazon.com/whitepapers/latest/aws-vpc-connectivity-options/introduction.html)

For caching, a content delivery network (CDN) can reduce the load on an application origin and improve the experience of the requestor by delivering a local copy of the content from a nearby cache edge, or Point of Presence (PoP). Because the CDN delivers the content, the application origin doesn’t need to open the connection and deliver the content directly to the user.

To learn more, see: [How caching works with CloudFront edge locations](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/cache-hit-ratio-explained.html)

The ability to properly store, secure, and retrieve data effectively can make a difference when you consider customer experiences, optimize processes, and make informed, proactive decisions. Because data (and its proper handling) are so important, choosing the correct way to manage it requires understanding the strengths and functionality of the available options.

To learn more, see: [Overview of Amazon Web Services - Database](https://docs.aws.amazon.com/whitepapers/latest/aws-overview/database.html)

Though Amazon Relational Database Service (Amazon RDS) is a managed service, understanding the best practices and optimization recommendations for Amazon RDS can help you achieve better efficiencies in operations, performance, and cost.

# Module 3: Secure Applications

## Secure Resource Access

### Overview
 - Consider security at every level
 - Domain Segments:
   - Design secure access to AWS Services
   - Design secure application tiers
   - Select appropriate data security options
 - How people, tools and applications you build will access the AWS services should be the place to start.
 - Determine who or what can launch or terminate your resources, but also managing how and when access is given, operational permissions and almost anything else that would involve calls to the service
 - AWS Identity and Access Management (IAM): Look into users, groups, and roles, and what goes into decding between which to use, and how they might be combined. How do you create them? what are their streangths and limitations? and what scenarios will dictate possibly switching between the various user, group and role-based permissions?
 - Make sure you know how IAM and other AWS services enable you to secure the necessary credentials as well as the best practices for handling those credentials
 - Look for Best practices around controlling your application's access to the AWS APIs
   - When hould you hardcode credentials into your application? NEVER
   - What other ways are there to enable API Access?
   - Should know how to write and Interpret policy documents
   - Learn major parts of policy statmenet? whats required and what are the ways in which the policies provide granularity with permissions?
   - Understand IAM's decision logic when evaluating policies and how that will affect an identity with multiple policies attached
   - Understand methods, services and tools available that help you to create traceability for access to AWS resources
   - Just as you need to be aware of performance and behaviours of your application components, You need to have insoght into who and what have access to your account resources, know how to gain that visibility, how to enforce security standards and how to alert  and automate based off of that data

### Example Question
The CIO of a company is concerned about the security of the root user of their AWS account. Hw can the CIO ensure that the root account follows the best practices for securely logging in? (Select two)   
a. Enforce the use of an access key ID and secret access key for the root user logins
b. Enforce the use of MFA for the root user logins
c. Enforce a root user to assume a role to access the root user's own resources
d. Enforce the use of complex passwords for member account root user login's
e. Enforce the deletion of the root account so that it can't be used
Ans:
b, d

## Secure Application Tiers

### Overview
 - Pay significant attention to the use and functionality of security groups and network access control lists (network ACLs), This will provide a lot of management over traffic-control requirements, and can greatly enable granulity in the rules, restrictions and allowances that you need. Understand how they work, both together and individually. Also understand how to build their rules, pitfalls to avoid, rule-processing logic and methods to employ for better combined functionality
 - Network segmentation will be important for you to dive into.
 - Understand strategies behind public and private subnets, What differentiates a public from a private subnet and common practices around the use of these subnets. 
 - Understand routing mechanisms within a virtual private cloud (VPC), this will involve the implementation, use and features of route tables, but this will also require you to know how to appropriately select and deploy other components, such as AWS service endpoints, VPN connections and other network connection tools and methodsthat are commonly used with deployed VPCs
 - How do you built in security to these networking tiers?
 - How do you secure application use across these tiers? What does management of these security components look like?
 - All the above topics do not stand alone
   - Understanding the usage of security groups and network ACLs
     - Requires understanding of public and private subnet
	   - To comprehend the public and private subnets , you need to know about routing mechanisms and how to utilize them with the subnets
   - Example: Allow specific type of traffic to access your application servers, but the traffic was going to be coming from your on-premises location, traversing a VPN, and your application servers were in private subnet, how would you set that up?
  - What could you do to make sure the application servers were safe from access coming from public Internet, but that there wouldn't be any issues that the VPC, subnet or instance levels of the requests coming from the on premises, connected via VPN
  - What may be considered as a simple request, requires understanding of everything above and the exam items will require you to have studies the individal components as well as their interactions
  - Select appropriate AWS services to protect applications from external threats. Essentially waht services can be used to mitigate and prevent various types of attacks that might be targeted at your application? What managed options do you have? What security controls do the secuity services provde for the types of traffic that might be allowed? At which point in traffic flow towards your application, would the services be placed? 
  - What would operate at the edge locations versus within your VPCs
  
### Example Question
A solution architect needs to design a secure environment for AWS resources that are being deployed to Amazon EC2 instances in a VPC. The solution should support for a three tier architecture consisting of web servers, application servers and a database clusters. The VPC need to allow resources in a web tier to be accessible from internet with only the HTTPS protocol  
Which combination of actions would meet these requirements? (Select two)
a. Attach Amazon API Gateway to the VPC. Create private subnets for the web, application, and database tiers
b. Attach an internet gateway to the VPC. Create public subnets for the web tier. create private subnets for the application and database tiers
c. Attach a virtual private gateway to the VPC. Create public subnets for the web and application tiers. Create private subnets for the database tier
d. create a Web server security group that allows the trafic from the internet. Create an application server security group that allows requests from only the Amazon API gateway on the application port. Create a database cluster security group that allows TCP connection from the application security group on the databse port only.
e. Create a webs erver secuirty group that allows HTTPs requests from the internet. Crete an application server security group that allows request from the web security group only. Create a database cluster security group that allows TCP connections from the application security group on database port only
Ans:
b,e

## Data Security Options

### Overview
 - Whether the data is in transit or rest, its security needs to be evaluated
 - What methods are available within AWS to secure your data, both at rest and at transit?
   - Example: What would data protection look like when using a VPN over the internet; a private connection through AWS Direct Connect, Connections between VPCs, transfers between services such as Amazon simole storage service and your VPC and protecting data in transit when reaching end users over the public internet?
 - How do the various data management and storage services handle data protection?
   - Example: How do various data management and storage services handle data protection? How would it look in S3 vs Elastc block storage. and does the use of those protections change the performance of the services? ANd when you are thhinking about theses ecurity topics, its natural to look at encryption, and then encryption leads to key management
 - What are encryption options in AWS?
   - How will networking services handle encryption for data in transit and waht options do you have for various services?
   - What will the encryption look licke across the different types of Load balancers within AWS and how would certificates be handled?
   - How do storage services handle Encryption? Does this differ between services like S3, EBS and various options within those services?
 - How are encryption keys handled?
   - How services handle keys? how key management services operate? and How a service like S3 will interact with a service like AWS key management service
   - COnsider a scenario where data is being generated on an instance which is using and EBS volume, and the data needs to be protected while maintaining durability. Would you want to store the data on Encrypted EBS volumes or transfer the data to encrypted S3 bucket? Will use of encryption affect performance? and if so how? How would you handle the root keys and will that method differ for your data keys? Are their any managed services that can help you secure, evaluate and audit the security of your data?
 - Understanding your data security options will require understanding how the services operate theri security options, and how the services will interact
 - Look into data security based on access patterns
   - Certain services like s3 allow you to not only manage security  for entire bucket but also to cotrol based on specific paths or objects. You should know which services will provide this level of granularity, read and build policies based on different access patterns and needs and understand how these policies are evaluated by the service backend. 

### Example Question
A company needs to implement a secure data encryption solution to meet the regulatory requirements. The solution must provide security and durabuility in generating, storing and controlling cryptographic data keys. Which action should be taken to provide the Most secure solution?
a. Use AWS KMS key management service to generate AWS KMS keys and data keys. Use AWS KMS key policies to control access to the KMS keys
b. Use AWS key management service to generate cryptographic keys and import the keys to AWS Certificate Manager. Use IAM policies to control access to the keys
c. Use a third party solution from AWS Marketplace to generate the cryptographic keys and store them on encrypted instance store volumes. Use IAM policies to control Access to the necryption key APIs
d. Use OpenSSL to genrate the cryptographic keys and upload the keys to an Amazon S3 bucket with encryption enabled. Apply AWS key management service key policies to control access to the keys.
Ans:
A 

## Secure Applications summary

### Summary
 - Designing ecure access to AWS resources provides clarification around who and what can access the resources in your account, how that access is allowed and the methods around creating and managing the access
 - Secure access to resources is crucial in determining how secure the initial design and development will be as well as how protected the continued management will remain
 - How you would protect web application and database tiers
 - What firewalls you might use
 - How individual instances and resources should be protected, such as instances and interactions with the managed services that could be included as well as how you are protecting the network environment that contain these elements.
 - VPCs have various control mechanisms that enable granular management of traffic flow and monitoring within the network
 - Data security requires understanding how data is used, how its accessed and what services youre utilizing to properly house the data. Many of the services will have built-in protection tools. There are also many other resources within AWS that can compliment the functionality native to the service, provide additional insight and security, and add granularity to your control

Example: You have an application that checks on development resources with specific tags, to verify whether they should still be running or if they need to be terminated. It runs on a single instance, with an attached block storage volume within a single subnet and VPC, and access is provided over the company's VPN connection. Considering all the levels, what are some of the considerations that need to be accounted for?  
Ans:    
 - Start with resource access level, there is a question of who might have access to start or terminate this instance
 - Moving further, Will need to consider what resources this application needs access to, and how that access would be provided. What calls does the application need to make, and how will it make those calls? and will the jobs run? What path is the instance taking to make these calls? 
 - When it comes to application tiers, it might seem straighforward, bt its always good to cover the bases. What do these security groups and network ACLs and route tables look like? Are there any gateways that need to be considered when evaluating the security? What does management access look like? What methods are available for updates to the application? 
 - For data security, This needs to be evaluated based on the type of data being gathered and sent by the application? How is it holding the data? What are the security and/or compliance requirements? is encryption necessary? With the reports being sent, how is it sending those reports and what types of in-transit protection needs to be utilized?
 
### Lesson
AWS and AWS Partner Network (APN) Partners offer a wide range of tools and features to help you to meet your security objectives. These tools mirror the familiar controls that you deploy in your on-premises environments. AWS provides security-specific tools and features across network security, configuration management, access control, and data security.

To learn more, see: [Security Products and Features](https://docs.aws.amazon.com/whitepapers/latest/introduction-aws-security/security-products-and-features.html)

You can use AWS Identity and Access Management (IAM) to manage access to AWS services and resources securely. By using IAM, you can create and manage AWS users and groups, and use permissions to allow and deny their access to AWS resources.

To learn more, see:
 - [Security best practices in IAM](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html)
 - [What is IAM?](https://docs.aws.amazon.com/IAM/latest/UserGuide/introduction.html)

A security group acts as a virtual firewall for your instance to control inbound and outbound traffic. When you launch an instance in a virtual private cloud (VPC), you can assign up to five security groups to the instance. Security groups act at the instance level, not the subnet level. Therefore, each instance in a subnet in your VPC can be assigned to a different set of security groups.

To learn more, see: [Security groups for your VPC](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_SecurityGroups.html)

A network access control list (network ACL) is an optional layer of security for your VPC that acts as a firewall for controlling traffic in and out of one or more subnets. You might set up network ACLs with rules that are similar to your security groups to add an additional layer of security to your VPC.

To learn more, see: [Network ACLs](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html)

Your VPC has an implicit router, and you use route tables to control where network traffic is directed. Each subnet in your VPC must be associated with a route table, which controls the routing for the subnet (subnet route table).

To learn more, see: [Route tables for your VPC](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html)

Data at rest represents any data that you persist in non-volatile storage for any duration in your workload. This includes block storage, object storage, databases, archives, Internet of Things (IoT) devices, and any other storage medium where data is persisted. Protecting your data at rest reduces the risk of unauthorized access, when encryption and appropriate access controls are implemented.

To learn more, see: [Protecting Data at Rest](https://docs.aws.amazon.com/wellarchitected/latest/security-pillar/protecting-data-at-rest.html)

Data in transit is any data that is sent from one system to another. This includes communication between resources within your workload, and communication between other services and your end users. By providing the appropriate level of protection for your data in transit, you protect the confidentiality and integrity of your workload’s data.

To learn more, see: [Protecting Data in Transit](https://docs.aws.amazon.com/wellarchitected/latest/security-pillar/protecting-data-in-transit.html)

AWS recommends encryption as an additional access control to complement the identity, resource, and network-oriented access controls that were already described. AWS provides a number of features that customers can use to encrypt data and manage keys. All AWS services offer the ability to encrypt data at rest and in transit. AWS Key Management Service (AWS KMS) integrates with the majority of AWS services. With AWS KMS, customers can control the lifecycle of and permissions on the keys that are used to encrypt data on their behalf.

To learn more, see: [Encrypting Data-at-Rest and -in-Transit](https://docs.aws.amazon.com/whitepapers/latest/logical-separation/encrypting-data-at-rest-and--in-transit.html)

Data classification provides a way to categorize organizational data based on criticality and sensitivity to help you determine appropriate protection and retention controls.

To learn more, see: [Data Classification](https://docs.aws.amazon.com/wellarchitected/latest/security-pillar/data-classification.html)

AWS KMS keys can be divided into two general types: AWS managed and customer managed. An AWS managed key is created when you choose to enable server-side encryption of an AWS resource under the AWS managed key for that service for the first time. The AWS managed key is unique to your AWS account and the Region where it’s used. An AWS managed key can only be used to protect resources within the specific AWS service for which it’s created. It does not provide the level of granular control that a customer managed key provides.

Note: AWS KMS is replacing the term customer master key (CMK) with AWS KMS key and KMS key. The concept has not changed. Also, the AWS-managed CMK is now known as the AWS managed key, and the customer-managed CMK is now known as the customer managed key. The following documentation link uses the previous terminology.

To learn more, see: [AWS-managed and Customer-managed CMKs](https://docs.aws.amazon.com/whitepapers/latest/kms-best-practices/aws-managed-and-customer-managed-cmks.html)

# Module 4: Cost Optimized Architectures

## Cost effective storage solutions

### Overview
 - Understand different storage solutions exist and know which one t use given a scenario
 - What service should be used at a high level for a use case
 - Amazon EBS: Cost optimization
   - Choose correct size of EBS volumes
   - Choose the appropriate volume type
   - Use services like AWS Trusted advisors
   - Delete Old Snapshots
     - Amazo data lifecycle manager
	 - AWS BAckup
 - Amazon S3: Cost optimization
   - Pricing based on how often we are accessing the data
   - By default, Objects are stored with the S3 standard storage class, which incurs highest cost for storage and lowest cost for retrieval
   - Cost can be optimized by making use of different storage class when appropriate
     - S3 Standard
	 - S3 Standard IA (infrequent Access)
	 - S3 Glacier
	 - S3 Glacier Deep Archive
   - Should be familiar with different storage classes an how they impact cost and retrieval times
   - Use S3 Intelligent-tiering for variable or unknown access patterns: best for scenarios where access patterns change or unknown access patterns

### Example Question
After reviewing the cost optimization checks in AWS Trusted Advisor, a team finds that it has 10,000 Amazon Elastic Block Store (Amazon EBS) Snapshots in its account that are more than 30 days old. The team has determined that it needs to implement better governance for the lifecycle of its resources. Which actions should the team take to automate the lifecycle management of the EBS snapshots with the LEAST effort? (Select TWO)  
a. Create and schedule a backup plan with AWS backup  
b. Copy the EBS Snapshots to Amazon S3, and then create lifecycle configurations in the S3 bucket  
c. Use Amazon Data Lifecycle manager (Amazon DLM)  
d. Use a scheduled event in Amazon EventBridge (Amazon CLoudWatch Events) and invoke AWS step functions to manage the snapshots  
e. schedule and run backups in AWS systems manager  
Ans:
a, c


## Cost effective Compute and Database services

### Overview
 - EC2 
   - pricing models
     - On demand Instances
     - Reserved Instances
     - Dedicated Instances
     - Spot Instances
     - Savings Plans (can be used for other compute resources as well)
   - Each of the above pricing models will have different impacts on costs and are best used under specific scenarios
   - Reserved instances can also be used for Amazon RDS database instances which can further optimize cost 
   - Consider Instance size and family for cost optimization
   - You must have monitoring in place to know how much utilixation your instance have over time
   - Right sizing EC2 instances and RDS DB instances are a good way to optimize for cost
 - Select appropriate scaling strategies given a scenario
 - Introducing a read replica can enable you to optimize for both cost and performance. It is more cost-effective than scaling the instances
 - Storage auto-scaling can be used for right sizing the RDS database
 - Select correct database service for your use case
 - Use managed services when possible, example: AWS Fargate for containers, AWS Lambda for compute or DynamoDB for Database

### Example Question
A startup company is lookig for a solution to cost-effectively run and access micro-services without the operational overhead of managing infrastructure. The solution needs to be able to scale quickly to accomodate rapid changes in the volume of requests and protect against common DDoS attacks. What is the MOST cost-effective solution that meets these requrements?
a. Run the Micro-services in containers using AWS Elastic BeanStalk  
b. Run the Micro-services in AWS Lambda behind API Gateway  
c. Run the Micro-services on Amazon EC2 instances in an Auto Scaling Group
d. Run the Micro-services in containers using Amazon Elastic Container Service (Amazon ECS) backed by EC2 instances  
Ans:
b, 
 - AWS Lambda is a great solution for hosting backend microservices without the operational overhead of managing infrastructure as AS Lambda is a serverless service. 
 - AWS Lambda:
   - Serverless compute service
   - Fast built in scaling
   - Massive capability for scaling
 - API Gateway
   - Serverless API hosting
   - Fast built in scaling
   - Massive capabilities for scaling
   - Throttle traffic to protect against Distributed Denial of Service (DDoS)
   - Protects at Layer 7 and Layer 3 against DDOS
     - Counterfeit requests are found at layer 7 while SYN flood which is at layer 3 in OSI model   

## Cost optimized network architectures

### Overview
 - In AWS, We pay for what we use, But, Remeber that data transfer and API calls can potentially incur costs
 - Example: Using S3 incurs charges for the amount of data stored in S3 bucket, but it also incurs cost for API calls made to S3, as well as data transfer out of bucket. SO one way to optimize cost is to reduce the number of API calls and data transfer that is occuring. It can be done via CDN like CloudFront
 - When and how to use CloudFront to optimize for costs and look for other scenarios apart from mentioned above
 - Determine strategies for reducing data transfer costs within AWS
   - data transfer charges are incurred when transferring data across AWS regions. So, if you are sending data across regions, consider how you are connecting those services. Using somethig like VPC peering could be more cost effective than using something like AWS Transit gateway. Knowing the network connectivity services and their pricing will be helpful
 - Understand the cost differences between using AWS managed VPN and AWS Direct connect. Generally, solution using Direct connect wil be more costly than using VPN connections. SO, unless you really need the throughput or security AWS direct connect provides , you should instead look to use VPN connections, as they are going to be more cost effective
 - Another type of connectivity is how you connect to AWS resources to manage them. For example if you need to log on to an Amazon EC2 instance to check out some logs or do some troubleshooting, there are many ways you can achieve this
 - Understand the cost implications of connectivity to resources
   - Secure Shell (SSH) or Remote Desktop Protocol (RDP) with a bastion host
   - AWS systems Manager session manager
   - Amazon EC2 instance connect
 - Be aware of cost implications of other Networking services like NAT gateway or transit gateways

### Example Question
A Company is developing an application that runs on Amazon EC2 instances in a private subnet. The EC2 instances use a NAT gateway to access the internet. A solutions architect must provide a secure option so that developers can log into the instances.  Which solution meets these requirements MOST cost-effectively?
a. Configure AWS systems manager session manager for the EC2 instances to enable login  
b. Configure a bastion host in a public subnet to log in to the EC2 instances in a private subnet  
c. Use the existing NAT gateway to log into the EC2 instances in a private subnet  
d. Configure AWS site-to-site VPN to log in directly to the EC2 instances  
Ans:
a Correct, Session manager enables you to setup secure and auditable instance management without the need to open inbound ports, maintain bastion hosts or manage SSH keys. No additional charhe for accessing EC2 instances by using session manager. This makes it great option for accessing private instances and its also very cost-effective   
b, incorrect, classic way to access private instances but not the cost effective one  
c, Incorrect, NAT gateways oly allow egress network traffic, not ingress. This meas you cannot establish an inbount connection  
d, Incorrect, VPN connections do incur costs and wont be cost effective

## Cost Optimized Architectures summary

### Summary
 - You can take iterative approach ro optimize your AWS bill
 - SO through monitoring you realized that your EC2 instances are underutilized, this gives an opportunity to right size the instance for the application or scale in
 - Next Intelligent Tiering can be enables for S3 and can take advantage of correct storage classes for your access patterns
 - For compute, 
   - choosing the most appropriate instance type and size is good place to start if you are using Amazon EC2
   - For AWS Lambda, consider runtime and resource consumption
   - For containers you can save by having smaller, more light weight containers and choosing the correct compute platform for your cluster, whether that is Amazon EC2 or AWS Fargae
 - For storage
   - For S3: Use appropriate storage tier for the use case
   - For EBS: Choose appropriate volume type and automate the management of the snapshot lifecycle
   - For database: Choosing the most aligned database for the use case. Consider caching mechanisms for cost savings instead of always scaling in or scaling out, and also consider database design or tweaking queries.
 - For networking:
   - Choose the correct connectivity service for the use case
### Lesson
Because Amazon Simple Storage Service (Amazon S3) is one of the most popular storage services in AWS, you should have an understanding of the best approach to optimizing Amazon S3 costs—without impacting application performance or adding operational overhead. After you learn the fundamentals to optimizing your S3 storage costs across your entire organization, they aren’t difficult to implement. Whether you have raw data that must be moved, analyzed, or archived—S3 has a storage class that is optimized for your data access, performance, and cost requirements.

To learn more, see: [Amazon S3 cost optimization for predictable and dynamic access patterns](https://aws.amazon.com/blogs/storage/amazon-s3-cost-optimization-for-predictable-and-dynamic-access-patterns/)

To manage your objects so that they are stored cost effectively throughout their lifecycle, configure their S3 Lifecycle. An S3 Lifecycle configuration is a set of rules that define actions that S3 applies to a group of objects.

To learn more, see: [Managing your storage lifecycle](https://docs.aws.amazon.com/AmazonS3/latest/userguide/object-lifecycle-mgmt.html)

Operating in the cloud has changed the way that organizations relate to—and account for—their costs. With pay-as-you-go pricing, decentralized resource procurement, and a wider breadth of product functionality, it’s important to re-evaluate the way that you approach IT-based financial processes.

To learn more, see: [A Beginner’s Guide to AWS Cost Management](https://aws.amazon.com/blogs/aws-cloud-financial-management/beginners-guide-to-aws-cost-management/)

The [AWS Free Tier](https://aws.amazon.com/free/) provides you with a great opportunity to further learn about—and get hands-on experience with—the various services. With the AWS Free Tier, you have the chance to work with database services that you might not have explored yet.

To learn more, see:
 - [Build Modern Applications with Free Databases on AWS](https://aws.amazon.com/free/database/)
 - [Control your AWS costs with the AWS Free Tier and AWS Budgets](https://aws.amazon.com/getting-started/hands-on/control-your-costs-free-tier-budgets/)

You can make informed decisions to design, modify, or accept architectural decisions by understanding where the data transfer occurs in your workload, the cost of the transfer, and its associated benefits.

To learn more, see: [Plan for Data Transfer](https://docs.aws.amazon.com/wellarchitected/latest/cost-optimization-pillar/plan-for-data-transfer.html)

AWS Cost Explorer is a tool that you can use to view and analyze your costs and usage. You can explore your usage and costs by using the main graph, the Cost Explorer cost and usage reports, or the Cost Explorer Reserved Instance (RI) reports.

To learn more, see: [Analyzing your costs with AWS Cost Explorer](https://docs.aws.amazon.com/cost-management/latest/userguide/ce-what-is.html)


## SAA-C02 versus SAA-C03

### Overview
This section provides an overview of the differences between the [SAA-C02](https://d1.awsstatic.com/training-and-certification/docs-sa-assoc/AWS-Certified-Solutions-Architect-Associate_Exam-Guide.pdf) and [SAA-C03](https://d1.awsstatic.com/training-and-certification/docs-sa-assoc/AWS-Certified-Solutions-Architect-Associate_Exam-Guide_C03.pdf) versions of the AWS Certified Solutions Architect - Associate exam.

What is the same?  
Most of the content in SAA-C02 is included in SAA-C03. In fact, more than 90% of the task statements in the SAA-C03 version were included in the SAA-C02 version.

What has changed?  
Some of the task statements have been reworded for clarity. Some task statements from the SAA-C02 content outline are covered in different domains in the SAA-C03 content outline. Examples of such task statements include “Design a multi-tier architecture solution” and “Choose appropriate resilient storage.”

What is new?  
The only new task statement is in Domain 3: Design High-Performing Architectures. The new task statement is Task Statement 5: Determine high-performing data ingestion and transformation solutions.

### SAA-C03 New Material
The new version of the exam includes one new task statement under the new Domain 3: Design High-Performing Architectures: The new task statement is Task Statement 5: Determine high-performing data ingestion and transformation solutions.

Knowledge of:
 - Data analytics and visualization services with appropriate use cases (for example, Amazon Athena, AWS Lake Formation, Amazon QuickSight)
 - Data ingestion patterns (for example, frequency)
 - Data transfer services with appropriate use cases (for example, AWS DataSync, AWS Storage Gateway)
 - Data transformation services with appropriate use cases (for example, AWS Glue)
 - Secure access to ingestion access points
 - Sizes and speeds needed to meet business requirements
 - Streaming data services with appropriate use cases (for example, Amazon Kinesis)

Skills in:
 - Building and securing data lakes
 - Designing data streaming architectures
 - Designing data transfer solutions
 - Implementing visualization strategies
 - Selecting appropriate compute options for data processing (for example, Amazon EMR)
 - Selecting appropriate configurations for ingestion
 - Transforming data between formats (for example, .csv to .parquet)

The following is a partial list of references to assist you in preparing for exam questions about this task statement:

Amazon Athena
 - [What is Amazon Athena?](https://docs.aws.amazon.com/athena/latest/ug/what-is.html)  
 - [Amazon Athena FAQs](https://aws.amazon.com/athena/faqs/)

AWS Lake Formation
 - [What is AWS Lake Formation? ](https://docs.aws.amazon.com/lake-formation/latest/dg/what-is-lake-formation.html)
 - [AWS Lake Formation FAQs](https://aws.amazon.com/lake-formation/faqs/)

Amazon QuickSight
 - [What is Amazon QuickSight?](https://docs.aws.amazon.com/quicksight/latest/user/welcome.html)
 - [Amazon QuickSight FAQs](https://aws.amazon.com/quicksight/resources/faqs/)

Storage Best Practices for Data and Analytics Applications
 - [Storage Best Practices for Data and Analytics Applications](https://docs.aws.amazon.com/whitepapers/latest/building-data-lakes/building-data-lake-aws.html)
 - [Data ingestion methods](http://https//docs.aws.amazon.com/whitepapers/latest/building-data-lakes/data-ingestion-methods.html)
 - [Transforming data assets](http://https//docs.aws.amazon.com/whitepapers/latest/building-data-lakes/transforming-data-assets.html)
 - [Securing, protecting, and managing data](http://https//docs.aws.amazon.com/whitepapers/latest/building-data-lakes/securing-protecting-managing-data.html)

AWS DataSync
 - [What is AWS DataSync?](https://docs.aws.amazon.com/datasync/latest/userguide/what-is-datasync.html)
 - [AWS DataSync FAQs](https://aws.amazon.com/datasync/faqs/)

AWS Storage Gateway
 - [AWS Storage Gateway Documentation](https://docs.aws.amazon.com/storagegateway/index.html)
 - [AWS Storage Gateway FAQs](https://aws.amazon.com/storagegateway/faqs/)

AWS Glue
 - [AWS Glue Documentation](https://docs.aws.amazon.com/glue/index.html)
 - [Built-in transforms](https://docs.aws.amazon.com/glue/latest/dg/built-in-transforms.html)

Amazon Kinesis
 - [Amazon Kinesis Documentation](https://docs.aws.amazon.com/kinesis/index.html)
 - [Amazon Kinesis Data Streams FAQs](https://aws.amazon.com/kinesis/data-streams/faqs/)
 - [Amazon Kinesis Data Firehose FAQs](https://aws.amazon.com/kinesis/data-firehose/faqs/)
 - [Amazon Kinesis Video Streams FAQs](https://aws.amazon.com/kinesis/video-streams/faqs/)

Amazon EMR
 - [What is Amazon EMR?](https://docs.aws.amazon.com/emr/latest/ManagementGuide/emr-what-is-emr.html)
 - [Amazon EMR FAQs](https://aws.amazon.com/emr/faqs/)
 - [Live Streaming on AWS](https://aws.amazon.com/solutions/implementations/live-streaming-on-aws/)

## Exam tips
 - Exam items will require you to understand and implement several aspects across topics
 - EC2:
   - Cluster placement group: low latency
   - Spread placement group: high availability

# Source
 - [AWS Skill builder Course link](https://explore.skillbuilder.aws/learn/course/9496/play/32556/welcome-to-solutions-architect-associate-exam-prep)