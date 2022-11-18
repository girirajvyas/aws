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
 
 


## Quiz

Which EC2 storage would you use to create a shared network file system for your EC2 instances?
 - EFS is a fully managd service that makes it easy to setup, scale, and cost-optimize file storage in the Amazon cloud


