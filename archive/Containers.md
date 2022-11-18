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
