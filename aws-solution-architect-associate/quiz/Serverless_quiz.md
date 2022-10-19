# Serverless Quiz

1. You have created a Lambda function that typically will take around 1 hour to process some data. The code works fine when you run it locally on your machine, but when you invoke the Lambda function it fails with a "timeout" error after 3 seconds. What should you do?
a. configure your Lambda's timeout to 25 minute
b. Configure your Lambda's memory to 10 GB
c. Run your code somewhere else (eg: EC2 instance)
Ans:
c, Lambda's maximum execution time is 15 minutes. You can run your code somewhere else such as an EC2 instance or use Amazon ECS.

2. Before you create a DynamoDB table, you need to provision the EC2 instance the DynamoDB table will be running on.
a. true
b. false
Ans:
b, DynamoDB is serverless with no servers to provision, patch, or manage and no software to install, maintain or operate. It automatically scales tables up and down to adjust for capacity and maintain performance. It provides both provisioned (specify RCU & WCU) and on-demand (pay for what you use) capacity modes

3. You have provisioned a DynamoDB table with 10 RCUs and 10 WCUs. A month later you want to increase the RCU to handle more read traffic. What should you do?
a. Increase RCU and keep WCU the same
b. You need to increase both RCU and WCU
c. Increase RCU and Decrease WCU
Ans:
a, RCU and WCU are decoupled, so you can increase/decrease each value separately

4. You have an e-commerce website where you are using DynamoDB as your database. You are about to enter the Christmas sale and you have a few items which are very popular and you expect that they will be read often. Unfortunately, last year due to the huge traffic you had the ProvisionedThroughputExceededException exception. What would you do to prevent this error from happening again?
a. Increase the RCU to a very high value
b. Create a DAX cluster
c. Migrate the database away from DynamoDB for the time of the sale
Ans:
b, DynamoDB Accelerator (DAX) is a fully managed, highly available, in-memory cache for DynamoDB that delivers up to 10x performance improvement. It caches the most frequently used data, thus offloading the heavy reads on hot keys off your DynamoDB table, hence preventing the "ProvisionedThroughputExceededException" exception.

5. You have developed a mobile application that uses DynamoDB as its datastore. You want to automate sending welcome emails to new users after they sign up. What is the most efficient way to achieve this?
a. Schedule a Lambda function to run every minute using CloudWatch events, scan the entire table looking for new users
b. Enable SNS and DynamoDB Integration
c. Enable DynamoDB streamsand configure it to invoke 
Ans:
c, DynamoDB Streams allows you to capture a time-ordered sequence of item-level modifications in a DynamoDB table. It's integrated with AWS Lambda so that you create triggers that automatically respond to events in real-time.

6. To create a serverless API, you should integrate Amazon API Gateway with 
a. EC2 Instance
b. Elastic Load Balancing
c. AWS Lambda

7. When you are using an Edge-Optimized API Gateway, your API Gateway lives in CloudFront Edge Locations across all AWS Regions.
a. False
b. true
Ans:
a, An Edge-Optimized API Gateway is best for geographically distributed clients. API requests are routed to the nearest CloudFront Edge Location which improves latency. The API Gateway still lives in one AWS Region.

8. You are running an application in production that is leveraging DynamoDB as its datastore and is experiencing smooth sustained usage. There is a need to make the application run in development mode as well, where it will experience the unpredictable volume of requests. What is the most cost-effective solution that you recommend?
a. Use provisioned capacity mode with Auto scaling enabled for both development and production
b. Use provisioned capacity mode with Auto scaling enabled for production and use On-demand capacity mode for development
c. Use Provisioned Capacity mode with auto scaling enabled for development and use on-demand capacity mode for production
d. Use on-demand Capacity mode for both development and production
Ans:
b, correct

9. You have an application that is served globally using CloudFront Distribution. You want to authenticate users at the CloudFront Edge Locations instead of authentication requests go all the way to your origins. What should you use to satisfy this requirement?
a. Lambda@Edge
b. API Gateway
c. DynamoDB
d. AWS Global Accelerator
Ans:
a, Lambda@Edge is a feature of CloudFront that lets you run code closer to your users, which improves performance and reduces latency.

10. The maximum size of an item in a DynamoDB table is ...
a. 1 MB
b. 500 KB
c. 400 KB
d. 400 MB
Ans:
c

11. Which AWS service allows you to build Serverless workflows using AWS services (e.g., Lambda) and supports human approval?
a. AWS lambda
b. ECS
c. Step Functions
d. Storage gateway
Ans: 
c

12. A company has a serverless application on AWS which consists of Lambda, DynamoDB, and Step Functions. In the last month, there are an increase in the number of requests against the application which results in an increase in DynamoDB costs, and requests started to be throttled. After further investigation, it shows that the majority of requests are read requests against some queries in the DynamoDB table. What do you recommend to prevent throttles and reduce costs efficiently?
a. Use an EC2 instance with Redis installed and place it between the Lambda function and the DynamoDB table
b. Migrate from DynamoDB to Aurora and use ElastiCache to cache the most requested read data
c. Migrate from DynamoDB to S3 and use CloudFront to cache the most requested read data
d. USe DynamoDB Accelerator (DAX) to cache the most requested read data
And:
d

13. You are a DevOps engineer in a football company that has a website that is backed by a DynamoDB table. The table stores viewers’ feedback for football matches. You have been tasked to work with the analytics team to generate reports on the viewers’ feedback. The analytics team wants the data in DynamoDB in json format and hosted in an S3 bucket to start working on it and create the reports. What is the best and most cost-effective way to convert DynamoDB data to json files?
a. Select DynamoDB table and then select Export to S3
b. Create a Lambda function to read DynamoDB data, convert them to json files, then store the files in S3 bucket
c. Use AWS transfer family
d. Use AWS DataSync 
Ans:
a 

14. A website is currently in the development process and it is going to be hosted on AWS. There is a requirement to store user sessions for users logged in to the website with an automatic expiry and deletion of expired user sessions. Which of the following AWS services are best suited for this use case?
a. Store users session in an s3 bucket and enable s3 lifecycle policy
b. Store users session locally in an EC2 instance
c. Store users session in a DynamoDB table and enable TTL
d. Store users session in an EFS file system
Ans:
c 


 
