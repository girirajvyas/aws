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
 

