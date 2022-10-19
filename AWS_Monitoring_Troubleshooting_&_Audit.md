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
 - 



