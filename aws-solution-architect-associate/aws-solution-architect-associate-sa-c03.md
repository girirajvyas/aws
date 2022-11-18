# 2. Identity and Access Management (IAM)

## 2.1 Basics

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

## 2.2 How to land to IAM (Common step across this section)
 - Go to: https://aws.amazon.com -> login with root user
 - You can use the Global search -> Search IAM -> Select
 - Or go to Services -> Security, Identity and compliance -> IAM
 - You will be landed to Dashboard of IAM
 - ![Landing Page](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/IAM_landing_page.PNG)
 
## 2.3 Add User  
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

## 2.4 Policies

**Overview**  
 - If you remove the user from the group admins having AdministratorAccess policy, user will not be able to create/read groups and users
 - if you add a readonly policy, your user will only be able to read

**Basic structure of policy**  
 - On high level it has:
   - Version: Policy language version
   - Id: Identifier of policy
   - Statement: Array of statemenets (atleast 1 required)
 - Array of statements include:
   - Sid: identifier for statment
   - Effect: whether it allows or Denies access
   - Principal: account:user:role this policy is applied to
   - Action: list of actions this policy allows or denies
   - Resource: List of resources where this action will be applied


 - Go to Access Management -> Policies  
 - Here you can see all the policies and can create custom ones as well
 - you can see the policy summary and json tab of policy, where policy summary is human readable and json has the same thing in json format
 - For creating policy: you can do it via visual editor or directly write the json as well

## 2.5 Multi Factor Authentication - MFA

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

## 2.6 Ways to access AWS

 - `AWS Management Console:`(Protected by password + MFA)
 - `AWS Command line Interface (CLI):` protected by access keys
 - `AWS Software Development Kit (SDK) for code:` protected by access keys
   - Access keys used above are generated through AWS console
   - Users Manage their own access keys
   - **Access keys are secret like you have passwords, it is recommended not to share them**

**Acess keys:**   
 - `Access Key Id` which is used like a username but it is not the same
 - `Secret Access Key` which is used like a password but it is not the same.

### 2.6.1 AWS console
 - All the hands on we did till now is on console

### 2.6.2 AWS cli on Windows

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

### 2.6.3 AWS Cloudshell (Alternative to AWS cli on Windows)


### 2.6.4 AWS SDK
 - https://aws.amazon.com/tools/
 - Here, you will find all the SDKs avaialble based on the programming language you use

## 2.7 IAM Roles for services

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

## 2.8 IAM Security Tools

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

## 2.9 IAM Guidelines and Best Practices

 - Don't use the root account except for AWS account setup
 - One Physical user = One AWS user
 - Assign Users to groups and assign permissions to groups to make sure security is maintained at group level
 - Create a Strong password policy/
 - Use and enforce the use of Multi Factor Authentication (MFA)
 - Create and use Roles for giving permissions to AWS services.
 - Use access keys for Programatic access (CLI/SDK)
 - Audit permissions of your account with the IAM Credentials Report and IAM Access Advisor
 - **Never share IAM users and Access Keys**

**Best practices from IAM Dashboard in AWS console**  
 - [Grant least privilege access](http://docs.aws.amazon.com/wellarchitected/latest/security-pillar/permissions-management.html) : Establishing a principle of least privilege ensures that identities are only permitted to perform the most minimal set of functions necessary to fulfill a specific task, while balancing usability and efficiency.
 - Enable Identity federation: Centrally manage users and access across multiple applications and services. For federation to multiple accounts in your AWS Organization, you can configure your identity source in [AWS Single Sign-on](https://console.aws.amazon.com/singlesignon/home).
 - Enable MFA: For extra security, we recommend that you require multi-factor authentication (MFA) for [all users](https://console.aws.amazon.com/iam/home?region=us-east-2#users).
 - [Rotate credentials regularly](https://console.aws.amazon.com/iam/home?region=us-east-2#security_credentials): Change your own passwords and access keys regularly, and make sure that all users in your account do as well.
 - Enable [IAM Access Analyzer](https://console.aws.amazon.com/access-analyzer/home): Enable IAM Access Analyzer to analyze public, cross-account, and cross-organization access.
 - Learn more about all **[security best practices](https://console.aws.amazon.com/access-analyzer/home)**.

## 2.10 Shared Responsibility Model for IAM

| AWS                                           | You                           |
| -------------                                 |:-------------:                      |
| Infrastructure (global network security)      | Users, Groups, Roles, Policies management and monitoring |
| Configuration and vulnerability analysis of services they offer     | Enable MFA on all accounts |
| Compliance Validation                         | Rotate all your keys often |
|                                               | Use IAM tools to apply appropriate permissions|
|                                               | Analyze access patterns and review permissions|

## 2.11 IAM Summary

 - Users: mapped to physical user, has a password for AWS Console
 - Groups: contains users only
 - Policies: JSON documents that outlines permissions for users or groups
 - Roles: for EC2 instances or AWS services
 - Security: MFA + password policy
 - Access keys: Access AWS using the CLI/SDK
 - Audit: IAM Credential Reports and IAM Access Advisor

## 2.12 QUIZ

What is a proper Definition of IAM roles:
 - An IAM entity that defines a set of permissions for making AWS service requests, that will be used by AWS services
 - Some AWS service will need to perform actions on your behalf. To do so, you assign permissions to aws services with IAM Roles

Which is an IAM Security Tool?
 - IAM Credential Report
 - IAM Credentials report lists all your account's users and the status of their various credentials. The other IAM security Tool is IAM Access Advisor. It shows the service permissions granted to a user and when those services were last accessed.
 
What are IAM Policies?
 - JSON Documents to define Users, Groups or Roles' permissions
 - An IAM policy is an entity that, when attached to an identity or resource, defines their permissions.

### Reference:
 - [Identity Management](https://www.aws.training/Details/eLearning?id=55148)

# IAM - Advanced

## AWS Organizations

### Overview
 - Global Service
 - Allows to manage multiple AWS accounts
 - The main account is the management account
 - Other accounts are member accounts
 - Member account can only be part of one orgainization
 - Consolidated billing across all accounts - single payment method
 - Pricing benefits from aggregated usage (Volume discount for EC2, S3, ..)
 - Shared reserved instances and savings plans discounts across accounts
 - API is available to automate AWS account creation
 
### Advantages
 - Multi Account vs One account multi VPC
 - Use tagging standards for billing purposes
 - Enable CloudTrail on all accounts, send logs to central s3 account
 - Send CloudWatch Logs to central logging account
 - Establish Cross Account Roles for admin purposes

### Security: Service Control Policies
 - IAM Policies applied to OU or Accounts to restrict Users and Roles
 - They do not apply to the management account (Full admin power)
 - Must have an Explicit allow (does not allow anything by default - like IAM)

### SCP Examples:
 - Allowlist vs denyList

## IAM Conditions
 - `aws:SourceIP`: restrict the client IP **from** which the API calls are being made 
 - `aws:Requested Region`: restrict the region, the API calls are made **to**
 - `ec2:ResourceTag`: restrict based on tags
 - S3:
   - `s3:ListBucket` permission applies to `arn:aws:s3:::test` (bucket level permission)
   - `s3:GetObject`, `s3:PutObject`, `s3:DeleteObject` applies to `arn:aws:s3:::test/*` 
 - Resource Policies and aws:PrincipalOrgID
   - `aws:PrincipalOrgID` can be used in any resource policies to restrict access to accounts that are member of an AWS Orgainization

## Resource based policy vs IAM Roles
 - Cross Acount:
   - Attaching a resource-based policy to a resource (example: S3 bucket policy)
   - Or using a role as a proxy
 - Resource based policy:
   - When you assume a role (user, application or service), you give up your original permissions and take the permissions assigned to the role
   - When using a resource-based policy, the principal doesnt have to give up his permissions
   - Example: User in Account A needs to scan a DynamoDB table in Account A and dump it in an S3 bucket in Account B
   - Supported by: Amazon S3 buckets, SNS topics, SQS queues, Lambdas, etc

### Amazon EventBridge security example for both scenarios
 - Resource based policy
   - When a rule runs it needs permission on the target
   - Resource based policy: Lambda, SNS, SQS, CloudWatch Logs, API Gateway
   - EventBridge Rule -> Lambda with Resource based policy (eg: Allow EventBridge)
 - IAM Role: Kinesis stream, Systems Manager Run Command, ECS Task
   - EventBridge Rule (IAM Role) -> Kinesis

## IAM Permission Boundaries
 - IAM Permission Boundaries are supported for users and roles (not groups)
 - Advanced feature to use a managed policy to set the maximum permissions an IAM entity can get
 - Can be used in combinations of AWS Organizations SCP  
 - Use cases:
   - Delegate responsibilities to non administrators within their permission boundaries, for example create new IAM users
   - Allow developers to self-assign policies and manage their own permissions, while making sure they cant escalate their privileges ( = make themselves admin)
   - Useful to restrict one specific user (instead of a whole account using Organizations & SCP)

## AWS Cognito

### Overview
 - Give users an identity to interact with our web or mobile application
 - Cognito Users pool:
   - Sign in functionality for app users
   - Integrate with API gateway and Application Load balancer
 - Cognito Identity Pools (Federated Identity)
   - Provided AWS Credentials to users so that they can access AWS resources directly
   - Integrate with Cognito users pool as an Identity povider
 - Cognito vs IAM: "hundreds of users", "mobile users", "authenticate with SAML"
 
### Cognito User Pools (CUP) - User Features
 - Create a serverless database of user for your web and mobile apps
 - Simple Login: USername(or Email) / password combination
 - Password reset
 - Email and phone number verification
 - Multi Factor authentication (MFA)
 - Federated Identities: Users from Facebook,Google, SAML
 - CUP integrates with API Gateway and Application Load Balancer

### Cognito Identity Pools (Federated Identities)
 - Get Identities for "users" so they obtain temporary AWS credentials
 - Users source can be Cognito User Pools, 3rd party logins, etc
 - Users can then access AWS services directly or through API Gateway
 - The IAM policies applied to the credentials are defined in Cognito
 - They can be customized based on the user_id for fine grained control
 - Default IAM roles for Authenticated and guest users

## AWS IAM Identity center (Succesor to AWS Single Signon)

### Overview 
 - One Login (Single Sign-on) for all your:
   - AWS accounts in AWS Organizations
   - Business cloud applications (eg: Salesforce, Box, Microsoft 365, etc)
   - SAML 2.0 Enabled applications
   - EC2 Windows instances
 - Identity providers
   - Built-in identity store in IAM Identity center
   - 3rd party: Active directory (AD), OneLogin, Okta
   
### Fine grained permissions and assignments
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

## AWS Directory services

### What is Microsoft Active Directory (AD)?
 - Found on any Windows server with AD domain Services
 - Database of Objects: User accounts, Computers, Printers, File Shares, Security Groups
 - Centralized security management, create account, assign permissions
 - Objects are organized in trees
 - A group of trees is a forest

### AWS Directory services overview
 - AWS Managed Microsoft AD
   - Create your own AD in AWS, Manage users locally, supports MFA
   - Establish "trust" connections with your on premise AD
 - AD Connector
   - Directory Gateway (proxy) to redirect to on-premise AD, supports MFA
   - Users are managed on the o-premise AD
 - Simple AD
   - AD-compatible managed directory on AWS
   - Cannot be joined with on-premise AD

### IAM Identity center - Active Directory Setup
 - Connect to an AWS Managed Microsoft AD (Directory service)
   - Integration is out of the box
 - Connect to slf-managed Directory
   - Create Two-way trust relationship using AWS Managed Microsoft AD
   - Create an AD-Connector
   
## AWS Control tower

### Overview
 - Easy way to setup and govern a secure and compliant multi-account AWS environment based on best practices
 - AWS Control Tower uses AWS Organizations to create accounts
 - Benefits:
   - Automate the setup of your envirnments in a few clicks
   - Automate Ongoing Policy management using guardrails
   - Detect Policy violations and remediate them
   - Monitor Compliance through an interactive dashboard

### Guardrails
 - Provides ongoing governance for your control tower environments (AWS Accounts)
 - Preventive GuardRails - using SCPs (Eg: Restrict regions across all your accounts)
 - Detective GuardRails - Using AWS Config (Eg: Identify untagged resources)




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


# AWS Monitoring, Troubleshooting & Audit
(CloudWatch, X-ray and CloudTrail)

## Cloudwatch Metrics
 - Cloudwatch provides metrics for every services in AWS
 - Metric is a variable to monitor (EC2: CPU Utilization, Networking, S3: Bucket size)
 - Metric belong to namespaces
 - Dimension is an attribute of a metric (instance id, environment, etc)
 - Upto 10 dimenstions per metric
 - Metrics have timestamps
 - Can create CloudWatch dashboard of metrics
 - Can create Cloudwatch custom metrics (Example: For the RAM)

## CloudWatch Metric Streams
 - Continuosly stream CloudWatch metric to a destination of your choice, with near-real-time delivery and low latency
   - Amazon Kinesis Data Firehose (and then its destinations)
   - 3rd party service provider: Dynatrace, DataDog, New relic, Splunk, Sumo Logic
 - Option to filter metrics to only stream a subset of them

## CloudWatch Logs

### Overview
 - Log Groups: Arbitrary name, Ususally representing an application
 - Log Stream: Instances within application/log files/containers
 - Can define log expiration policies (never expire, 30 days, etc)
 - CloudWatch logs can end logs to:
   - Amazon S3 (exports)
   - Kinesis Data streams
   - Kinesis Data Firehose
   - AWS Lambda
   - ElasticSearch

### Sources
 - SDK, CloudWatch logs Agent (older), CloudWatch Unified agent (newer)
 - ElasticBeanstalk: Collection of logs from application
 - ECS: Collection from containers
 - AWS Lambda: Collection from function logs
 - VPC Flow logs: VPC Specific logs
 - API Gateway
 - CloudTrail based on filter
 - Route53: Log DNS queries

### Metric Filter and Insights
 - CloudWatch Logs can use filter expressions
   - For Example: Find a specific IP inside of a log
   - Or Count occurances of "Error" in your logs
 - Metric Filters can be used to trigger CloudWatch alarms
 - CloudWatch Logs Insights can be used to query logs and add queries to CloudWatch Dashboards

### CloudWatch Logs - S3 Export
 - Flow: CloudWatch Logs -> S3 
 - Log data can take upto 12 hours to become available for export
 - The API call is CreateExportTask
 - Not near-real time or real-time , use logs subscripton instead
 
### CloudWatch Logs Subscription
 - TBD
 
### CloudWatch Logs Aggregation (Multi Account and Multi Region)
 - TBD

### CloudWatch Logs for EC2
 - By default, No Logs from your EC2 machine will go to CloudWatch
 - You need to run a CloudWatch agent on EC2 to push the log files you want
 - Make sure IAM permissions are correct
 - The Cloudwatch Log agent can be setup on-premises too

### CloudWatch logs Agent (older), Unified agent (newer)
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

## CloudWatch Alarms

### Overview
 - Alarms are used to trigger notifications for any metric
 - Various Options (Sampling, %, Max, Min, etc)
 - Alarm states
   - OK
   - INSUFFICIENT_DATA
   - ALARM
 - Period:
   - Length of time in seconds to evaluate the metric
   - High resolution custom metrics: 10 sec, 30 sec or multiple of 60 sec

### CloudWatch Alarm Targets
 - Stop, Terminate, Reebot or recover an EC2 instance
 - Trigger Autoscaling action
 - Send notifications to SNS (From which you can do pretty much anything)

### EC2 instance Recovery
 - Status check
   - instance status = check the EC2 VM
   - system status = check the underlying hardware
 - Recovery: 
   - While recovering, you get same private, public, Elastic IP, Metadata, Placement group
   - Can also send alarm/notification to SNS Topic

### CloudWatch Alarm: Good To Know
 - Alarms can be created based on CloudWatch Logs Metrics Filters
 - To test alarms and notifications, set the Alarm state to Alarm using CLI
 - `aws cloudwatch set-alarm-state --alarm-name "myalarm" --state-value ALARM --state-reason "testing purpose"`

### CloudWatch Container Insights
 - Collect, Aggregate, summarize metrics and logs from containers
 - Available for containers on:
   - Amazon Elastic container services (Amazon ECS)
   - Amazon Elastic kubernetes services (Amazon EKS)
   - Kubernetes platforms on EC2
   - Fargate (both for ECS and EKS)
 - In Amazon EKS and Kubernetes, CloudWatch insights is using a containerized version of the CloudWatch agent to discover containers

### CloudWatch Lambda Insights
 - Monitoring and troubleshooting solution for serverless applications running on AWS Lambda
 - Collects, Aggregates and summarizes system-level metrics including CPU time, Memory, Disk and network
 - Collects, Aggregates, and summarizes diagnostic information such as cold starts and Lambda worker shutdowns
 - Lambda insights is provided as a Lambda layer

### CloudWatch Contributor Insights
 - Analyze log data and create time series that display contributor data
   - See metrics about the top-N tier contributors
   - The total number of unique contributors and their usage
 - This helps you find top talkers and understand who or what is impacting system performance
 - Works for any AWS-generated logs (VPC, DNS, etc)
 - For example: you can find bad hosts, identify the heaviest network users, or find the URLs that generate the most errors
 - You can build your rules from scratch, or you can also use sample rules that AWS has created - leverages your cloudwatch logs 
 - CloudWatch also provides built-in rules that you can us to analyze metrics from other AWS services

### CloudWatch Application Insights
 - Provides automated dashboards that show potential problems with monitored applications, to help isolate ongoing issues
 - Your application run on Amazon EC2 instances with select technologies only (Java, .Net, Microsoft IIS Web server, databases)
 - And you can use other AWS resources such as Amazon EBS, RDS, ELB, ASG, Lambda, SQS, DynamoDB, S3 bucket, ECS, EKS, SNS, API Gateway, etc
 - It is powered by SageMaker
 - Enhanced visibility into your application health to reduce the time it will take you to troubleshoot and repair your applications
 - Findings and alerts are sent to Amazon EventBridge and SSM OpsCenter

### CloudWatch Insights and Operational Visibility Summary
 - CloudWatch container insights
   - ECS, EKS, Kubernetes on EC2, Fargate, needs agent for kubernetes
   - Metrics and logs
 - CloudWatch Lambda insights
   - Detailed metrics to troubleshoot serverless applications
 - CloudWatch Contributor insights
   - Find "Top-N" contributors through CloudWatch logs
 - CloudWatch application logs
   - Automatic dashboard to troubleshoot your application and related aws services

## EventBridge (Formerly CloudWatch Events)

### Overview
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

### EventBridge Rules
 - TBD

### EventBridge Schema Registry
 - EventBridge can analyze the events in your bus and infer the schema
 - The Schema Registry allows you to generate code for your application, that will know in advance how data is structured in the event bus
 - Schema can be versioned

### EventBridge - Resource based policy
 - Manage permissions for a specific event bus
 - Example: allow/deny events from another AWS account or Region
 - Use case: Aggregate all events from your AWS organizations in a single aws account or region

## CloudTrail

### CloudTrail Overview
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

### CloudTrail Events
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

## AWS Config 

### Overview
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

### Config rules
 - Can use AWS managed config rules (over 75)
 - Can make custom config rules (Must be defined in AWS Lambda)
   - Ex: Evaluate if each EBS disk is of type gp2
   - Ex: Evaluate if each EC2 instance is t2.micro
 - Rules can evaluated/triggered
   - For each config change
   - And/or at regular time intervals
 - AWS config rules does not prevent actions from happening (no deny)
 - Pricing: no free tier; $0.003 per onfiguration item recorded per region, $0.001 per config rule evaluation per region
 
### Config
 - View compliance of a resource over time
 - View configuration of a resource over time
 - View CloudTrail API calls of a resource over time

### Config rules - remediations
 - Automate Remediation of non-compliant resources using SSM automation Documents
 - Use AWS-Managed automation Documents or Create custom Automation Documents
   - Tip: you can create custom automation documents that invokes lambda functions
 - You can set Remediation Retries if the resource is still non-compliant after auto-remediation

### Config rules - notifications
 - Use EventBridge to trigger notifications when AWS resources are non-compliant
 - Ability to send configuration changes and complaince state notifications to SNS (All events - use SNS filtering or filter at client side)

## CloudWatch vs CloudTrail vs Config

### Comparison
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

### Comparing with an example for an Elastic LoadBalancer
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


# Data and Analytics

## Athena
 - Serverless query service to analyze data stored in Amazon S3
 - Uses standard SQL Language to query the files (built on Presto)
 - Supports CSV, JSON, ORC, Avro, Parquet
 - Pricing: 5$ per TB of data scanned
 - Commonly used with Amazon QuickSight for reporting/dashboards
 - Use Cases: Business Intelligence/ Analytics/ Reporting Analyze and query VPC flow logs, ELB Logs, CloudTrail trails, etc
 - Examp Tip: Analyze data in S3 using serverless SQL, use Athena
 
## Athena - Improve performance while using Athena Tips
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


## RedShift

### Overview
 - RedShift is based on PostgreSql, but its not used for OLTP
 - Its OLAP - Online Analytical Processing (Analytics and data warehousing)
 - 10x better performance than other data warehouses, scale to petabytes of data
 - Columnar storage of data (instead of row based) and parallel query engine
 - Pay as you go based on the instances provisioned
 - Has a SQL interface for performing the queries
 - BI Tools such as AMazon QUickSight or Tableau integrate with it
 - Redshift vs Athena: faste queries/joins/aggregations thanks of indexes

### RedShift Cluster
 - Leader Node: for query planning, rsults aggregation
 - Compute Node: for performing the queries, send results to leader
 - You provision the node size in advance
 - You can use reserved instances for cost savings

### Snapshots and Disaster recovery
 - Redshift as no Multi-AZ mode
 - Snapshots are Point in Time backups of a cluster, stored internally in S3
 - Snapshots are incremental (Only what has changed is saved)
 - You can restore a snapshot into a new cluster
 - Automated: Every 8 hours, Every 5 GB, or on a schedule. Set Retention
 - Manual: Snapshot is retained until you delete it
 - You can configure Amazon Redshift to automatically copy snapshots (Automated or Manual) of a cluster to another AWS Region

### Loading data into Redshift: Large inserts are much better
 - Amazon Kinesis Data Firehose
   - Kinesis Data Firehose to Redshift cluster (through S3 copy)
 - S3 using COPY command
   - Through Internet without using Enhanced VPC routing
   - Through VPC with Enhanced VPC routing
 - EC2 instance JDBC driver
   - Ec2 instance to Amazon Redshift cluster (Better to write data in batches)

### Redshift Spectrum
 - Query data that is already in S3, without loading it
 - Must have a Redshift cluster available to start the query
 - The query is then submitted to thousands of Redshift spectrum nodes

## OpenSearch

### Overview
 - Amazon Opensearch is successor to ElasticSearch and name change was due to some licensing issue
 - In DynamoDB, queries only exist by primary keys or indexes
 - With OpenSearch, you can search any field, even partially matches
 - Its common to use OOpenSearch as a Complement to another database
 - OpenSearch requires a cluster of instances (not serverless)
 - Does not support SQL (It has its own query language)
 - Ingestion from Kinesis data firehose, AWS IOT, and CLoudwatch Logs
 - Security through Cognito and IAM, KMS encryption, TLS
 - Comes with OpenSearch Dashboard (Visualization)

### OpenSearch Patterns 
 - DynamoDB 
   - CRUD -> DynamoDB Table -> DynamoDB Stream -> Lambda -> Amazon Opensearch -> API to search items -> and finally the details data is fetched from DynamoDB by using the id returned from ElasticSearch
 - CloudWatch Logs
   - CloudWatch Logs -> Subscription filter -> Lambda Function -> OpenSearch (Real time)
   - CloudWatch Logs -> Subscription filter -> Kinesis data firehose -> OpenSearch (Near Real time)
 - Kinesis Data streams and data Firehose

## EMR

### Overview
 - EMR stands for Elastic MapReduce
 - EMR helps creating Hadoop clusters (Big Data) to analyze and process vast amount of data
 - The clusters can be made of hundreds of EC2 instances
 - EMR comes bundled with Apache Spark, HBase, Presto, Flink
 - EMR takes care of all the Provisioning and configuration
 - Auto-scaling and Integrated with spot instances
 - Use Cases: Data processing, Machine learning, Web Indexing, Big Data

### Node types and purchasing
 - Master Node: Manage the cluster, coordinate, manage health - long running
 - Core Node: Run Tasks and store data - long running
 - Task Node (Optional): Just to run tasks  - usually spot
 - Purchasing Options:
   - On demand: reliable, predictable, wont be terminated
   - Reserved (Minimum 1 year): Cost savings (EMR will automatically use if available)
   - Spot instances: Cheaper, can be terminated, less reliable
 - Can have long running cluster, or transient (temporary) cluster

## QuickSight

### Overview
 - Serverless machine learning powered business intelligence service to create interactive dashboards
 - Fast, automatically scalable, embeddable, with per session pricing
 - Use cases:
   - Business Analytics
   - Building Visualizations
   - Perform Ad-hoc analysis
 - Integrated with RDS, Aurora, Athena, Redshift, S3
 - In Memory computation using SPICE enhine if data is imported into QuickSight
 - Enterprise edition: Possibility to setup Column level Security (CLS)
 
### Integrations
 - AWS Services
   - RDS, Aurora, RedShift, Athena, S3, OpenSearch, TimeStream
 - Data Sources (SaaS)
   - Salesforce, Jira, etc
 - Third party databases
   - On-premises databases (JDBC)
   - Teradata, 
 - Imports via data sources
   - XLSX, CSV, JSON, .TSV, ELF and CLF (Log format)
   
### Dashboards and Analysis
 - Define users (standard versions) and Groups (enterprise versions)
   - These users and groups exist within Quicksight, not IAM !!
 - A Dashboard
   - is a readonly snapshot of an analysis that you can share
   - preserves the configuration of the analysis (Filtering, parameters, control, sort)
 - You can share the Analysis or the dashboards with Users or Groups
 - To share a dashboard, you must first publish it
 - Users who see the dashboard can also see the underlying data

## Glue

### Overview
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

### Convert dsta into Parquet format
 - 

### Glue data catalog: catalog of datasets
 - 

## Lake formation
 - Data Lake = central place to have all your data for analytics purpose
 - Fully Managed service that makes it easy to setup a data lake in days
 - Discover, Cleanse, transform and ingest data into your data lake
 - It automates many complex manual steps (Collecting, Cleansing, moving, cataloging data, etc) and de-duplicate (using ML transforms)
 - Combine structured and unstructured data in the data lake
 - Out of the box source blueprints: S3, RDS, Relational and NoSQL DB
 - Fine grained Access control for your applications (row and column level)
 - Built on top of AWS Glue
 - Use case: Centralized permissions example

## Kinesis Data analytics

### Kinesis Data analytics For SQL Applications
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
 
### Kinesis Data analytics For Apache Flink
 - Use Flink (Java, Scala or SQL) to process and analyze streaming data
 - Run any Apache Flink application on a managed cluster on AWS
   - provisioning compute resources, parallel computation, automatic scaling
   - application backups (implemented as checkpoints and snapshots)
   - Use any apache flink programming features
   - Flink does not read from Firehose (Use Kinesis Analytics for SQL instead)   

## MSK - Managed Streaming for Apache Kafka

### Overview
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

### Kinesis data streams vs MSK
 - Kinesis data streams
   - 1 MB message size limit
   - Data streams with shards
   - shard splitting and Merging
   - TLS in-flight encryption
   - KMS at-rest encryption
 - MSK
   - 1 MB default, configure for higher (Ex: 10 MB)
   - kafka topics with partitions
   - can only add prtitions to a topic
   - PLAINTEXT or TLS in-flight encryption
   - KMS at-rest encryption

### MSK cosumers
 - Kinesis data analytics for Apache Flink
 - AWS Glue, Streaming ETL Jobs, Powered by Apache spark streaming
 - Lambda
 - Custom (Applications running on EC2, ECS, EKS)

## Big Data ingestion pipelines

### Requirements
 - We want the ingestion pipeline to be fully serverless
 - We want to collect data in real time
 - We want to transform the data
 - We want to query the transformed data using SQL
 - The reports created using the queries should be in S3
 - We want to load the data into a warehouse and create dashboards

### Solution
 - IOT core allows you to harvest data data from IoT devices
 - Kinesis is great for real-time data collection
 - Firehose helps with data delivery to S3 in near real-time (1 minute)
 - Lambda can help Firehose with data transformations
 - Amazon S3 can trigger notifications to SQS
 - Lambda can subscribe to SQS (We could have connector S3 to Lambda)
 - Athena is a serverless SQL service and results are stored in S3
 - The reporting bucket contains analyzed data and can be used by reporting tool such as AWS quicksight, redshift etc

# 6. Elastic Load balancing and Auto scaling groups

## 6.1 Scalability and Hiogh Availability

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
 - Elasticity: Once a system ois scalable, elasticity means that there will be some auto-scaling so that the system can scale based on the load. this is "cloud-friendly" i.e pay-per-us, match demand, optimize costs.
 - Agility(not related to scalability <ins>distractor</ins>): new IT resources are only a click away, which means that you reduce the time to make those resources available to your developers from weeks to just minutes.

## 6.2 Elastic load balancing (ELB) Overview

**What is load balancing**  
 - Load balancers are servers that forward internet traffic to multiple servers (ec2 instances) downstream

**Why use Load balancers**  
 - Spread load across multiple downstream instances
 - Expose a single point of access (DNS) to your application
 - Seamlessly handle failures of downstream instances
 - Do regular health checks to your instances
 - Provide SSL termination (Https) for your websites
 - High avaialbility across zones

**Why use ElastiC Load balancers (ELB)**  

 - An ELB is a managed load balancer
   - AWS guarantees that it will be working
   - AWS takes care of upgrades, maintenance, high avaialbility
   - AWS provides only a few configurations knob
 - It costs less to setup your own load balancer but it will be a lot more effort on your end (maintenance, integrations)
 - 3 kinds of load balancer:
   - Application Load Balancer (HTTP/HTTPS only) - Layer 7
   - Network Load Balancer (ultra-high performance, allows for TCP) = Layer 4
   - Classic Load Balancer (retiring soon) - Layer 4 and 7 (Previous generation)

## 6.3 Application Load Balancer hands on

 - Create 2 EC2 instances with the user data script to differentiate between them
 - First create 1 instance -> right click -> "Launch more like this" -> Edit instance details -> change subnet i.e change the zone now so that it is highly available
 - Verify both the instances are up via hitting the public ip
 - EC2 -> LOAD BALANCING -> Load Balancers -> Create Load balancer -> Select Application Load Balancer as we are using http over browser-based
 - 1. Configure load balancer: Provide Name and all the avaialbility zones 
 - 2. Configure security settings: it suggests to have https, we ignore for now
 - 3. Configure Security groups: create new security group -> next 
 - 4. Configure Routing: Create new target group via giving name
 - 5. Register Targets: Select the instances and use Add to registered to add them
 - 6. review and create
 - It takes some time to create, 
 - Now, use DNS name to verify if it is working and you can refresh the page to verify if it is sending request to different servers
 - In case you stop one of the instance, then elb will redirect all the calls to the instance that is up (I got 502 bad gateway once and then it was flawless)

## 6.4 Auto Scaling Groups (ASG)

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
 - 1. Choose launch template or configuration: Provide name
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
 - 2. Configure Settings
   - Instance Purchase Options: Adhere to launch template
   - Network -> Subnets: select all for higher availability -> Next
 - 3. Configure Advanced options
   - Load balancing -> Attach to an existing load balancer -> Choose from Application or network load balancer target groups -> select the target group already created
   - Health checks: check ELB option as well -> Next
 - 4. Configure group size and scaling options
   - Desired Capacity: 2 (number of instances that will be created)
   - Minimum Capacity: 1
   - Maximum Capacity: 4
   - Scaling policies: None
   - Next
 - 5. Add Notifications: Next
 - 6. Add tags: Next
 - Create Auto Scaling Group
 - Now you can go to Instance Management section to verify if the instances are created
 - Verification:
   - You can verify that 2 instances are created in the INSTANCES -> instances section
   - They are registerd in the target groups via LOAD BALANCING -> Target Groups
   - Open the DNS from load balancer and see it gives you the result

## 6.5 Summary

 - High Availability vs Scalability (Vertical and horizontal) vs Elasticity vs Agility in the cloud
 - Elastic Load Balancers (ELB)
   - Disctribute traffic across backend EC2 instances, can be Multi-AZ
   - Supports health checks
   - 3 types: Application LB (HTTP-L7), Network LB (TCP-L4), Classic LB (old)
 - Auto Scaling Groups (ASG)
   - Implement Elasticity for your application, across multiple AZ
   - Scale EC2 instances based on the demand on your system, replace unhealthy
   - Integrated with the ELB

## 6.6 Quiz

Which load balancer should you use to handle hundreds of thousands of connections with low latency
 - Network Load balancer: A network load balancer can handle millions of requests per second with low-latency. It operates at Layer 4 and is best suited for load-balancing TCP, UDP and TLS traffic with ultra high-performance


## 6.7 Resources

 - https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/what-is-load-balancing.html?icmpid=docs_elbv2_console#elb-features
 - Instance types and comparison: https://instances.vantage.sh/

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

# 7. Amaozon S3 (Simple storage Service)

## 7.1 S3 Overview

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

## 7.1 Bucket Policy

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

## 7.2 S3 Websites

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
 
## 7.3 S3 Versioning

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

## 7.4 S3 Access logs

**basics**  
 - For audit purpose, you may want to log all access to S3 buckets
 - Any request made to S3, from any account, authorized or denied will be logged into another S3 bucket
 - That data can be analyzed using data analysis tools..
 - Very helpful to come down to the root cause of an issue, or audit usage, view suspicious patterns, etc..

**Hands on**  
 - To enable logging you have to create a new bucket where access logs will be written
 - Go to original S3 bucket -> Properties -> Server access logging setting -> edit -> enable and provide the target bucket that we created above.
 - You can access the objects and then these logs will be reflected in another s3 bucket after an hour or two.

## 7.5 S3 Replication (CRR and SRR)

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

## 7.6 S3 Storage classes

**Types**  
 - Amazon S3 Standard - General purpose
 - Amazon S3 Standard-Infrequent Access (not accessed ver often)
 - Amazon S3 one Zone-Infrequent Access (you can recreate and fine with losing it)
 - Amazon S3 Intelligent tiering (Not sure which one to choose between frquent and infrequent access)
 - Amazon Glacier (Backups and archives)
 - Amazon Glacier Deep Archive (Backups and archives)

**Durability and Availability**  
**Durability:**  
 - High durability (99.99999999999, 11 time 9) of objects across multiple Azure
 - If you store 10,000,000 objects with Amazon S3, you can on average expect to incur a loss of a single object once every 10,000 years
 - `Same for all storage classess`

**Availability**  
 - Measures how readily available a service is
 - S3 standard has 99.99% availability, which means it wil not be available 53 minutes a year
 - `Varies depending on storage class`

***1. Amazon S3 Standard - General purpose**  
 - 99.99% availability
 - Used for frequently accessed data
 - low latency and high throughput
 - sustain 2 concurrent facility failures
 - Use cases:
   - Big Data analytics
   - Mobile and gamin applications
   - content distribution

***2. Amazon S3 Standard-Infrequent Access (IA)**  
 - Suitable for data that is less frequently accessed, but requires rapid access when needed
 - 99.9% Availability
 - Lower cost compared to Amazon S3 standard, but retrieval fee
 - Sustain 2 concurrent facility failures
 - Use Cases: 
   - As a data store for disaster recovery
   - backups

***3. Amazon S3 one Zone-Infrequent Access (IA)**  
 - Same as IA but data is stored in a single Azure
 - 99.5% Availability
 - Low latency and high throughput performance
 - Lower cost compared to S3-IA (by 20%)
 - Use Cases:
   - Storing secondary backup copies of on premise data or
   - storing data you can recreate

***4. Amazon S3 Intelligent tiering**  
 - 99.9% Availability
 - Same low latency and high throughput performance of S3 Standard
 - Cost optimized by automatically moving objects between two access tiers based on changing access patterns:
   - frequent access
   - Infrequent access
 - Resilient against events that impact an entire Availability zone

***5. Amazon Glacier and Amazon Glacier Deep Archive**  
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

**Summary**  
 - https://aws.amazon.com/s3/storage-classes/

**Moving between storage classes**  
 - You can transition objects between storage classes
 - For infrequently accessed object, move them to Standard_IA
 - For archive objects you dont need in real-time, GLACIER or DEEP_ARCHIVE
 - Moving Objects can be automated using a lifecycle configuration

**Hands on**  
 - S3 -> Objects -> upload -> Additional upload options, here you can select the storage class and details are also available on the same -> upload
 - if you select the uploaded file, yyou can verify the storage class and you can update the same if needed
 - S3 -> Buckets -> Select bucket -> management tab -> Create lifecycle rule
   - LifeCycle Rule name
   - Choose a rule scope
   - Lifecycle Rule actions: depending on the checkboxes you select, you will be provided with the options to configure below
   - Create Rule

## 7.7 Snowball, Snowball Edge and SnowMobile Overview

**Snowball**  
 - Physical data transport solution that heps moving TBs or PBs of data in or out of AWS
 - Alternative to moving data over the network (and paying network fees)
 - pay per data transfer jobs
 - Use cases:
   - LArge data cloud migrations
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

**Snowball Edge**  
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

**AWS Snowmobile (Truck)**  
 - Transfer exabytes of data (1 EB = 1,000 PB = 10,000,00 TBs)
 - Each Snowmobile has 100PB of capacity (use multiple in parallel)
 - Better than snowball if you transfer more than 10 PB

Snow *

Snowball Edge

Snowcone


## 7.8 Storage gateway overview

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

**AWS storage gateway**  
 - Bridge between on-premise data and cloud data in S3
 - Hybrid storage service to allow on-premises to seamlessly use the AWS Cloud
 - Use cases:
   - Disaster recovery
   - Backup and restore
   - Tiered storage
 - Types of storage gateway
   - File gateway
   - Volume gateway
   - Tape gateway
 - No need to know the types at the exam

## 7.9 Shared responsibility model for S3

| AWS                                                 | You                           |
| -------------                                       |:-------------:                      |
| Infrastructure (global security, durability, availability, sustain concurrent loss of data in two facilities) | S3 Versioning |
| Configuration and vulnerability analysis            | S3 Bucket Policies|
| Compliance Validation                               | S3 Replication Setup |
|                                                     | Logging and Monitoring |
|                                                     | S3 Storage Classes |
|                                                     | Data encryption at rest and in transit |

## 7.10 S3 Summary

 - Buckets vs Objects: global unique name, tied to a region
 - S3 security: IAM policy, S3 bucket Policy (public access), S3 encryption
 - S3 Websites: host a static website on Amazon S3
 - S3 Versioning: Multiple versions for files, prevents accidental deletes
 - S3 Access Logs: Log requests made within your S3 buckets
 - S3 Replication: Same-region or Cross-Region, must enable versioning
 - S3 Storage Classes: Standard. IA, IZ-IA, Intelligent, Glacier, Deep Archive
 - S3 Lifecycle Rules: transition objects between classes
 - Snowball/Snowmobile: import data into S3 through a physical device
 - Storage gateway: hybrid solution to extend on-premises storage to S3

## S3 Encryption
Their are 4 methods of encrypting objects in s3:
 - SSE-S3: Encrypts S3 objects using keys handled and managed by aws
 - SSE-KMS: leverage aws key management service to manage encryption keys
 - SSE-C: When you want to manage your encryption keys
 - Client Side encryption

### SSE-S3
 - Encryption using keys handled and managed by aws
 - Object is encrypted server side
 - AES-256 encryption type
 - Must set header: "x-amz-server-side-encryption":"AES256"

### SSE-KMS
 - Encryption using keys managed and handles by KMS
 - Object is encrypted server side
 - KMS advantages: User control + audit trail
 - Must set header: "x-amz-server-side-encryption":"aws:kms"

### SSE-C
 - Encryption using data keys fully managed by the customer outside of AWS
 - Amazon S3 does not store the encryption keys you provide
 - HTTPS must be used
 - Encryption key must be provided in headers, for every HTTP request made

### Client Side encryption
 - Cleint library such as Amazon S3 encryption client
 - Clients must encrypt data themselves before sending to S3
 - Clients must decrypt data themselves when retrieving from S3
 - Customer fully manages the keys and encryption cycle

### Encryption in Transit:
 - Amazon S3 exposes:
   - HTTP endpoint: non encrypted
   - HTTPS endpoint: encryption in flight
 - You are free to use the endpoint you want, but HTTPS is recommended
 - Most clients would use the HTTPS endpoint by default
 - HTTPS is mandatory for SSE-C
 - Encryption in flight is also called SSL/TLS

# AWS Security and Encryption

## Encryption

### What is Encryption?


### Types
 - In Flight
 - At rest - server side
 - client side
 
### In Flight Encryption
 - Data is encrypted before sending and dcrypted after receiving 
 - SSL certificates help with encryption (HTTPS)
 - Encryption in flight ensures no MITM (Man in the middle) can happen

### Server side Encryption At rest
 - Data is encrypted after being received by the server
 - Data is decrypted before being sent
 - It is stored in an encrypted form thanks to a key (usually a data key)
 - The encryption/decryption keys must be managed somewhere and the server must have access to it

### Client side
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

### S3 replication Encryption considerations
 - Unencrypted objects and objects encrypted with SSE-S3 are replicated by default
 - Objects encrypted with SSE-C (Customer provided key) are never replicated
 - For objects encrypted with SSE-KMS, you need to enable this option
   - specify which KMS key to encrypt the objects within the target bucket
   - Adapt the KMS key policy for the target keys
   - An IAM Role with `kms:Decrypt` for the source KMS key and `kms:Encrypt` for the target KMS key
   - You might get KMS throttling errors, in which case you can ask for a service quotas increase
   - You can use multi region AWS KMS keys, but they are currently treated as independent keys by Amazon S3 (Hence, Object will still be decrypted and encrypted again)

### Encrypted AMI (Amazon Machine Image) sharing option
 - AMI in source account is encrypted with KMS key from source account
 - Must modify the image attribute to add a Launch Permission which corresponds to the specified target AWS account
 - Must share the KMS keys used to encrypt the snapshot of AMI references with the "target account/IAM Role" 
 - The IAM Role/User in the target account must have the permissions to `DescribeKey`, `ReEncrypted`, `CreateGrant`, `Decrypt`
 - When Launching an EC2 instance from the AMI, optionally the target account can specify a new KMS key in its own account to re-encrypt the volumes
 
# AWS Systems Manager (SSM) parameter store

## Overview
 - Secure storage for configuration and secrets
 - Optional seamless Encryption using KMS
 - Serverless, Scalable, Durable, Easy SDK
 - Version tracking of Configurations/Secrets
 - Configuration management using Path & IAM
 - Notifications with CloudWatch Events
 - Integration with CloudFormation

## SSM Parameter Store Hierarchy
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

## Parameter Policies (for Advanced Parameters only)
 - Allow to assign TTL to a parameter (expiration date) to force updating or deleting sensitive data such as passwords
 - Can assign multiple policies at a time
 - Sample policies:
   - Expiration (to delete a parameter)
   - ExpirationNotification (CW Events)
   - NoChangeNotification (CW Events)
 
# AWS Secrets Manager

## Overview
 - Newer service meant for storing secrets
 - Capability to force rotation of secrets every X days
 - Automate generation of secrets on rotation (Uses Lambda)
 - Integration with Amazon RDS (MySQL, PostgreSQL, Aurora)
 - Secrets are encrypted using KMS
 - Mostly meant for RDS integartion

## Multi-Region Secrets
 - Replicate secrets across multiple AWS Regions
 - Secrets manager keeps read replicas in sync with the primary secret
 - Ability to promote a read replica Secret to a standalone secret
 - Use cases: Multi-region apps, disaster recovery strategies, Multi-region DB

# AWS Certificate Manager (ACM)

## Overview
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

## Requesting Public Certificates
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

## Importing Public certificates
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

# AWS WAF - Web Application Firewall

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
   - HTTP headers, HTTP body, or URI Strings protects from common attack - SQL injection andCross-site Scripting (XSS)
   - Size constraints, geo match (block countries)
   - Rate based rules (to count occurances of events) - for DDoS Protection
 - Web ACL are Regional except for CloudFront
 - A rule group is a reusable et of rules that you can add to Web ACL
 - WAF - Fixed IP while using WAF with a load balancer
   - WAF does not support the Network Load Balancer (Layer 4)
   - We can use Global Accelerator for Fixed IP and WAF on the ALB

# AWS Shield

 - DDoS: Distributed Denial of Service - many requests at the same time
 - AWS Shield Standard:
   - Free Service that is activated for every AWS customer
   - Provides against more sophisticated attack on Amazon EC2, Elastic Load Balancer (ELB), Amazon CloudFront, AWS Global Accelerator, and route 53
   - 24/7 access to AWS DDoS response team (DRP)
   - Protect against higher fees during usage spikes due to DDoS 
   - Shield Advanced automatic application layers DDoS mitigation automatically creates, evaluates and deploys AWS WAF rules to mitigate layer 7 attacks

# Firewall Manager

 - WAF, Shield and Firewall manager are used together for comprehensive protection
 - Define your Web ACL rules in WAF
 - For granular protection of your resources, WAF alone is correct choice
 - if you want to use AWS WAF across accounts, accelerate WAF configuration, automate the protection of new resources, use firewall manager wth AWS WAF
 - Shield Advanced adds additional features on top of AWS WAF, such as dedicated support from the Shield Response team (SRT) and advanced reporting
 - If you are prone to frequent DDoS attacks, consider purchasing Shield Advanced

# AWS Best Practices (BP) for DDoS Resiliency

 - For Edge Location Mitigation
   - BP1 - CloudFront
     - Web Application delivery at the edge
	 - Protect from DDoS Common attacks (SYN Floods, UDP reflection, ..)
   - BP1 - Global Accelerator
     - Access your application from the edge
	 - Integration with SHield for DDoS protection
	 - Helpful if your backend is not compatible with CloudFront
   - BP3 - Route 53
     - Domain name resolution at the edge
	 - DDoS pritection mechanism 
 - For DDoS Mitigation
   - Infrastructure layer defense (BP1, BP2, BP6)
     - Protect AMazon EC2 against high traffic 
	 - That includes using Global Accelerator, Route 53, CloudFront, Elastic Load Balancing
   - Amazon EC2 with AUto Scaling (BP7)
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

# Amazon Guard Duty

 - Intelligent Threat discovery to protect AWS account
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

# Amazon Inspector

 - Automated Security assessments
 - For EC2 instances
   - Leverage the AWS System Manager (SSM) agent
   - Analyze against unintended network accesibility
   - Analyze the running OS against known vulnerabilities
 - For Containers Push to Amazon ECR   
   - Assessment of containers as they are pushed
 - Reporting and integartion with AWS security hub
 - Send findings to Amazon Event Bridge
 - What does AWS inspector evaluate?
   - Rememeber: Only for EC2 instaces and container infrastructure
   - Continous scanning of the infrastructure, only when needed
   - Package vulnerabilities (EC2 and ECR) - database of CVE
   - Network reachability (EC2)
   - A risk score is associated with all vulnerabilities for prioritization

# Macie

 - Fully managed data security and data privacy service that uses machine learning and pattern matching to discover and protect your sensitive data in AWS
 - Macie helps identify and alert you to sensitive data, such as Personally Identifiable Information (PII)
 - Analyzes data stored in S3 
 


 




     


 