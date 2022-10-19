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


 

