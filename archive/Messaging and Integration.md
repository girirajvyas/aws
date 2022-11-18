# Messaging and Integration

# Kinesis

## Overview
 - Makes it easy to collect, process and analyze streaming data in real time
 - Injest real time data such as: Application logs, Metrics, Website cickstreams, IOT telemetry data
 - **Kinesis data streams:** Capture, process and store data streams
 - **Kinesis data firehose:** load data streams into AWS data stores
 - **Kinesis data Analytics:** analyze data streams with SQL of Apache Flink
 - **Kinesis video streams:** Capture, Process and sore data streams

## Kinesis data streams

### Overview
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

### Capacity Modes
 - Provisioned modes:
   - You chose the number of shards provisioned, scale manually or using API
   - Each shard gets 1MB/s in (or 1000 records per second)
   - Each shard gets 2 MB/s out (classing or enhances fan-out consumer)
   - You pay per shard provisioned per hour

 - On-demand mode:
   - No need to manage or provision the capacity
   - Default capacity provisioned (4 MB/s in or 4000 records per second)
   - Scales automaticall based on the observed throughput peak during the last 30 days
   - Pay per stream per hour & data in/out per GB

### Security
 - Control Access/authorization using IAM policies
 - Encryption in flight using HTTPS endpoints
 - Encryption at rest using KMS
 - You can implement encryption/decryption of data on client side (harder)
 - VPC endpoints available for kinesis to access within VPC
 - Monitor API calls using CloudTrail

## Kinesis data Firehose

### Different participants
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

### Overview
 - Fully managed service, no administration, automatic scaling, serverless
 - Pay for data going through Firehose
 - Near RealTime
   - 60 seconds latency minimum for non full batches
   - or minimum 1 MB of data at a time
 - Supports many data formats, conversions, transformations, compression
 - Supports custom data transformations using AWS Lambda
 - Can send failed or all data to a S3 bucket

### Kinesis Data Streams vs Firehose

Data Streams:
 - Streaming service for ingestion at scale
 - Write custom code (producer/consumer)
 - Real time (~200ms)
 - Manage Scaling (shard splitting/merging)
 - Data storage for 1 to 365 days
 - Supports replay capability
 
Firehose:
 - Load streaming data into S3,Redshift, Elastic search, 3rd party, Custom HTTP
 - Fully managed
 - Near real time (buffer time minimum 60 seconds)
 - Automatic scaling
 - No data storage
 - does not support replay capability
 

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




