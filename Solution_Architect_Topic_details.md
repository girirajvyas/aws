# 4. EC2 - Elastic Compute Cloud

## 4.1 EC2 Basics

**Basics**  
 - EC2 is one of te most popular AWS offering
 - EC2 = Elastic Compute Cloud = Infrastructe as a Service (IaaS)
 - It mainly consiste of capability of:
   - Renting virtual machines **(EC2)**  
   - Storing data on Virtual drives **(EBS)**
   - Distributing load across machines **(ELB)**
   - Scaling the services using an auto-scaling group **(ASG)**
 - Knowing EC2 is fundamental to understand how the Cloud Works

**EC2 Sizing and configuration options**  
 - Operating System (OS): **Linux or Windows**
 - How much compute power and cores **(CPU)**
 - How much random-access memory **(RAM)**
 - How much storage space:
   - Network-attached **(EBS and EFS)**
   - hardware **(EC2 instance store)**
 - Network card: **speed of the card, public IP address**
 - Firewall rules: **security group**
 - Bootstrap script (configure at first launch): **EC2 User Data**

**EC2 instance types**
 - Different depending on the cpu ram storage and network performance
 - [High level image]

**EC2 User data**
 - It is possible to bootstrap our instances using and `EC2 user data` script
 - Bootstrapping means launching commands when a machine starts
 - That Script is only run once at the instance first start
 - EC2 user data is used to automate boot tasks such as:
   - Installing updates|
   - Installing softwares
   - Downloading common files from internet
   - Any other use cases as well
 - "EC2 user data script" runs with root user

**Hands on**

Launch EC2 instance running Linux

 - Launch first virtual server using the AWS Console
   - Choose region close to you
   - EC2 dashboard -> instances -> Launch Instance 
   - Step 1: Choose Amazon Machine Image (AMI)
   - These AMI is template image for all your work
   - you have 4 options to choose from:
     - Quick start
     - My AMIs
     - AWS Marketplace 
     - Community AMIs
   - Lets continue with quickstart and free tier option -> Amazon Linux 2 AMI (HVM), SSD Volume Type AMI
   - Step 2: Choose an instance type
     - there are multiple choice available here as well and can be filtered based on instance families and generation, we will select t2.micro (Free tier eligible)
   - Step 3: Configure Instance details
     - Number of instances: 1
     - Network: same as default
     - IAM roles: same as default (none)
     - User data
        - ```cmd
          # Install httpd
          yum update -yaml
          yum install -y httpd
          systemctl start httpd
          systemctl enable httpd
          echo "<h1>hello world from ${hostname -f} </h1>" > /var/www/html/index.html
        ```
     - Done
   - Step 4: Add storage: keep default
   - Step 5: Add tags -> you can add name for identification
   - Step 6: Configure security groups
     - Here, you have to create new security group, by default option is available for tcp, we will add for http with port 80
   - Step 7: Review and launch
      - Key pair: A key pair consists of a public key that AWS stores, and a private key file that you store. Together, they allow you to connect to your instance securely. For Windows AMIs, the private key file is required to obtain the password used to log into your instance. For Linux AMIs, the private key file allows you to securely SSH into your instance
      - Once you click on launch you are popped up to create a new key pair if not laready have or use and existing one
      - We will create a new one, provide a name and download key pair
      - `.pem` file will be downloaded
      - Launch instances
 - Get a First high-level approach to the various parameters
   - All steps given above
 - Launch a web-server using EC2 user data
   - We achieved this in step 3 above
 - Learn how to start/stop/terminate the instance
   - Actions -> Instance state -> start/stop/terminate
      - Start: Start the instance
      - Stop: Stop the instance, starting again will have new ip address assigned to the instance
      - Terminate: it will delete everything of this instance

## 4.2 Introduction to Security Groups

**Basics**  
 - Security Groups are the fundamental of network security in AWS
 - They control how traffic is allowed into or out of our EC2 instances
 - Security Groups only contain allow rules
 - Security groups rules can reference by IP or by security group

**In depth**  
- Security groups are acting as a firewall on EC2 instances
- They regulate:
  - Access to ports
  - Authorized ip ranges: ipv4 and ipv6
  - Control of inbound network (from other to the instance)
  - Control of outbound network (from the instance to other): Default is allowed all

**Classic ports to know**  

- 22  : SSH (Secure shell) - Log into a Linux instance
- 21  : FTP (File transfer protocol) - Upload files into a file share
- 22  : SFTP (Secure file transport protocol) - Upload files using SSH
- 80  : HTTP - Access unsecured websites
- 443 : HTTPS - Access secured websites
- 3389: RDP (Remote Desktop Protocol) - log into a Windows instance

**Hands on**

 - EC2 -> Network & Security -> Security Groups
 - You will have 1 default security group that is created by default with the account creation
 - And the another is that we created while creating the EC2 instance
 - [Screenshot]()
 - 0.0.0.0/0 -> Everywhere on ipv4
 - ::/0 -> Everywhere on ipv6
 - If you remove the http rules assigne above, it will affect all the EC2 instances it is assigne to.
 - Most of the time out issues are related to misconfiguration of security group
 - While creating a Security group, you have to select a type and then you can have source as:
   - Custom: ips you configure are allowed
   - Anywhere(0.0.0.0/0 or ::/0): any one is allowed
   - My IP: only your ip is allowed
 - You can resue these security groups

**Tips:**  
not accesible/Timeout: Security group issues
"connection refused error": application error/app not launched 

## 4.3 SSH Overview

**Summary**  

| OS               | SSH            |  Putty         |  EC2 Instance Connect|
| -------------    |:-------------: |:-------------: |:-------------: |
|  MAC             |    Yes         |                |    Yes         |
|  Linux           |    Yes         |                |    Yes         |
|  Windows < 10    |                |    Yes         |    Yes         | 
|  Windows >= 10   |    Yes         |    Yes         |    Yes         |  

**SSH on Windows 10**  

 - open powershell/cmd -> ssh -> if options comes up, you can continue or else have to use putty
 - [Screenshot](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/ssh/check_ssh_windows10.PNG)
 - Command to run: ssh -i <path to pem file downloaded> ec2-user@<ip addres of EC2 instance>
 - eg: ssh -i D:\Data\aws\firstEC2instance.pem ec2-user@3.135.188.103
 - ec2-user is the default user that is logged in to the ec2 instances
 - Confirm yes
 - [Screenshot]
 - bad permissions error
 - [Screenshot]
 - go to pem file -> right click -> security -> advanced
   - update owner to yourself
   - remove other users by first disabling inheritance
   - done
 - now you will be able to login

**SSH Instance Connect**  

 - Go to instances -> connect -> you have 3 choices here
   - A standalone ssh client
   - session manager
   - EC2 instance Connect (browser-based SSH connection)
 - We will select 3rd option -> ec2-user will be autopopulated -> Connect
 - SSH is mandatory for above to work, in case the security group attached to this instance does not have SSH enabled, it wont work
 - Works only with Amazon Linux 2 AMI

**EC2 Instance roles**

 - Connect via Instance connect
 - type `ping google.com` to verify if the connection is working from ec2 instance
 - lets try `aws --version` to check if aws-cli is installed
 - As it is installed lets try to fetch all users: `aws iam list-users`
 - [screenshot]
 - we do not have permissions to do so, we can try `aws configure` and provide our access key, but this will enable all the users login into this instance to use our credentials and hence we achieve this via roles.
 - NEVER USE `aws configure` in EC2 instance
 - Verify from: go to IAM -> Roles -> DemoRoleForEC2 is already created in above steps
 - EC2 -> instances -> Actions -> Instance Settings -> Attach/Replace IAM roles -> Assign DemoRoleForEC2 -> Apply
 - try again `aws iam list-users` and it will work for you this time.

## 4.4 EC2 Instance Launch types

**Summary**  
  - **On Demand**: Ideal for short workload, predictable procing
  - **Reserved**: Minimum 1 year is required
    - **Reserved Instances:** long workloads like databases
    - **Convertible Reserved Instances:** long workloads with flexible instances
    - **Scheduled Reserved Instances:** example is "every sunday 3 pm to 6 pm"
  - **Spot Instances:** short workloads, cheap, can lose instances (less reliable)
  - **Dedicated Hosts** book an entire physical server, control instance placement

**On Demand**  
 - Pay for what you use
   - Linux: billing per second, after the first minute
   - All other OS: billing per hour
 - Has the highest cost but no upfront payment
 - No long term commitment
 - Recommended for: 
   - Short term and uniterrupted workloads, where you cant predict how the application wwill behave

**Reserved Insances**
 - Upto 75% dicount compared to On-demand
 - Reservatio period: 1 year = discount +, 3 year = discount +++
 - Purchasing options: no upfront,  partial upfront = discount +, All upfront = discount +++
 - Reserve a specific instance type
 - Recommended for: 
   - steady state usage applications (think database)

 - **Convertible Reserved Instance:**
   - Can change the EC2 instance type
   - Upto 54% discount
 - **Scheduled Reserved Instances**
   - launch withing timewindow you reserve
   - when you require a fraction of day/week/month
   - still commintment over 1 to 3 years

**EC2 Spot instances**  

 - Can get a discount of upto 90% compared to On-Demand
 - Instances that you can "lose" at any point of time if your max price is less than the current spot price
 - The most cost efficient instances in AWS
 - Useful for workloads that are resilient to failure
   - batch jobs
   - data analysis
   - image processing
   - Any distributed workloads
   - workloads with a flexible start and end time
 - Not suitable for critical jobs and databases

**Dedicated Hosts**

 - An Amazon EC2 Dedicated host is a physical server with EC2 instance capacity fully dedicated to your use
 - Dedicated hosts can help you address "Compliance requirements" and reduce costs by allowing you to **use your existing server bound software licenses**
 - Allocated for your account for a 3 year period reservation
 - They are expensive
 - Useful for software that have complicated licensing model (BYOL - Bring your own license)
 - or for companies that have strong regulatory or compliance needs

**Dedicated Instances**

 - Instances running on hardware that's dedicated to you
 - may share hardware with other instances in same account
 - No control over instance placement (can move hardware after stop/start)
 - Soft version of dedicated host

## 4.5. Shared Responsibility Model for IAM

| AWS                                           | You                           |
| -------------                                 |:-------------:                      |
| Infrastructure (global network security)      | Security group rules|
| Isolation on physical hosts                   | OS patches and updates|
| Replacing faulty hardware                     | Software and utilities installed on the EC2 instance|
| Compliance Validation                         | IAM Roles assigned to EC2 and IAM user access management|
|                                               | Data security on your instance|

## 4.6 EC2 Summary
 
 - *EC2 Instance:*
   - AMI(OS)
   - Instance size (CPU + RAM)
   - Storage
   - Security groups
   - EC2 User data
 - *Security groups:* Firewall attached to the EC2 instance
 - *EC2 User data:* Script launched at the first start of an instance
 - *SSH:* start a terminal into our EC2 instances (port 22)
 - *EC2 Instance Role:* link to IAM roles
 - *Purchasing Options:*
   - On-demand
   - Reserved (Standard, Convertible, Scheduled)
   - Spot
   - Dedicated Hosts
   - Dedicated Insance

## 4.7 EC2 Quiz

Which network security tool can you use to control traffic in and out of EC2 instances?
 - Security groups

How long can you reserve an EC2 Reserved instance?
 - 1 or 3 years (Not anytime between 1 and 3 years)

Which EC2 purchasing option should you use for an application you plan on running on a server continously for 1 year?
 - Reserved Instances

# 5. EC2 Instance Storage

## 5.1 Whats an EBS Volume

**Basics**  
 - An EBS (Elastic Block Store) Volume is a network drive you can attach to your instances while they run
 - It allows your instances to persist data, even after their termination
 - They can **only be mounnted to one instance at a time (at the Certified cloud Practitioner level)**
 - They are **bound to a specific availability zone** and cannot be attached to another zone
 - Free tier: 30gb of free EBs storage of type gp2 per month
 - Its a network drive (i.e not a physical drive)
   - It uses a network to communicate the instance, which means there might be a bit of latency
   - It can be detached from an EC2 instance and attached to another one quickly
 - Its locked to an avaialbility zone (AZ)
   - An EBS volume in `us-east-1a` cannot be attached to `us-east-1b`
   - To move a volume across, you first need to snapshot it
 - Have a provisioned capacity (size in GBs and IOPS - I/O operations per second)
   - You get billed for all the provisioned capacity
   - You can increase the capacity of the drive over time

**Hands on**  
 - EC2 -> instances -> already one instance created -> verify `Root device` and `Block devices` in the details which links to an EBS volume -> you can click on the link or go as below
 - EC2 -> Elastic Block Store -> Volume -> a root block store is already avaialble (this is the one we provisioned while creating an EC2 instance)
 - Create new Volume: EC2 -> Elastic Block Store -> Volumes -> Create Volume -> 
   - Volume type: default (General Purpose SSD (gp2))
   - Size (GiB): 2
   - Availability zone: (should be same that of an EC2 instance you are planning to attach, you can verify via EC2 -> instances -> "Availability zone" column of your instance)
   - Create Volume
 - Created vilume will be populated in the list-users`
 - you can attach this volume via Actions -> attach Volume -> Select your instance from dropdown -> attach
 - Verify via EC2 -> instances -> already one instance created -> `Block devices` -> 2 entries now
 - If you delete an EC2 instance, an EBS volume attached to that instance if marked `delete on termination` to true while creating that instance will also be deleted

## 5.2 EBS Snapshots

**Basics**  
 - Make a backup (snapshot) of your EBS volume at a point in time
 - Not necessary to detach volume to do snapshot, **but recommended**
 - Can copy snapshots across AZ or Region

**Hands on**  
 - EC2 -> Elastic Block Store -> Volume -> select volume -> Actions -> Create snapshot 
   - Add Description
   - Create
   - Verify it is created via EC2 -> Elastic Block Store -> Snapshots
   - Now you can copy this snapshot anywhere to get that data via Actions -> Copy
   - Also, a volume can be created from this snapshot via Actions -> Create Volume -> now you can select the availability zone -> Create Volume

## 5.3 AMI Overview

**Basics**  
 - AMI - Amazon Machine Image
 - AMI are a customization of an EC2 instance
   - You add your own software, configuration, operating system, monitoring, etc
   - Faster boot/configuration time because all your software is pre-packaged
 - AMI are built for a specific region (and can be copied across regions)
 - You can launch EC2 instances from:
   - A public AMI: AWS provided
   - Your Oen AMI: you make and maintain them yourself
   - An AWS Marketplace AMI: An AMI someone lese made (and potentially sells)

**How to create an Custom AMI**  
 - Start an EC2 instance and customize it
 - Stop the instance (for data integrity)
 - Build an AMI - this will also create EBS snapshots
 - Launch instances from other AMIs

**Hands on**  
 - We already have an EC2 instance created, in case it is not dn follow the steps defined above to create the same
 - Instances -> Action -> Image -> Create Image -> provide name and then create
 - Go to Images -> AMI -> it will take some time(3 to 5 minutes for me) to create and will be in pending state.
 - Now lets use this above created custom AMI to create an Instance
 - Instances -> Instance -> Launch Instance -> My AMIs -> Select AMI that you just created -> t2.micro next -> Next (no user data as we will be having this from AMI created above) -> Next -> reuse all existing keys and security group -> launch
 - now you can hit the ip and you will get the same ip that you had before as we are using the same image and that ip was hard coded in index.html already
 
## 5.4 EC2 Instance Store

 - EBS volumes are network drives with good but "limited" performance
 - If you need high-performance hardware disk, use EC2 instance store
 - Better I/O Performance
 - EC2 Instance store lose their storage if they're stopped (ephermal)
 - Good to buffer/cache/scratch data/temporary content
 - Risk of data loss if hardware fails
 - Backups and replication are your responsibility

## 5.5 EFS - Elastic File System

**Basics**  
 - Managed NFS (Network file system) that can be mounted on 100s of EC2
 - EFS works with linux EC2 instances in multi avaialbility zones
 - High available, Scalable, expensive (3x gp2), pay per use, no capacity planning 

## 5.6 Shared responsibility model for EC2 storage

| AWS                                                 | You                           |
| -------------                                       |:-------------:                      |
| Infrastructure (global network security)            | Setting up backup/snapshot peocedures |
| Replication for data for EBS volumes and EFS drives | Setting up data encryption for additional security|
| Replacing faulty hardware                           | Responsibility of any data on the drives|
| Ensuring their employees cannot access your data    | Understanding the risk of using EC2 instance store|

## 5.7 EC2 instance Storage - Summary

 - **EBS Volumes**
   - network drives attached to one EC2 instance at a time
   - mapped to an avaialbility zones
   - Can use EBS snapshots for backups/transferring EBS volumes across avaialbility zones
 - **AMI**
   - Create ready-to-use EC2 instance with our customizations
 - **EC2 instance store**
   - High performance hardware disk attached to our EC2 instance
   - Lost if our instance is stopped/terminated
 - **EFS**
   - Network file system, can ve attached to 100s of instances in a region


### EC2 Metadata
 - EC2 instance Metadata is powerful but less known features
 - It allows EC2 instances to "learn about themselves" without using an IAM Role for that purpose.
 - URL: http://169.254.169.254/latest/meta-data
 - You can retrieve the IAM Role name from the metadat, but you CANNOT retrieve the IAM policy
 - Metadata: info about EC2 instance
 - Userdata: LAunch script of EC2 instance
 

# Containers 

## Docker container management on AWS
 - Amazon Elastic Container Service (ECS)
   - Amazon's own container platform
 - Amazon Elastic Kubernetes Service (EKS)
   - Amazon's managed kubernetes (open source)
 - AWS Fargate
   - Amazon's own serverless container platform
   - Works with ECS and with EKS
 - Amazon ECR
   - Store container images 

## ECS

### ECS - EC2 Launch type
 - ECS = Elastic container servvice
 - Launch Docker container on AWS = Launch ECS Tasks on ECS clusters
 - EC2 Launch Type: you must provision and maintain the infrastructure (the EC2 instances)
 - Each EC2 instance must run the ECS agent to register in the ECS cluster
 - AWS takes care of starting/stopping containers

### ECS - EC2 Launch Type - Autoscaling EC2 instances
 - Accomodate ECS Service scaling by adding underlying EC2 instances
 - Auto scaling Group scaling
   - Scale your ASG based on CPU utilization
   - Add EC2 instances over time
 - ECS cluster capacity provider
   - Used to automatically provision and scale the infrastructure for your ECS Tasks
   - Capacity provider paired with an Auto scaling group
   - Add EC2 instances when you are missing capacity (CPU, RAM,)
 
### ECS - Fargate Launch type 
 - Launch Docker containers on AWS
 - You do not provision the infrastructure (no EC2 instances to manage)
 - Its all serverless
 - You just create task definitions
 - AWS just runs the ECS tasks for you based on the CPU/RAM you need
 - To scale, just increase the number of tasks. Simple - no more EC2 instances
 
### IAM Roles
 - EC2 instance profiles (EC2 Launchtype only)
   - Used by ECS agent
   - Makes API calls to ECS service   
   - Send container logs to CloudWatch Logs
   - Pull Docker image from ECR
   - Reference Sensitive data in Secrets manager or SSM Parameter store
 - ECS Task Roles
   - Allows each task to have a specific role
   - Use different roles for different ECS services you run
   - task role is defined in the task definition

### Load balancer integrations
 - Application Load Balancer supported and works for most use cases
 - Network Load balancer recommended only for high throughput/high performance use cases, or to pair it with AWS private link
 - Elastic load balancer supported but not recommended (no advance features, no fargate)

### Data Persistances/Volumes (EFS)
 - Mount EFS file systems to ECS tasks
 - Works for both EC2 and Fargate launch types
 - Taks running in any AZ will share the same data in the EFS file system
 - Fargate + EFS  = Serverless
 - Use cases: Persistent multi-az shared storage for your containers
 - Note: Amazon S3 cannot be mountes as a file system

### ECS Service Auto scaling
 - Automatically increase/decrease the desired number of ECS tasks
 - Amazon ECS Auto Scaling uses AWS Application Auto Scaling
   - ECS serice average CPU utilization
   - ECS Service average memory utilization
   - ALB request count per target - metric coming from the ALB
 - Target tracking: scale based on target value for a specific CloudWatch Metric
 - Step Scaling: scale based on a specified cloudwatch alarm
 - Scheduler scaling: scale based on a specified date/time (predictable changes)
 - ECS service auto scaling (task level) != EC2 Auto scaling (EC2 instance level)
 - Fargate Autoscaling is much easier to setup (as serverless)

## ECR
 - ECR = Elastic container registry
 - Store and Manage Docker images on AWS
 - private and public repository (Amazon ECR public gallery - https://gallery.ecr.aws)
 - Fully integrated with ECS and backed by S3
 - Access is controlled through IAM  (permissions error are related to policy)
 - Supports image vulnerability scanning, versioning, image tags, image lifecycle

## EKS

### EKS Overview
 - EKS = Elastic kubernetes service
 - It is a way to launch managed kubernetes clusters on AWS
 - K8s is an open source system for automatic deployment, scaling and management of containerized (usually docker) application
 - Its an alternative to ECS, imilar goal but different API
 - EKS supports EC2 if you want to deploy worker nodes or Fargate to deploy serverless containers
 - Use case: if your company is already using k8s on premises or in another cloud, and wants to migrate to AWS using K8s
 - Kubernetes is cloud agnostic (can be used in any cloud: Azure, GCP)
 
### EKS Node Types
 - Managed Node Groups
   - Creates and manages Nodes (EC2 Instances) for you
   - Nodes are part of an ASG managed by EKS
   - Supports on-demand or spot instances
 - Self-Managed Nodes
   - Nodes created by you and registered to the EKS cluster and managed by an ASG
   - You can use pre-built AMI - Amazon EKS optimized AMI
   - Supports On-demand or spot instances
 - AWS Fargate:
   - No Maintenance required; no nodes managed

### EKS Data volumes
 - Need to specify StorageClass manifest on your EKS cluster
 - Leverages a Container Storage Interface (CSI) compliant driver
 - Support for:
   - EBS
   - EFS (works with fargate)
   - Amazon Fsx for Lustre
   - Amazon Fsx for NetApp ONTAP
 
## App Runner
 - Fully managed service that makes it easy to deploy web applications and the APIs at scale
 - No Infrastructure experience required
 - Start with your source code or container image
 - Automatically builds and deploy the web app
 - Automatic scaling, highly available, load balancer, encryption
 - VPC access support
 - Connect to database, cache and message queue services
 - Use case: WebApps, APIs, Micro-services, Rapid production deployments


# Databases

## Choose Database
 - We have a lot of Managed databases on AWS to choose from
 - Questions to ask to choose the right database based on your architecture
   - Read-heavy, write-heavy or balanced workload?
     - Throughput needs?
     - Will it change, does it need to scale or fluctuate during the day?
   - How much data to store and for how long?
     - Will it grow?
     - Average Object size
     - How are they accessed
   - Data Durablity?
     - Source of truth for the data?
   - Latency requirements?
     - Concurrent users
   - Data Model?
     - How will you query the data?
     - Joins?
     - Structured?
     - Semi-structured
   - Strong Schema?
     - More flexibility?
     - Reporting?
     - Search?
     - RDBMS?
     - NoSQL?
   - License Costs
     - Switch to cloud native DB such as Aurora

## Database Types:
 - RDBMS (=SQL/OLTP):  great for Joins
   - RDS
   - Aurora
 - NoSQL Databases: no joins, no SQL 
   - DynamoDB (~JSON)
   - ElastiCache (key/value pairs)
   - Neptune (graphs)
   - DocumentDB (for MongoDB)
   - KeySpaces (for Cassandra)
 - Object Store: 
   - S3 (for big objects)
   - Glacier (for backups/archives)
 - Data Warehouse (= SQL Analytics/BI )
   - Redshift (OLAP)
   - Athena
   - EMR
 - Search 
   - OpenSearch (JSON) - Free text, unstructured searches
 - Graphs
   - Amazon Neptune - displays relationship between data
 - Ledger
   - Amazon Quantum Ledger Database
 - Time Series
   - Amazon Timestream

## Amazon RDS
 - Managed PostgreSQL/MySQL/Oracle/SQL Server/MariaDB/Custom
 - Provisioned RDS instance size and EBS Volume Type and size
 - Auto-scaling capability for storage
 - Support for Read Replicas and Multi AZ
 - Security through IAM, Security groups, KMS, SSL in transit
 - Automated Backup with point in time restore feature (up to 35 days)
 - Manual DB snapshot for long-term recovery
 - Managed and scheduled maintenance ( with downtime)
 - Support for IAM Authentication, Integration with secrets manager
 - RDS Custom for Access to and customize the underlying instance (Oracle and SQL Server)
 - **Use Case:** Store relational datasets (RDBMS/OLTP), perform SQL queries, transactions
 
## Amazon Aurora
 - Compatible API for PostgreSQL/MySQL, Saperation of storage and compute
 - Storage data is stored in 6 replicas, across 3 AZ - Highly Available, Self-healing, Auto-scaling
 - **Compute:** Cluster of DB instances across Multiple AZ, Ato scaling of read replicas
 - **Cluster:** Custom endpoints for Writer and reader DB instances
 - Same security/monitoring/maintenance features as RDS
 - Know the backup and restore options for Aurora
 - **Aurora Serverless:** For unpredictable/intermittent workloads, no capacity planning
 - **Aurora Multi-Master:** For continous writes failover (high write availability)
 - **Upto 16 DB Read instances in each region, < 1 second storage replication
 - **Aurora Machine learning:** Perform ML using SageMaker & Comprehend on Aurora
 - **Aurora Database Cloning:** New CLuster from existing one, faster than restoring a snapshot
 - **Use Case:** Same as RDS, but with less maintenance/More flexibility/more performance/more features
 
## ElastiCache
 - Managed Redis/Memcached (Similar offering as RDS, but for caches)
 - In-memory data store, sub millisecond latency
 - Must provision an EC2 instance type
 - Support for Clustering (Redis) and Multi AZ, Read Replicas (sharding)
 - Security through IAM, Security Groups, KMS, Redis Auth
 - Backup/Snapshot/Point in time restore feature
 - Managed and scheduled maintenance
 - **Requires some application code changes to be leveraged**
 - **Use Case:** Key/Value store, Frequent reads, Less writes, Cache results for DB queries, store session data for websites, Cannot use SQL

## DynamoDB
 - AWS proprietary technology, managed serverless NoSQL database, millisecond latency
 - **Capacity modes:** Provisioned capacity with optional auto-scaling or on-demand capacity
 - Can replace ElastiCache as a kkey/value store (storing sessiondata for example, Using TTL feature)
 - high Availability, Multi AZ by default, Read and Writes are decoupled, transaction capability
 - DAX cluster for read cache, microsecond read latency
 - Security, authentication and authorization is done through IAM
 - **Event Processing: ** DynamoDB streams to integrate with AWS Lambda, or kinesis data streams 
 - **Global Table feature:** Active-Active setup
 - Automated backups upto 35 days with PITR (restore to new table), or on demand backups
 - Export to S3 without using RCU within the PITR window, Import from S3 without using WCU
 - **Great to rapidly evolve schemas**
 - **Use case:** Serverless application development (small documents upto 400 kbs), distributed serverless cache, does not have SQL query language available

## S3
 - S3 is a key/value store for objects
 - Great for bigger objects, not so great for many small objects
 - Serverless, Scales infinitely, Max object size is 5TB, versioning capability
 - **Tiers:** S3 standard, S3 infrequent access, S3 intelligent, S3 Glacier + lifecycle policy
 - **Features:** Versioning, Encryption, Replication, MFA-Delete, Access Logs
 - **Security:** IAM, Bucket Policy, ACL, Access points, Object Lamda, CORS, Object/Vault lock
 - **Encryption:** SSE-S3, SSE-KMS, SSE-C, Client-side, TLS in transit, default encryption
 - **Batch operations** on object using S3 batch, listing files using S3 inventory
 - **Performance:** Multi-part upload, S3 transfer acceleration, S3 Select
 - **Automation:** S3 event Notifications (SNS, SQS, Lambda, Event Bridge)
 - **Use Cases:** static files, key value store for big files, website hosting
 
## DocumentDB
 - Like Aurora is an "AWS implementation" of PostgreSQL/MySQL, DocumentDB is same for MongoDB which is a NOSQL DB 
 - MongoDB is used to store, query and index JSON data
 - Similar "deployment concepts" as Aurora
 - Fully Managed, Highly Available with replication across 3 AZ
 - DocumentDB storage automatically grows in increments of 10GB, upto 64TB
 - Automatically scales to workloads with millions of requests per second

## Neptune
 - Fully managed graph Database
 - A popular graph dataset  would be socal network
   - Users have friends
   - Posts have comments
   - Comments have like from users
   - Users share and like posts
 - Highly available across 3 AZ, with upto 15 read replicas
 - Build and run applications working with highly connected datasets - Optimized for these complex and hard queries
 - Can store upto billions of relations and query the graph with milliseconds latency
 - Highly available with replications across Multiple AZs
 - Great for storing knowledge graphs (Wikipedia), Fraud detection, recommendation engines, social networking

## KeySpaces 
 - Apache cassandra is an opensource NoSQL Database and Keyspaces is a managed Apache cassandra-compatible service
 - Serverless, scalable, Highly available, fully managed by AWS
 - Automatically scale table up/down based on applications traffic
 - Tables are replicated 3 times across multiple AZ
 - Using the Cassandra Query Language (CQL)
 - Single digit milliseconds latency at any scale, 1000s of request per second
 - Capacity: On-Demand mode or provisioned mode with auto scaling
 - Encryption, Backup, Point-In-Time Recovery upto 35 days
 - Use cases: Stre IoT devices info, time series data

## Quantum Ledger DB (QLDB)
 - A ledger is a book recording financial transactions
 - Fully managed, serverless, High available, Replication across 3 AZ
 - Used to review history of all the changes made to your application data over time
 - Immutable system: No entry can be removed or modified, cryptographically verifiable
 - 2-3 times better performance than common ledger blockchain frameworks, manipulate data using SQL
 - Difference with Amazon Managed Blockchain: No decentralization in accordance with the financial regulation rules in QLDB

## Timestream
 - Fully managed, Fast, Scalable, Serverless time series database
 - Automatically scales up/down to adjust capacity
 - Store and Analyze trillions of events per day
 - 1000s time faster and 1/10th the cost of relationsal database in case we have time series data
 - Scheduled queries, Mult-measure records, SQL COmpaibility
 - Data Storage tiering: recent data kept in memory and historical data kept in a cost-optimized storage data
 - Built in time series analytics function (help you identify patterns in your data in near real time)
 - Encryption in transit and at rest
 - Use cases: IoT apps, operational applications, real time analytics

## RDS Read replicas vs RDS Multi AZ (Availability zone)

### RDS Read replicas
 - upto 5 read replicas
 - Repliction are ASYNC i.e they are eventually consistent
 - Application must update the connection string to access read replicas

**Use case:**
 - Reporting application to run some analytics
 - Read replicas for SELECT statement only

**Network cost:**
 - In same region, dont pay fee for replication in same region
 - Across region replication fee is applicable
 
### RDS Multi AZ (Disaster Recovery)
 - SYNC Replication
 - One DNS name - Automatic App failover to standby
 - this Automatic failover increases availability
 - Automatic failover scenarios:
    - Loss of Availability Zone
    - Loss of Instance or
    - Loss of STorage
 - No manual intervention is required as its all automatic
 - It cannot be used for scaling as it is always on standby and cannot be accessed directly

**RDS: Move from single AZ to Multi AZ**  
How?  
 - Zero down time operation
 - can be done via clicking modify

What happens internally?   
 - A snapshot is taken
 - A new DB is restored from snapshot in a new AZ
 - Synchronization is establisdhed between the 2 DB

## Amazon Aurora
Advanced:
 - Aurora Replicas - Auto Scaling
 - Aurora - Custom endpoints
   - Define a subset of aurora instances as a custom endpoints
   - Example: Run analytical queries on specific replicas 
   - Reader endpoint is generally not used after creating custom endpoint
 - Aurora - Serverless
   - Automated database instantiation and auto-scaling based on actual usage
   - Good for infrequent, intermittent or unpredictable workloads
   - No capacity planning needed
   - Pay per second, can be more cost-effective
 - Aurora Multi master
   - In case we need immediate failover for write node (High Availability)
   - Every Node does R/W - vs promoting a Read Replica (RR) as the new master
 - Global Aurora
   - Aurora Cross region read replicas
     - Useful for disaster recovery
     - Simple to put in place
   - Aurora Global Database (Recommended)
     - 1 primary region (read/write)
     - Upto 5 secondary (readonly) regions, replication lag is less than 1 second
     - upto 16 Read Replicas per secondary region
     - Helps for decreasing latency
     - Promoting another region (for disaster recovery) has an Recovery Time Objective (RTO) of < 1 minute
   - Aurora Machine learning
     - Enables you to add ML based predictions to your application via SQL
     - Simple, Optimized and secure integration between Aurora and AWS ML Services
     - Supported Services:
       - Amazon Sage maker (use with any ML Model)
       - Amazon Comprehend (for sentiment analysis)
     - You dont need to have ML experience
     - Use Cases: Fraud detection, Ads targeting, Sentiment analysis, product recommendations

## ElasticCache
 - RDS is to get **managed** Relational Databases, same way Elastic cache is to get **managed** Redis or Memcached
 - Caches are in-memory databases with really high performance, low latency
 - Helps reduce load off databases for read intensive workloads
 - Helps make your application stateless
 - AWS takes care of:
   - OS Maintenance/patching
   - Optimizations
   - Setup
   - configuration
   - Monitoring
   - Failure recovery
   - backups
 - Using ElasticCache involves heavy application code changes

### ElastiCache - Cache security
 - All caches in ElasticCache:
   - Do not support IAM authentication
   - IAM policies on ElasticCache are only used for AWS API-level security
 - REDIS Auth
   - you can set "password/token" when you create Redis cluster
   - This is an extra layer of security for your cache (on top of security groups)
   - Support SSL in flight encryption
 - Memcached
   - Supports SASL-based authentication (advanced)

**Patterns for ElastiCache**  
 - Lazy Loading:
   - All the read data is cached, data can become stale in cache
 - Write through
   - Adds or updates data in the cache when written to a DB (no stale data)
 - Session store
   - Store temporary session data in a cache (using Time to live (TTL) feature)

**Use case - Redis:**
 - Gaming Leaderboards are complutationally complex
 - Redis sorted sets guarantee both uniqueness and element ordering
 - Each time a new element is added, its ranked in real time, then added in correct order
 
# Storage

##Snow types for huge data transfers

### Types
 - Snowball Edge
   - Storage optimized
   - Compute optmized
   - Compute optmized with GPU
 - Snowcone
    - 
 - SnowMobile

### Snowball data into glacier
 - Snowball cannot improt data into glacier
 - You must use Amazon S3 first, in combination with an S3 lifecycle
 
## Amazon FSx
 - FSx similar to RDs but for file systems
 - Fully managed service

# CloudFront

## Overview
 - Content Delivery Network (CDN)
 - Improves read performance, content is cached at the edge
 - 216 Points of Presence globally (edge locations)
 - DDoS protection, Integration with Shield, AWS Web application Firewall (WAF)
 - Can expose external HTTPS and can talk to internal HTTPS backends

## Origins
 - S3 Bucket
   - For distributed files and caching them at the edge
   - Enhanced Security with CloudFront Origin Access Identity
   - CloudFront can be used as an ingress (to upload files to S3)
 - Custom Origin (HTTP)
   - Application Load Balancer
   - EC2 Instance
   - S3 Website (must first enable the bucket as a static S3 website)
   - Any HTTP backend you want

## Security (how to access)
  - S3
  - EC2
  - ELB

## Geo Location

## Price Classes

## Cache Invalidations

## AWS Global Accelerator

## Route 53

### Overview
 - A highly available, scalable, fully managed and Authoritative DNS
   - Authoritative = customer can update the DNS records
 - Route 53 is also a Domain Registrar
 - Ability to check the health of your resources
 - Only service that provide 100% availability
 - Why Route 53?
   - 53 is a reference to the traditional DNS port

### Records
 - How you want to route traffic for a domain
 - Each Record contains
   - Domain/Sub-domain Nam: Eg: example.com
   - Record Type: eg: A or AAAA
   - Value: eg 12.34.56.78
   - Routing policy: How route 53 responds to queries
   - TTL: amount of time the record is cached at DNS resolvers
 - Route 53 supports the following DNS record types:
   - Must know: A/AAAA/CNAME/NS
   - Advanced : CAA/DS/MX/NAPTR/PTR/SOA/TXT/SPF/SRV

### Record Types
 - A: maps a hostname to IPV4
 - AAAA: maps a hostname to IPV6
 - CNAME: Maps a hostname to another hostname
   - The target is a domain name which must have an A or AAAA record
   - Can't create a CNAME record for the top node of a DNS namespace (Zone APEX)
   - Example: You cant create for example.com, but you can create for www.example.com
 - NS: Name Servers for the hosted zone
   - Controls how traffic is routed to a domain

### Hosted Zones
 - A container for records that define how to route traffic to a domain and its subdomains
 - Public Hosted Zones: contains records that specify how to route traffic on the internet (Public domain names), eg: application1.mypublicdomain.com  
 - Private Hosted Zones: contains record that specify how you route traffic within one of more VPCs (Private Domain names), eg: application1.company.internal
 - you pay $ 0.50 per month per hosted zone

### TTL


### CNAME vs Alisas


### Route 53  health checks


### Routing Policies
 - Defines how Route 53 responds to DNS queries
 - Note:
    - Its not same as the load balancer routing which routes the traffic
    - DNS does not route any traffic, it only responds to DNS queries.
 - Route 53 supports following policies:
   - Simple
   - Weighted
   - Latency based
   - Failover (Active-Passive)
   - Geolocation
   - Geoproximity (Using Route53 traffic floe feature)
   - Multi-value Answer
    
#### Routing Policies - Simple



### Domain Registrar vs DNS Service


# Serverless

## Overview
 - Serverless is a new paradigm in which the developers dont have to manage servers anymore
 - The just need to deploy code, rather functions at start
 - Initially Serverless = FaaS (Function as a service)
 - Serverless was pioneered by AWS Lambda but now also includes anything thats managed: "databases, messaging, storage, etc"
 - Serverless does not mean there are no servers, it means you just dont need to manage/provision/see them
 
## Serverless Services 
 - AWS Lambda
 - DynamoDB
 - AWS Cognito
 - AWS API Gateway
 - Amazon S3
 - SNS and SQS
 - Kinesis Data Firehose
 - Aurora Serverless
 - Step Functions
 - Fargate
 
## Lambda

### Overview
 - Good overview: https://aws.amazon.com/lambda/

### Why Lambda?
 - EC2 
   - Virtual servers in the cloud 
   - Limited by RAM and CPU
   - Continuously running
   - Scaling means intervention to add/remove servers
 - Lambda
   - Virtual functions - no servers to manage!
   - Limited by time - short executions
   - Run on-demand
   - Scaling is automated!

### Benefits
 - Easy pricing
   - pay per request and compute time
   - free tier of 10,00,000 Lambda requests and 4,00,000 GBs of compute time
 - Integrated with whole suite of AWS services
 - Easy monitoring through AWS cloudwatch
 - Easy to get more resources per functions (upto 10GB RAM)
 - Increasing RAM will also improve CPU and network

### Language support
 - Node.js (JavaScript)
 - Python
 - Java (Java 8/11 compatible)
 - C# (.NET core)
 - Golang
 - C# / powershell
 - Ruby
 - Custom Runtime API (community supported example is RUST)
 - Lambda container image
   - The container image must implement the Lambda Runtime API
   - ECS/Fargate is preferred for running arbitrary Docker images
   - NOte: Docker is not for Lambda but for ECS/Fargate

### Examples
 - CRON job application
 - image in multiple formats creation app

### Pricing
 - Latest Pricing: https://aws.amazon.com/lambda/pricing/
 - Pay per calls:
   - First 10,00,000 requests are free
   - $0.20 per 1 million requests thereafter ($0.0000002 per request)
 - pay per duration:
   - 4,00,000 GB seconds of compute time per month if free
   - == 4,00,000 Seconds if function is 1 GB RAM
   - == 32,00,000 seconds if function is 128MB RAM
   - After that $1.00 for 6,00,000 GB-seconds
 - It is usually very cheap to run AWS Lambda so its very popular

### Lambda Limits - per region
 - Execution
   - Memory allocation: 128MB - 10GB (1 MB increments)
   - Maximum execution time: 900 seconds (15 minutes)
   - Environment variables (4KB)
   - Disk capacity in the "function container" (in /tmp): 512MB to 10 GB
      - Use case: load file to process
   - Concurrency executions: 1000 (can be increased)
 - Deployment
   - Lambda function deployment size (compressed .zip): 50MB
   - size of uncompressed deployment (code + dependencies): 250MB
   - can use /tmp directory to load other files at startup   
   - size of environment variables 4KB

### Lambda@Edge
 - You have deployed a CDN using CloudFront
 - What if you wanted to run a global AWS Lambda alongside?
 - or how to implement request filtering before reaching your application?
 - For all above scenarios, you can use Lambda@Edge
   - **Deploy Lambda functions alongside your cloudfront CDN**
   - Build more responsive applications
   - you dont manage servers, Lambda is deployed globally
   - Customize the CDN content
   - Pay only for what you use
 - Usage: Lambda can be used to change CloudFront requests and responses
   - After CloudFront receives a request from a viewer (viewer request)
   - Before CloudFront forwards the request to the origin (origin request)
   - After CloudFront receives the response from origin (origin response)
   - Before CloudFront forwards the response to viewer (viewer response)
 - Usage: Lambda can be used to generate responses to viewers without even sending them to origin
 - Use cases:
   - Website security and Privacy
   - Dynamic web application at edge
   - Search Engine Optimization (SEO)
   - Intelligently route across origins and data centers
   - Bot Mitigation at the edge
   - Real time image transformation
   - A/B Testing
   - User Authentication and authorization
   - User prioritization
   - User Tracking and analytics 

### Lambda Networking (VPC)
 - By Default
   - Lambda function is launched outside your own VPC (in AWS owned VPC)
   - Therefore it cannot access resources in your VPC (RDS, ElastiCache, internal ELB, etc)
   - But can access global services like dynamoDB and also to external world
 - Lambda in VPC
   - You must define the VPC ID, the subnets and security groups
   - Lambda will create an ENI (Elastic Network Interface) in your subnets

### Lambda with RDS Proxy
 - if lambda function directly access your database, they may open too many connections under high load
 - RDS Proxy:
   - Improve scalability by pooling and sharing DB connections
   - Improve availability by reducing 66% the failover time and preserving connections
   - Improve security by enforcing IAM authentication and storing credentials in secrets manager
 - Lambda function must be deployed in your VPC , because **RDS proxy is never publicly accessible**

# DynamoDB

## Overview
 - Fully managed, Highly available with replication across Multiple AZs
 - NoSQL database: with transaction support
 - Scales to massive workloads, disctributed database
 - Millions of requests per second, trillions of row, 100s of TBs of storage
 - Fast and Consistent in peroformance (Single Digit millisecond)
 - Integrated with IAM for security, authorization and administration
 - Low cost and auto-scaling capabilities
 - No Maintenance or patching, always available
 - Standard and Infrequent Access (IA) Table class

## Basics
 - It is made of tables
 - Each table has a primary key (must be decided at creation time)
 - Each table can have an infinite number of items (=rows)
 - Each item has attributes (can be added over time and can be null)
 - Maximum size of an item is 400KB
 - Data types supported:
   - Scalar types: String, Binary, Boolean, Null
   - Document types: List, Map
   - Set Types: String Set, Number Set, Binary Set
 - Therefore, in DynamoDB you can rapidly evolve schemas

## Read/Write capacity modes
 - Control how you manage your table's capacity (read/write throughput)
 - Provisioned mode (default)
   - You specify the number of reads/writes per second
   - You need to plan the capacity beforehand
   - Pay for provisioned Read Capacity Units (RCU) and Write Capacity Units (WCU)
   - Possibility to add auto-scaling mode for RCU and WCU

## On Demand Mode
 - Read/Writes automatically scale up/down with you workloads
 - No Capacity planning needed
 - Pay for what you use, more expensive
 - Great for unpredictable workloads, steep sudden spikes

## DynamoDB Accelerator (DAX)
 - Fully managed, Highly avaialble, seamless in-memory cache for DynamoDB
 - Help solve read congestion by caching
 - Micro-seconds latency for cached data
 - Doesn't require application logic modification (Compatible with existing DynamoDB APIs)
 - 5 minutes TTL for cache

## DAX vs ElastiCache
 - DAX:
   - Individual Object cache
   - Query and Scan cache
 - ElastiCache
   - Store aggregation result

## DynamoDB - Stream processing
 - Ordered stream of item level modifications (create/update/delete) in a table
 - Use Cases:
   - React to changes in real time (Welcom email to users)
   - Real time usage anlytics
   - Insert into derivative tables
   - Implement cross-region replication
   - Invoke AWS Lambda on changes to your DynamoDB table
 - DynamoDB Streams:
   - 24 hours retention
   - Limited number of consumers
   - Process using AWS Lambda triggers or DynamoDB stream kinesis adapter
 - Kinesis data strems (newer)
   - 1 year retention
   - High number of consumers
   - Process using AWS Lambda, Kinesis data analytics, Kinesis data firehose, AWS Glue Streaming ETL 

## DynamoDB Streams use case diagram
 - https://aws.amazon.com/blogs/database/dynamodb-streams-use-cases-and-design-patterns/

## DynamoDB Global Tables
 - Make a DynamoDB table accesible with low-latency in multiple regions
 - Active-Active replication
 - Applications can READ and WRITE to the table in any region
 - Must enable DynamoDB Streams as a pre-requisite

## DynamoDB - Time To Live (TTL)
 - Automatically delete items after an expiry timestamp
 - Use cases: reduce stored data by keeping only current items, adhere to regulatory obligations

## DynamoDB - Backups for disaster recovery
 - Continous backups using point in time recovery (PITR)
  - Optionally enabled for last 35 days
  - Point in time recovery to any time within the backup window
  - The backup process creates a new table
 - On demand backups
  - Full backups for long-term retention, until explicitly deleted
  - Doesnt affect performance or latency
  - Can be configured and managed in AWS backup (enable cross-region copy)
  - The recovery process creates a new table

## DynamoDB - Integration with Amazon S3
 - Export to S3 (must enable PITR)
   - Works for any point of time in the last 35 days
   - Does not affect the read capacity of your table
   - Perform data analysis on top of DynamoDB
   - Retain snapshots for auditing
   - ETL on top of S3 data before importing back into DynamoDB
   - Export in DynamoDB JSOn or ION format
 - Import to S3 
   - Import CSV, DynamoDB JSOn or ION format
   - Doesn't consume any write capacity
   - Creates a new table
   - Import errors are logged in CloudWatch Logs

# API Gateway

## Overview
 - AWS Lambda + API Gateway: No Infrastructure to manage
 - Support for the websocket protocol
 - Handle API Versioning (v1, v2)
 - Handle different environments (dev, test, prod, etc)
 - Handle Security (Authentication and Authorization)
 - Create API keys, handle request throttling
 - Swagger/Open API import to quickly define APIs
 - Transform and validate requests and responses
 - Generate SDK and API specifications
 - Cache API responses

## API Gateway - Integrations 
 - Lambda Function
   - Invoke Lambda function
   - Easy way to expose Rest API backed by AWS lambda
 - HTTP
   - Expose HTTP endpoints in the backend
   - Example: Internal HTTP API on premise, Application load balancer
   - Why? to add rate limiting, caching, user authentications, API keys, etc
 - AWS Service
   - Expose any AWS API therough the API gateway
   - Example: start an AWS step function workflow, post a message to SQS
   - Why? Add authentication, deploy publicly, rate control   

## API Gateway - Endpoint Types
 - Edge Optimized (default)
   - Requests are routed through the CloudFront Edge Locations (improves latency)
   - The API gateway still lives in only 1 region
 - Regional
   - For clients within the same region
   - Could Manually combine with CloudFront (More control over the caching strategies and the distribution)
 - Private
   - Can only be accessed from your VPC using an interface VPC Endpoint (ENI)
   - Use a resource policy to define access

## API Gateway - Security
 - User authentication via:
   - IAM Roles (usefule for Internal applications)
   - Cognito (Identity for external users - example mobile users)
   - Custom Authorizer (Your own  logic)
 - Custom Domain Name HTTPS security through integration with AWS Certificate Manager (ACM)
   - If using Edge optimized endpoint, then the certificate must be in us-east-1
   - If using regional endpoint, then the certificate must be in API Gateway Region
   - Must setup CNAME or A-alias record in Route 53

# AWS Step functions
 - Build serverless visual workflow to orchestrate your Lambda functions
 - Features: Sequence, parallel, conditions, timeouts, error handling
 - Can integrate with EC2, ECS, On-premises servers, API Gateway, SQS queues, etc
 - Possibility of implementing human approval feature
 - Use cases: order fullfillment, data processing, web applications, any workflow


 

