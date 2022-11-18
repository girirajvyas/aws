# 6. Elastic Load balancing and Auto scaling groups

## 6.1 Scalability and Hiogh Availability

**Basics**  
 - Scalability means that an application/system can handle greater loads by adapting
 - There are two kinds of scalability:
   - Vertical scalability (increase cpu power)
   - Horizontal scalability (=elasticity)
 - Scalability is linked but different to high availability

**Vertical Scalability**  
 - Vertical Scalability means increasing the size of the instance 
   - eg: upgrading from t2.micro to t2.large
 - Vertical Scalability is very common for non distributed systems, such as database
 - There's a limit to how much you can vertically scale (hardware limit)

**Horizontal Scalability**  
 - Horizontal Scalability means increasing the number of instances /systems for your application
 - Horizontal scaling implies distributed systems.
 - This is very common for web applications/modern applications
 - Its easy to horizontally scale thanks to the cloud offerings such as Amazon EC2

**High Availability**  
 - High availability usually goes hand in hand with horizontal scaling
 - High availability means running your application/system in atleast 2 Availability zones
 - The goal of high availability is to survive a data center loss (disaster)

**Summary**  
 - Vertical scaling: increase instance size ( = scale up/down)
   - from t2.nano (0.5Gb of RAM, 1vCPU) to u-12tbl.metal (12.3 TB of RAM, 448 vCpus) 
 - Horizontal Scaling: increase number of instances ( = scaling out), decrease number of instances (scaling in)
   - Auto Scaling Group
   - Load balancer
 - High Availability: Run instances for the same application across multi Availability Zones
   - Auto Scaling Group in Multi AZ mode
   - Load balancer in multi AZ Mode

**Scalability vs Elasticity vs Agility**  

 - Scalability: Ability to accomodate a larger load by making the hardware stronger (i.e scaling up) or by adding nodes (i.e scale out)
 - Elasticity: Once a system ois scalable, elasticity means that there will be some auto-scaling so that the system can scale based on the load. this is "cloud-friendly" i.e pay-per-us, match demand, optimize costs.
 - Agility(not related to scalability <ins>distractor</ins>): new IT resources are only a click away, which means that you reduce the time to make those resources available to your developers from weeks to just minutes.

## 6.2 Elastic load balancing (ELB) Overview

**What is load balancing**  
 - Load balancers are servers that forward internet traffic to multiple servers (ec2 instances) downstream

**Why use Load balancers**  
 - Spread load across multiple downstream instances
 - Expose a single point of access (DNS) to your application
 - Seamlessly handle failures of downstream instances
 - Do regular health checks to your instances
 - Provide SSL termination (Https) for your websites
 - High avaialbility across zones

**Why use ElastiC Load balancers (ELB)**  

 - An ELB is a managed load balancer
   - AWS guarantees that it will be working
   - AWS takes care of upgrades, maintenance, high avaialbility
   - AWS provides only a few configurations knob
 - It costs less to setup your own load balancer but it will be a lot more effort on your end (maintenance, integrations)
 - 3 kinds of load balancer:
   - Application Load Balancer (HTTP/HTTPS only) - Layer 7
   - Network Load Balancer (ultra-high performance, allows for TCP) = Layer 4
   - Classic Load Balancer (retiring soon) - Layer 4 and 7 (Previous generation)

## 6.3 Application Load Balancer hands on

 - Create 2 EC2 instances with the user data script to differentiate between them
 - First create 1 instance -> right click -> "Launch more like this" -> Edit instance details -> change subnet i.e change the zone now so that it is highly available
 - Verify both the instances are up via hitting the public ip
 - EC2 -> LOAD BALANCING -> Load Balancers -> Create Load balancer -> Select Application Load Balancer as we are using http over browser-based
 - 1. Configure load balancer: Provide Name and all the avaialbility zones 
 - 2. Configure security settings: it suggests to have https, we ignore for now
 - 3. Configure Security groups: create new security group -> next 
 - 4. Configure Routing: Create new target group via giving name
 - 5. Register Targets: Select the instances and use Add to registered to add them
 - 6. review and create
 - It takes some time to create, 
 - Now, use DNS name to verify if it is working and you can refresh the page to verify if it is sending request to different servers
 - In case you stop one of the instance, then elb will redirect all the calls to the instance that is up (I got 502 bad gateway once and then it was flawless)

## 6.4 Auto Scaling Groups (ASG)

**Whats an Auto Scaling Group**
 - In real life, the load on your websites and application can change
 - In the cloud, you can create and get rid of servers very quickly
 - The goal of an AutoScaling Group (ASG) is to:
   - Scale out (Add EC2 instances) to match an increased load
   - Scale in (remove EC2 instances) to match a decreased load
   - Ensure we have a minimum and a maximum number of machines running
   - Automatically register new instances to a load balancer
   - Replace unhealthy instances
 - Cost savings: Only run at an optimal capacity (principal of the cloud)
 - Define minimum size, Actual size/ Desired capacity, Maximum size of your ASG
 - Load balancer automatically registers the instances added by ASG

**Hands on**  
 - EC2 -> AUTO SCALING -> Auto Scaling Groups -> Create Auto Scaling Group 
 - 1. Choose launch template or configuration: Provide name
   - As we donot have Launch template, Create a launch template. This helps in defining the type of instances we want ASG to spinoff
     - Launch Template name
     - Amazon Machine Image (AMI): Amazon Linux 2 AMI
     - Instance type: t2.micro
     - Key pair : use laready created key
     - Network settings: Virtual Private Cloud (VPC)
     - Security groups: use already created SG
     - Storage: Default
     - Expand Advanced settings -> use User data
   - Use the template created above -> Next
 - 2. Configure Settings
   - Instance Purchase Options: Adhere to launch template
   - Network -> Subnets: select all for higher availability -> Next
 - 3. Configure Advanced options
   - Load balancing -> Attach to an existing load balancer -> Choose from Application or network load balancer target groups -> select the target group already created
   - Health checks: check ELB option as well -> Next
 - 4. Configure group size and scaling options
   - Desired Capacity: 2 (number of instances that will be created)
   - Minimum Capacity: 1
   - Maximum Capacity: 4
   - Scaling policies: None
   - Next
 - 5. Add Notifications: Next
 - 6. Add tags: Next
 - Create Auto Scaling Group
 - Now you can go to Instance Management section to verify if the instances are created
 - Verification:
   - You can verify that 2 instances are created in the INSTANCES -> instances section
   - They are registerd in the target groups via LOAD BALANCING -> Target Groups
   - Open the DNS from load balancer and see it gives you the result

## 6.5 Summary

 - High Availability vs Scalability (Vertical and horizontal) vs Elasticity vs Agility in the cloud
 - Elastic Load Balancers (ELB)
   - Disctribute traffic across backend EC2 instances, can be Multi-AZ
   - Supports health checks
   - 3 types: Application LB (HTTP-L7), Network LB (TCP-L4), Classic LB (old)
 - Auto Scaling Groups (ASG)
   - Implement Elasticity for your application, across multiple AZ
   - Scale EC2 instances based on the demand on your system, replace unhealthy
   - Integrated with the ELB

## 6.6 Quiz

Which load balancer should you use to handle hundreds of thousands of connections with low latency
 - Network Load balancer: A network load balancer can handle millions of requests per second with low-latency. It operates at Layer 4 and is best suited for load-balancing TCP, UDP and TLS traffic with ultra high-performance


## 6.7 Resources

 - https://docs.aws.amazon.com/elasticloadbalancing/latest/userguide/what-is-load-balancing.html?icmpid=docs_elbv2_console#elb-features
 - Instance types and comparison: https://instances.vantage.sh/