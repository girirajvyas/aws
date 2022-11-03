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


