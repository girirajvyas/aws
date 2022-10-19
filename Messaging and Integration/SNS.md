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
