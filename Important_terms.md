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