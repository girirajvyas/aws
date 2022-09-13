1. Your website TriangleSunglasses.com is hosted on a fleet of EC2 instances managed by an Auto Scaling Group and fronted by an Application Load Balancer. Your ASG has been configured to scale on-demand based on the traffic going to your website. To reduce costs, you have configured the ASG to scale based on the traffic going through the ALB. To make the solution highly available, you have updated your ASG and set the minimum capacity to 2. How can you further reduce the costs while respecting the requirements?
a. Remove the ALB and use an Elastic IP instead
b. Reserve 2 EC2 instances
c. Reduce the minimum capacity to 1
d. Reduce the minimum capacity to 0
Ans: 
b, Correct, This is the way to save further costs as we will run 2 EC2 instances no matter what.

2. Which of the following will NOT help us while designing a STATELESS application tier?
a. Store session data in Amazon RDS
b. Store session data in Amazon ElasticCache
c. Store session data in the client Http cookies
d. Store Session data in EBS volumes
Ans:
d. Correct, EBS volumes are created in a specific AZ and can only be attached to one EC2 instance at a time.

3. You want to install software updates on 100s of Linux EC2 instances that you manage. You want to store these updates on shared storage which should be dynamically loaded on the EC2 instances and shouldn't require heavy operations. What do you suggest?
a. Store the software updates on EBS and sync them using data replication software from one master in each AZ
b. Store the software updates on EFS and mount EFS as a netwrok drive at startup
c. Package the software updates as an EBS snapshot and create EBS volumes for each new sotware update
d. Store the software update in Amazon RDS
Ans:
c, Incorrect, This requires too many operations although it would work.
a, Incorrect, This requires too many operations and is not a native AWS feature.
b, Correct, EFS is a network file system (NFS) that allows you to mount the same file system to 100s of EC2 instances. Storing software updates on an EFS allows each EC2 instance to access them.

4. As a Solutions Architect, you're planning to migrate a complex ERP software suite to AWS Cloud. You're planning to host the software on a set of Linux EC2 instances managed by an Auto Scaling Group. The software traditionally takes over an hour to set up on a Linux machine. How do you recommend you speed up the installation process when there's a scale-out event?
a. Use Golden AMI Image
b. Bootstrap using EC2 user data
c. Store the application in AMazon RDS
d. Retrieve the application setup files from EFS
Ans:
a, Correct, Golden AMI is an image that contains all your software installed and configured so that future EC2 instances can boot up quickly from that AMI

5. You're developing an application and would like to deploy it to Elastic Beanstalk with minimal cost. You should run it in ..................
a. Single Instance Mode
b. High Availaibity Mode
Ans:
a, Correct, The question mentions that you're still in the development stage and you want to reduce costs. Single Instance Mode will create one EC2 instance and one Elastic IP.

6. You're deploying your application to an Elastic Beanstalk environment but you notice that the deployment process is painfully slow. After reviewing the logs, you found that your dependencies are resolved on each EC2 instance each time you deploy. How can you speed up the deployment process with minimal impact?
a. Remove some dependencies in your code
b. Place the dependencies in Amazon EFS
c. Create a Golden AMI that contains the dependencies and use that image to launch the EC2 instances
Ans:
c, Corect, Golden AMI is an image that contains all your software, dependencies, and configurations, so that future EC2 instances can boot up quickly from that AMI.


