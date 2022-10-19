# Serverless Architectures

# MyToDoList

## Requirements:
 - Expose as Rest APIs with HTTPS
 - Serverless architecture
 - Users should be able to directly interact with their own folder in S3
 - Users should authenticate through a managed serverless service
 - The users can write and read TODos, but they mostly read them
 - The database should scale and have some high read throughput

## Solution:
 - Serverless REST API: HTTPS, API Gateway, Lambda, DynamoDB
 - Use Cognito to generate temporary credentials with STS to access S3 bucket with restricted policy. App users can directly access AWS resources this way. The Patterns can be applied to DynamoDB, Lambda, etc
 - Caching the reads on DynamoDB using DAX
 - Caching the Rest rquests at the API gateway level
 - Security for authentication and authorization with Cognito, STS

# Serverless hosted website: MyBlog.com

## Requirements
 - Website should scale globally
 - Blogs are rarely written and often read
 - Some of the website are purely static files, the rest is a dynamic REST API
 - Caching must be implemented wherever possible
 - Any new user that subscribes should receive a welcome email
 - Any photo uploaded to the blog should have a thumbnail generated

## Solution:
 - Serving static content securely, globally: Amazon cloudfront as CDN for globally and with CloudFront OAI (Origin Access Identity) to secure the data and add bucket policy on s3 which allows specific OAI
 - Static content can be distributed using CloudFront with S3
 - REST API was serverless, didn't need Cognito because public
 - We leveraged a **Global** DynamoDB table to serve the data globally
 - We could have used the Aurora Global Database, but it requires provisioning
 - We enabled dynamodb streams to trigger a lambda function
 - Lambda unction had an IAM role which could use SES
 - SES (Simple Email Service) was used to send Emails in a serverless way
 - S3 can trigger SQS/SNS/Lambda to notify of events

# Micro-service

## Requirements
 - We want to switch to a micro-service architecture
 - Many services interact with each other directly using a REST API
 - Each architecture for each micro-service may vary in form and shape
 - We want a micro-service architecture so we can have a leaner development lifecycle for each service

## Discussion
 - Free to design each micro-service the way you want
 - Synchronous patterns: API Gateway, Load balancers
 - Asynchronous patterns: SQS, Kinesis, SNS, Lambda Triggers (S3)
 - Challenges with micro-services:
   - repeated overhead for creating each new micro-service
   - issues with optimizing server density/utilization
   - complexity of running multiple versions of multiple micro-services simultaneously
   - proliferation of client-side code requirements to integrate with many separate services
 - Challenges solved by serverless patterns:
   - API Gateway, Lambda Scale automatically and you pay per usage
   - You can easily clone API, reproduce environments
   - Generated Client SDK through swagger integration for the API gateway   

## Solution
 - TBD
 
# Software update offloading
## Requirements
 - We have an application running on EC2, that distributes software updates once in a while
 - When a new software update is out, we get a lot of requests and the content is distributed in mass over the network, its overly costly
 - We dont want to change our application, but want to optiize our cost and CPU, How can we do it?

## Solution
 - Add cloudfront, and reasons are below for why
 - No changes to architecture
 - Will cache software update files at the edge
 - Software update files are not dynamic, they're static (never changing)
 - Our EC2 instances aren't serverless, but cloudfront is and can scale in this situation
 - Our Auto Scaling Group (ASG) will not scale much, and we'll save tremendously in EC2 and ultimately costs
 - We'll also save in availability, network bandwidth, cost, etc
 - Easy way to make existing application more scalable and cheaper

 