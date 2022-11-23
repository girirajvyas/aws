# 1. Identity and Access Management (IAM)

## 1.1 Basics

**Components:**  
 - It is a *global Service*
 - Root account is created by default when you create your account
 - It is recommended to **not use or share this root user and should only be used for user creation**
 - `Users:` These are the people within the organization and can be grouped based on departments, roles, etc
 - `Groups:` These contains only the users created above and no other groups
 - `Note:` 
   - It is not mandatory for Users to be assigned to a group. 
   - It can be the case that a user does not belong to any group 
   - or it can belong to multiple groups as well

**Permissions:**  
 - Users or Groups can be assigned JSON documents called `policies`
 - Policies define what users/groups are allowed or in short the permissions of the users
 - [Least privilege access](http://docs.aws.amazon.com/wellarchitected/latest/security-pillar/permissions-management.html): It is recommended to follow this principle that states dont give user more permissions that he needs 

## 1.2 How to land to IAM (Common step across this section)
 - Go to: https://aws.amazon.com -> login with root user
 - You can use the Global search -> Search IAM -> Select
 - Or go to Services -> Security, Identity and compliance -> IAM
 - You will be landed to Dashboard of IAM
 - ![Landing Page](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/IAM_landing_page.PNG)
 
## 1.3 Add User  
 - Go to Access Management -> Users -> Add Users

**Step 1: user details and access type**  
**Set User Details**  
 - User Name: johndoe/raj
 - You can have multiple users added at the same time

**Select AWS access Type**  
 - Access type 
   - Programmatic Access: check in case you want to allow this user to connect via code
   - AWS Managemenet Console access: check
     - Selecting this section will require you to provide below details
   - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_1.1.PNG)
 - Console password
   - Autogenerate Pasword: unchecked
   - Custom Password: checked
 - Require Password Reset Checkbox: unchecked
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_1.2.PNG)
 
**Step 2: Set Permissions**  
 - `Add user to group:` In case you donot have groups already, "get started with group"
 - Copy permissions from existing user
 - Add existing policies directly
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_2.1.png)

get started with group  
 - Group Name: admins
 - Create policy/ add policies
 - For starter add AdministratorAccess policy to make the users in group as admins
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_2.2_create_group.png)

**Step 3: Add tags (Optional)**  
 - for eg: Department as key and value as Enineering
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_3_tags.png)

**Step 4: Review**  
 - Review whatever you have selected till now
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_4.1_review.png)
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_4.2_review.png)

**Step 5: Success, you have created an user.**  
 - There is a one time option to download the csv that contains every information including how to login and passwords, etc
 - A link will also be generated that can be shared with the user directly.
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_5_Created.png)

**Detailed steps:**  
 - Step by step screenshots are also available [here](https://github.com/girirajvyas/101-series/tree/master/resources/images/aws/IAM/add_user)

`Tip:` In `IAM dashboard`, set Sign-in URL for IAM users in the account, you will have a default value by default or you can edit and see if you have an alias available

**How to differentiate a root user or IAM user?**
 - `root user:` you will directly have name 
 - `IAM user:` username @ account name

## 1.4 Policies

**Overview**  
 - If you remove the user from the group admins having AdministratorAccess policy, user will not be able to create/read groups and users
 - if you add a readonly policy, your user will only be able to read

**Basic structure of policy**  
 - On high level it has:
   - **Version:** Policy language version
   - **Id:** Identifier of policy
   - **Statement:** Array of statemenets (atleast 1 required)
 - Array of statements include:
   - **Sid:** identifier for statment
   - **Effect:** whether it allows or Denies access
   - **Principal:** account:user:role this policy is applied to
   - **Action:** list of actions this policy allows or denies
   - **Resource:** List of resources where this action will be applied

 - Go to Access Management -> Policies  
 - Here you can see all the policies and can create custom ones as well
 - you can see the policy summary and json tab of policy, where policy summary is human readable and json has the same thing in json format
 - For creating policy: you can do it via visual editor or directly write the json as well

## 1.5 Multi Factor Authentication - MFA

**Password policy Basics**  
 - Strong password means higher security for your account
 - In AWS, you can setup a password policy:
   - set a min password length
   - Require specific character types:
     - including uppercase letters
     - lowercase letters
     - numbers
     - non-alphanumeric characters
 - Allow All IAM users to change their own passwords
 - Require users to change their password after some time (password expiration)
 - prevent password re-use

**Hands on console**  
- Go to Access Management -> Account settings -> Password policy
- Default password policy:
  - Minimum password length is 8 characters
  - Include a minimum of three of the following mix of character types: uppercase, lowercase, numbers, and ! @ # $ % ^ & * ( ) _ + - = [ ] { } | '
  - Must not be identical to your AWS account name or email address
- now you can modify the options as per yor org guidelines

**MFA device options:**  
 - Virtual MFA device (Support for multiple tokens on a single device)
   - Google authenticator (phone only)
   - Authy (multi-device)
 - Universal 2nd Factor (U2F) Security Key 
   - Support for multiple root and IAM users using a single security key
   - YubiKey by Yubico (3rd party)
 - Hardware Key Fob MFA Device
   - provided by Gemalto (3rd party)
 - Hardware Key Fob MFA Device for AWS GovCloud (US)
   - Provided by Surepassid (3rd party)

**Hands on console**  
 - Activate MFA
   - Virtual MFA Device
   - U2F security key
   - Other Hardware MFA device
 - We select Virtual MFA Device
   - List of supported devices: https://aws.amazon.com/iam/features/mfa/?audit=2019q1
   - Install any of the above apps
   - Scan QR code
   - Type 2 consecutive MFA codes
   - Success
 - Next time you login via your root account you have to provide MFA token along with your password

## 1.6 Ways to access AWS

 - `AWS Management Console:`(Protected by password + MFA)
 - `AWS Command line Interface (CLI):` protected by access keys
 - `AWS Software Development Kit (SDK) for code:` protected by access keys
   - Access keys used above are generated through AWS console
   - Users Manage their own access keys
   - **Access keys are secret like you have passwords, it is recommended not to share them**

**Acess keys:**   
 - `Access Key Id` which is used like a username but it is not the same
 - `Secret Access Key` which is used like a password but it is not the same.

### 1.6.1 AWS console
 - All the hands on we did till now is on console

### 1.6.2 AWS cli on Windows

**Installation:**  
 - Google -> Download aws cli on windows -> [Link](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html)
 - Install the downloaded msi file. for me it was 28 MB.
 - After finishing installation, go to cmd -> type aws --version -> you will get the result as below
 - ![Successful installation](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_installed_successfully.PNG)
 - In case you want to upgrade, you have to download the latest version of msi and install again

**Hands On:**  
 - Do not use your root account to create security credentials
 - Login via IAM user -> IAM -> Access management -> Users -> select your name -> Security credentials tab
 - Access keys -> Create access keys (Single time download and next time you have to create a new one in case you forgot or missed)
 - Open cmd prompt -> type `aws configure` -> 
   - provide access key id: one you created just above
   - access key secret: one you created just above
   - Default region name: the region close to you
   - Default output format: nothing, just press enter
   - ![configure](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_initial_setup.PNG)
 - Verify if aws is configured correctly by: `aws iam list-users`
 - ![output](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_list_users.PNG)
 - Permissions updated via console will reflect in console as well.
 - In case you do not have permissions, there will be a blank response

**Reference documentation:** https://docs.aws.amazon.com/cli/latest/reference/
 - You get the same info via `aws help or aws ec2 help`

### 1.6.3 AWS Cloudshell (Alternative to AWS cli on Windows)
 - https://docs.aws.amazon.com/cloudshell/latest/userguide/welcome.html

### 1.6.4 AWS SDK
 - https://aws.amazon.com/tools/
 - Here, you will find all the SDKs available based on the programming language you use

## 1.7 IAM Roles for services

**Basics**  
 - Some AWS services will need to perform actions on your behalf
 - To achieve that, we will assign permissions to AWS services with IAM Roles.
 - Common roles:
   - EC2 Instance roles
   - Lambda Function roles
   - Roles for CloudFormation

**From AWS console: What are IAM Roles?**  
IAM roles are a secure way to grant permissions to entities that you trust. Examples of entities include the following:  
 - IAM user in another account
 - Application code running on an EC2 instance that needs to perform actions on AWS resources
 - An AWS service that needs to act on resources in your account to provide its features
 - Users from a corporate directory who use identity federation with SAML
 - IAM roles issue keys that are valid for short durations, making them a more secure way to grant access.

**Hands on**  
 - Go to Access Management -> Roles -> Create Roles
 - You can create role for:
    - AWS service:EC2, Lambda and others
    - Another AWS account: Belonging to you or any third party
    - Web identity: Cognito or any openId provider
    - SAML 2.0 federation: your corporate directory
 - We will do only for `AWS service` as of now, select EC2 and click `Next:Permissions`
 - Lets provide **IAMReadOnlyAccess** to this role and `Next:Tags`
 - We can skip this and do `Next:Review`
 - Provide Role name -> Create Role

## 1.8 IAM Security Tools

**Basics**  
 - IAM Credentials Report(account-level)
   - A report that lists all your account's users and the status of their various credentials
 
 - IAM Acess Advisor (user-level)
   - Access Advisor shows the service permissions granted to a user and when those services were last accessed.
   - You can use this information to revise your policies. (Enforce policy of least privilege)

**Hands On**  
Credentila Report  
 - Go to Access Reports -> Credential Report
 - you will get complete summary of all the users

Access Advisor
 - Go to Access Management -> Users -> select user -> Access Advisor
 - Gives you detailed summary of the services used and in turn helps in effectively managing the access
 - Ref: https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_access-advisor.html

## 1.9 IAM Guidelines and Best Practices

 - Don't use the root account except for AWS account setup
 - One Physical user = One AWS user
 - **Assign Users to groups and assign permissions to groups to make sure security is maintained at group level**
 - Create a Strong password policy
 - Use and enforce the use of Multi Factor Authentication (MFA)
 - **Create and use Roles for giving permissions to AWS services.**
 - Use access keys for Programatic access (CLI/SDK)
 - Audit permissions of your account with the IAM Credentials Report and IAM Access Advisor
 - **Never share IAM users and Access Keys**

**Best practices from IAM Dashboard in AWS console**  
 - [Grant least privilege access](http://docs.aws.amazon.com/wellarchitected/latest/security-pillar/permissions-management.html) : Establishing a principle of least privilege ensures that identities are only permitted to perform the most minimal set of functions necessary to fulfill a specific task, while balancing usability and efficiency.
 - Enable Identity federation: Centrally manage users and access across multiple applications and services. For federation to multiple accounts in your AWS Organization, you can configure your identity source in [AWS Single Sign-on](https://console.aws.amazon.com/singlesignon/home).
 - Enable MFA: For extra security, we recommend that you require multi-factor authentication (MFA) for [all users](https://console.aws.amazon.com/iam/home?region=us-east-2#users).
 - [Rotate credentials regularly](https://console.aws.amazon.com/iam/home?region=us-east-2#security_credentials): Change your own passwords and access keys regularly, and make sure that all users in your account do as well.
 - Enable [IAM Access Analyzer](https://console.aws.amazon.com/access-analyzer/home): Enable IAM Access Analyzer to analyze public, cross-account, and cross-organization access.
 - AWS recommends to turn on CloudTrail to log all IAM actions for monitoring and audit purposes
 - Learn more about all **[security best practices](https://console.aws.amazon.com/access-analyzer/home)**.

## 1.10 Shared Responsibility Model for IAM

| AWS                                           | You                           |
| -------------                                 |:-------------:                      |
| Infrastructure (global network security)      | Users, Groups, Roles, Policies management and monitoring |
| Configuration and vulnerability analysis of services they offer     | Enable MFA on all accounts |
| Compliance Validation                         | Rotate all your keys often |
|                                               | Use IAM tools to apply appropriate permissions|
|                                               | Analyze access patterns and review permissions|

## 1.11 IAM Basic Summary

 - Users: mapped to physical user, has a password for AWS Console
 - Groups: contains users only
 - Policies: JSON documents that outlines permissions for users or groups
 - Roles: for EC2 instances or AWS services
 - Security: MFA + password policy
 - Access keys: Access AWS using the CLI/SDK
 - Audit: IAM Credential Reports and IAM Access Advisor

## 1.12 AWS Organizations

### 1.12.1 Overview
 - Global Service
 - Allows to manage multiple AWS accounts
 - The main account is the management account
 - Other accounts are member accounts
 - Member account can only be part of one orgainization
 - Consolidated billing across all accounts - single payment method
 - Pricing benefits from aggregated usage (Volume discount for EC2, S3, ..)
 - Shared reserved instances and savings plans discounts across accounts
 - API is available to automate AWS account creation
 
### 1.12.2 Advantages
 - Multi Account vs One account multi VPC
 - Use tagging standards for billing purposes
 - Enable CloudTrail on all accounts, send logs to central s3 account
 - Send CloudWatch Logs to central logging account
 - Establish Cross Account Roles for admin purposes

### 1.12.3 Security: Service Control Policies
 - IAM Policies applied to OU or Accounts to restrict Users and Roles
 - They do not apply to the management account (Full admin power)
 - Must have an Explicit allow (does not allow anything by default - like IAM)

### 1.12.4 SCP Examples:
 - Allowlist vs denyList

## 1.13 IAM Conditions
 - `aws:SourceIP`: restrict the client IP **from** which the API calls are being made 
 - `aws:Requested Region`: restrict the region, the API calls are made **to**
 - `ec2:ResourceTag`: restrict based on tags
 - S3:
   - `s3:ListBucket` permission applies to `arn:aws:s3:::test` (bucket level permission)
   - `s3:GetObject`, `s3:PutObject`, `s3:DeleteObject` applies to `arn:aws:s3:::test/*` 
 - Resource Policies and aws:PrincipalOrgID
   - `aws:PrincipalOrgID` can be used in any resource policies to restrict access to accounts that are member of an AWS Orgainization

## 1.14 Resource based policy vs IAM Roles

### 1.14.1 Overview 
 - IAM Roles (Cross Acount):
   - Attaching a resource-based policy to a resource (example: S3 bucket policy)
   - Or using a role as a proxy
 - Resource based policy:
   - When you assume a role (user, application or service), you give up your original permissions and take the permissions assigned to the role
   - When using a resource-based policy, the principal doesnt have to give up his permissions
   - Example: User in Account A needs to scan a DynamoDB table in Account A and dump it in an S3 bucket in Account B
   - Supported by: Amazon S3 buckets, SNS topics, SQS queues, Lambdas, etc

### 1.14.2 Amazon EventBridge security example for both scenarios
 - Resource based policy
   - When a rule runs it needs permission on the target
   - Resource based policy: Lambda, SNS, SQS, CloudWatch Logs, API Gateway
   - EventBridge Rule -> Lambda with Resource based policy (eg: Allow EventBridge)
 - IAM Role: Kinesis stream, Systems Manager Run Command, ECS Task
   - EventBridge Rule (IAM Role) -> Kinesis

## 1.15 IAM Permission Boundaries
 - IAM Permission Boundaries are supported for users and roles (not groups)
 - Advanced feature to use a managed policy to set the maximum permissions an IAM entity can get
 - Can be used in combinations of AWS Organizations SCP  
 - Use cases:
   - Delegate responsibilities to non administrators within their permission boundaries, for example create new IAM users
   - Allow developers to self-assign policies and manage their own permissions, while making sure they cant escalate their privileges ( = make themselves admin)
   - Useful to restrict one specific user (instead of a whole account using Organizations & SCP)

## 1.16 AWS Cognito

### 1.16.1 Overview
 - Give users an identity to interact with our web or mobile application
 - Cognito Users pool:
   - Sign in functionality for app users
   - Integrate with API gateway and Application Load balancer
 - Cognito Identity Pools (Federated Identity)
   - Provided AWS Credentials to users so that they can access AWS resources directly
   - Integrate with Cognito users pool as an Identity povider
 - Cognito vs IAM: "hundreds of users", "mobile users", "authenticate with SAML"
 
### 1.16.2 Cognito User Pools (CUP) - User Features
 - Create a serverless database of user for your web and mobile apps
 - Simple Login: USername(or Email) / password combination
 - Password reset
 - Email and phone number verification
 - Multi Factor authentication (MFA)
 - Federated Identities: Users from Facebook,Google, SAML
 - CUP integrates with API Gateway and Application Load Balancer

### 1.16.3 Cognito Identity Pools (Federated Identities)
 - Get Identities for "users" so they obtain temporary AWS credentials
 - Users source can be Cognito User Pools, 3rd party logins, etc
 - Users can then access AWS services directly or through API Gateway
 - The IAM policies applied to the credentials are defined in Cognito
 - They can be customized based on the user_id for fine grained control
 - Default IAM roles for Authenticated and guest users

## 1.17 AWS IAM Identity center (Succesor to AWS Single Signon)

### 1.17.1 Overview 
 - One Login (Single Sign-on) for all your:
   - AWS accounts in AWS Organizations
   - Business cloud applications (eg: Salesforce, Box, Microsoft 365, etc)
   - SAML 2.0 Enabled applications
   - EC2 Windows instances
 - Identity providers
   - Built-in identity store in IAM Identity center
   - 3rd party: Active directory (AD), OneLogin, Okta
   
### 1.17.2 Fine grained permissions and assignments
 - Multi-Account permissions
   - Manage access across AWS accounts in your AWS Organization
   - Permission Sets - a collection of one or more IAM policies assigned to users and groups to define AWS Access
 - Application Assignments
   - SSO Access to many SAML 2.0 business applications (Salsforce, Box, Microsoft 365, etc)
   - Provide required URLs, certificates and metadata
 - Attribue based Access control (ABAC)
   - Fine grained permissions based on users attributes stored in IAM Identity center Identity store
   - Example: cost center, title, Locale
   - Use case: Define permissions once, then modify AWS Access by changing the attributes

## 1.18 AWS Directory services

### 1.18.1 What is Microsoft Active Directory (AD)?
 - Found on any Windows server with AD domain Services
 - Database of Objects: User accounts, Computers, Printers, File Shares, Security Groups
 - Centralized security management, create account, assign permissions
 - Objects are organized in trees
 - A group of trees is a forest

### 1.18.2 AWS Directory services overview
 - AWS Managed Microsoft AD
   - Create your own AD in AWS, Manage users locally, supports MFA
   - Establish "trust" connections with your on premise AD
 - AD Connector
   - Directory Gateway (proxy) to redirect to on-premise AD, supports MFA
   - Users are managed on the o-premise AD
 - Simple AD
   - AD-compatible managed directory on AWS
   - Cannot be joined with on-premise AD

### 1.18.3 IAM Identity center - Active Directory Setup
 - Connect to an AWS Managed Microsoft AD (Directory service)
   - Integration is out of the box
 - Connect to slf-managed Directory
   - Create Two-way trust relationship using AWS Managed Microsoft AD
   - Create an AD-Connector
   
## 1.19 AWS Control tower

### 1.19.1 Overview
 - Easy way to setup and govern a secure and compliant multi-account AWS environment based on best practices
 - AWS Control Tower uses AWS Organizations to create accounts
 - Benefits:
   - Automate the setup of your envirnments in a few clicks
   - Automate Ongoing Policy management using guardrails
   - Detect Policy violations and remediate them
   - Monitor Compliance through an interactive dashboard

### 1.19.2 Guardrails
 - Provides ongoing governance for your control tower environments (AWS Accounts)
 - Preventive GuardRails - using SCPs (Eg: Restrict regions across all your accounts)
 - Detective GuardRails - Using AWS Config (Eg: Identify untagged resources)

## 1.21 Exam Points
 - An IAM user with full administrator access can perform almost all AWS tasks except a few tasks designated only for the root account user. Some of the AWS tasks that only a root account user can do are as follows: 
   - Change account name
   - Root password or 
   - Root email address, 
   - Change AWS support plan, 
   - Close AWS account, 
   - Enable MFA on S3 bucket delete, 
   - Create Cloudfront key pair
   - Register for GovCloud. 
 - What is a proper Definition of IAM roles:  
   - An IAM entity that defines a set of permissions for making AWS service requests, that will be used by AWS services
   - Some AWS service will need to perform actions on your behalf. To do so, you assign permissions to aws services with IAM Roles
 - Which is an IAM Security Tool?  
   - IAM Credential Report
   - IAM Credentials report lists all your account's users and the status of their various credentials. The other IAM security Tool is IAM Access Advisor. It shows the service permissions granted to a user and when those services were last accessed.
 - What are IAM Policies?
   - JSON Documents to define Users, Groups or Roles' permissions
   - An IAM policy is an entity that, when attached to an identity or resource, defines their permissions.
 - Use AZ ID to uniquely identify the Availability Zones across the two AWS Accounts
   - An Availability Zone is represented by a region code followed by a letter identifier; for example, us-east-1a. To ensure that resources are distributed across the Availability Zones for a region, AWS maps Availability Zones to names for each AWS account. For example, the Availability Zone us-west-2a for one AWS account might not be the same location as us-west-2a for another AWS account.
   - To coordinate Availability Zones across accounts, you must use the AZ ID, which is a unique and consistent identifier for an Availability Zone. For example, usw2-az2 is an AZ ID for the us-west-2 region and it has the same location in every AWS account.
   - Viewing AZ IDs enables you to determine the location of resources in one account relative to the resources in another account. For example, if you share a subnet in the Availability Zone with the AZ ID usw2-az2 with another account, this subnet is available to that account in the Availability Zone whose AZ ID is also usw2-az2.
   - You can view the AZ IDs by going to the service health section of the EC2 Dashboard via your AWS Management Console.

## 1.22 Reference:
 - [Identity Management](https://www.aws.training/Details/eLearning?id=55148)

# 2. EC2 - Elastic Compute Cloud Fundamentals

## 2.1 EC2 Basics

### 2.1.1 Overview  
 - EC2 is one of te most popular AWS offering
 - EC2 = Elastic Compute Cloud = Infrastructe as a Service (IaaS)
 - It mainly consists capability of:
   - Renting virtual machines **(EC2)**  
   - Storing data on Virtual drives **(EBS)**
   - Distributing load across machines **(ELB)**
   - Scaling the services using an auto-scaling group **(ASG)**
 - Knowing EC2 is fundamental to understand how the Cloud Works

### 2.1.2 EC2 Sizing and configuration options
 - Operating System (OS): **Linux or Windows**
 - How much compute power and cores **(CPU)**
 - How much random-access memory **(RAM)**
 - How much storage space:
   - Network-attached **(EBS and EFS)**
   - hardware **(EC2 instance store)**
 - Network card: **speed of the card, public IP address**
 - Firewall rules: **security group**
 - Bootstrap script (configure at first launch): **EC2 User Data**

### 2.1.3 EC2 instance types
 - Different depending on the cpu ram storage and network performance
 - [High level image]

### 2.1.4 EC2 User data
 - It is possible to bootstrap our instances using and `EC2 user data` script
 - Bootstrapping means launching commands when a machine starts
 - That Script is only run once at the instance first start
 - EC2 user data is used to automate boot tasks such as:
   - Installing updates
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
       ```cmd
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

## 2.2 Introduction to Security Groups

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

## 2.3 SSH Overview

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

## 2.4 EC2 Instance Launch types

### 2.4.1 Summary  
  - **On Demand**: Ideal for short workload, predictable procing
  - **Reserved**: Minimum 1 year is required
    - **Reserved Instances:** long workloads like databases
    - **Convertible Reserved Instances:** long workloads with flexible instances
    - **Scheduled Reserved Instances:** example is "every sunday 3 pm to 6 pm"
  - **Spot Instances:** short workloads, cheap, can lose instances (less reliable)
  - **Dedicated Hosts** book an entire physical server, control instance placement

### 2.4.2 On Demand
 - Pay for what you use
   - Linux: billing per second, after the first minute
   - All other OS: billing per hour
 - Has the highest cost but no upfront payment
 - No long term commitment
 - Recommended for: 
   - Short term and uniterrupted workloads, where you cant predict how the application wwill behave

### 2.4.3 Reserved Insances
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

### 2.4.4 EC2 Spot instances
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
 - https://aws.amazon.com/blogs/aws/new-ec2-spot-blocks-for-defined-duration-workloads/
 - https://aws.amazon.com/ec2/spot/
 
### 2.4.5 Dedicated Hosts
 - An Amazon EC2 Dedicated host is a physical server with EC2 instance capacity fully dedicated to your use
 - Dedicated hosts can help you address "Compliance requirements" and reduce costs by allowing you to **use your existing server bound software licenses**
 - Allocated for your account for a 3 year period reservation
 - They are expensive
 - Useful for software that have complicated licensing model (BYOL - Bring your own license)
 - or for companies that have strong regulatory or compliance needs

### 2.4.6 Dedicated Instances
 - Instances running on hardware that's dedicated to you
 - may share hardware with other instances in same account
 - No control over instance placement (can move hardware after stop/start)
 - Soft version of dedicated host

## 2.5 Shared Responsibility Model for IAM

| AWS                                           | You                           |
| -------------                                 |:-------------:                      |
| Infrastructure (global network security)      | Security group rules|
| Isolation on physical hosts                   | OS patches and updates|
| Replacing faulty hardware                     | Software and utilities installed on the EC2 instance|
| Compliance Validation                         | IAM Roles assigned to EC2 and IAM user access management|
|                                               | Data security on your instance|

## 2.6 EC2 Summary
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

## 2.7 EC2 Metadata
 - EC2 instance Metadata is powerful but less known features
 - It allows EC2 instances to "learn about themselves" without using an IAM Role for that purpose.
 - URL: http://169.254.169.254/latest/meta-data
 - You can retrieve the IAM Role name from the metadat, but you CANNOT retrieve the IAM policy
 - Metadata: info about EC2 instance
 - Userdata: Launch script of EC2 instance

## 2.8 EC2 Placement Group
 - **Cluster**: packs instances close together inside an Availability Zone. This strategy enables workloads to achieve the low-latency network performance necessary for tightly-coupled node-to-node communication that is typical of high-performance computing (HPC) applications.
 - **Partition**: spreads your instances across logical partitions such that groups of instances in one partition do not share the underlying hardware with groups of instances in different partitions. This strategy is typically used by large distributed and replicated workloads, such as Hadoop, Cassandra, and Kafka.
 - **Spread**: strictly places a small group of instances across distinct underlying hardware to reduce correlated failures.
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html

## 2.8 Exam guide - EC2
**Security Groups**
 - Which network security tool can you use to control traffic in and out of EC2 instances?
   - Security groups
 - How many security groups per EC2?
   - 5
 - In which state security group can be changed for EC2?
   - TBD
 - A security group acts as a virtual firewall that controls the traffic for one or more instances. When you launch an instance, you can specify one or more security groups; otherwise, **we use the default security group**. You can add rules to each security group that allow traffic to or from its associated instances. You can modify the rules for a security group at any time; the new rules are automatically applied to all instances that are associated with the security group. When we decide whether to allow traffic to reach an instance, we evaluate all the rules from all the security groups that are associated with the instance.
 - Characterstics of security groups:
   - By default, security groups allow all outbound traffic.
   - Security group rules are always permissive; you can't create rules that deny access.
   - Security groups are stateful.
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-security-groups.html  
 - Can be attached to running or stopped EC2 instance  
**EC2**
 - How long can you reserve an EC2 Reserved instance?
   - 1 or 3 years (Not anytime between 1 and 3 years)
 - Which EC2 purchasing option should you use for an application you plan on running on a server continously for 1 year?
   - Reserved Instances
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html
   - User data:
     - http://169.254.169.254/latest/user-data
   - Metadata: 
     - IPV4: http://169.254.169.254/latest/meta-data/
	 - IPV6: http://[fd00:ec2::254]/latest/meta-data/
   - Dynamic data:
     - http://169.254.169.254/latest/dynamic/
 - Instance states and lifecycle:
   - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html

 - How to restrict 50 users out of 100 to launch EC2?
   - TBD
 - There is a vCPU based on-demand instance limit per region. Your aws account has default quotas (formerly referred as limits), for each AWS service. Unless otherwise noted, Each quota is region specific. you can request increase for some quotas, and other quotas cannot be increased.
 - A spread placement group can span multiple Availability Zones in the same Region. **You can have a maximum of seven running instances per Availability Zone per group.** Therefore, to deploy 15 EC2 instances in a single Spread placement group, the company needs to use 3 AZs. And accordingly you can calculate for any number of instances
 - ENI: https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/best-practices-for-configuring-network-interfaces.html
 
# 3. EC2 Instance Storage

## 3.1 Whats an EBS Volume

**Basics**  
 - An EBS (Elastic Block Store) Volume is a network drive you can attach to your instances while they run
 - It allows your instances to persist data, even after their termination
 - They can **only be mounnted to one instance at a time (at the Certified cloud Practitioner level)**
 - They are **bound to a specific availability zone** and cannot be attached to another zone
 - Free tier: 30gb of free EBs storage of type gp2 per month
 - Its a network drive (i.e not a physical drive)
   - It uses a network to communicate the instance, which means there might be a bit of latency
   - It can be detached from an EC2 instance and attached to another one quickly
 - Its locked to an availability zone (AZ)
   - An EBS volume in `us-east-1a` cannot be attached to `us-east-1b`
   - To move a volume across, you first need to snapshot it
 - Have a provisioned capacity (size in GBs and IOPS - I/O operations per second)
   - You get billed for all the provisioned capacity
   - You can increase the capacity of the drive over time

**Hands on**  
 - EC2 -> instances -> already one instance created -> verify `Root device` and `Block devices` in the details which links to an EBS volume -> you can click on the link or go as below
 - EC2 -> Elastic Block Store -> Volume -> a root block store is already available (this is the one we provisioned while creating an EC2 instance)
 - Create new Volume: EC2 -> Elastic Block Store -> Volumes -> Create Volume -> 
   - Volume type: default (General Purpose SSD (gp2))
   - Size (GiB): 2
   - Availability zone: (should be same that of an EC2 instance you are planning to attach, you can verify via EC2 -> instances -> "Availability zone" column of your instance)
   - Create Volume
 - Created vilume will be populated in the list-users`
 - you can attach this volume via Actions -> attach Volume -> Select your instance from dropdown -> attach
 - Verify via EC2 -> instances -> already one instance created -> `Block devices` -> 2 entries now
 - If you delete an EC2 instance, an EBS volume attached to that instance if marked `delete on termination` to true while creating that instance will also be deleted

## 3.2 EBS Snapshots

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

## 3.3 EBS volume types
 - Amazon EBS provides the following volume types, which differ in performance characteristics and price, so that you can tailor your storage performance and cost to the needs of your applications
 - **Solid state drives (SSD)**: Optimized for transactional workloads involving frequent read/write operations with small I/O size, where the dominant performance attribute is IOPS. SSD-backed volume types include:
   - General Purpose SSD volumes (gp2,gp3)
     - General Purpose SSD (gp2) volumes offer cost-effective storage that is ideal for a broad range of workloads. These volumes deliver single-digit millisecond latencies and the ability to burst to 3,000 IOPS for extended periods. 
	 - Between a minimum of 100 IOPS (at 33.33 GiB and below) and a maximum of 16,000 IOPS (at 5,334 GiB and above), baseline performance scales linearly at 3 IOPS per GiB of volume size. 
	 - AWS designs gp2 volumes to deliver its provisioned performance 99% of the time. A gp2 volume can range in size from 1 GiB to 16 TiB. EBS gp2 is persistent storage and costlier than Instance Stores (the cost of the storage volume is in addition to that of the EC2 instance)
   - Provisioned IOPS SSD volumes (io1, io2)
     - Provisioned IOPS SSD (io1) volumes are designed to meet the needs of I/O-intensive workloads, particularly database workloads, that are sensitive to storage performance and consistency. 
	 - Unlike gp2, which uses a bucket and credit model to calculate performance, an io1 volume allows you to specify a consistent IOPS rate when you create the volume, and Amazon EBS delivers the provisioned performance 99.9 percent of the time. 
	 - EBS io1 is persistent storage and costlier than Instance Stores (the cost of the storage volume is in addition to that of the EC2 instance)
 - **Hard disk drives (HDD):** Optimized for large streaming workloads where the dominant performance attribute is throughput. HDD-backed volume types include Throughput Optimized HDD and Cold HDD volumes
   - Throughput optimized (st1)
     -  Throughput Optimized HDD (st1) are low-cost HDD volumes designed for frequently accessed, throughput-intensive workloads such as Big data and Data warehouses. 
	 - EBS st1 is persistent storage and costlier than Instance Stores (the cost of the storage volume is in addition to that of the EC2 instance)
   - Cold HDD volumes (sc1)
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-volume-types.html

## 3.4 AMI Overview

**Basics**  
 - AMI - Amazon Machine Image
 - AMI are a customization of an EC2 instance
   - You add your own software, configuration, operating system, monitoring, etc
   - Faster boot/configuration time because all your software is pre-packaged
 - AMI are built for a specific region (and can be copied across regions)
 - You can launch EC2 instances from:
   - A public AMI: AWS provided
   - Your Own AMI: you make and maintain them yourself
   - An AWS Marketplace AMI: An AMI someone lese made (and potentially sells)

**How to create an Custom AMI**  
 - Start an EC2 instance and customize it
 - Stop the instance (for data integrity)
 - Build an AMI - this will also create EBS snapshots
 - Launch instances from other AMIs

**Hands on**  
 - We already have an EC2 instance created, in case it is not, then follow the steps defined above to create the same
 - Instances -> Action -> Image -> Create Image -> provide name and then create
 - Go to Images -> AMI -> it will take some time(3 to 5 minutes for me) to create and will be in pending state.
 - Now lets use this above created custom AMI to create an Instance
 - Instances -> Instance -> Launch Instance -> My AMIs -> Select AMI that you just created -> t2.micro next -> Next (no user data as we will be having this from AMI created above) -> Next -> reuse all existing keys and security group -> launch
 - now you can hit the ip and you will get the same ip that you had before as we are using the same image and that ip was hard coded in index.html already
 
## 3.4 EC2 Instance Store

 - EBS volumes are network drives with good but "limited" performance
 - If you need high-performance hardware disk, use EC2 instance store
 - Better I/O Performance
 - EC2 Instance store lose their storage if they're stopped (ephermal)
 - Good to buffer/cache/scratch data/temporary content
 - Risk of data loss if hardware fails
 - Backups and replication are your responsibility

## 3.5 EFS - Elastic File System

**Basics**  
 - Managed NFS (Network file system) that can be mounted on 100s of EC2
 - EFS works with linux EC2 instances in multi availability zones
 - High available, Scalable, expensive (3x gp2), pay per use, no capacity planning 
 - Amazon Elastic File System (Amazon EFS) provides a simple, scalable, fully managed elastic NFS file system for use with AWS Cloud services and on-premises resources.
 - Amazon EFS is a regional service storing data within and across multiple Availability Zones (AZs) for high availability and durability. 
 - Amazon EC2 instances can access your file system across AZs, regions, and VPCs, while on-premises servers can access using AWS Direct Connect or AWS VPN.

You can connect to Amazon EFS file systems from EC2 instances in other AWS regions using an inter-region VPC peering connection, and from on-premises servers using an AWS VPN connection.

## 3.6 Shared responsibility model for EC2 storage

| AWS                                                 | You                           |
| -------------                                       |:-------------:                      |
| Infrastructure (global network security)            | Setting up backup/snapshot peocedures |
| Replication for data for EBS volumes and EFS drives | Setting up data encryption for additional security|
| Replacing faulty hardware                           | Responsibility of any data on the drives|
| Ensuring their employees cannot access your data    | Understanding the risk of using EC2 instance store|

## 3.7 EC2 instance Storage - Summary

 - **EBS Volumes**
   - network drives attached to one EC2 instance at a time
   - mapped to an availability zones
   - Can use EBS snapshots for backups/transferring EBS volumes across availability zones
 - **AMI**
   - Create ready-to-use EC2 instance with our customizations
 - **EC2 instance store**
   - High performance hardware disk attached to our EC2 instance
   - Lost if our instance is stopped/terminated
 - **EFS**
   - Network file system, can ve attached to 100s of instances in a region

##  3.7 Exam Guide
 - Ephermal storage (Check with EBS as well) - a reboot will not lose the data. This storage is located in disks that are physically attached to the host computer
 - EBS Volume types:
   - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-volume-types.html
 - While taking EBS snapshots, EBS volumes can be used for read and write as usual
 - Snapshot restorations are restricted to the region in which the snapshots are created
 - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-creating-snapshot.html
 - Capabilities of an encrypted EBS volume
   - Data at rest inside the volume is encrypted
   - Any snapshot created from the volume is encrypted
   - Data moving between the volume and the instance is encrypted
 - for a scenario where a group needs a fleet of EC2 instances for a specialized task that must deliver high random I/O performance. Each instance in the fleet would have access to a dataset that is replicated across the instances. Because of the resilient application architecture, the specialized task would continue to be processed even if any instance goes down, as the underlying application architecture would ensure the replacement instance has access to the required dataset.
   - EC2 Instance Store: https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/InstanceStorage.html
 
 
# 4. Elastic Load balancing and Auto scaling groups

## 4.1 Scalability and High Availability overview

**Basics**  
 - Scalability means that an application/system can handle greater loads by adapting
 - There are two kinds of scalability:
   - Vertical scalability (increase cpu power)
   - Horizontal scalability (=elasticity)
 - Scalability is linked but different to high availability

**Vertical Scalability**  
 - Vertical Scalability means increasing the size of the instance 
   - eg: upgrading from t2.micro to t2.large
 - Vertical Scalability is very common for non distributed systems, such as database
 - There's a limit to how much you can vertically scale (hardware limit)

**Horizontal Scalability**  
 - Horizontal Scalability means increasing the number of instances /systems for your application
 - Horizontal scaling implies distributed systems.
 - This is very common for web applications/modern applications
 - Its easy to horizontally scale thanks to the cloud offerings such as Amazon EC2

**High Availability**  
 - High availability usually goes hand in hand with horizontal scaling
 - High availability means running your application/system in atleast 2 Availability zones
 - The goal of high availability is to survive a data center loss (disaster)

**Summary**  
 - Vertical scaling: increase instance size ( = scale up/down)
   - from t2.nano (0.5Gb of RAM, 1vCPU) to u-12tbl.metal (12.3 TB of RAM, 448 vCpus) 
 - Horizontal Scaling: increase number of instances ( = scaling out), decrease number of instances (scaling in)
   - Auto Scaling Group
   - Load balancer
 - High Availability: Run instances for the same application across multi Availability Zones
   - Auto Scaling Group in Multi AZ mode
   - Load balancer in multi AZ Mode

**Scalability vs Elasticity vs Agility**  

 - Scalability: Ability to accomodate a larger load by making the hardware stronger (i.e scaling up) or by adding nodes (i.e scale out)
 - Elasticity: Once a system is scalable, elasticity means that there will be some auto-scaling so that the system can scale based on the load. this is "cloud-friendly" i.e pay-per-us, match demand, optimize costs.
 - Agility(not related to scalability <ins>distractor</ins>): new IT resources are only a click away, which means that you reduce the time to make those resources available to your developers from weeks to just minutes.

## 4.2 Elastic load balancing (ELB) Overview

**What is load balancing**  
 - Load balancers are servers that forward internet traffic to multiple servers (ec2 instances) downstream

**Why use Load balancers**  
 - Spread load across multiple downstream instances
 - Expose a single point of access (DNS) to your application
 - Seamlessly handle failures of downstream instances
 - Do regular health checks to your instances
 - Provide SSL termination (Https) for your websites
 - High availability across zones

**Why use Elastic Load balancers (ELB)**  

 - An ELB is a managed load balancer
   - AWS guarantees that it will be working
   - AWS takes care of upgrades, maintenance, high availability
   - AWS provides only a few configurations knob
 - It costs less to setup your own load balancer but it will be a lot more effort on your end (maintenance, integrations)
 - 3 kinds of load balancer:
   - Application Load Balancer (HTTP/HTTPS only) - Layer 7
   - Network Load Balancer (ultra-high performance, allows for TCP) = Layer 4
   - Classic Load Balancer (retiring soon) - Layer 4 and 7 (Previous generation)
 - https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/how-elastic-load-balancing-works.html
 
## 4.3 Classic Load balancers


## 4.4 Application Load Balancer (ALB)


**hands on**  
 - Create 2 EC2 instances with the user data script to differentiate between them
 - First create 1 instance -> right click -> "Launch more like this" -> Edit instance details -> change subnet i.e change the zone now so that it is highly available
 - Verify both the instances are up via hitting the public ip
 - EC2 -> LOAD BALANCING -> Load Balancers -> Create Load balancer -> Select Application Load Balancer as we are using http over browser-based
 - 1. Configure load balancer: Provide Name and all the availability zones 
 - 2. Configure security settings: it suggests to have https, we ignore for now
 - 3. Configure Security groups: create new security group -> next 
 - 4. Configure Routing: Create new target group via giving name
 - 5. Register Targets: Select the instances and use Add to registered to add them
 - 6. review and create
 - It takes some time to create, 
 - Now, use DNS name to verify if it is working and you can refresh the page to verify if it is sending request to different servers
 - In case you stop one of the instance, then elb will redirect all the calls to the instance that is up (I got 502 bad gateway once and then it was flawless)

## 4.5 Network Load Balancer (NLB)

## 4.6 Gateway Load Balancer

## 4.7 Elastic Load Balancer
 - Sticky sessions
 - Cross Zone Load Balancing
 - SSL Certificates
 - Connection Draining
 - Elastic Load Balancing does not work across regions

## 4.8 Auto Scaling Groups (ASG)

**Whats an Auto Scaling Group**
 - In real life, the load on your websites and application can change
 - In the cloud, you can create and get rid of servers very quickly
 - The goal of an AutoScaling Group (ASG) is to:
   - Scale out (Add EC2 instances) to match an increased load
   - Scale in (remove EC2 instances) to match a decreased load
   - Ensure we have a minimum and a maximum number of machines running
   - Automatically register new instances to a load balancer
   - Replace unhealthy instances
 - Cost savings: Only run at an optimal capacity (principal of the cloud)
 - Define minimum size, Actual size/ Desired capacity, Maximum size of your ASG
 - Load balancer automatically registers the instances added by ASG

**Hands on**  
 - EC2 -> AUTO SCALING -> Auto Scaling Groups -> Create Auto Scaling Group 
 -  **1. Choose launch template or configuration:** Provide name
   - As we donot have Launch template, Create a launch template. This helps in defining the type of instances we want ASG to spinoff
     - Launch Template name
     - Amazon Machine Image (AMI): Amazon Linux 2 AMI
     - Instance type: t2.micro
     - Key pair : use laready created key
     - Network settings: Virtual Private Cloud (VPC)
     - Security groups: use already created SG
     - Storage: Default
     - Expand Advanced settings -> use User data
   - Use the template created above -> Next
 -  **2. Configure Settings**
   - Instance Purchase Options: Adhere to launch template
   - Network -> Subnets: select all for higher availability -> Next
 -  **3. Configure Advanced options**
   - Load balancing -> Attach to an existing load balancer -> Choose from Application or network load balancer target groups -> select the target group already created
   - Health checks: check ELB option as well -> Next
 -  **4. Configure group size and scaling options**
   - Desired Capacity: 2 (number of instances that will be created)
   - Minimum Capacity: 1
   - Maximum Capacity: 4
   - Scaling policies: None
   - Next
 -  **5. Add Notifications: Next**
 -  **6. Add tags: Next**
 - Create Auto Scaling Group
 - Now you can go to Instance Management section to verify if the instances are created
 - Verification:
   - You can verify that 2 instances are created in the INSTANCES -> instances section
   - They are registerd in the target groups via LOAD BALANCING -> Target Groups
   - Open the DNS from load balancer and see it gives you the result

## 4.9 Summary

 - High Availability vs Scalability (Vertical and horizontal) vs Elasticity vs Agility in the cloud
 - Elastic Load Balancers (ELB)
   - Disctribute traffic across backend EC2 instances, can be Multi-AZ
   - Supports health checks
   - 3 types: Application LB (HTTP-L7), Network LB (TCP-L4), Classic LB (old)
 - Auto Scaling Groups (ASG)
   - Implement Elasticity for your application, across multiple AZ
   - Scale EC2 instances based on the demand on your system, replace unhealthy
   - Integrated with the ELB

## 4.10 Exam Guide
 - Which load balancer should you use to handle hundreds of thousands of connections with low latency
   - Network Load balancer: A network load balancer can handle millions of requests per second with low-latency. It operates at Layer 4 and is best suited for load-balancing TCP, UDP and TLS traffic with ultra high-performance
 - Amazon EC2 Auto Scaling doesn't terminate an instance that came into service based on EC2 status checks and ELB health checks until the health check grace period expires.
 - **Launch configurations** were released in 2010 and **Launch templates** were released as successor in 2017
   - https://aws.amazon.com/blogs/compute/amazon-ec2-auto-scaling-will-no-longer-add-support-for-new-ec2-features-to-launch-configurations/
 - Problem: Launch Configuration selected for the Auto Scaling group is using the incorrect instance type that is not optimized to handle the application workflow
   - A launch configuration is an instance configuration template that an Auto Scaling group uses to launch EC2 instances. When you create a launch configuration, you specify information for the instances. Include the ID of the Amazon Machine Image (AMI), the instance type, a key pair, one or more security groups, and a block device mapping.
   - It is not possible to modify a launch configuration once it is created. The correct option is to create a new launch configuration to use the correct instance type. Then modify the Auto Scaling group to use this new launch configuration. Lastly to clean-up, just delete the old launch configuration as it is no longer needed
   - https://docs.aws.amazon.com/autoscaling/ec2/userguide/LaunchConfiguration.html
 - In ASG, When we want to specify a range of instances, then we must use min and max values. And if the use-case requires exactly 10 instances to be available during the peak hour, so we must set the desired capacity to 10 or whatever mentioned
 - Update/install patches to EC2 instances in ASG
   - You can put an instance that is in the InService state into the Standby state, update some software or troubleshoot the instance, and then return the instance to service. Instances that are on standby are still part of the Auto Scaling group, but they do not actively handle application traffic
 - ELB target health checks https://docs.aws.amazon.com/elasticloadbalancing/latest/network/target-group-health-checks.html
 - ALB vs NLB: https://blog.cloudcraft.co/alb-vs-nlb-which-aws-load-balancer-fits-your-needs
 - What happens when EC2 instance fails health check

## 4.11 Resources

 - https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/what-is-load-balancing.html?icmpid=docs_elbv2_console#elb-features
 - Instance types and comparison: https://instances.vantage.sh/

# 5. Amaozon S3 (Simple storage Service)

## 5.1 S3 Overview

**Basics**  
 - S3 is one of the main building blocks of AWS
 - Its advertised as "infinity scaling" storage
 - Many website use Amazon S3 as a backbone
 - Many AWS Services uses Amazon S3 as an integration as well
 - EBS snapshots are stored in S3 in backend
 - Certified cloud Practitioner requires deeper knowledge of S3

**Use Cases**  
 - Backup and Storage
 - Disaster recovery
 - Archive
 - Hybrid Cloud Storage
 - Application Hosting
 - Media hosting
 - Data lakes and big data analytics
 - Software delivery
 - Static website

**Buckets**  
 - Amazon S3 allows people to store object (files) in "buckets" (directories)
 - Buckets must have a globally unique name (across all regions and all accounts)
 - Bucktes are defined at the region level
 - S3 looks like a global service but buckets are created in a region
 - Naming Convention
   - No uppercase
   - No Underscore
   - 3-62 characters long
   - Not an IP
   - Must start with lowercase letter or number

**Objects**  
 - Objects (files) have a key
 - The key is the full path:
   - s3://my-bucket/my_file.txt
   - s3://my-bucket/my_folder/another_folder/my_file.txt
 - The key is composed of `prefix` + *object name*
   - s3://my-bucket/`my_folder/another_folder/`*my_file.txt*
 - Theres no concept of "directories" within buckets (although the UI will trick you to think otherwise)
 - Just keys with very long names that contains slashes ("/")
 - Object values are the content of the body
   - Max Object size is 5TB (5000GB)
   - If uploading more that 5 GB, must use "multi-part upload"
 - Metadata (list of text key/value pairs - system or metadata)
 - Tags (Unicode key / value pair - upto 10) - useful for security/lifecycle
 - Version ID (if Versioning is enabled)

**S3 Hands on**  

 - S3 -> buckets -> Create Bucket -> provide uniques bucket name and region along with default options
 - [Bucket naming rules](https://docs.aws.amazon.com/AmazonS3/latest/dev/BucketRestrictions.html#bucketnamingrules)
 - Add files -> select file -> upload
 - Now, if you select the uploaded file, you will get the details about that file
 - You will see the Object url under Object Overview heading in Properties tag
 - Now, as we had chosen all the default options that means block public access included in it and hence if you click on object URL, you will not be able to access the same
 - Whereas clicking on Object Actions -> Open will open the file in new tab

## 5.2 Bucket Policy

**S3 Security**  
 - User Based
   - IAM policies - Which API calls should be allowed for a specific user from IAM console
 Resource Based
   - Bucket Policies - bucket wide rules from the S3 console - allows cross account
   - Object Access Control List (ACL) - Fine grained
   - Bucket Access Control List (ACL) - Less Common

 - Note: An IAM principal can access an S3 object if
   - the user IAM permissions allow it **OR** the resource policy allows it
   - **AND** there's no explicit DENY
 - Encryption: encrypt objects in Amazon S3 using encryption keys

**Examples**  
 - Public Access ->   Use Bucket policy (Allow S3 bucket policy)
 - IAM User Access -> IAM Policy only will be sufficient
 - EC2 Instance -> Create EC2 instance role and assign IAM permissions to this role
 - Cross-Account access -> S3 bucket policy allow cross account policy

**Policy**  
 - JSON based Policies
   - Resources: buckets and Objects
   - Actions: Set of API to allow or Deny
   - Effect: Allow/Deny
   - Principal: The Account or user to apply the policy to
 - Use S3 bucket for policy to:
   - Grant public access to the bucket
   - Force Objects to be encrypted at upload
   - Grant Access to another Account (Cross Account)

**Bucket Settings for Block public access**  
[Screenshot]

 - These settings were created to prevent company data leaks
 - If you know your bucket should never be public, leave thse on
 - Can be set at the account level

**Hands On**  

 - go to S3 bucket created above -> permissions -> Bucket policy
 - To enable this, you have to uncheck "block public access" policies
 - Once done, you can click on edit button in edit bucket policy 
   - Select "policy generator" as it provides a nice ui to generate a policy -> select
   - Also notice you have Bucket ARN that will be used in next steps
   - Select type of policy: S3 bucket policy
   - Principal: *
   - Actions: getObject
   - ARN: copy from the step above with `/*`
   - Add statement
   - Generate Policy -> copy content 
   - now go back to previous tab and update the copied policy and save
   - now you will have the objects inside this bucket as accessible

## 5.3 S3 Websites

**basics**  
 - S3 can host static websites and have them accessible on the www
 - The website URL will be:
   - <bucket-name>.s3-website-<aws-region>.amazonaws.com or
   - <bucket-name>.s3-website.<aws-region>.amazonaws.com
 - If you get a 403 (Forbidden) error, make sure the bucket policy allows public reads

**hands on**  
 - upload your static content
 - in Properties tab -> Static website hosting -> Enable
 - host a static wbesite
 - provide name of your index and error document
 - save
 - now you get the url for the newly hosted website
 
## 5.4 S3 Versioning

**basics**
 - you can version your files in Amazon S3
 - It is enabled at the biucket level
 - Same key overrite will increment the version: 1,2,3..
 - It is best practice to version your buckets
   - protect against unintended deletes (ability to restore a version)
   - Easy roll back to previous version
 - Notes: 
   - Any file that is not versioned prior to enabling versioning will have version null
   - Suspending versioning does not delete the previous versions

**hands on**
 - S3 -> Select the bucket you created above -> properties tab -> Bucket versioning -> edit -> Enable -> Save changes
 - Now any file uploaded from now on will have version in it
 - you can select the file and click on versions tab you will see the versions
 - Also in S3 bucket level -> Objects section -> List Versions and you will see the versions of the file
 - you can select the version you want to rollback and delete that so it will be rolled back to the previous version
 - Deleting a file with list versions enabled will do a soft delete.
   - To get back this file you have to enable List versions and then you will see the deleted file with "Delete marker" and then select delete tp restore

## 5.5 S3 Access logs

**basics**  
 - For audit purpose, you may want to log all access to S3 buckets
 - Any request made to S3, from any account, authorized or denied will be logged into another S3 bucket
 - That data can be analyzed using data analysis tools..
 - Very helpful to come down to the root cause of an issue, or audit usage, view suspicious patterns, etc..

**Hands on**  
 - To enable logging you have to create a new bucket where access logs will be written
 - Go to original S3 bucket -> Properties -> Server access logging setting -> edit -> enable and provide the target bucket that we created above.
 - You can access the objects and then these logs will be reflected in another s3 bucket after an hour or two.

## 5.6 S3 Replication (CRR and SRR)

**basics**  
 - Must enable versioning in source and destination
 - Cross Region Replication (CRR) 
 - Same Region Replication (SRR)
 - Buckets can be in different accounts
 - Copying is asynchronous
 - Must give proper IAM permissions to S3
 - CRR use cases
   - Compliance
   - lower latency access
   - Replication across accounts
 - SRR use cases
   - log aggregation
   - live replication between production and test accounts

**hands on**  
 - create a new S3 bucket where you need data to be replicated
 - enable versioning in source and target bucket
 - go to main bucket -> management tab -> Replication rule -> Create replication rule
   - provide rule name
   - status : enabled
   - Source bucket
   - Destination: buckte in this account, provide the name
   - IAM role: Create a new role
   - Save
   - Replication rule created
 - Replication will work only for objects that are being uploaded after enabling the replication rule

## 5.7 S3 Storage classes

### 5.7.1 Durability and Availability
**Durability:**  
 - High durability (99.99999999999, 11 time 9) of objects across multiple Azure
 - If you store 10,000,000 objects with Amazon S3, you can on average expect to incur a loss of a single object once every 10,000 years
 - `Same for all storage classess`

**Availability**  
 - Measures how readily available a service is
 - S3 standard has 99.99% availability, which means it wil not be available 53 minutes a year
 - `Varies depending on storage class`
 
### 5.7.2 Types
 - Amazon S3 Standard - General purpose
 - Amazon S3 Standard-Infrequent Access (not accessed ver often)
 - Amazon S3 one Zone-Infrequent Access (you can recreate and fine with losing it)
 - Amazon S3 Intelligent tiering (Not sure which one to choose between frquent and infrequent access)
 - Amazon Glacier (Backups and archives)
 - Amazon Glacier Deep Archive (Backups and archives)



#### 5.7.2.1 Amazon S3 Standard - General purpose 
 - 99.99% availability
 - Used for frequently accessed data
 - low latency and high throughput
 - sustain 2 concurrent facility failures
 - Use cases:
   - Big Data analytics
   - Mobile and gamin applications
   - content distribution

#### 5.7.2.2. Amazon S3 Standard-Infrequent Access (IA) 
 - Suitable for data that is less frequently accessed, but requires rapid access when needed
 - 99.9% Availability
 - Lower cost compared to Amazon S3 standard, but retrieval fee
 - Sustain 2 concurrent facility failures
 - Use Cases: 
   - As a data store for disaster recovery
   - backups

#### 5.7.2.3. Amazon S3 one Zone-Infrequent Access (IA) 
 - Same as IA but data is stored in a single Azure
 - 99.5% Availability
 - Low latency and high throughput performance
 - Lower cost compared to S3-IA (by 20%)
 - Use Cases:
   - Storing secondary backup copies of on premise data or
   - storing data you can recreate

#### 5.7.2.4. Amazon S3 Intelligent tiering  
 - 99.9% Availability
 - Same low latency and high throughput performance of S3 Standard
 - Cost optimized by automatically moving objects between two access tiers based on changing access patterns:
   - frequent access
   - Infrequent access
 - Resilient against events that impact an entire Availability zone

#### 5.7.2.5 Amazon Glacier and Amazon Glacier Deep Archive
 - Low cost object storage (in GB/month) meant for archiving/backup
 - Data is retained for the longer term (years)
 - Various retrieval options of time + fees for retrieval
 - Amazon Glacier: Cheap
   - Expedited (1 to 5 minutes)
   - Standard (3 to 5 hours)
   - Bulk (5 to 12 hours)
 - Amazon Glacier Deep Archive: Cheapest
   - Standard (12 hours)
   - Bulk (48 hours)

#### 5.7.2.6 Storage classes Summary  
 - https://aws.amazon.com/s3/storage-classes/

#### 5.7.2.7 Moving between storage classes  
 - You can transition objects between storage classes
 - For infrequently accessed object, move them to Standard_IA
 - For archive objects you dont need in real-time, GLACIER or DEEP_ARCHIVE
 - Moving Objects can be automated using a lifecycle configuration
 - https://docs.aws.amazon.com/AmazonS3/latest/userguide/lifecycle-transition-general-considerations.html

**Hands on**  
 - S3 -> Objects -> upload -> Additional upload options, here you can select the storage class and details are also available on the same -> upload
 - if you select the uploaded file, yyou can verify the storage class and you can update the same if needed
 - S3 -> Buckets -> Select bucket -> management tab -> Create lifecycle rule
   - LifeCycle Rule name
   - Choose a rule scope
   - Lifecycle Rule actions: depending on the checkboxes you select, you will be provided with the options to configure below
   - Create Rule

## 5.8 Snowball, Snowball Edge and SnowMobile Overview

### 5.8.1 Snowball

**Basics**    
 - Physical data transport solution that heps moving TBs or PBs of data in or out of AWS
 - Alternative to moving data over the network (and paying network fees)
 - pay per data transfer jobs
 - Use cases:
   - Large data cloud migrations
   - Data center decommision
   - Disaster recovery
 - if it takes more than a week to transfer over the network, use snowball devices

**Snowball Process**  
 - Request snowball devices from the AWS console for delivery
 - Install the snowball client on your servers
 - Connect the snowball to your servers and copy files using the client
 - Ship back the device when you are done (goes to the right AWS facility)
 - Data will be loaded into an S3 bucket
 - Snowball is completely wiped

### 5.8.2 Snowball Edge  
 - Snowball Edge (100 TB) add computational capability to the device
 - Supports a custom EC2 AMI so you cab perform processing on the go
 - Supports customm lambda functions
 - Very useful to pre-process the data while moving
 - Use case:
   - data migrations
   - image collation
   - IoT capture
   - Machine Learning
 - "Snowball" is deprecated in favour of "Snowball Edge"

### 5.8.3 AWS Snowmobile (Truck)  
 - Transfer exabytes of data (1 EB = 1,000 PB = 10,000,00 TBs)
 - Each Snowmobile has 100PB of capacity (use multiple in parallel)
 - Better than snowball if you transfer more than 10 PB

Snow *

Snowball Edge

Snowcone


## 5.9 Storage gateway overview

### 5.9.1 Overview
**Where do we need?**  
 - AWS is pushing for "hybrid cloud"
   - Part of your infrastructure is on-premises
   - Part of your infrastructure is on the cloud
 - This can be due to
   - Long cloud migrations
   - Security requirements
   - Compliance requirements
   - IT strategy
 - S3 is a propritary storage technology (unlike EFS/NFS), **so how do you expose the S3 data in-premise?**
    - via AWS Storage Gateway

**AWS Storage Cloud Native Options**  
 - Block storage
   - Amazon EBS
   - EC2 instance store
 - File Syorage
   - Amazon EFS
 - Object storage
   - Amazon S3
   - Glacier

### 5.9.2 AWS storage gateway 
 - AWS Storage Gateway is a hybrid cloud storage service that gives you on-premises access to virtually unlimited cloud storage
 - It seamlessly connect on-premises applications to cloud storage, caching data locally for low-latency access.
 - Bridge between on-premise data and cloud data in S3
 - Hybrid storage service to allow on-premises to seamlessly use the AWS Cloud
 - Use cases:
   - Disaster recovery
   - Backup and restore
   - Tiered storage
 - **Types of storage gateway**
   - File gateway
   - Volume gateway
   - Tape gateway
 - https://docs.aws.amazon.com/storagegateway/latest/userguide/StorageGatewayConcepts.html

**File gateway**  
 - AWS Storage Gateway's file interface, or file gateway, offers a seamless way to connect to the cloud in order to store application data files and backup images as durable objects on Amazon S3 cloud storage. 
 - File gateway offers SMB or NFS-based access to data in Amazon S3 with local caching
 - https://aws.amazon.com/storagegateway/file/

**Volume gateway**  
 - You can configure the AWS Storage Gateway service as a Volume Gateway to present cloud-based iSCSI block storage volumes to your on-premises applications. 
 - Volume Gateway does not support NFS interface
 - https://aws.amazon.com/storagegateway/volume/

**Tape gateway**  
 - Tape Gateway allows moving tape backups to the cloud. Tape Gateway does not support NFS interface
 - https://aws.amazon.com/storagegateway/vtl/
 
## 5.10 Shared responsibility model for S3

| AWS                                                 | You                           |
| -------------                                       |:-------------:                      |
| Infrastructure (global security, durability, availability, sustain concurrent loss of data in two facilities) | S3 Versioning |
| Configuration and vulnerability analysis            | S3 Bucket Policies|
| Compliance Validation                               | S3 Replication Setup |
|                                                     | Logging and Monitoring |
|                                                     | S3 Storage Classes |
|                                                     | Data encryption at rest and in transit |

## 5.12 S3 Summary
 - **Buckets vs Objects:** global unique name, tied to a region
 - **S3 security:** IAM policy, S3 bucket Policy (public access), S3 encryption
 - **S3 Websites:** host a static website on Amazon S3
 - **S3 Versioning:** Multiple versions for files, prevents accidental deletes
 - **S3 Access Logs:** Log requests made within your S3 buckets
 - **S3 Replication:** Same-region or Cross-Region, must enable versioning
 - **S3 Storage Classes:** Standard, IA, IZ-IA, Intelligent, Glacier, Deep Archive
 - **S3 Lifecycle Rules:** transition objects between classes
 - **Snowball/Snowmobile:** import data into S3 through a physical device
 - **Storage gateway:** hybrid solution to extend on-premises storage to S3

## 5.13 S3 Encryption
Their are 4 methods of encrypting objects in s3:
 - SSE-S3: Encrypts S3 objects using keys handled and managed by aws
 - SSE-KMS: leverage aws key management service to manage encryption keys
 - SSE-C: When you want to manage your encryption keys
 - Client Side encryption

### 5.13.1 SSE-S3
 - Encryption using keys handled and managed by aws
 - Object is encrypted server side
 - AES-256 encryption type
 - Must set header: "x-amz-server-side-encryption":"AES256"

### 5.13.2 SSE-KMS
 - Encryption using keys managed and handles by KMS
 - Object is encrypted server side
 - KMS advantages: User control + audit trail
 - Must set header: "x-amz-server-side-encryption":"aws:kms"

### 5.13.3 SSE-C
 - Encryption using data keys fully managed by the customer outside of AWS
 - Amazon S3 does not store the encryption keys you provide
 - HTTPS must be used
 - Encryption key must be provided in headers, for every HTTP request made

### 5.13.4 Client Side encryption
 - Cleint library such as Amazon S3 encryption client
 - Clients must encrypt data themselves before sending to S3
 - Clients must decrypt data themselves when retrieving from S3
 - Customer fully manages the keys and encryption cycle

### 5.13.5 Encryption in Transit:
 - Amazon S3 exposes:
   - HTTP endpoint: non encrypted
   - HTTPS endpoint: encryption in flight
 - You are free to use the endpoint you want, but HTTPS is recommended
 - Most clients would use the HTTPS endpoint by default
 - HTTPS is mandatory for SSE-C
 - Encryption in flight is also called SSL/TLS

## 5.14 Exam Guide - S3
 - Bucket policies in Amazon S3 can be used to add or deny permissions across some or all of the objects within a single bucket. Policies can be attached to users, groups, or Amazon S3 buckets, enabling centralized management of permissions. With bucket policies, you can grant users within your AWS Account or other AWS Accounts access to your Amazon S3 resources.
 - You can further restrict access to specific resources based on certain conditions. For example:
   - You can restrict access based on request time (Date Condition)
   - Whether the request was sent using SSL (Boolean Conditions)
   - A requester’s IP address (IP Address Condition)
   - Or based on the requester's client application (String Conditions). To identify these conditions, you use policy keys
 - Use Amazon S3 Transfer Acceleration to enable faster file uploads into the destination S3 bucket 
   - Use multipart uploads for faster file uploads into the destination S3 bucket
   - There are no S3 data transfer charges when data is transferred in from the internet. 
   - For S3 Transfer Acceleration, you pay only for transfers that are accelerated
 - Amazon Simple Storage Service (Amazon S3) is an object storage service that offers industry-leading scalability, data availability, security, and performance. 
   - Your applications can easily achieve thousands of transactions per second in request performance when uploading and retrieving storage from Amazon S3. Amazon S3 automatically scales to high request rates. For example, your application can achieve at least 3,500 PUT/COPY/POST/DELETE or 5,500 GET/HEAD requests per second per prefix in a bucket.
   - There are no limits to the number of prefixes in a bucket. You can increase your read or write performance by parallelizing reads. For example, if you create 10 prefixes in an Amazon S3 bucket to parallelize reads, you could scale your read performance to 55,000 read requests per second
   - https://docs.aws.amazon.com/AmazonS3/latest/dev/optimizing-performance.html
 - S3 Event notification destinations (SNS, SQS, Lambda, EventBridge)
 - Different versions of a Single Object can have different retention modes and periods
 - When you apply a retention period to an object version explicitly, you can specify a `Retain Until Date` for the oject version
   - https://docs.aws.amazon.com/AmazonS3/latest/dev/object-lock-overview.html
 - S3 versioning: Once you version-enable a bucket, it can never return to an unversioned state. Versioning can only be suspended once it has been enabled. 
 - A process replaces an existing object and immediately tries to read it. Amazon S3 always returns the latest version of the object
   - Amazon S3 delivers strong read-after-write consistency automatically, without changes to performance or availability, without sacrificing regional isolation for applications, and at no additional cost.
   - After a successful write of a new object or an overwrite of an existing object, any subsequent read request immediately receives the latest version of the object. S3 also provides strong consistency for list operations, so after a write, you can immediately perform a listing of the objects in a bucket with any changes reflected.
   - Strong read-after-write consistency helps when you need to immediately read an object after a write. For example, strong read-after-write consistency when you often read and list immediately after writing objects.
   - **To summarize, all S3 GET, PUT, and LIST operations, as well as operations that change object tags, ACLs, or metadata, are strongly consistent.** What you write is what you will read, and the results of a LIST will be an accurate reflection of what’s in the bucket.

 
# 6. Containers 

## 6.1 Docker container management on AWS
 - Amazon Elastic Container Service (ECS)
   - Amazon's own container platform
 - Amazon Elastic Kubernetes Service (EKS)
   - Amazon's managed kubernetes (open source)
 - AWS Fargate
   - Amazon's own serverless container platform
   - Works with ECS and with EKS
 - Amazon ECR
   - Store container images 

## 6.2 ECS

### 6.2.1  ECS - EC2 Launch type
 - ECS = Elastic container servvice
 - Launch Docker container on AWS = Launch ECS Tasks on ECS clusters
 - EC2 Launch Type: you must provision and maintain the infrastructure (the EC2 instances)
 - Each EC2 instance must run the ECS agent to register in the ECS cluster
 - AWS takes care of starting/stopping containers

### 6.2.2 ECS - EC2 Launch Type - Autoscaling EC2 instances
 - Accomodate ECS Service scaling by adding underlying EC2 instances
 - Auto scaling Group scaling
   - Scale your ASG based on CPU utilization
   - Add EC2 instances over time
 - ECS cluster capacity provider
   - Used to automatically provision and scale the infrastructure for your ECS Tasks
   - Capacity provider paired with an Auto scaling group
   - Add EC2 instances when you are missing capacity (CPU, RAM,)
 
### 6.2.3 ECS - Fargate Launch type 
 - Launch Docker containers on AWS
 - You do not provision the infrastructure (no EC2 instances to manage)
 - Its all serverless
 - You just create task definitions
 - AWS just runs the ECS tasks for you based on the CPU/RAM you need
 - To scale, just increase the number of tasks. Simple - no more EC2 instances
 
### 6.2.4 IAM Roles
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

### 6.2.5 Load balancer integrations
 - Application Load Balancer supported and works for most use cases
 - Network Load balancer recommended only for high throughput/high performance use cases, or to pair it with AWS private link
 - Elastic load balancer supported but not recommended (no advance features, no fargate)

### 6.2.6 Data Persistances/Volumes (EFS)
 - Mount EFS file systems to ECS tasks
 - Works for both EC2 and Fargate launch types
 - Taks running in any AZ will share the same data in the EFS file system
 - Fargate + EFS  = Serverless
 - Use cases: Persistent multi-az shared storage for your containers
 - Note: Amazon S3 cannot be mountes as a file system

### 6.2.7 ECS Service Auto scaling
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

## 6.3 ECR
 - ECR = Elastic container registry
 - Store and Manage Docker images on AWS
 - private and public repository (Amazon ECR public gallery - https://gallery.ecr.aws)
 - Fully integrated with ECS and backed by S3
 - Access is controlled through IAM  (permissions error are related to policy)
 - Supports image vulnerability scanning, versioning, image tags, image lifecycle

## 6.4 EKS

### 6.4.1 EKS Overview
 - EKS = Elastic kubernetes service
 - It is a way to launch managed kubernetes clusters on AWS
 - K8s is an open source system for automatic deployment, scaling and management of containerized (usually docker) application
 - Its an alternative to ECS, imilar goal but different API
 - EKS supports EC2 if you want to deploy worker nodes or Fargate to deploy serverless containers
 - Use case: if your company is already using k8s on premises or in another cloud, and wants to migrate to AWS using K8s
 - Kubernetes is cloud agnostic (can be used in any cloud: Azure, GCP)
 
### 6.4.2 EKS Node Types
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

### 6.4.3 EKS Data volumes
 - Need to specify StorageClass manifest on your EKS cluster
 - Leverages a Container Storage Interface (CSI) compliant driver
 - Support for:
   - EBS
   - EFS (works with fargate)
   - Amazon Fsx for Lustre
   - Amazon Fsx for NetApp ONTAP
 
## 6.5 App Runner
 - Fully managed service that makes it easy to deploy web applications and the APIs at scale
 - No Infrastructure experience required
 - Start with your source code or container image
 - Automatically builds and deploy the web app
 - Automatic scaling, highly available, load balancer, encryption
 - VPC access support
 - Connect to database, cache and message queue services
 - Use case: WebApps, APIs, Micro-services, Rapid production deployments

# 7. Serverless

## 7.1 Overview
 - Serverless is a new paradigm in which the developers dont have to manage servers anymore
 - The just need to deploy code, rather functions at start
 - Initially Serverless = FaaS (Function as a service)
 - Serverless was pioneered by AWS Lambda but now also includes anything thats managed: "databases, messaging, storage, etc"
 - Serverless does not mean there are no servers, it means you just dont need to manage/provision/see them
 
## 7.2 Serverless Services 
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
 
## 7.3 Lambda

### 7.3.1 Overview
 - Good overview: https://aws.amazon.com/lambda/

### 7.3.2 Why Lambda?
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

### 7.3.3 Benefits
 - Easy pricing
   - pay per request and compute time
   - free tier of 10,00,000 Lambda requests and 4,00,000 GBs of compute time
 - Integrated with whole suite of AWS services
 - Easy monitoring through AWS cloudwatch
 - Easy to get more resources per functions (upto 10GB RAM)
 - Increasing RAM will also improve CPU and network

### 7.3.4 Language support
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

### 7.3.5 Examples
 - CRON job application
 - image in multiple formats creation app

### 7.3.6 Pricing
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

### 7.3.7 Lambda Limits - per region
 - Execution
   - Memory allocation: 128MB - 10GB (1 MB increments)
   - Maximum execution time: 900 seconds (15 minutes)
   - Environment variables (4KB)
   - Disk capacity in the "function container" (in /tmp): 512MB to 10 GB
      - Use case: load file to process
   - **Concurrency executions: 1000 (can be increased)**
 - Deployment
   - Lambda function deployment size (compressed .zip): 50MB
   - size of uncompressed deployment (code + dependencies): 250MB
   - can use /tmp directory to load other files at startup   
   - size of environment variables 4KB

### 7.3.8 Lambda@Edge
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

### 7.3.9 Lambda Networking (VPC)
 - By Default
   - Lambda function is launched outside your own VPC (in AWS owned VPC)
   - Therefore it cannot access resources in your VPC (RDS, ElastiCache, internal ELB, etc)
   - But can access global services like dynamoDB and also to external world
 - Lambda in VPC
   - You must define the VPC ID, the subnets and security groups
   - Lambda will create an ENI (Elastic Network Interface) in your subnets

### 7.3.10 Lambda with RDS Proxy
 - if lambda function directly access your database, they may open too many connections under high load
 - RDS Proxy:
   - Improve scalability by pooling and sharing DB connections
   - Improve availability by reducing 66% the failover time and preserving connections
   - Improve security by enforcing IAM authentication and storing credentials in secrets manager
 - Lambda function must be deployed in your VPC , because **RDS proxy is never publicly accessible**

## 7.4 DynamoDB

### 7.4.1 Overview
 - Fully managed, Highly available with replication across Multiple AZs
 - NoSQL database: with transaction support
 - Scales to massive workloads, disctributed database
 - Millions of requests per second, trillions of row, 100s of TBs of storage
 - Fast and Consistent in peroformance (Single Digit millisecond)
 - Integrated with IAM for security, authorization and administration
 - Low cost and auto-scaling capabilities
 - No Maintenance or patching, always available
 - Standard and Infrequent Access (IA) Table class

### 7.4.2 Basics
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

### 7.4.3 Read/Write capacity modes
 - Control how you manage your table's capacity (read/write throughput)
 - Provisioned mode (default)
   - You specify the number of reads/writes per second
   - You need to plan the capacity beforehand
   - Pay for provisioned Read Capacity Units (RCU) and Write Capacity Units (WCU)
   - Possibility to add auto-scaling mode for RCU and WCU

### 7.4.4 On Demand Mode
 - Read/Writes automatically scale up/down with you workloads
 - No Capacity planning needed
 - Pay for what you use, more expensive
 - Great for unpredictable workloads, steep sudden spikes

### 7.4.5 DynamoDB Accelerator (DAX)
 - DAX is a fully managed, highly available, in-memory cache for DynamoDB that delivers up to a 10x performance improvement – from milliseconds to microseconds – even at millions of requests per second
 - It does all the heavy lifting required to add in-memory acceleration to your DynamoDB tables, without requiring developers to manage cache invalidation, data population, or cluster management
 - Help solve read congestion by caching
 - Micro-seconds latency for cached data
 - Doesn't require application logic modification (Compatible with existing DynamoDB APIs)
 - 5 minutes TTL for cache
 - https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DAX.concepts.html
 - https://aws.amazon.com/dynamodb/dax/

### 7.4.6 DAX vs ElastiCache
 - DAX:
   - Individual Object cache
   - Query and Scan cache
 - ElastiCache
   - Store aggregation result

### 7.4.7 DynamoDB - Stream processing
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

### 7.4.8 DynamoDB Streams use case diagram
 - https://aws.amazon.com/blogs/database/dynamodb-streams-use-cases-and-design-patterns/

### 7.4.9 DynamoDB Global Tables
 - Make a DynamoDB table accesible with low-latency in multiple regions
 - Active-Active replication
 - Applications can READ and WRITE to the table in any region
 - Must enable DynamoDB Streams as a pre-requisite

### 7.4.10 DynamoDB - Time To Live (TTL)
 - Automatically delete items after an expiry timestamp
 - Use cases: reduce stored data by keeping only current items, adhere to regulatory obligations

### 7.4.11 DynamoDB - Backups for disaster recovery
 - Continous backups using point in time recovery (PITR)
  - Optionally enabled for last 35 days
  - Point in time recovery to any time within the backup window
  - The backup process creates a new table
 - On demand backups
  - Full backups for long-term retention, until explicitly deleted
  - Doesnt affect performance or latency
  - Can be configured and managed in AWS backup (enable cross-region copy)
  - The recovery process creates a new table

### 7.4.12 DynamoDB - Integration with Amazon S3
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

## 7.5 API Gateway

### 7.5.1 Overview
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

### 7.5.2 API Gateway - Integrations 
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

### 7.5.3 API Gateway - Endpoint Types
 - Edge Optimized (default)
   - Requests are routed through the CloudFront Edge Locations (improves latency)
   - The API gateway still lives in only 1 region
 - Regional
   - For clients within the same region
   - Could Manually combine with CloudFront (More control over the caching strategies and the distribution)
 - Private
   - Can only be accessed from your VPC using an interface VPC Endpoint (ENI)
   - Use a resource policy to define access

### 7.5.4 API Gateway - Security
 - User authentication via:
   - IAM Roles (usefule for Internal applications)
   - Cognito (Identity for external users - example mobile users)
   - Custom Authorizer (Your own  logic)
 - Custom Domain Name HTTPS security through integration with AWS Certificate Manager (ACM)
   - If using Edge optimized endpoint, then the certificate must be in us-east-1
   - If using regional endpoint, then the certificate must be in API Gateway Region
   - Must setup CNAME or A-alias record in Route 53

## 7.6 AWS Step functions
 - Build serverless visual workflow to orchestrate your Lambda functions
 - Features: Sequence, parallel, conditions, timeouts, error handling
 - Can integrate with EC2, ECS, On-premises servers, API Gateway, SQS queues, etc
 - Possibility of implementing human approval feature
 - Use cases: order fullfillment, data processing, web applications, any workflow

## 7.7 Exam Guide
**API Gateway**  
 - API Gateway creates RESTful APIs that:
   - Are HTTP-based.
   - Enable stateless client-server communication.
   - Implement standard HTTP methods such as GET, POST, PUT, PATCH, and DELETE.
 - API Gateway creates WebSocket APIs that:
   - Adhere to the WebSocket protocol, which enables stateful, full-duplex communication between client and server.
   - Route incoming messages based on message content.
 - So API Gateway supports stateless RESTful APIs as well as stateful WebSocket APIs. 
 - https://aws.amazon.com/api-gateway/
 - To prevent your API from being overwhelmed by too many requests, Amazon API Gateway throttles requests to your API using the token bucket algorithm, where a token counts for a request. 
   - Specifically, API Gateway sets a limit on a steady-state rate and a burst of request submissions against all APIs in your account. In the token bucket algorithm, the burst is the maximum bucket size.

**Lambda**  
 - AWS Lambda currently supports 1000 concurrent executions per AWS account per region. You need to contact AWS support to raise the account limit.

# 8. Databases

## 8.1 Choose Database
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

## 8.2 Database Types:
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

## 8.3 Amazon RDS

### 8.3.1 Overview
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

### 8.3.2 RDS Read replicas vs RDS Multi AZ (Availability zone)
Please see the sections below

#### 8.3.2.1 RDS Read replicas
 - upto 5 read replicas
 - Repliction are ASYNC i.e they are eventually consistent
 - Application must update the connection string to access read replicas

**Use case:**
 - Reporting application to run some analytics
 - Read replicas for SELECT statement only

**Network cost:**
 - In same region, dont pay fee for replication in same region
 - Across region replication fee is applicable
 
#### 8.3.2.2 RDS Multi AZ (Disaster Recovery)
 - SYNC Replication
 - One DNS name - Automatic App failover to standby
 - this Automatic failover increases availability
 - Automatic failover scenarios:
    - Loss of Availability Zone
    - Loss of Instance or
    - Loss of STorage
 - No manual intervention is required as its all automatic
 - It cannot be used for scaling as it is always on standby and cannot be accessed directly

## 8.4 Amazon Aurora

### 8.4.1 Overview
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
 - **Aurora Database Cloning:** New Cluster from existing one, faster than restoring a snapshot
 - **Use Case:** Same as RDS, but with less maintenance/More flexibility/more performance/more features
 
### 8.4.2 Advanced concepts
 - **Aurora Replicas - Auto Scaling**
 - **Aurora - Custom endpoints**
   - Define a subset of aurora instances as a custom endpoints
   - Example: Run analytical queries on specific replicas 
   - Reader endpoint is generally not used after creating custom endpoint
 - **Aurora - Serverless**
   - Automated database instantiation and auto-scaling based on actual usage
   - Good for infrequent, intermittent or unpredictable workloads
   - No capacity planning needed
   - Pay per second, can be more cost-effective
 - **Aurora Multi master**
   - In case we need immediate failover for write node (High Availability)
   - Every Node does R/W - vs promoting a Read Replica (RR) as the new master
 - **Global Aurora**
   - **Aurora Cross region read replicas**
     - Useful for disaster recovery
     - Simple to put in place
   - **Aurora Global Database (Recommended)**
     - 1 primary region (read/write)
     - Upto 5 secondary (readonly) regions, replication lag is less than 1 second
     - upto 16 Read Replicas per secondary region
     - Helps for decreasing latency
     - Promoting another region (for disaster recovery) has an Recovery Time Objective (RTO) of < 1 minute
   - **Aurora Machine learning**
     - Enables you to add ML based predictions to your application via SQL
     - Simple, Optimized and secure integration between Aurora and AWS ML Services
     - Supported Services:
       - Amazon Sage maker (use with any ML Model)
       - Amazon Comprehend (for sentiment analysis)
     - You dont need to have ML experience
     - Use Cases: Fraud detection, Ads targeting, Sentiment analysis, product recommendations

## 8.5 ElastiCache

### 8.5.1 Overview
 - Managed Redis/Memcached (Similar offering as RDS, but for caches)
 - In-memory data store, sub millisecond latency
 - Must provision an EC2 instance type
 - Support for Clustering (Redis) and Multi AZ, Read Replicas (sharding)
 - Security through IAM, Security Groups, KMS, Redis Auth
 - Backup/Snapshot/Point in time restore feature
 - Managed and scheduled maintenance
 - **Requires some application code changes to be leveraged**
 - **Use Case:** Key/Value store, Frequent reads, Less writes, Cache results for DB queries, store session data for websites, Cannot use SQL

### 8.5.2 Advanced
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

### 8.5.3 ElastiCache - Cache security
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
 - Gaming Leaderboards are computationally complex
 - Redis sorted sets guarantee both uniqueness and element ordering
 - Each time a new element is added, its ranked in real time, then added in correct order

### 8.5.4 ElastiCache for Redis
 - Amazon ElastiCache is a web service that makes it easy to set up, manage, and scale a distributed in-memory data store or cache environment in the cloud. 
 - **Redis, which stands for Remote Dictionary Server**, is a fast, open-source, in-memory key-value data store for use as a database, cache, message broker, and queue. 
 Redis now delivers sub-millisecond response times enabling millions of requests per second for real-time applications in Gaming, Ad-Tech, Financial Services, Healthcare, and IoT. 
 - Redis is a popular choice for caching, session management, gaming, leaderboards, real-time analytics, geospatial, ride-hailing, chat/messaging, media streaming, and pub/sub apps.
 - All Redis data resides in the server’s main memory, in contrast to databases such as PostgreSQL, Cassandra, MongoDB and others that store most data on disk or on SSDs. In comparison to traditional disk based databases where most operations require a roundtrip to disk, in-memory data stores such as Redis don’t suffer the same penalty. They can therefore support an order of magnitude more operations and faster response times. The result is – blazing fast performance with average read or write operations taking less than a millisecond and support for millions of operations per second.
 - Redis has purpose-built commands for working with real-time geospatial data at scale. You can perform operations like finding the distance between two elements (for example people or places) and finding all elements within a given distance of a point

### 8.5.5 ElastiCache for Memcached
 - Both Redis and MemCached are in-memory, open-source data stores. 
 - Memcached, a high-performance distributed memory cache service, is designed for simplicity while Redis offers a rich set of features that make it effective for a wide range of use cases. Memcached does not offer support for geospatial data.


## 8.6 DynamoDB
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

## 8.7 S3
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
 
## 8.8 DocumentDB
 - Like Aurora is an "AWS implementation" of PostgreSQL/MySQL, DocumentDB is same for MongoDB which is a NOSQL DB 
 - MongoDB is used to store, query and index JSON data
 - Similar "deployment concepts" as Aurora
 - Fully Managed, Highly Available with replication across 3 AZ
 - DocumentDB storage automatically grows in increments of 10GB, upto 64TB
 - Automatically scales to workloads with millions of requests per second

## 8.9 Neptune
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

## 8.10 KeySpaces 
 - Apache cassandra is an opensource NoSQL Database and Keyspaces is a managed Apache cassandra-compatible service
 - Serverless, scalable, Highly available, fully managed by AWS
 - Automatically scale table up/down based on applications traffic
 - Tables are replicated 3 times across multiple AZ
 - Using the Cassandra Query Language (CQL)
 - Single digit milliseconds latency at any scale, 1000s of request per second
 - Capacity: On-Demand mode or provisioned mode with auto scaling
 - Encryption, Backup, Point-In-Time Recovery upto 35 days
 - Use cases: Stre IoT devices info, time series data

## 8.11 Quantum Ledger DB (QLDB)
 - A ledger is a book recording financial transactions
 - Fully managed, serverless, High available, Replication across 3 AZ
 - Used to review history of all the changes made to your application data over time
 - Immutable system: No entry can be removed or modified, cryptographically verifiable
 - 2-3 times better performance than common ledger blockchain frameworks, manipulate data using SQL
 - Difference with Amazon Managed Blockchain: No decentralization in accordance with the financial regulation rules in QLDB

## 8.12 Timestream
 - Fully managed, Fast, Scalable, Serverless time series database
 - Automatically scales up/down to adjust capacity
 - Store and Analyze trillions of events per day
 - 1000s time faster and 1/10th the cost of relationsal database in case we have time series data
 - Scheduled queries, Mult-measure records, SQL COmpaibility
 - Data Storage tiering: recent data kept in memory and historical data kept in a cost-optimized storage data
 - Built in time series analytics function (help you identify patterns in your data in near real time)
 - Encryption in transit and at rest
 - Use cases: IoT apps, operational applications, real time analytics

## 8.13 Exam Guide
**RDS**  
 - RDS: Move from single AZ to Multi AZ
   - How?  
     - Zero down time operation
     - can be done via clicking modify
   - What happens internally?   
     - A snapshot is taken
     - A new DB is restored from snapshot in a new AZ
     - Synchronization is establisdhed between the 2 DB
 - Amazon RDS can automatically back up your database and keep your database software up to date with the latest version. However, RDS does not allow you to access the host OS of the database.
   - RDS Custom for Oracle allows you to access and customize your database server host and operating system, for example by applying special patches and changing the database software settings to support third-party applications that require privileged access. RDS Custom for Oracle facilitates these functionalities with minimum infrastructure maintenance effort. You need to set up the RDS Custom for Oracle in multi-AZ configuration for high availability.
   - https://aws.amazon.com/blogs/aws/amazon-rds-custom-for-oracle-new-control-capabilities-in-database-environment/
 - Multi-AZ replication vs Multi-Region vs read replicas
 - https://cloud.in28minutes.com/aws-certification-multi-az-vs-multi-region-vs-read-replicas
 - Cross Region Multi-AZ replication
 - https://docs.aws.amazon.com/AmazonRDS/latest/UserGuide/Concepts.MultiAZ.html
 - Problem: make the database instance resilient from a disaster recovery perspective
   - Use cross-Region Read Replicas
   - Enable the automated backup feature of Amazon RDS in a multi-AZ deployment that creates backups across multiple Regions
 - Failover is automatically handled by Amazon RDS so that you can resume database operations as quickly as possible without administrative intervention. When failing over, Amazon RDS simply flips the canonical name record (CNAME) for your DB instance to point at the standby, which is in turn promoted to become the new primary. Multi-AZ means the URL is the same, the failover is automated, and the CNAME will automatically be updated to point to the standby database.
 
**Aurora**  
 - For Amazon Aurora, each Read Replica is associated with a priority tier (0-15). 
   - In the event of a failover, Amazon Aurora will promote the Read Replica that has the highest priority (the lowest numbered tier). 
   - If two or more Aurora Replicas share the same priority, then Amazon RDS promotes the replica that is largest in size. 
   - If two or more Aurora Replicas share the same priority and size, then Amazon Aurora promotes an arbitrary replica in the same promotion tier.
**Redis vs Memcached**
 - https://aws.amazon.com/elasticache/redis-vs-memcached/

# 9. AWS Monitoring, Troubleshooting & Audit
 - (CloudWatch, X-ray and CloudTrail)

## 9.1 Cloudwatch Metrics
 - Cloudwatch provides metrics for every services in AWS
 - Metric is a variable to monitor (EC2: CPU Utilization, Networking, S3: Bucket size)
 - Metric belong to namespaces
 - Dimension is an attribute of a metric (instance id, environment, etc)
 - Upto 10 dimenstions per metric
 - Metrics have timestamps
 - Can create CloudWatch dashboard of metrics
 - Can create Cloudwatch custom metrics (Example: For the RAM)

## 9.2 CloudWatch Metric Streams
 - Continuosly stream CloudWatch metric to a destination of your choice, with near-real-time delivery and low latency
   - Amazon Kinesis Data Firehose (and then its destinations)
   - 3rd party service provider: Dynatrace, DataDog, New relic, Splunk, Sumo Logic
 - Option to filter metrics to only stream a subset of them

## 9.3 CloudWatch Logs

### 9.3.1 Overview
 - **Log Groups:** Arbitrary name, Ususally representing an application
 - **Log Stream:** Instances within application/log files/containers
 - Can define log expiration policies (never expire, 30 days, etc)
 - CloudWatch logs can end logs to:
   - Amazon S3 (exports)
   - Kinesis Data streams
   - Kinesis Data Firehose
   - AWS Lambda
   - ElasticSearch

### 9.3.2 Sources
 - SDK, CloudWatch logs Agent (older), CloudWatch Unified agent (newer)
 - ElasticBeanstalk: Collection of logs from application
 - ECS: Collection from containers
 - AWS Lambda: Collection from function logs
 - VPC Flow logs: VPC Specific logs
 - API Gateway
 - CloudTrail based on filter
 - Route53: Log DNS queries

### 9.3.3 Metric Filter and Insights
 - CloudWatch Logs can use filter expressions
   - For Example: Find a specific IP inside of a log
   - Or Count occurances of "Error" in your logs
 - Metric Filters can be used to trigger CloudWatch alarms
 - CloudWatch Logs Insights can be used to query logs and add queries to CloudWatch Dashboards

### 9.3.4 CloudWatch Logs - S3 Export
 - Flow: CloudWatch Logs -> S3 
 - Log data can take upto 12 hours to become available for export
 - The API call is `CreateExportTask`
 - Not near-real time or real-time , use logs subscription instead
 
### 9.3.5 CloudWatch Logs Subscription
 - TBD
 
### 9.3.6 CloudWatch Logs Aggregation (Multi Account and Multi Region)
 - TBD

### 9.3.7 CloudWatch Logs for EC2
 - By default, No Logs from your EC2 machine will go to CloudWatch
 - You need to run a CloudWatch agent on EC2 to push the log files you want
 - Make sure IAM permissions are correct
 - The Cloudwatch Log agent can be setup on-premises too

### 9.3.8 CloudWatch logs Agent (older), Unified agent (newer)
 - For virtual servers (EC2 instances, On-premise servers)
 - CloudWatch Logs Agent:
   - Old version of the Agent
   - Can only send to cloudwatch logs
 - CloudWatch Unified Agent
   - Collect Additional system-level metrics such as RAM, Processes, etc
   - Collect logs to send to CloudWatch Logs
   - Centralized configuration using SSM Parameter Store
   - Metrics:
     - Collected directly  on your Linux server/EC2 instance
     - CPU (active, guest, idle, system, user, steal)
     - Disk Metrics (Free, used, total), Disk IO (Writes, reads, bytes, iops)
     - RAM (free, inactive, used, total, cached)
     - Netstat (Number of TCP and UDP connections, net packets, bytes)
     - Processes (total, dead, bloqued, idle, running, sleep)
     - Swap Space (free, used, used %)
   - Reminder: out of the box metrics for EC2 - disk, CPU, Network (high level)

## 9.4 CloudWatch Alarms

### 9.4.1 Overview
 - Alarms are used to trigger notifications for any metric
 - Various Options (Sampling, %, Max, Min, etc)
 - Alarm states
   - OK
   - INSUFFICIENT_DATA
   - ALARM
 - Period:
   - Length of time in seconds to evaluate the metric
   - High resolution custom metrics: 10 sec, 30 sec or multiple of 60 sec

### 9.4.2 CloudWatch Alarm Targets
 - Stop, Terminate, Reebot or recover an EC2 instance
 - Trigger Autoscaling action
 - Send notifications to SNS (From which you can do pretty much anything)

### 9.4.3 EC2 instance Recovery
 - Status check
   - instance status = check the EC2 VM
   - system status = check the underlying hardware
 - Recovery: 
   - While recovering, you get same private, public, Elastic IP, Metadata, Placement group
   - Can also send alarm/notification to SNS Topic

### 9.4.4 CloudWatch Alarm: Good To Know
 - Alarms can be created based on CloudWatch Logs Metrics Filters
 - To test alarms and notifications, set the Alarm state to Alarm using CLI
 - `aws cloudwatch set-alarm-state --alarm-name "myalarm" --state-value ALARM --state-reason "testing purpose"`

## 9.5 CloudWatch Insights

### 9.5.1 CloudWatch Container Insights
 - Collect, Aggregate, summarize metrics and logs from containers
 - Available for containers on:
   - Amazon Elastic container services (Amazon ECS)
   - Amazon Elastic kubernetes services (Amazon EKS)
   - Kubernetes platforms on EC2
   - Fargate (both for ECS and EKS)
 - In Amazon EKS and Kubernetes, CloudWatch insights is using a containerized version of the CloudWatch agent to discover containers

### 9.5.2 CloudWatch Lambda Insights
 - Monitoring and troubleshooting solution for serverless applications running on AWS Lambda
 - Collects, Aggregates and summarizes system-level metrics including CPU time, Memory, Disk and network
 - Collects, Aggregates, and summarizes diagnostic information such as cold starts and Lambda worker shutdowns
 - Lambda insights is provided as a Lambda layer

### 9.5.3 CloudWatch Contributor Insights
 - Analyze log data and create time series that display contributor data
   - See metrics about the top-N tier contributors
   - The total number of unique contributors and their usage
 - This helps you find top talkers and understand who or what is impacting system performance
 - Works for any AWS-generated logs (VPC, DNS, etc)
 - For example: you can find bad hosts, identify the heaviest network users, or find the URLs that generate the most errors
 - You can build your rules from scratch, or you can also use sample rules that AWS has created - leverages your cloudwatch logs 
 - CloudWatch also provides built-in rules that you can us to analyze metrics from other AWS services

### 9.5.4 CloudWatch Application Insights
 - Provides automated dashboards that show potential problems with monitored applications, to help isolate ongoing issues
 - Your application run on Amazon EC2 instances with select technologies only (Java, .Net, Microsoft IIS Web server, databases)
 - And you can use other AWS resources such as Amazon EBS, RDS, ELB, ASG, Lambda, SQS, DynamoDB, S3 bucket, ECS, EKS, SNS, API Gateway, etc
 - It is powered by SageMaker
 - Enhanced visibility into your application health to reduce the time it will take you to troubleshoot and repair your applications
 - Findings and alerts are sent to Amazon EventBridge and SSM OpsCenter

### 9.5.5 CloudWatch Insights and Operational Visibility Summary
 - CloudWatch container insights
   - ECS, EKS, Kubernetes on EC2, Fargate, needs agent for kubernetes
   - Metrics and logs
 - CloudWatch Lambda insights
   - Detailed metrics to troubleshoot serverless applications
 - CloudWatch Contributor insights
   - Find "Top-N" contributors through CloudWatch logs
 - CloudWatch application logs
   - Automatic dashboard to troubleshoot your application and related aws services

## 9.6 EventBridge (Formerly CloudWatch Events)

### 9.6.1 Overview
 - Schedule: cron jobs (scheduled scripts)
   - Schedule every hour -> Trigger script on Lambda function
 - Event Pattern: Event rules to react to a service doing something
   - "IAM Root user sign in Event" -> SNS topic with EMAIl notification
 - Trigger Lambda functions, Send SQS/SNS messages
 - Types:
   - Default EventBus
   - Partner EventBus
   - Custom EventBus
 - Event buses can be accessed by other AWS accounts using Resource-based policies
 - You can archieve events (all/filter) sent to an event bus (indefinitely or set period)
 - Ability to replay archieved events

### 9.6.2 EventBridge Rules
 - TBD

### 9.6.3 EventBridge Schema Registry
 - EventBridge can analyze the events in your bus and infer the schema
 - The Schema Registry allows you to generate code for your application, that will know in advance how data is structured in the event bus
 - Schema can be versioned

### 9.6.4 EventBridge - Resource based policy
 - Manage permissions for a specific event bus
 - Example: allow/deny events from another AWS account or Region
 - Use case: Aggregate all events from your AWS organizations in a single aws account or region

## 9.7 CloudTrail

### 9.7.1 CloudTrail Overview
 - Provides governance, compliance and audit for your AWS account
 - CloudTrail is enabled by default!
 - Get an history of events/API calls made within your AWS account by:
   - Console
   - SDK
   - CLI
   - AWS Services
 - Can put logs from CloudTrail into CloudWatch Logs or S3
 - A trail can be applied to all regions (default) or single Region
 - If a resource is deleted in AWS, investigate CloudTrail first!

### 9.7.2 CloudTrail Events
 - Management Events
   - Operations that are performed in your AWS account
   - Examples:
     - Configuring security (IAM AttachRolePolicy)
     - Configuring roles for routing data (Amazon EC2 CreateSubnet)
     - Setting up logging (AWS CloudTrail CreateTrail)
   - By default, trails are configured to log management events
   - Can separate Read Events (that don't modify resources) from Write Events (that modify resources)
 - Data Events
   - By default, data events are not logged (because high volume operations)
   - Amazon S3 object-level activity (ex: GetObject, DeleteObject, PutObject): Can separate Read and Write events
   - AWS Lambda function execution activity (the Invoke API)
 - CloudTrail Insights Events
   - Enable CloudTrail insights to detect unusual activity in your account
     - inaccurate resource planning
     - Hitting service limits
     - Bursts of AWS IAM actions
     - Gaps in periodic maintenance activity
   - CloudTrail Insights analyzes normal management events to create a baseline
   - and then continously analyzes Write events to detect unusual patterns
     - Anomalies appear in CloudTrail console
     - Event is sent to Amazon S3
     - An EventBridge event is generated (for automation needs)
   - CloudTrail event retention:
     - Events are stored for 90 days in CloudTrail
     - To keep events beyond this period, log them to S3 and use Athena
   - Usecase: CloudTrail integration with EventBridge -> to intercept API calls

## 9.8 AWS Config 

### 9.8.1 Overview
 - Helps with auditing and recording compliance of your AWS resources
 - Helps record configurations and changes over time
 - Questions that can be solved with AWS config:
   - Is there unrestricted SSH access to my security groups?
   - Do my buckets have any public access?
   - How has my ALB configuration changed over time?
 - You can receive alerts (SNS notifications) for any change
 - AWS config is a per region service
 - Can be aggregated across regions and accounts
 - possibility of storing the configuration data into s3 (analyzed by athena)

### 9.8.2 Config rules
 - Can use AWS managed config rules (over 75)
 - Can make custom config rules (Must be defined in AWS Lambda)
   - Ex: Evaluate if each EBS disk is of type gp2
   - Ex: Evaluate if each EC2 instance is t2.micro
 - Rules can evaluated/triggered
   - For each config change
   - And/or at regular time intervals
 - AWS config rules does not prevent actions from happening (no deny)
 - Pricing: no free tier; $0.003 per onfiguration item recorded per region, $0.001 per config rule evaluation per region
 
### 9.8.3 Config
 - View compliance of a resource over time
 - View configuration of a resource over time
 - View CloudTrail API calls of a resource over time

### 9.8.4 Config rules - remediations
 - Automate Remediation of non-compliant resources using SSM automation Documents
 - Use AWS-Managed automation Documents or Create custom Automation Documents
   - Tip: you can create custom automation documents that invokes lambda functions
 - You can set Remediation Retries if the resource is still non-compliant after auto-remediation

### 9.8.5 Config rules - notifications
 - Use EventBridge to trigger notifications when AWS resources are non-compliant
 - Ability to send configuration changes and complaince state notifications to SNS (All events - use SNS filtering or filter at client side)

## 9.9 CloudWatch vs CloudTrail vs Config

### 9.9.1 Comparison
 - CloudWatch
   - Performance monitoring (Metrics, CPU, Network, etc) & Dashboards
   - Events and alerting
   - Log Aggregation and Analysis
 - CloudTrail
   - Record API Calls made within your account by everyone
   - Can define trails for specific resources
   - Global Services
 - Config
   - Record Configuration changes
   - Evaluate resources against compliance rules
   - Get timeline of changes and compliance

### 9.9.2 Comparing with an example for an Elastic LoadBalancer
 - CloudWatch
   - Monitoring Incoming connections metric
   - Visualize error codes as a % over time
   - Make a dashboard to get an idea of your load balancer performance
 - Config
   - Track security group rules for the Load balancer
   - Track configuration for the load balancer
   - Ensure an SSL certificate is always assigned to the Load Balancer (Compliance)
 - CloudTrail
   - Track who made any changes to the load balancer config

### 9.9.3 CloudWatch vs CloudTrail vs Config. 
 - **Think resource performance monitoring, events, and alerts;** think CloudWatch.
 - **Think account-specific activity and audit;** think CloudTrail.
 - **Think resource-specific history, audit, and compliance;** think Config.

## 9.10 Exam Guide
**CloudWatch**  
 - Using Amazon CloudWatch alarm actions, you can create alarms that automatically **stop, terminate, reboot, or recover** your EC2 instances. You can use the stop or terminate actions to help you save money when you no longer need an instance to be running. You can use the reboot and recover actions to automatically reboot those instances or recover them onto new hardware if a system impairment occurs.
 - You can create an Amazon CloudWatch alarm that monitors an Amazon EC2 instance and automatically reboots the instance. The reboot alarm action is recommended for Instance Health Check failures (as opposed to the recover alarm action, which is suited for System Health Check failures)

# 10. Data and Analytics

## 10.1 Athena

### 10.1.1 Overview
 - Serverless query service to analyze data stored in Amazon S3
 - Uses standard SQL Language to query the files (built on Presto)
 - Supports CSV, JSON, ORC, Avro, Parquet
 - Pricing: 5$ per TB of data scanned
 - Commonly used with Amazon QuickSight for reporting/dashboards
 - Use Cases: Business Intelligence/ Analytics/ Reporting Analyze and query VPC flow logs, ELB Logs, CloudTrail trails, etc
 - Examp Tip: Analyze data in S3 using serverless SQL, use Athena
 
### 10.1.2 Improve performance while using Athena Tips
 - Use Columnar data for cost savings (less scans)
   - Apache Parquet or ORC is recommended
   - Huge Performance improvement
   - Use Glue to convert your data into Parquet or ORC
 - Compress data for smaller retrieval (bzip2, gzip, iz4, snappy, zlip, zstd)
 - Partition datasets in S3 for easy querying on virtual columns
```cmd  
  s3://yourBucket/pathToTable/
                           /<PARTITION_COLUMN_NAME>=<VALUE>
                            /<PARTITION_COLUMN_NAME>=<VALUE>                             
  
  Example: s3://athena-examples/flights/parquet/year=1991/month=10/day=1
```
 - Use Larger files  (>128MB) to minimize overhead

## 10.2 RedShift

### 10.2.1 Overview
 - RedShift is based on PostgreSql, but its not used for OLTP
 - Its OLAP - Online Analytical Processing (Analytics and data warehousing)
 - 10x better performance than other data warehouses, scale to petabytes of data
 - Columnar storage of data (instead of row based) and parallel query engine
 - Pay as you go based on the instances provisioned
 - Has a SQL interface for performing the queries
 - BI Tools such as AMazon QUickSight or Tableau integrate with it
 - Redshift vs Athena: faste queries/joins/aggregations thanks of indexes

### 10.2.2 RedShift Cluster
 - Leader Node: for query planning, results aggregation
 - Compute Node: for performing the queries, send results to leader
 - You provision the node size in advance
 - You can use reserved instances for cost savings

### 10.2.3 Snapshots and Disaster recovery
 - Redshift as no Multi-AZ mode
 - Snapshots are Point in Time backups of a cluster, stored internally in S3
 - Snapshots are incremental (Only what has changed is saved)
 - You can restore a snapshot into a new cluster
 - Automated: Every 8 hours, Every 5 GB, or on a schedule. Set Retention
 - Manual: Snapshot is retained until you delete it
 - You can configure Amazon Redshift to automatically copy snapshots (Automated or Manual) of a cluster to another AWS Region

### 10.2.4 Loading data into Redshift: Large inserts are much better
 - Amazon Kinesis Data Firehose
   - Kinesis Data Firehose to Redshift cluster (through S3 copy)
 - S3 using COPY command
   - Through Internet without using Enhanced VPC routing
   - Through VPC with Enhanced VPC routing
 - EC2 instance JDBC driver
   - Ec2 instance to Amazon Redshift cluster (Better to write data in batches)

### 10.2.5 Redshift Spectrum
 - Query data that is already in S3, without loading it
 - Must have a Redshift cluster available to start the query
 - The query is then submitted to thousands of Redshift spectrum nodes

## 10.3 OpenSearch

### 10.3.1 Overview
 - Amazon Opensearch is successor to ElasticSearch and name change was due to some licensing issue
 - In DynamoDB, queries only exist by primary keys or indexes
 - With OpenSearch, you can search any field, even partially matches
 - Its common to use OOpenSearch as a Complement to another database
 - OpenSearch requires a cluster of instances (not serverless)
 - Does not support SQL (It has its own query language)
 - Ingestion from Kinesis data firehose, AWS IOT, and CLoudwatch Logs
 - Security through Cognito and IAM, KMS encryption, TLS
 - Comes with OpenSearch Dashboard (Visualization)

### 10.3.2 OpenSearch Patterns 
 - DynamoDB 
   - CRUD -> DynamoDB Table -> DynamoDB Stream -> Lambda -> Amazon Opensearch -> API to search items -> and finally the details data is fetched from DynamoDB by using the id returned from ElasticSearch
 - CloudWatch Logs
   - CloudWatch Logs -> Subscription filter -> Lambda Function -> OpenSearch (Real time)
   - CloudWatch Logs -> Subscription filter -> Kinesis data firehose -> OpenSearch (Near Real time)
 - Kinesis Data streams and data Firehose

## 10.4 EMR

### 10.4.1 Overview
 - EMR stands for Elastic MapReduce
 - EMR helps creating Hadoop clusters (Big Data) to analyze and process vast amount of data
 - The clusters can be made of hundreds of EC2 instances
 - EMR comes bundled with Apache Spark, HBase, Presto, Flink
 - EMR takes care of all the Provisioning and configuration
 - Auto-scaling and Integrated with spot instances
 - Use Cases: Data processing, Machine learning, Web Indexing, Big Data

### 10.4.2 Node types and purchasing
 - Master Node: Manage the cluster, coordinate, manage health - long running
 - Core Node: Run Tasks and store data - long running
 - Task Node (Optional): Just to run tasks  - usually spot
 - Purchasing Options:
   - On demand: reliable, predictable, wont be terminated
   - Reserved (Minimum 1 year): Cost savings (EMR will automatically use if available)
   - Spot instances: Cheaper, can be terminated, less reliable
 - Can have long running cluster, or transient (temporary) cluster

## 10.5 QuickSight

### 10.5.1 Overview
 - Serverless machine learning powered business intelligence service to create interactive dashboards
 - Fast, automatically scalable, embeddable, with per session pricing
 - Use cases:
   - Business Analytics
   - Building Visualizations
   - Perform Ad-hoc analysis
 - Integrated with RDS, Aurora, Athena, Redshift, S3
 - In Memory computation using SPICE engine if data is imported into QuickSight
 - Enterprise edition: Possibility to setup Column level Security (CLS)
 
### 10.5.2 Integrations
 - AWS Services
   - RDS, Aurora, RedShift, Athena, S3, OpenSearch, TimeStream
 - Data Sources (SaaS)
   - Salesforce, Jira, etc
 - Third party databases
   - On-premises databases (JDBC)
   - Teradata, 
 - Imports via data sources
   - XLSX, CSV, JSON, .TSV, ELF and CLF (Log format)
   
### 10.5.3 Dashboards and Analysis
 - Define users (standard versions) and Groups (enterprise versions)
   - These users and groups exist within Quicksight, not IAM !!
 - A Dashboard
   - is a readonly snapshot of an analysis that you can share
   - preserves the configuration of the analysis (Filtering, parameters, control, sort)
 - You can share the Analysis or the dashboards with Users or Groups
 - To share a dashboard, you must first publish it
 - Users who see the dashboard can also see the underlying data

## 10.6 Glue

### 10.6.1 Overview
 - Managed Extract, Transform and Load (ETL) service
 - Useful to prepare and transform data for analytics
 - Fully serverless service
 - Glue Job Bookmarks: prevent reprocessing old data
 - Glue Elastic views: 
   - Combine and replicate data across multiple data stores using SQL
   - No custom code, Glue monitors for changes in the source data, serverless 
   - Leverages a "Virtual table" (Materialized view)
 - Glue DataBrew: Clean and Normalize data using pre-built transformation
 - Glue Studio: new GUI to create, run and monitor ETL jobs in Glue
 - Glue streaming ETL (built on apache spark structured streaming): Compatible with Kinesis data streaming, Kafka, MSK (managed kafka)

### 10.6.2 Convert dsta into Parquet format
 - TBD

### 10.6.3 Glue data catalog: catalog of datasets
 - 

## 10.7 Lake formation
 - Data Lake = central place to have all your data for analytics purpose
 - Fully Managed service that makes it easy to setup a data lake in days
 - Discover, Cleanse, transform and ingest data into your data lake
 - It automates many complex manual steps (Collecting, Cleansing, moving, cataloging data, etc) and de-duplicate (using ML transforms)
 - Combine structured and unstructured data in the data lake
 - Out of the box source blueprints: S3, RDS, Relational and NoSQL DB
 - Fine grained Access control for your applications (row and column level)
 - Built on top of AWS Glue
 - Use case: Centralized permissions example

## 10.8 Kinesis Data analytics

### 10.8.1 Kinesis Data analytics For SQL Applications
 - Real time analytics for Kinesis data streams and firehose using SQL
 - Add reference data from Amazon S3 to enrich data streaming data
 - Fully managed, no servers to provision
 - Automatic scaling
 - Pay for actual consumption rate
 - Output:
   - Kinesis Data streams: create streams out of the real-time analytics queries
   - Kinesis data firehose: send analytics quey results to destinations
 - Use cases:
   - Time series analytics
   - Real time dashboards
   - Real time metrics
 
### 10.8.2 Kinesis Data analytics For Apache Flink
 - Use Flink (Java, Scala or SQL) to process and analyze streaming data
 - Run any Apache Flink application on a managed cluster on AWS
   - provisioning compute resources, parallel computation, automatic scaling
   - application backups (implemented as checkpoints and snapshots)
   - Use any apache flink programming features
   - Flink does not read from Firehose (Use Kinesis Analytics for SQL instead)   

## 10.9 MSK - Managed Streaming for Apache Kafka

### 10.9.1 Overview
 - Alternate to Amazon kinesis
 - Fully managed Apache Kafka on AWS
   - Allow you to create/update/delete clusters
   - MSK creates and manages kafka broker nodes and zookeeper nodes for you
   - Deploy the MSK cluster in your VPC, multi-AZ (up to 3 for High Availability)
   - Automatic recovery from common apache kafka failures
   - Data is tored on EBS volumes for as long as you want
 - MSK serverless
   - Run Apache kafka on MSK without Managing the capacity
   - MSK automatically provisions resources and scales compute and storage

### 10.9.2 Kinesis data streams vs MSK
 - Kinesis data streams
   - 1 MB message size limit
   - Data streams with shards
   - shard splitting and Merging
   - TLS in-flight encryption
   - KMS at-rest encryption
 - MSK
   - 1 MB default, configure for higher (Ex: 10 MB)
   - kafka topics with partitions
   - can only add partitions to a topic
   - PLAINTEXT or TLS in-flight encryption
   - KMS at-rest encryption

### 10.9.3 MSK cosumers
 - Kinesis data analytics for Apache Flink
 - AWS Glue, Streaming ETL Jobs, Powered by Apache spark streaming
 - Lambda
 - Custom (Applications running on EC2, ECS, EKS)

## 10.10 Big Data ingestion pipelines

### 10.10.1 Requirements
 - We want the ingestion pipeline to be fully serverless
 - We want to collect data in real time
 - We want to transform the data
 - We want to query the transformed data using SQL
 - The reports created using the queries should be in S3
 - We want to load the data into a warehouse and create dashboards

### 10.10.2 Solution
 - IOT core allows you to harvest data data from IoT devices
 - Kinesis is great for real-time data collection
 - Firehose helps with data delivery to S3 in near real-time (1 minute)
 - Lambda can help Firehose with data transformations
 - Amazon S3 can trigger notifications to SQS
 - Lambda can subscribe to SQS (We could have connector S3 to Lambda)
 - Athena is a serverless SQL service and results are stored in S3
 - The reporting bucket contains analyzed data and can be used by reporting tool such as AWS quicksight, redshift etc

# 11. Messaging and Integration 

## 11.1 Kinesis

### 11.1.1 Overview
 - Makes it easy to collect, process and analyze streaming data in real time
 - Injest real time data such as: Application logs, Metrics, Website cickstreams, IOT telemetry data
 - **Kinesis data streams:** Capture, process and store data streams
 - **Kinesis data firehose:** load data streams into AWS data stores
 - **Kinesis data Analytics:** analyze data streams with SQL of Apache Flink
 - **Kinesis video streams:** Capture, Process and sore data streams

## 11.2 Kinesis data streams

### 11.2.1 Overview
 - Stream bigdata into the systems
 - Made up of multiple shards
 - Producers, Records, partition keys, Data Blob, consumers 
 - Retention between 1 to 365 days
 - Ability to express (replay) data
 - Once data is inserted in Kinesis, It can't be deleted (immutability)
 - Data that shares the same partition goes to the same shard (ordering)
 - Producers: 
    - AWS SDK, Kinesis Producer Library (KPL), Kinesis Agent
 - Consumers: 
    - Write your own: Kinesis Client Library
    - Managed: Lambda, Kinesis Data Firehose, Kinesis Data Analytics

### 11.2.2 Capacity Modes
 - **Provisioned modes:**
   - You chose the number of shards provisioned, scale manually or using API
   - Each shard gets 1MB/s in (or 1000 records per second)
   - Each shard gets 2 MB/s out (classing or enhances fan-out consumer)
   - You pay per shard provisioned per hour

 - **On-demand mode:**
   - No need to manage or provision the capacity
   - Default capacity provisioned (4 MB/s in or 4000 records per second)
   - Scales automaticall based on the observed throughput peak during the last 30 days
   - Pay per stream per hour & data in/out per GB

### 11.2.3 Security
 - Control Access/authorization using IAM policies
 - Encryption in flight using HTTPS endpoints
 - Encryption at rest using KMS
 - You can implement encryption/decryption of data on client side (harder)
 - VPC endpoints available for kinesis to access within VPC
 - Monitor API calls using CloudTrail

### 11.2.4 Use Cases
 - Routing related records to the same record processor (as in streaming MapReduce). For example, counting and aggregation are simpler when all records for a given key are routed to the same record processor.
 - Ordering of records. For example, you want to transfer log data from the application host to the processing/archival host while maintaining the order of log statements.
 - Ability for multiple applications to consume the same stream concurrently. For example, you have one application that updates a real-time dashboard and another that archives data to Amazon Redshift. You want both applications to consume data from the same stream concurrently and independently.
 - Ability to consume records in the same order a few hours later. For example, you have a billing application and an audit application that runs a few hours behind the billing application. Because Amazon Kinesis Data Streams stores data for up to 365 days, you can run the audit application up to 365 days behind the billing application.

## 11.3 Kinesis data Firehose

### 11.3.1 Overview
 - Fully managed service, no administration, automatic scaling, serverless
 - Pay for data going through Firehose
 - Near RealTime
   - 60 seconds latency minimum for non full batches
   - or minimum 1 MB of data at a time
 - Supports many data formats, conversions, transformations, compression
 - Supports custom data transformations using AWS Lambda
 - Can send failed or all data to a S3 bucket
 
### 11.3.2 Different participants
 - Producers: 
    - Applications, Client, AWS SDK, Kinesis Producer Library (KPL), Kinesis Agent, Kinesis Data streams, Amazon Cloudwatch (Logs and Events), AWS IOT 
 - Transferred data is called **record** and it can be upto 1MB
 - Data Transfomations: Firehose can optionally use Lambda to transform the data
 - Consumers:
  - AWS COnsumers:
    - S3
    - Redshift (Warehouse DB) - it copies through S3
    - Elastic Search
  - Third Party:
    - Datadog
    - Splunk
    - New Relic
    - MongoDB
  - Custom Destinations:
    - HTTP Endpoint
 - Data backup:
    - All 
    - failed records

### 11.4 Kinesis Data Streams vs Firehose
**Data Streams:**
 - Streaming service for ingestion at scale
 - Write custom code (producer/consumer)
 - Real time (~200ms)
 - Manage Scaling (shard splitting/merging)
 - Data storage for 1 to 365 days
 - Supports replay capability
 
**Firehose:**
 - Load streaming data into S3,Redshift, Elastic search, 3rd party, Custom HTTP
 - Fully managed
 - Near real time (buffer time minimum 60 seconds)
 - Automatic scaling
 - No data storage
 - does not support replay capability
 
## 11.5 Exam Guide
 - Kinesis Agent cannot write to a Kinesis Firehose for which the delivery stream source is already set as Kinesis Data Streams
   - Amazon Kinesis Data Firehose is the easiest way to reliably load streaming data into data lakes, data stores, and analytics tools. It is a fully managed service that automatically scales to match the throughput of your data and requires no ongoing administration. It can also batch, compress, transform, and encrypt the data before loading it, minimizing the amount of storage used at the destination and increasing security.
   - When a Kinesis data stream is configured as the source of a Firehose delivery stream, Firehose’s `PutRecord` and `PutRecordBatch` operations are disabled and Kinesis Agent cannot write to Firehose delivery stream directly. Data needs to be added to the Kinesis data stream through the Kinesis Data Streams PutRecord and PutRecords operations instead.

# AWS Encryption

## What is Encryption?
 - TBD

## Types
 - In Flight
 - At rest - server side
 - client side
 
## In Flight Encryption
 - Data is encrypted before sending and dcrypted after receiving 
 - SSL certificates help with encryption (HTTPS)
 - Encryption in flight ensures no MITM (Man in the middle) can happen

## Server side Encryption At rest
 - Data is encrypted after being received by the server
 - Data is decrypted before being sent
 - It is stored in an encrypted form thanks to a key (usually a data key)
 - The encryption/decryption keys must be managed somewhere and the server must have access to it

## Client side
 - Data is encrypted by the client and never decrypted by the server
 - Data will be decrypted by a receiving client
 - The server should not be able to decrypt the data
 - Could leverage envelope encryption
  
## KMS

### Overview
 - Any time you hear "encryption" for an AWS service, Its most likey KMS
 - AWS manages encryption keys for us
 - Fully integrated with IAM for authorization
 - Easy way to control access to your data
 - Able to audit KMS key usage using CloudTrail
 - Seamlessly integrated into most AWS services (EBS, S3, RDS, SSM)
 - Never ever store your secrets in plaintext, especially in your code
   - KMS key encryption also available therough API calls (SDK, CLI)
   - Encrypted secrets can be stored in the code/environment variables
 
### KMS key types
 - KMS keys is the new name of "KMS customer master key"
 - Symmetric (AES-256 keys)
   - Single encryption key that is used to Encrypt and Decrypt
   - AWS services that are integrated with KMS use Symmetric CMKs
   - You may never get access to the KMS key unencrypted (must call KMS APi to use)
 - Asymmetric (RSA and ECC key pairs)
   - Public (Encrypt) and private (Decrypt) key pair
   - Used for Encrypt/decrypt or sign/verify operations
   - The public key is downloadable, but you cant access th private key unencrypted
   - Use case: encryption outside of AWS by users who cant call the KMS API
 - Three types:
   - AWS Managed key: free (aws/service-name. example: aws/rds or aws/ebs)
   - Customer Managed Keys (CMK) created in KMS: $1/month
   - Customer Managed Keys imported (must be 256 bit symmetric key): $1/month
 - Plus pay for API calls to KMS ($0.03/10,000 calls)   
 - Automatic key rotation
   - AWS Managed KMS key: automatic every 1 year
   - Customer Managed KMS key: (must be enabled) automatic every 1 year
   - Imported KMS key: only manual rotation possible using alias

### KMS key policies
 - Control access to KMS keys, "similar" to S3 bucket policies
 - Difference: You cannot control access without them
 - Default KMS key policy:
   - Created if you dont provide a specific KMS key policy
   - Complete access to the key to the root user = entire AWS account
   - Useful for cross-accout access of your KMS key
 - Copying Snapshots across accounts
   - Create a Snapshot, encrypted with your own KMS key (Customer managed key)
   - Attach a KMS key policy to authorize cross-account access
   - Share the encrypted snapshot
   - (in target) Create a copy of the snapshot, encrypt with a CMK in your account
   - Create a volume from the snapshot

### KMS Multi region keys 
 - Identical KMS keys in different AWS Regions that can be used interchangeably
 - Multi-Region keys have the same key ID, key material, automatic rotation 
 - Encrypt in one region and decrypt in other region
 - No need to re-encrypt or making cross-region API calls
 - KMS Multi-Region are not global (primary + replicas)
 - Each Multi-Region key is managed independently
 - Use cases: Global client-side encryption, Encryption on Global DynamoDB, Global Aurora
 - DynamoDB Global tables and KMS Multi-Region keys client side encryption
   - We can encrypt specific attributes client-side in our DynamoDB Encryption client
   - Combined with global tables, the client side encrypted data is replicated to other regions
   - If we use a multi region key, replicated in the same region as the DynamoDB Global table, then clients in these regions can use low-latency API calls to KMS in their region to decrypt the data client-side
   - Using Client-side encryption we can protect specific fields and guarantee only decryption if the client has access to an API key 
 - Global Aurora and KMS Multi-Region keys client side encryption
   - We can encrypt specific attributes client-side in our AUrora table using the AWS Encryption key
   - Combined with Aurora Global tables, the client-side encrypted data is replicated to other regions
   - If we use a multi region key, replicated in the same region as the Global Aurora DB, then clients in these regions can use low-latency API calls to KMS in their region to decrypt the data client-side
   - Using client-side encryption we can protect specific fields and guarantee only decryption if the client has access to an API key, we can protect specific fiels even from database admins

## S3 replication Encryption considerations
 - Unencrypted objects and objects encrypted with SSE-S3 are replicated by default
 - Objects encrypted with SSE-C (Customer provided key) are never replicated
 - For objects encrypted with SSE-KMS, you need to enable this option
   - specify which KMS key to encrypt the objects within the target bucket
   - Adapt the KMS key policy for the target keys
   - An IAM Role with `kms:Decrypt` for the source KMS key and `kms:Encrypt` for the target KMS key
   - You might get KMS throttling errors, in which case you can ask for a service quotas increase
   - You can use multi region AWS KMS keys, but they are currently treated as independent keys by Amazon S3 (Hence, Object will still be decrypted and encrypted again)

## Encrypted AMI (Amazon Machine Image) sharing option
 - AMI in source account is encrypted with KMS key from source account
 - Must modify the image attribute to add a Launch Permission which corresponds to the specified target AWS account
 - Must share the KMS keys used to encrypt the snapshot of AMI references with the "target account/IAM Role" 
 - The IAM Role/User in the target account must have the permissions to `DescribeKey`, `ReEncrypted`, `CreateGrant`, `Decrypt`
 - When Launching an EC2 instance from the AMI, optionally the target account can specify a new KMS key in its own account to re-encrypt the volumes

## Exam Guide:
 - AWS KMS is a secure and resilient service that uses hardware security modules that have been validated under FIPS 140-2
 - Deleting a customer master key (CMK) in AWS Key Management Service (AWS KMS) is destructive and potentially dangerous. 
   - Therefore, AWS KMS enforces a waiting period. To delete a CMK in AWS KMS you schedule key deletion. 
   - You can set the waiting period from a minimum of 7 days up to a maximum of 30 days.
   - The default waiting period is 30 days. During the waiting period, the CMK status and key state is Pending deletion. 
   - To recover the CMK, you can cancel key deletion before the waiting period ends. After the waiting period ends you cannot cancel key deletion, and AWS KMS deletes the CMK.

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

## Exam Guide - CloudFront
 - Global Accelerator
   - AWS Global Accelerator is a network layer service that directs traffic to optimal endpoints over the AWS global network, this improves the availability and performance of your internet applications.


# Route 53

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
 - https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/routing-policy.html

#### Routing Policies - Simple



### Domain Registrar vs DNS Service

## Exam Guide - Route 53
 - Negative points
   - While relying on the DNS service is a great option for blue/green deployments, it may not fit use-cases that require a fast and controlled transition of the traffic. Some client devices and internet resolvers cache DNS answers for long periods




# AWS Security
 
## AWS Systems Manager (SSM) parameter store

### Overview
 - Secure storage for configuration and secrets
 - Optional seamless Encryption using KMS
 - Serverless, Scalable, Durable, Easy SDK
 - Version tracking of Configurations/Secrets
 - Configuration management using Path & IAM
 - Notifications with CloudWatch Events
 - Integration with CloudFormation

### SSM Parameter Store Hierarchy
 - /my-department/
   - my-app/
     - dev/
	   - db-password
	   - db-user
     - prod/
	   - db-password
	   - db-user
   - other-app/
 - /other-department/
 - /aws/reference/secretsmanager/secret_ID_in_Secrets_Manager
 - /aws/service/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2: this will get latest Amazon AMI
 - Example: Lambda will get data by `GetParameters` or `GetParametersByPath` API

### Parameter Policies (for Advanced Parameters only)
 - Allow to assign TTL to a parameter (expiration date) to force updating or deleting sensitive data such as passwords
 - Can assign multiple policies at a time
 - Sample policies:
   - Expiration (to delete a parameter)
   - ExpirationNotification (CW Events)
   - NoChangeNotification (CW Events)
 
## AWS Secrets Manager

### Overview
 - Newer service meant for storing secrets
 - Capability to force rotation of secrets every X days
 - Automate generation of secrets on rotation (Uses Lambda)
 - Integration with Amazon RDS (MySQL, PostgreSQL, Aurora)
 - Secrets are encrypted using KMS
 - Mostly meant for RDS integration

### Multi-Region Secrets
 - Replicate secrets across multiple AWS Regions
 - Secrets manager keeps read replicas in sync with the primary secret
 - Ability to promote a read replica Secret to a standalone secret
 - Use cases: Multi-region apps, disaster recovery strategies, Multi-region DB

## AWS Certificate Manager (ACM)

### Overview
 - Easily provision, manage and deploy TLS certificates
 - Provide in-flight encryption for websites (HTTPS)
 - Supports both public and private TLS certificates
 - Free of charge for public TLS certificates
 - Automatic TLS certificate renewal
 - Integration with (Load TLS certificates on)
   - Elastic Load Balancers (Classic, Application, Network Load balancers)
   - CloudFront Distributions
   - APIs on API gateway
 - Cannot use ACM with EC2 (Public certificates cant be extracted)

### Requesting Public Certificates
 - List Domain names to be included in the certificate
   - Fully Qualified Domain Name (FQDN): corp.example.com
   - Wildcard Domain: .example.com
 - Select Validation method: DNS Validation or Email Validation
   - DNS Validation is preferred for automation purposes
   - Email Validation will send emails to contact addresses in the WHOIS databases
   - DNS Validation will leverage a CNAME record to DNS config (Eg: Route53)
 - It will take a few hours to get verified
 - The public certificate will be enrolled for automatic renewal 
   - ACM automatically renews ACM-generated certificates 60 days before expiry

### Importing Public certificates
 - Option to generate the certificate outside of ACM and then import it
 - No automatic renewal, must import a new certificate before expiry
 - ACM sends daily expiration events starting 45 days prior to expiration
   - The # of days can be configured 
   - Events will appear in EventBridge
 - AWS Config has a Managed Rule check to check for expriring certificates (configurable number of days)    
 
## Integration with ALB 

## Integration with API Gateway
 - API Gateways - Endpoint Types overview
   - Edge Optimized (default) : For Global Clients
     - Requests are routed through the cloudFront Edge Locations (Improves Latency)
	 - The API Gateway still lives in only one region
   - Regional
     - For clients within the same region
	 - Could manually combine with CloudFront (More control over caching strategies and distribution)
   - Private   
     - Can only be accessed from your VPC using an interface VPC Endpoint (ENI)
	 - Use a resource policy to define access
 - Integration with API Gateways
   - Create a custom domain name in API Gateway
   - Edge-Optimized (default): For global clients
     - Requests are routed through the CloudFront Edge Locations (improves Latency)
	 - The API gateway still lives in only one region
	 - **The TLS certificate must be in the same region as CloudFront**
	 - Setup CNAME or better A-Alias record in Route-53
   - Regional:
     - For Clients within the same region
	 - **The TLS certificate must be imported on API Gateways, in the same region as API Stage**
	 - The setup CNAME or better A-Alias record in Route 53

## AWS WAF - Web Application Firewall
 - Protects your web applications from common web exploits (Layer 7)
 - Layer 7 is HTTP (vs Layer 4 is TCP/UDP)
 - Deploy on:
   - Application Load Balancer
   - API Gateway
   - CloudFront
   - AppSync GraphQL API
   - Cognito User Pool
 - Define Web ACL (Web Access Control List) Rules
   - IP Set: up to 10,000 IP addresses - Use multiple rules for more IPs
   - HTTP headers, HTTP body, or URI Strings protects from common attack - SQL injection and Cross-site Scripting (XSS)
   - Size constraints, geo match (block countries)
   - Rate based rules (to count occurances of events) - for DDoS Protection
 - Web ACL are Regional except for CloudFront
 - A rule group is a reusable set of rules that you can add to Web ACL
 - WAF - Fixed IP while using WAF with a load balancer
   - WAF does not support the Network Load Balancer (Layer 4)
   - We can use Global Accelerator for Fixed IP and WAF on the ALB
 - https://docs.aws.amazon.com/waf/latest/developerguide/what-is-aws-waf.html

## AWS Shield
 - DDoS: Distributed Denial of Service - many requests at the same time
 - AWS Shield Standard:
   - Free Service that is activated for every AWS customer
   - Provides against more sophisticated attack on Amazon EC2, Elastic Load Balancer (ELB), Amazon CloudFront, AWS Global Accelerator, and route 53
   - 24/7 access to AWS DDoS response team (DRP)
   - Protect against higher fees during usage spikes due to DDoS 
   - Shield Advanced automatic application layers DDoS mitigation automatically creates, evaluates and deploys AWS WAF rules to mitigate layer 7 attacks

## Firewall Manager
 - WAF, Shield and Firewall manager are used together for comprehensive protection
 - Define your Web ACL rules in WAF
 - For granular protection of your resources, WAF alone is correct choice
 - if you want to use AWS WAF across accounts, accelerate WAF configuration, automate the protection of new resources, use firewall manager with AWS WAF
 - Shield Advanced adds additional features on top of AWS WAF, such as dedicated support from the Shield Response team (SRT) and advanced reporting
 - If you are prone to frequent DDoS attacks, consider purchasing Shield Advanced

## AWS Best Practices (BP) for DDoS Resiliency
 - For Edge Location Mitigation
   - BP1 - CloudFront
     - Web Application delivery at the edge
	 - Protect from DDoS Common attacks (SYN Floods, UDP reflection, ..)
   - BP2 - Global Accelerator
     - Access your application from the edge
	 - Integration with Shield for DDoS protection
	 - Helpful if your backend is not compatible with CloudFront
   - BP3 - Route 53
     - Domain name resolution at the edge
	 - DDoS protection mechanism 
 - For DDoS Mitigation
   - Infrastructure layer defense (BP1, BP2, BP6)
     - Protect Amazon EC2 against high traffic 
	 - That includes using Global Accelerator, Route 53, CloudFront, Elastic Load Balancing
   - Amazon EC2 with Auto Scaling (BP7)
     - Helps scale in case of sudden traffic surges including a flash crowd or a DDoS attack
   - Elastic Load Balancing (BP6)
     - Elastic Load Balancing scales with the traffic increases and will distribute the traffic to many EC2 instances
 - For Application Layer defense 
   - Detect an filter malicious web requests (BP1, BP2)
     - CloudFront cache static content and serve it from edge locations, protecting your backend
     - AWS WAF is used on top of CloudFront and Application Load balancer to filter and block requests based on request signatures
     - WAF rate based rules can automatically block the IPs of bad actors
     - Use Managed rules on WAF to block attacks based on IP reputation, or block anonymous IPs
     - CloudFront can block specific Geogrophies
   - Shield Advanced (BP1, BP2, BP6)
     - Shield  advanced automatic application layer DDoS mitigation automatically creates, evaluates and deploys AWS WAF rules to mitigate Layer 7 attacks
 - Reduce attack surface
   - Obfuscating AWS resources (BP1, BP4, BP6)
     - Using CloudFront, API Gateway, Elastic Load Balancing to hide your backend resources (Lambda Functions, EC2 instances)
   - Security Groups and Network ACLs (BP5)
     - Use security groups and NACLs to filter traffic based on specific IP at the subnet or ENI level
	 - Elastic IP are protected by AWS shield Advanced
   - Protecting API endpoints (BP4)
     - Hide EC2, Lambda elsewhere
	 - Edge optimized mode or CloudFront + Regional mode (more control for DDoS)
	 - WAF + API Gateway, burst limits, headers filtering, Use API keys
 - https://docs.aws.amazon.com/whitepapers/latest/aws-best-practices-ddos-resiliency/welcome.html

## Amazon Guard Duty
 - Amazon GuardDuty is a threat detection service that continuously monitors for malicious activity and unauthorized behavior to protect your AWS accounts, workloads, and data stored in Amazon S3.
 - With the cloud, the collection and aggregation of account and network activities is simplified, but it can be time-consuming for security teams to continuously analyze event log data for potential threats. 
 - With GuardDuty, you now have an intelligent and cost-effective option for continuous threat detection in AWS. The service uses machine learning, anomaly detection, and integrated threat intelligence to identify and prioritize potential threats.
 - GuardDuty analyzes tens of billions of events across multiple AWS data sources, such as AWS CloudTrail events, Amazon VPC Flow Logs, and DNS logs.
 - With a few clicks in the AWS Management Console, GuardDuty can be enabled with no software or hardware to deploy or maintain. By integrating with Amazon EventBridge Events, GuardDuty alerts are actionable, easy to aggregate across multiple accounts, and straightforward to push into existing event management and workflow systems.
 - Uses machine learning algorithms, anomaly detection, 3rd party data
 - One click to enable (30 days trial), no software installation required
 - Input data includes:
   - CloudTrail Event Logs: Unusual API calls, UnAuthorized deployments
     - CloudTrail Management Events: Create VPC Subnet,Create Trail, ..
	 - CloudTrail S3 data events: get object, list objects, delete object, ..
   - VPC Flow Logs: Unusual internet traffic, Unusual IP addresses
   - DNS Logs: Compromised EC2 instances sending encoded data within DNS queries
   - K8s Audit Logs: suspicious activities and potential EKS cluster compromises
 - Can Setup CloudWatch Event rules to be notified in case of findings
 - CloudWatch Events rules can target AWS Lambda or SNS
 - Can protect against CryptoCurrency attacks (has a dedicated "finding" for it)

## Amazon Inspector
 - Automated Security assessments
 - For EC2 instances
   - Leverage the AWS System Manager (SSM) agent
   - Analyze against unintended network accesibility
   - Analyze the running OS against known vulnerabilities
 - For Containers Push to Amazon ECR   
   - Assessment of containers as they are pushed
 - Reporting and integration with AWS security hub
 - Send findings to Amazon Event Bridge
 - What does AWS inspector evaluate?
   - Rememeber: Only for EC2 instaces and container infrastructure
   - Continous scanning of the infrastructure, only when needed
   - Package vulnerabilities (EC2 and ECR) - database of CVE
   - Network reachability (EC2)
   - A risk score is associated with all vulnerabilities for prioritization

## Macie
 - Fully managed data security and data privacy service that uses machine learning and pattern matching to discover and protect your sensitive data in AWS
 - Macie helps identify and alert you to sensitive data, such as Personally Identifiable Information (PII)
 - Analyzes data stored in S3 

## Summary
 - **AWS Systems Manager (SSM) Parameter store**: 
 - **AWS Secrets Manager**: newer service mostly meant for RDS integration 
 - **AWS Config**: auditing and recording compliance of your AWS resources. Record configuration changes
 - **AWS Certificates Manager**: 
 - **AWS Shield**: DDoS
 - **Web Application Firewall (WAF)**: 
   - protects from common attack - SQL injection and Cross-site Scripting (XSS)
   - block access from some countries and allow access only from the home country of the company via 
 - **Firewall Manager**: Used in conjunction with WAF for ease of management across acocounts
 - **Amazon Guard Duty**: Intelligent Threat discovery to protect AWS account using ML (can be nabled via one click and is free for 30 days)
 - **Amazon Inspector**: It is for EC2 instances and containers, helps in analysing the vulnerabilities and unknown accesibility
 - **Amazon Macie**: It is for s3 and helps in identfying the PII data storage

## Exam Guide
 - Macie vs Guard Duty
 - Disabling the Guard Duty service will delete all remaining data, including your findings and configurations before relinquishing the service permissions and resetting the service
**WAF**  
 - When you use AWS WAF with CloudFront, you can protect your applications running on any HTTP webserver, whether it's a webserver that's running in Amazon Elastic Compute Cloud (Amazon EC2) or a web server that you manage privately. You can also configure CloudFront to require HTTPS between CloudFront and your own webserver, as well as between viewers and CloudFront.
 - AWS WAF is tightly integrated with Amazon CloudFront and the Application Load Balancer (ALB), services that AWS customers commonly use to deliver content for their websites and applications (have integration with other services as well, see above). When you use AWS WAF on Amazon CloudFront, your rules run in all AWS Edge Locations, located around the world close to your end-users. This means security doesn’t come at the expense of performance. Blocked requests are stopped before they reach your web servers. When you use AWS WAF on Application Load Balancer, your rules run in the region and can be used to protect internet-facing as well as internal load balancers


# MQ
TBD

# SNS

## Overview
 - It works on publisher/subscriber model
 - Event producer only sends message to one SNS topic
 - As many event receivers (subscriptions) as we want to listen to the SNS topic notifications
 - Each subscriber to the topic will get all the messages (note: few feature to filter messages)
 - upto 1,25,00,000 subscriptions per topic (can increase over time)
 - 100,000 topics limit

## Integration
 - SNS integrates with a lot of AWS services
 - Many AWS services can send data directly to SNS for notifications

## How to publish

### Topic publish (Using SDK)
 - Create a topic
 - create a subscription (or many)
 - publish to the topic

### Direct publish (for mobile apps SDK)
 - Create a platform application
 - Create a platform endpoint
 - Publish to the platform endpoint
 - Works with Google GCM, Apple APNS, Amazon ADM

## Security

### Encryption: 
 - Same as SQS
 - Inflight encryption using HTTPS API
 - At-rest enryption using KMS keys
 - Client side encryption if the client wants to perform encryption/decryption itself

### Access Controls
 - IAM policies to regulate access to SNS API

### SNS Access Policies
 - Similar to S3 bucket policies
 - Useful for cross-account access to SNS topics
 - Useful for allowing other services (s3, etc) to write to a SNS topic 

### SQS and SNS Fan-out pattern
 - Push once in a SNS topic, receive in all SQS queues that are subscribers
 - Fully decoupled, no data loss
 - SQS allows for : data persistence, delayed processing and retries of work
 - Ability to add more SQS subscribers over time
 - Make sure your SQS queue access policy allows for SNS to write

Applications of Fan-out pattern:
 - S3 event to multiple queues
   - For same combination of event type (example: object create) and prefix (example: images/) you can only have one s3 event rule
   - If you want to send the same S3 event to many SQS queues, use Fan-out
   
 - SNS to S3 through Kinesis Data Firehose
   - SNS can send data to Kinesis and therefore we can have the following

### SNS - FIFO topic
 - First in First Out (ordering of messages in topic)
 - Similar features like SQS
   - Ordering by message group id (all messages in same group are ordered)
   - Deduplication using a Dedupolication id or content based deduplication
 - **Can only have SQS FIFO queues as subscribers**
 - Limited throughput (same throughput as SQS FIFO)

### SNS FIFO + SQS FIFO Topic
 - for Fanout + ordering + dedupliction

### SNS Message filtering
 - JSON Policy used to filter messages sent to SNS topic's subscriptions
 - If a subscription doesn't have a filter policy, it receives every message

## SNS Subscriptions:
 - Amazon Kinesis Data firehose
 - Amazon SQS
 - AWS Lambda
 - Email
 - Email-JSON
 - HTTP
 - HTTPS
 - SMS

# Simple Queuing Service (SQS)

## Types:
 - Standard Queue
 
## Standard Queue

### Actors:
 - Producer (1 or many)
 - Queue (SQS)
 - Consumer (poll for messages)

### Overview
 - 1 of oldest service
 - used for decoupling application
 - Attributes:
   - Unlimited throughput, unlimited number of messages in a queue
   - Default retention of messages: 4 days
   - Maximum retention of messages: 14 days
   - Low latency (<10 milliseconds on publish and receive)
   - limitation of 256 kb in size
- Can have duplicate messages (at least once delivery)
- Can have out of order messages (best effort ordering)

### Producing messages
 - Produced to SQS using the SDK (SendMessage API)
 - Message is persisted in SQS until a consumer deletes it.
 - Example: Send an order to process
   - orderid
   - any other attribute

### Consuming messages
 - Consumers can be running on EC2, Servers, Lambda
 - Polls SQS for messages (receives upto 10 messages at a time)
 - Process the message (Example: insert message into database)
 - Delete the messages using the DeleteMessage API

### Multiple EC2 instances as consumers
 - Consumers receive and process messages in parallel
 - at least once delivery
 - best effort message delivery
 - Consumers delete messages after processing them
 - Consumers can be scaled horizontally to improve throughput of processing

### SQS - Security
Encryption:
 - in flight encryption using HTTPS API
 - At rest encryption using KMS
 - Client-side encryption if client wants to perform encryption/decryption themselves

Access Controls
 - IAM policies to regulate access to SQS API

SQS Access policies:
 - Similar to S3 bucket policies
 - Useful for cross account access to SQS queues
 - Useful for allowing other services (SNS, S3) to write to an SQS queue

### Message visibility timeout
 - After a message is polled by consumer, it becomes invisible t other consumers
 - By Default, the "message visibility timeout" is 30 seconds
 - That is, message has 30 seconds to be processed
 - After the timeout, message is visible again in SQS
 - if a message is not processed within timeout visisbility, it will be processed twice.
 - ChangeMessageVisibility API can be called by consumer to get more time
 - if visibility timeout is high (hours), and consumer crashes, re-processing will take time
 - if visibility timeout is less(seconds), it may result in duplicates

### Long Polling
 - When a consumer requests messages from the queue, it can optionally wait for messages to arrive if their are non in the queue
 - This is called long polling
 - LongPolling decreases the number of API calls maded to SQS while increasing the efficiency and latency of application
 - Wait time can be between 1 sec to 20 sec (20 sec preferrable)
 - Long polling is preferable to Short polling
 - Long polling can be enabled at queue level or at the API level using WaitTimeSeconds
 
## FIFO queues
 - FIFO: First In First Out
 - Limited throughput 300 msg/sec without batching, 3000 msg/sec with batching
 - Exactly once send capability by removing duplicates
 - Messages are processed in order by consumer
 - Name should end with .fifo, exmple: "myfirstqueue.fifo" 
 
## SQS with ASG 
 - SQS as buffer to database writes
 - SQS to decouple between application tiers




# Machine Learning

## Rekognition

### Overview
 - Find Objects, people, text, scenes, in Images and Videos using ML
 - Facial analysis and facial search to do user Verification, people counting
 - Create a Database of "Familiar Faces" r compare against celebrities
 - Use cases:
   - Labelling
   - Content Moderation
   - Text Detection
   - Face Detection and Analysis (Gender, Age range, Emotions)
   - Face Search and Verification
   - Celebrity Recognition
   - Pathing (example: for sports game analysis)

### Use case: Content moderation
 - Detect content that is inappropriate, unwanted, or offensive (images and videos)
 - Used in Social media, Broadcast media, advertising, and e-commerce situations to create a safer user experience
 - Set a minimum confidence threshold for items that will be flagged
 - Flag sensitive content for manual review in Amazon Augmented AI (A2I)
 - Help comply with regulations

## Transcribe
 - Automaticaly convert speech to text
 - uses a deep learning process called Automatic Speech Recognition (ASR) to convert speech to text quickly and accurately
 - Automatically remove Personally Identifiable Information (PII) using Redaction
 - Supports Automatic Language Identification for multi-lingual audio
 - Use cases:
   - Transcribe customer service calls
   - Automate closed captioning and subtitling
   - Generate metadata for media assets to create a fully searchable archive

## Polly
 - Opposite of Transcribe 
 - Turn text into lifelike speech using deep learning
 - Allows you to create applications that talk
 - Feature: Lexicon and SSML
   - Customize the pronouniciation of words with Pronounciation lexicons
     - Styled words: St3ph4ne -> Stephane
     - Acronyms: AWS -> Amazon Web services
   - Upload the Lixicons and use them in the Synthesize speech operation
   - Generate speech from Plain text or from documents markded up with Speech synthesis Markup Langauage (SSML) - Enables more Customization
     - Emphasizing specific words or phrases
     - Using phonetic pronounciation
     - Including breathig sounds, whispering
     - Using the Newscaster speaking style

## Translate
 - Natural and accurate Language translation
 - Amazon Translate allows you to localize content - for internatioal users, and to easily translate large volumes of text efficiently

## Amazon Lex and Connect 
 - Amazon Lex: Same technology that powers Alexa
   - Automatic speech recognition (ASR) to convert speech to text
   - Natural Language Understanding to recognize the intent of text, callers
   - Helps build chatbots, call center bots
 - Amazon Connect
   - Receive calls, create contact flows, cloud based viryual contact center
   - Can integrate with other CRM systems or AWS
   - No upfront payments, 80$ cheaper than traditional cost center solutions
   
## Amazon Comprehend
 - For Natural Language Processing (NLP)
 - Fully managed and serverless service
 - Uses Machine learning to find insights and relationships in text
   - Language of the text
   - Extracts key phrases, places, people, brands or events
   - Understands how positive or negative the text is
   - Analyzes text using tokenization and parts of speech
   - Automatically organizes a collection of text files by topic
 - Sample Use cases:
   - Analyze customer interations (Emails) to find what leads to a positive or negative experience
   - Create and Group articles by topics that Comprehend will uncover
   
## Amazon Comprehend Medical
 - Amazon Comprehend Medical detects and returns useful information in unstructured clinical text:
   - Physician's Notes
   - Discharge summaries
   - Test Results
   - Case Notes
 - Uses NLP to detect protected Health Information (PHI) - DetectPHI API
 - Store your documents in Amazon S3, Analyze realtime data with kinesis Data Firehose, or use Amazon Transcribe to transcribe patient narratives into text that can be analyzed by Amazon Comprehend Medical
 
## Amazon SageMaker
 - Fully managed service for Developers/data scientists to build ML models
 - Typically difficult to do all the process in one place  + Provision servers
 - Machine Learning process (Simplified): predicting your exam score 

## Amazon Forecast
 - Fully managed service that uses ML to deliver highly accurate forecasts
 - Example: Predict the future sales of a raincoat
 - 50% more accurate than looking at the data itself
 - Reduce forecasting time from months to hours
 - Use Cases: Product Demand planning, Financial Planning, Resource Planning

## Amazon Kendra
 - Fully Managed document search service powered by machine learning
 - Extract answers from within a document (text, pdf, HTML, Powerpoint, MS Word, FAQs)
 - Natural Language Search capabilities
 - Learn from user interactions/feedback to promote preferred results (Incremental Learning)
 - Ability to manually fine-tune search results (Importance of data, freshness, Custom, etc)

## Amazon Personalize
 - Fully managed ML-Service to build apps with real-time personalized recommendations
 - Example: Personalized product recommendations-ranking, customized direct marketing
   - Example: User brought gardening tools, provide recommendations on the next one to buy
 - Same technology is used by Amazon.com

## Amazon Textract 
 - Automatically extracts text, handwriting and data from any scanneddocuments using AI and ML
 - Extract data from forms and tables
 - Read and process any type of document (PDFs, Images )
 - Use cases: 
   - Finanial Services (eg: Invoices, financial reports)
   - HealthCare (eg: medical records, insurance claims)
   - Public Sector (eg: tax forms, ID docuemnts, passports)

## Machine Learning Summary
 - **Rekognition: ** Face detection, Labelling, Celebrity recognition
 - **Transcribe: ** audio to text (ex: subtitles)
 - **Polly: ** text to audio
 - **Translate: ** translations
 - **Lex: ** build conversational bots - chatbots
 - **Connect: ** cloud contact center
 - **Comprehend: ** Natural Language Processing
 - **SageMaker: ** Machine Learning for every developer and data scientist
 - **Forecast: ** Build highly accurate forecasts
 - **Kendra: ** ML-powered search engines
 - **Personalize: ** Real time personalized recommendations
 - **Textract: ** Detect text and data in documents

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
 - Snowball cannot import data into glacier
 - You must use Amazon S3 first, in combination with an S3 lifecycle
 
## Amazon FSx for Windows
 - FSx similar to RDs but for file systems
 - Fully managed service
 - FSx for Windows does not allow you to present S3 objects as files and does not allow you to write changed data back to S3
 - FSx supports the use of Microsoft’s Distributed File System (DFS) to organize shares into a single folder structure up to hundreds of PB in size
 - https://aws.amazon.com/fsx/windows/faqs/

## Amazon FSx for Lustre
 - Amazon FSx for Lustre makes it easy and cost-effective to launch and run the world’s most popular high-performance file system. 
 - It is used for workloads such as machine learning, high-performance computing (HPC), video processing, and financial modeling. 
 - The open-source Lustre file system is designed for applications that require fast storage – where you want your storage to keep up with your compute. 
 - FSx for Lustre integrates with Amazon S3, making it easy to process data sets with the Lustre file system. When linked to an S3 bucket, an FSx for Lustre file system transparently presents S3 objects as files and allows you to write changed data back to S3.
 - FSx for Lustre provides the ability to both process the 'hot data' in a parallel and distributed fashion as well as easily store the 'cold data' on Amazon S3.
 - FSx for Lustre does not support Microsoft’s Distributed File System (DFS)
 - https://aws.amazon.com/fsx/lustre/

## Exam Guide
 - Use Amazon FSx File Gateway to provide low-latency, on-premises access to fully managed file shares in Amazon FSx for Windows File Server. The applications deployed on AWS can access this data directly from Amazon FSx in AWS
   - For user or team file shares, and file-based application migrations, Amazon FSx File Gateway provides low-latency, on-premises access to fully managed file shares in Amazon FSx for Windows File Server. For applications deployed on AWS, you may access your file shares directly from Amazon FSx in AWS.
   - For your native Windows workloads and users, or your SMB (Server Message Block) clients, Amazon FSx for Windows File Server provides all of the benefits of a native Windows SMB environment that is fully managed and secured and scaled like any other AWS service. You get detailed reporting, replication, backup, failover, and support for native Windows tools like DFS and Active Directory.
 -  Amazon FSx File Gateway provides access to fully managed file shares in Amazon FSx for Windows File Server and it does not support EFS. You should also note that EFS uses the Network File System version 4 (NFS v4) protocol and it does not support SMB protocol.
 - https://aws.amazon.com/blogs/storage/aws-reinvent-recap-choosing-storage-for-on-premises-file-based-workloads/


# Price comparisons

## EBS vs EFS vs S3 (1 GB file scenario)
 - With Amazon EFS, you pay only for the resources that you use. The EFS Standard Storage pricing is $0.30 per GB per month. Therefore the cost for storing the test file of 1GB on EFS is $0.30 for the month.
 - For EBS General Purpose SSD (gp2) volumes, the charges are $0.10 per GB-month of provisioned storage. Therefore, for a provisioned storage of 100GB for this use-case, the monthly cost on EBS is $0.10\*100 = $10. This cost is irrespective of how much storage is actually consumed by the test file.
 - For S3 Standard storage, the pricing is $0.023 per GB per month. Therefore, the monthly storage cost on S3 for the test file is $0.023.
 - So if we provision only 1 GB in EBS then the cost comparison would be:
   - 0.023$ (S3) < 0.10$ (EBS) < 0.30$ (EFS)
 - And if we provision more volume, we have to pay more and hence above costs will change

## Pricing references:
 - https://aws.amazon.com/s3/pricing/
 - https://aws.amazon.com/ec2/pricing/
   - https://aws.amazon.com/ec2/spot/pricing/
 - https://aws.amazon.com/ebs/pricing/
 - https://aws.amazon.com/efs/pricing/
 
## FAQ References
 - All: https://aws.amazon.com/faqs/
 - https://aws.amazon.com/rds/faqs/
 - https://aws.amazon.com/global-accelerator/faqs/
 - https://aws.amazon.com/storagegateway/faqs/
 - https://aws.amazon.com/cloudtrail/faqs/
 - https://aws.amazon.com/elasticache/faqs/
 - https://aws.amazon.com/sns/faqs/
 - https://aws.amazon.com/global-accelerator/faqs/
 - https://aws.amazon.com/guardduty/faqs/

## Best Practices
 - Shared responsibility:
   - https://aws.amazon.com/compliance/shared-responsibility-model/
 - Resiliency
   - https://docs.aws.amazon.com/global-accelerator/latest/dg/disaster-recovery-resiliency.html
   - https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/disaster-recovery-resiliency.html
 - IAM
   - https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html
   - https://aws.amazon.com/blogs/security/techniques-for-writing-least-privilege-iam-policies/
 - VPC
   - https://docs.aws.amazon.com/whitepapers/latest/aws-vpc-connectivity-options/aws-direct-connect-vpn.html
# Across Service use case exam guides
 - You can use Aurora replicas and CloudFront distribution to make the application more resilient to spikes in request rates.

## ELB vs Global Accelerator
 - Both of the services, ELB and Global Accelerator solve the challenge of routing user requests to healthy application endpoints. 
 AWS Global Accelerator relies on ELB to provide the traditional load balancing features such as support for internal and non-AWS endpoints, pre-warming, and Layer 7 routing. 
 - However, while **ELB provides load balancing within one Region, AWS Global Accelerator provides traffic management across multiple Regions.**
 - A regional ELB load balancer is an ideal target for AWS Global Accelerator. By using a regional ELB load balancer, you can precisely distribute incoming application traffic across backends, such as Amazon EC2 instances or Amazon ECS tasks, within an AWS Region.
 - If you have workloads that cater to a global client base, AWS recommends that you use AWS Global Accelerator.
 - If you have workloads hosted in a single AWS Region and used by clients in and around the same Region, you can use an Application Load Balancer or Network Load Balancer to manage your resources.

## WAF and ALB
 - You can use AWS WAF with your Application Load Balancer to allow or block requests based on the rules in a web access control list (web ACL). 
 - Geographic (Geo) Match Conditions in AWS WAF allows you to use AWS WAF to restrict application access based on the geographic location of your viewers. 
 - With geo match conditions you can choose the countries from which AWS WAF should allow access.
 - Geo match conditions are important for many customers. For example, legal and licensing requirements restrict some customers from delivering their applications outside certain countries. These customers can configure a whitelist that allows only viewers in those countries. Other customers need to prevent the downloading of their encrypted software by users in certain countries. These customers can configure a blacklist so that end-users from those countries are blocked from downloading their software.

## Host a website in S3
 - To host a static website on Amazon S3, you configure an Amazon S3 bucket for website hosting and then upload your website content to the bucket. When you configure a bucket as a static website, you must enable website hosting, set permissions, and create and add an index document. Depending on your website requirements, you can also configure redirects, web traffic logging, and a custom error document.
 - After you configure your bucket as a static website, you can access the bucket through the AWS Region-specific Amazon S3 website endpoints for your bucket.
 - Website endpoints are different from the endpoints where you send REST API requests. Amazon S3 doesn't support HTTPS access for website endpoints. 
 - If you want to use HTTPS, you can use CloudFront to serve a static website hosted on Amazon S3.
 - You can use Amazon CloudFront to improve the performance of your website. CloudFront makes your website files (such as HTML, images, and video) available from data centers around the world (called edge locations). When a visitor requests a file from your website, CloudFront automatically redirects the request to a copy of the file at the nearest edge location. This results in faster download times than if the visitor had requested the content from a data center that is located farther away.
 - CloudFront caches content at edge locations for a period of time that you specify. If a visitor requests content that has been cached for longer than the expiration date, CloudFront checks the origin server to see if a newer version of the content is available. If a newer version is available, CloudFront copies the new version to the edge location. Changes that you make to the original content are replicated to edge locations as visitors request the content.
 - https://docs.aws.amazon.com/AmazonS3/latest/dev/WebsiteHosting.html
 - Endpoints for the above created site:
   - When you configure your bucket as a static website, the website is available at the AWS Region-specific website endpoint of the bucket.
   - Depending on your Region, your Amazon S3 website endpoints follow one of these two formats.
   - s3-website dash (-) Region: http://bucket-name.s3-website.Region.amazonaws.com
   - s3-website dot (.) Region: http://bucket-name.s3-website-Region.amazonaws.com
 - These URLs return the default index document that you configure for the website.


###################################################################

# VPC

## Site-to-Site VPN
 - AWS Site-to-Site VPN enables you to securely connect your on-premises network or branch office site to your Amazon Virtual Private Cloud (Amazon VPC). 
 - You can securely extend your data center or branch office network to the cloud with an AWS Site-to-Site VPN (Site-to-Site VPN) connection. 
 - It uses internet protocol security (IPSec) communications to create encrypted VPN tunnels between two locations

## Encrypted Conenction
 - https://docs.aws.amazon.com/vpn/latest/s2svpn/internetwork-traffic-privacy.html
 - https://docs.aws.amazon.com/directconnect/latest/UserGuide/encryption-in-transit.html

## NACL

**EXAM Guide:**
 - NACL Rules
   - asterisk (\*) rule cannot be changed or deleted, it is default deny for all 
   - https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html