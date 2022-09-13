Scaling an EC2 instance from r4.large to r4.4xlarge is called .....................
 - Vertical Scalability

Running an application on an Auto Scaling Group that scales the number of EC2 instances in and out is called .....................
 - Horizontal Scalability

Elastic Load Balancers provide a .......................
 - Static DNS name we can use in our application. (Only Network Load Balancer provides both static DNS name and static IP. While, Application Load Balancer provides a static DNS name but it does NOT provide a static IP. The reason being that AWS wants your Elastic Load Balancer to be accessible using a static endpoint, even if the underlying infrastructure that AWS manages changes.)

You are running a website on 10 EC2 instances fronted by an Elastic Load Balancer. Your users are complaining about the fact that the website always asks them to re-authenticate when they are moving between website pages. You are puzzled because it's working just fine on your machine and in the Dev environment with 1 EC2 instance. What could be the reason?
 - The Elastic Load Balancer does not have a sticky session enabled
 
You hosted an application on a set of EC2 instances fronted by an Elastic Load Balancer. A week later, users begin complaining that sometimes the application just doesn't work. You investigate the issue and found that some EC2 instances crash from time to time. What should you do to protect users from connecting to the EC2 instances that are crashing?
 - Enable ELB health checks. (When you enable ELB Health Checks, your ELB won't send traffic to unhealthy (crashed) EC2 instances.)



There is an Auto Scaling Configured running in eu-west-2 region, that is configured to spawn into two Availability Zones eu-west-2a and eu-west-2b. Currently, 3 EC2 instances are running in eu-west-2a and 4 EC2 instances are running in eu-west-2b. The ASG is about to scale in. Which EC2 instance will get terminated?

An application is deployed with an Application Load Balancer and an Auto Scaling Group. Currently, you manually scale the ASG and you would like to define a Scaling Policy that will ensure the average number of connections to your EC2 instances is around 1000. Which Scaling Policy should you use?
 - Target Tracking Policy

Your application hosted on EC2 instances managed by an Auto Scaling Group suddenly receives a spike in traffic which triggers your ASG to scale out and a new EC2 instance has been launched. The traffic continuously increases but the ASG doesn't launch any new EC2 instances immediately but after 5 minutes. What is a possible cause for this behavior?
 - Cooldown period (For each Auto Scaling Group, there's a Cooldown Period after each scaling activity. In this period, the ASG doesn't launch or terminate EC2 instances. This gives time to metrics to stabilize. The default value for the Cooldown Period is 300 seconds (5 minutes).
)

A company has an Auto Scaling Group where random EC2 instances suddenly crashed in the past month. They can't troubleshoot why the EC2 instances crash as the ASG terminates the unhealthy EC2 instances and replaces them with new EC2 instances. What will you do to troubleshoot the issue and prevent unhealthy instances from being terminated by the ASG?
 - Use ASG Lifecycle hooks to pause the EC2 instance in the terminating state for troubleshooting 
 
