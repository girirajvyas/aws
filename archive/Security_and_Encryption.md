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
 