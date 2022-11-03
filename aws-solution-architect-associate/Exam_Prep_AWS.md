# Domains
 - Design Resilient architectures
 - Design High-performance architectures
 - Design Secure Applications and Architectures
 - Design cost-optimized Architectures

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
 - Consider security at every level
 - Domain Segments:
   - Design secure access to AWS Services
   - Design secure application tiers
   - Select appropriate data security options





## Exam tips
 - Exam items will require you to understand and implement several aspects across topics
 - 







Course link: https://explore.skillbuilder.aws/learn/course/9496/play/32556/welcome-to-solutions-architect-associate-exam-prep

EC2:
Cluster placement group: low latency
Spread placement group: high availability

