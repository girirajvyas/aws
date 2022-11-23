## Ports
### Normal ports:
FTP: 21
SSH: 22
SFTP: 22 (same as SSH)
HTTP: 80
HTTPS: 443

### RDS Databases ports:
PostgreSQL: 5432
MySQL: 3306
Oracle RDS: 1521
MSSQL Server: 1433
MariaDB: 3306 (same as MySQL)
Aurora: 5432 (if PostgreSQL compatible) or 3306 (if MySQL compatible)

## DNS
### What is DNS
 - Domain Name system which transaltes the human friendly hostnames into machine ip addresses
 - www.google.com -> 
 - DNS is backbone of internet
 - DNS uses hierarchical naming structure
                .com  
         example.com  
     www.example.com  
     api.example.com  

### DNS Terminologies
 - Domain Registrar: Amazon Route 53, GoDaddy, ..
 - DNS Records: A, AAAA, CNAME, NS
 - Zone File: Contains DNS records
 - Name Server: resolves DNS queries (Authoritative or Non-Authoritative)
 - Top level Domain (TLD): .com, .in, .us, .ai, .gov, .org, ...
 - Second Level Domain (SLD): amazon.com, google.com 
 - Sub-domain: www.example.com
 - Domain Name: api.www.example.com
 - Fully Qualified Domain Name (FQDN): http://api.www.example.com
 ```
    http://api.www.example.com. (last dot is Root )
                           TLD  
                   SLD........
              Sub-domain......
           Domain name........
protocol       
    ......FQDN................ Fully Qualified DOmain Name
  ```

### How DNS Works?

### 5 pillars of well-architected framework
 - Cost
 - Performance
 - Security
 - reliability
 - Operationl Excellence

### AWS SDK
 - If you dont specify or configure a default region, then 'us-east-1' will be chosen by default

## S3

### Replication (CRR and SRR)

Cross Region Replication (CRR)
 - use case: Compliance, Lower latency access, Replication across accounts 

Same Region Replication (SRR)
 - use case: Log aggregation, Live replication between production and test accounts
 
### Storage classes use cases

 - S3 standard: 
   - Big data analytics, Mobile and Gaming applications, content distribution, ect
   - 99.99 % available (53 minutes not available in a year)
 - S3 Standard Infrequent Access:  
   - Disaster recovery
   - 99.9% available
 - S3 One zone Access:  
   - High DUrability (99.999999999) in Single AZ, data is lost when AZ is destroyed
   - 99.5% availability
   - Store secondary copies of on-premise data, or data you can recreate

Lifecycle rules

Unicast vs ANycast IPs

https://medium.com/javarevisited/6-best-aws-solution-architect-associate-certification-practice-test-mock-exams-and-dumps-240c269dab4f

EC2 COsts: https://aws.amazon.com/ec2/pricing/

- https://aws.amazon.com/ec2/pricing/
  - https://aws.amazon.com/ec2/spot/pricing/
- https://aws.amazon.com/lambda/pricing/

- https://aws.amazon.com/ec2/spot/instance-advisor/

Vertical scaling: increase instance size ( = scale up), decrease instance size ( = scale down)
   - from t2.nano (0.5Gb of RAM, 1vCPU) to u-12tbl.metal (12.3 TB of RAM, 448 vCpus) 
 - Horizontal Scaling: increase number of instances ( = scaling out), decrease number of instances (scaling in)


RTO: Recovery Time Objective
RPO: Recovery Point Objective

Boot Volume is a volume where operating system (Windows) stores its system files.

Throttling is the process of limiting the number of requests an authorized program can submit to a given operation in a given amount of time.

Throughput

Point in Time Recovery -  point-in-time recovery
point-in-time restore (PITR)


Blue/green deployment is a technique for releasing applications by shifting traffic between two identical environments running different versions of the application: "Blue" is the currently running version and "green" the new version. This type of deployment allows you to test features in the green environment without impacting the currently running version of your application. When you’re satisfied that the green version is working properly, you can gradually reroute the traffic from the old blue environment to the new green environment. Blue/green deployments can mitigate common risks associated with deploying software, such as downtime and rollback capability.

IPsec (internet protocol security) is a protocol suite for securing IP communications by authenticating and encrypting each IP packet in a data stream.


# Scalability

## Types
 - Vertical scalability means increasing the size of the instance. 
   - For example, your application runs on a t2.micro. 
   - *Scaling up* that application vertically means running it on a larger instance such as t2.large. 
   - *Scaling down* that application vertically means running it on a smaller instance such as t2.nano. 
   - Scalability is very common for non-distributed systems, such as a database. There’s usually a limit to how much you can vertically scale (hardware limit). 
   - Example can be the instance type was upgraded from t2.nano to u-12tb1.metal, this is a scale-up example of vertical scalability.
 - Horizontal Scalability means increasing the number of instances/systems for your application.
   - When you increase the number of instances, it's called *scale-out*, whereas if you decrease the number of instances, it's called *scale-in*. 
   - Scale-up is used in conjunction with vertical scaling and not with horizontal scaling. 

# Availability
 - High availability means running your application/system in at least 2 data centers (== Availability Zones). 
 - The goal of high availability is to survive a data center loss. An example of High Availability is when you run instances for the same application across multi AZ.