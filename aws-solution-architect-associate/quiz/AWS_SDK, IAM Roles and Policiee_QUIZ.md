1. An application hosted on an EC2 instance wants to upload objects to an S3 bucket using the PutObject API call, but it lacks the required permissions. What should you do?
a. From inside the EC2instance run `aws configure` and indert your personal IAM credentials, because you have access to do the required API call
b. Ask an administrator to attach an IAM policy to the IAM Role on your EC2 instance that authorises it to do the required API call
c. Export your environment variables with your IAM Credentials on the EC2 instance
d. use the EC2 metadata API call.
Ans:
d, Incorrect, This will not help with IAM credentials. The EC2 Metadata API call allows the EC2 instance to learn more about itself (e.g., AWS Region, AZ, public IPv4, ...)
b, correct, IAM Roles are the right way to provide credentials and permissions to an EC2 instance.

2. You and your colleague are working on an application that's interacting with some AWS services through making API calls. Your colleague can run the application on his machine without issues, while you get API Authorization Exceptions. What should you do?
a. Send him your AWS key and secret key so he can replicate the issue on his machine
b. Ask him to send you his IAM credentials so you can work without issues
c. Compare both your IAM policy and his IAM policy in AWS policy simulator to understand the differences
d. Ask him to create an EC2 instance and insert his IAM credentials inside it, so you can run the application from the EC2 instance
Ans:
 c, Correct
 
3. Your administrator launched a Linux EC2 instance and gives you the EC2 Key Pair so you can SSH into it. After getting into the EC2 instance, you want to get the EC2 instance ID. What is the best way to do this?
a. Create an IAM role and attach it to your EC2 instance so you can perform a `describe-instances` API call
b. Query the user data at http://169.254.169.254/latest/user-data
c. Query the meta data at http://169.254.169.254/latest/meta-data
d. Query the meta data at http://254.169.254.169/latest/meta-data
Ans:
c, Correct