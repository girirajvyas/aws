1. You have multiple Docker-based applications hosted on-premises that you want to migrate to AWS. You don't want to provision or manage any infrastructure; you just want to run your containers on AWS. Which AWS service should you choose?
a. Elastic Container Service (ECS) in EC2 Launch Mode
b. Elastic COntainer Registry (ECR)
c. Aws Fargate on ECS
Ans:
c, AWS Fargate allows you to run your containers on AWS without managing any servers.

2. Amazon Elastic Container Service (ECS) has two Launch Types: .................. and ..................
a. Amazon EC2 Launch Type and Fargate Launch Type
b. Amazon EC2 Launch Type and EKS Launch Type
c. Fargate Launch Type and EKS LAunch Type
Ans:
a, 

3. You have an application hosted on an ECS Cluster (EC2 Launch Type) where you want your ECS tasks to upload files to an S3 bucket. Which IAM Role for your ECS Tasks should you modify?
a. ECS Instance profile
b. ECS tasks role
Ans:
b, ECS Task Role is the IAM Role used by the ECS task itself. Use when your container wants to call other AWS services like S3, SQS, etc.
a, Incorrect: EC2 Instance Profile is the IAM Role used by the ECS Agent on the EC2 instance to execute ECS-specific actions such as pulling Docker images from ECR and storing the container logs into CloudWatch Logs.

4. You're planning to migrate a WordPress website running on Docker containers from on-premises to AWS. You have decided to run the application in an ECS Cluster, but you want your docker containers to access the same WordPress website content such as website files, images, videos, etc. What do you recommend to achieve this?
a. Mount an EFS volume
b. Mount an EBS volume
c. Use and EC2 instance store
Ans:
a, EFS volume can be shared between different EC2 instances and different ECS Tasks. It can be used as a persistent multi-AZ shared storage for your containers.

5. You are deploying an application on an ECS Cluster made of EC2 instances. Currently, the cluster is hosting one application that is issuing API calls to DynamoDB successfully. Upon adding a second application, which issues API calls to S3, you are getting authorization issues. What should you do to resolve the problem and ensure proper security?
a. Edit the EC2 instance role to add permissions to S3
b. Create an IAM Task role for new application
c. Enable the Fargate mode
d. Edit the S3 bucket policy to allow the ECS task
Ans:
b

6. Which feature allows an Application Load Balancer to redirect traffic to multiple ECS Tasks running on the same ECS Container instance?
a. Dynamic port mapping
b. Automatic port mapping
c. ECS task definition
d. ECS Service
a

7. You are migrating your on-premises Docker-based applications to Amazon ECS. You were using Docker Hub Container Image Library as your container image repository. Which is an alternative AWS service which is fully integrated with Amazon ECS?
a. Fargate
b. Elastic Container Registry (ECR)
c. Elastic Kubernetes Service (EKS)
d. EC2
Ans:
b, Amazon ECR is a fully managed container registry that makes it easy to store, manage, share, and deploy your container images. It won't help in running your Docker-based applications.

8. Amazon EKS supports the following node types, EXCEPT ………………..
a. Managed Node Groups
b. Self managed nodes
c. Fargate
d. Lambda
Ans:
d, 

9. A developer has a running website and APIs on his local machine using containers and he wants to deploy both of them on AWS. The developer is new to AWS and doesn’t know much about different AWS services. Which of the following AWS services allows the developer to build and deploy the website and the APIs in the easiest way according to AWS best practices?
a. App Runner
b. EC2 instance + Application Load balancer
c. Amazon ECS
d. Fargate
Ans:
a, 
