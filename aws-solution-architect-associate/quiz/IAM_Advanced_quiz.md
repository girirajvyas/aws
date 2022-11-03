1. You have a mobile application and would like to give your users access to their own personal space in the S3 bucket. How do you achieve that?
a. Generate IAM Credentials for each of your application's users
b. Use Amazon Cognito Identity Federation
c. Use SAML Identity Federation
d. Use a bucket policy to make your bucket public
Ans:
b, Amazon Cognito can be used to federate mobile user accounts and provide them with their own IAM permissions, so they can be able to access their own personal space in the S3 bucket. 

2. You have strong regulatory requirements to only allow fully internally audited AWS services in production. You still want to allow your teams to experiment in a development environment while services are being audited. How can you best set this up?
a. Provide Dev team with a completely independent AWS accounts
b. Apply a global IAM policy on your PROD environment
c. Create an AWS Organization and create two prod and dev OUs, then apply an SCP on prod OU
d. Create an AWS Cofig Rule
Ans:
 c 

3. You are managing the AWS account for your company, and you want to give one of the developers access to read files from an S3 bucket. You have updated the bucket policy to this, but he still can't access the files in the bucket. What is the problem?
Policy:
```
{
    "Version": "2012-10-17",
    "Statement": [{
        "Sid": "AllowsRead",
        "Effect": "Allow",
        "Principal": {
            "AWS": "arn:aws:iam::123456789012:user/Dave"
        },
        "Action": "s3:GetObject",
        "Resource": "arn:aws:s3:::static-files-bucket-xxx"
     }]
}
```
a. Everything is okay, he just needs to logout an login again
b. The bucket does not contain any files yet
c. You should change the resource to `arn:aws:s3:::static-files-bucket-xxx/*`, because this is an object-level permission
Ans:
c 

4. You have 5 AWS Accounts that you manage using AWS Organizations. You want to restrict access to certain AWS services in each account. How should you do that?
a. Using IAM Roles
b. Using AWS Organizations SCP
c. Using AWS Config
Ans:
b 

5. Which of the following IAM condition key you can use only to allow API calls from a specified AWS region?
a. `aws:RequiredRegion`
b. `aws:SourceRegion`
c. `aws:InitialRegion`
d. `aws:RequestedRegion`
Ans:
d 

6. When configuring permissions for EventBridge to configure a Lambda function as a target you should use ………………….. but when you want to configure a Kinesis Data Streams as a target you should use …………………..
a. Identity-based Policy, Resource-based Policy
b. Resource-based Policy, Identity-based Policy
c. Resource-based Policy, Resource-based Policy
d. Identity-based Policy, Identity-based Policy
Ans: b 

7. You are developing a new web and mobile application that will be hosted on AWS and currently, you are working on developing the login and signup page. The application backend is serverless and you are using Lambda, DynamoDB, and API Gateway. Which of the following is the best and easiest approach to configure the authentication for your backend?
a. Store users credentials in a DynamoDB table enrypted using KMS
b. Store users credential in an S3 bucket encrypted using KMS
c. Use Cognito User pools
d. Store Users credentials in AWS Secret manager
Ans:
c 
