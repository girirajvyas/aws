1. You have an e-commerce website and you are preparing for Black Friday which is the biggest sale of the year. You expect that your traffic will increase by 100x. Your website already using an SQS Standard Queue, and you're running a fleet of EC2 instances in an Auto Scaling Group to consume SQS messages. What should you do to prepare your SQS Queue?
a. Contact AWS support to pre-warm your SQS standard queue
b. Enable auto scaling in your SQS queue
c. Increase the capacity of the SQS queue
d. Do nothing, SQS scales automatically
Ans:
d, it scales automatically

2. You have an SQS Queue where each consumer polls 10 messages at a time and finishes processing them in 1 minute. After a while, you noticed that the same SQS messages are received by different consumers resulting in your messages being processed more than once. What should you do to resolve this issue?
a. Enable Long Polling
b. Add `DelaySeconds` parameter to the messages when being produced
c. Increase the visibility timeout
d. Decrease the visibility timeout
Ans:
c, SQS Visibility Timeout is a period of time during which Amazon SQS prevents other consumers from receiving and processing the message again. In Visibility Timeout, a message is hidden only after it is consumed from the queue. Increasing the Visibility Timeout gives more time to the consumer to process the message and prevent duplicate reading of the message. (default: 30 sec., min.: 0 sec., max.: 12 hours)

3. Which SQS Queue type allows your messages to be processed exactly once and in order?
a. SQS atandard queue
b. SQS Dead letter queue
c. SQS Delay queue
d. SQS FIFO queue
Ans:
d, SQS FIFO (First-In-First-Out) Queues have all the capabilities of the SQS Standard Queue, plus the following two features. First, The order in which messages are sent and received are strictly preserved and a message is delivered once and remains available until a consumer process and deletes it. Second, duplicated messages are not introduced into the queue.

4. You have 3 different applications that you'd like to send them the same message. All 3 applications are using SQS. What is the best approach would you choose?
a. use SQS replication features
b. Use SNS + SQS fanout pattern
c. Send messages individually to 3 SQS queues
Ans:
b, This is a common pattern where only one message is sent to the SNS topic and then "fan-out" to multiple SQS queues. This approach has the following features: it's fully decoupled, no data loss, and you have the ability to add more SQS queues (more applications) over time.

5. You have a Kinesis data stream with 6 shards provisioned. This data stream usually receiving 5 MB/s of data and sending out 8 MB/s. Occasionally, your traffic spikes up to 2x and you get a ProvisionedThroughputExceeded exception. What should you do to resolve the issue?
a. Add more shards
b. Enable Kinesis replication
c. Use SQS as a buffer to kinesis
Ans:
a, The capacity limits of a Kinesis data stream are defined by the number of shards within the data stream. The limits can be exceeded by either data throughput or the number of reading data calls. Each shard allows for 1 MB/s incoming data and 2 MB/s outgoing data. You should increase the number of shards within your data stream to provide enough capacity.

6. You have a website where you want to analyze clickstream data such as the sequence of clicks a user makes, the amount of time a user spends, and where the navigation begins and how it ends. You decided to use Amazon Kinesis, so you have configured the website to send these clickstream data all the way to a Kinesis data stream. While you checking the data sent to your Kinesis data stream, you found that the users' data is not ordered and the data for one individual user is spread across many shards. How would you fix this problem?
a. There are too many shards, you should only use 1 shards
b. You shouldnt use multiple consumers, only one and it should be re-order data
c. For each record sent to kinesis add a partition key that represents the identity of the user
And:
c, Kinesis Data Stream uses the partition key associated with each data record to determine which shard a given data record belongs to. When you use the identity of each user as the partition key, this ensures the data for each user is ordered hence sent to the same shard.

7. Which AWS service is most appropriate when you want to perform real-time analytics on streams of data?
a. SQS
b. SNS
c. Kinesis data analytics
d. Kinesis data firehose
Ans:
c, Use Kinesis Data Analytics with Kinesis Data Streams as the underlying source of data.

8. You are running an application that produces a large amount of real-time data that you want to load into S3 and Redshift. Also, these data need to be transformed before being delivered to their destination. What is the best architecture would you choose?
a. SQS + Lambda
b. SNS + HTTP Endpoint
c. Kinesis Data Streams + Kinesis Data Firehose
Ans:
c, This is a perfect combo of technology for loading data near real-time data into S3 and Redshift. Kinesis Data Firehose supports custom data transformations using AWS Lambda.

9. Which of the following is NOT a supported subscriber for AWS SNS?
a. Kinesis data streams
b. SQS
c. HTTP(s) enspoints
d. Lambda
Ans:
a, Note: Kinesis Data Firehose is now supported, but not Kinesis Data Streams.

10. Which AWS service helps you when you want to send email notifications to your users?
a. SQS + Lambda
b. SNS
c. Kinesis
Ans:
b

11. You're running many micro-services applications on-premises and they communicate using a message broker that supports MQTT protocol. You're planning to migrate these applications to AWS without re-engineering the applications and modifying the code. Which AWS service allows you to get a managed message broker that supports the MQTT protocol?
a. SQS
b. SNS
c. Kinesis
d. Amazon MQ
Ans:
d, Amazon MQ supports industry-standard APIs such as JMS and NMS, and protocols for messaging, including AMQP, STOMP, MQTT, and WebSocket.

12. An e-commerce company is preparing for a big marketing promotion that will bring millions of transactions. Their website is hosted on EC2 instances in an Auto Scaling Group and they are using Amazon Aurora as their database. The Aurora database has a bottleneck and a lot of transactions have been failed in the last promotion they have made as they had a lot of transaction and the Aurora database wasn’t prepared to handle these too many transactions. What do you recommend to handle those transactions and prevent any failed transactions?
a. Use SQS as buffer to write to Aurora
b. Host the website in AWS fargate instead of EC2 instances
c. Migrate Aurora to RDS for SQL server
Ans:
a, 

13. A company is using Amazon Kinesis Data Streams to ingest clickstream data and then do some analytical processes on it. There is a campaign in the next few days and the traffic is expected to grow 100x in less than 5 minutes. What Kinesis Data Stream capacity mode do you recommend?
a. provisioned mode
b. on demand mode
Ans:
b





