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


