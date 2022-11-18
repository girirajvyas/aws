## RDS Read replicas vs RDS Multi AZ (Availability zone)

### RDS Read replicas
 - upto 5 read replicas
 - Repliction are ASYNC i.e they are eventually consistent
 - Application must update the connection string to access read replicas

**Use case:**
 - Reporting application to run some analytics
 - Read replicas for SELECT statement only

**Network cost:**
 - In same region, dont pay fee for replication in same region
 - Across region replication fee is applicable
 
### RDS Multi AZ (Disaster Recovery)
 - SYNC Replication
 - One DNS name - Automatic App failover to standby
 - this Automatic failover increases availability
 - Automatic failover scenarios:
    - Loss of Availability Zone
    - Loss of Instance or
    - Loss of STorage
 - No manual intervention is required as its all automatic
 - It cannot be used for scaling as it is always on standby and cannot be accessed directly

**RDS: Move from single AZ to Multi AZ**  
How?  
 - Zero down time operation
 - can be done via clicking modify

What happens internally?   
 - A snapshot is taken
 - A new DB is restored from snapshot in a new AZ
 - Synchronization is establisdhed between the 2 DB

## Amazon Aurora
Advanced:
 - Aurora Replicas - Auto Scaling
 - Aurora - Custom endpoints
   - Define a subset of aurora instances as a custom endpoints
   - Example: Run analytical queries on specific replicas 
   - Reader endpoint is generally not used after creating custom endpoint
 - Aurora - Serverless
   - Automated database instantiation and auto-scaling based on actual usage
   - Good for infrequent, intermittent or unpredictable workloads
   - No capacity planning needed
   - Pay per second, can be more cost-effective
 - Aurora Multi master
   - In case we need immediate failover for write node (High Availability)
   - Every Node does R/W - vs promoting a Read Replica (RR) as the new master
 - Global Aurora
   - Aurora Cross region read replicas
     - Useful for disaster recovery
     - Simple to put in place
   - Aurora Global Database (Recommended)
     - 1 primary region (read/write)
     - Upto 5 secondary (readonly) regions, replication lag is less than 1 second
     - upto 16 Read Replicas per secondary region
     - Helps for decreasing latency
     - Promoting another region (for disaster recovery) has an Recovery Time Objective (RTO) of < 1 minute
   - Aurora Machine learning
     - Enables you to add ML based predictions to your application via SQL
     - Simple, Optimized and secure integration between Aurora and AWS ML Services
     - Supported Services:
       - Amazon Sage maker (use with any ML Model)
       - Amazon Comprehend (for sentiment analysis)
     - You dont need to have ML experience
     - Use Cases: Fraud detection, Ads targeting, Sentiment analysis, product recommendations

## ElasticCache
 - RDS is to get **managed** Relational Databases, same way Elastic cache is to get **managed** Redis or Memcached
 - Caches are in-memory databases with really high performance, low latency
 - Helps reduce load off databases for read intensive workloads
 - Helps make your application stateless
 - AWS takes care of:
   - OS Maintenance/patching
   - Optimizations
   - Setup
   - configuration
   - Monitoring
   - Failure recovery
   - backups
 - Using ElasticCache involves heavy application code changes

### ElastiCache - Cache security
 - All caches in ElasticCache:
   - Do not support IAM authentication
   - IAM policies on ElasticCache are only used for AWS API-level security
 - REDIS Auth
   - you can set "password/token" when you create Redis cluster
   - This is an extra layer of security for your cache (on top of security groups)
   - Support SSL in flight encryption
 - Memcached
   - Supports SASL-based authentication (advanced)

**Patterns for ElastiCache**  
 - Lazy Loading:
   - All the read data is cached, data can become stale in cache
 - Write through
   - Adds or updates data in the cache when written to a DB (no stale data)
 - Session store
   - Store temporary session data in a cache (using Time to live (TTL) feature)

**Use case - Redis:**
 - Gaming Leaderboards are complutationally complex
 - Redis sorted sets guarantee both uniqueness and element ordering
 - Each time a new element is added, its ranked in real time, then added in correct order
 
 

     
     - 

 


