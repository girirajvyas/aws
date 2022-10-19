# Databases

## Choose Database
 - We have a lot of Managed databases on AWS to choose from
 - Questions to ask to choose the right database based on your architecture
   - Read-heavy, write-heavy or balanced workload?
     - Throughput needs?
     - Will it change, does it need to scale or fluctuate during the day?
   - How much data to store and for how long?
     - Will it grow?
     - Average Object size
     - How are they accessed
   - Data Durablity?
     - Source of truth for the data?
   - Latency requirements?
     - Concurrent users
   - Data Model?
     - How will you query the data?
     - Joins?
     - Structured?
     - Semi-structured
   - Strong Schema?
     - More flexibility?
     - Reporting?
     - Search?
     - RDBMS?
     - NoSQL?
   - License Costs
     - Switch to cloud native DB such as Aurora

## Database Types:
 - RDBMS (=SQL/OLTP):  great for Joins
   - RDS
   - Aurora
 - NoSQL Databases: no joins, no SQL 
   - DynamoDB (~JSON)
   - ElastiCache (key/value pairs)
   - Neptune (graphs)
   - DocumentDB (for MongoDB)
   - KeySpaces (for Cassandra)
 - Object Store: 
   - S3 (for big objects)
   - Glacier (for backups/archives)
 - Data Warehouse (= SQL Analytics/BI )
   - Redshift (OLAP)
   - Athena
   - EMR
 - Search 
   - OpenSearch (JSON) - Free text, unstructured searches
 - Graphs
   - Amazon Neptune - displays relationship between data
 - Ledger
   - Amazon Quantum Ledger Database
 - Time Series
   - Amazon Timestream

## Amazon RDS
 - Managed PostgreSQL/MySQL/Oracle/SQL Server/MariaDB/Custom
 - Provisioned RDS instance size and EBS Volume Type and size
 - Auto-scaling capability for storage
 - Support for Read Replicas and Multi AZ
 - Security through IAM, Security groups, KMS, SSL in transit
 - Automated Backup with point in time restore feature (up to 35 days)
 - Manual DB snapshot for long-term recovery
 - Managed and scheduled maintenance ( with downtime)
 - Support for IAM Authentication, Integration with secrets manager
 - RDS Custom for Access to and customize the underlying instance (Oracle and SQL Server)
 - **Use Case:** Store relational datasets (RDBMS/OLTP), perform SQL queries, transactions
 
## Amazon Aurora
 - Compatible API for PostgreSQL/MySQL, Saperation of storage and compute
 - Storage data is stored in 6 replicas, across 3 AZ - Highly Available, Self-healing, Auto-scaling
 - **Compute:** Cluster of DB instances across Multiple AZ, Ato scaling of read replicas
 - **Cluster:** Custom endpoints for Writer and reader DB instances
 - Same security/monitoring/maintenance features as RDS
 - Know the backup and restore options for Aurora
 - **Aurora Serverless:** For unpredictable/intermittent workloads, no capacity planning
 - **Aurora Multi-Master:** For continous writes failover (high write availability)
 - **Upto 16 DB Read instances in each region, < 1 second storage replication
 - **Aurora Machine learning:** Perform ML using SageMaker & Comprehend on Aurora
 - **Aurora Database Cloning:** New CLuster from existing one, faster than restoring a snapshot
 - **Use Case:** Same as RDS, but with less maintenance/More flexibility/more performance/more features
 
## ElastiCache
 - Managed Redis/Memcached (Similar offering as RDS, but for caches)
 - In-memory data store, sub millisecond latency
 - Must provision an EC2 instance type
 - Support for Clustering (Redis) and Multi AZ, Read Replicas (sharding)
 - Security through IAM, Security Groups, KMS, Redis Auth
 - Backup/Snapshot/Point in time restore feature
 - Managed and scheduled maintenance
 - **Requires some application code changes to be leveraged**
 - **Use Case:** Key/Value store, Frequent reads, Less writes, Cache results for DB queries, store session data for websites, Cannot use SQL

## DynamoDB
 - AWS proprietary technology, managed serverless NoSQL database, millisecond latency
 - **Capacity modes:** Provisioned capacity with optional auto-scaling or on-demand capacity
 - Can replace ElastiCache as a kkey/value store (storing sessiondata for example, Using TTL feature)
 - high Availability, Multi AZ by default, Read and Writes are decoupled, transaction capability
 - DAX cluster for read cache, microsecond read latency
 - Security, authentication and authorization is done through IAM
 - **Event Processing: ** DynamoDB streams to integrate with AWS Lambda, or kinesis data streams 
 - **Global Table feature:** Active-Active setup
 - Automated backups upto 35 days with PITR (restore to new table), or on demand backups
 - Export to S3 without using RCU within the PITR window, Import from S3 without using WCU
 - **Great to rapidly evolve schemas**
 - **Use case:** Serverless application development (small documents upto 400 kbs), distributed serverless cache, does not have SQL query language available

## S3
 - S3 is a key/value store for objects
 - Great for bigger objects, not so great for many small objects
 - Serverless, Scales infinitely, Max object size is 5TB, versioning capability
 - **Tiers:** S3 standard, S3 infrequent access, S3 intelligent, S3 Glacier + lifecycle policy
 - **Features:** Versioning, Encryption, Replication, MFA-Delete, Access Logs
 - **Security:** IAM, Bucket Policy, ACL, Access points, Object Lamda, CORS, Object/Vault lock
 - **Encryption:** SSE-S3, SSE-KMS, SSE-C, Client-side, TLS in transit, default encryption
 - **Batch operations** on object using S3 batch, listing files using S3 inventory
 - **Performance:** Multi-part upload, S3 transfer acceleration, S3 Select
 - **Automation:** S3 event Notifications (SNS, SQS, Lambda, Event Bridge)
 - **Use Cases:** static files, key value store for big files, website hosting
 
## DocumentDB
 - Like Aurora is an "AWS implementation" of PostgreSQL/MySQL, DocumentDB is same for MongoDB which is a NOSQL DB 
 - MongoDB is used to store, query and index JSON data
 - Similar "deployment concepts" as Aurora
 - Fully Managed, Highly Available with replication across 3 AZ
 - DocumentDB storage automatically grows in increments of 10GB, upto 64TB
 - Automatically scales to workloads with millions of requests per second

## Neptune
 - Fully managed graph Database
 - A popular graph dataset  would be socal network
   - Users have friends
   - Posts have comments
   - Comments have like from users
   - Users share and like posts
 - Highly available across 3 AZ, with upto 15 read replicas
 - Build and run applications working with highly connected datasets - Optimized for these complex and hard queries
 - Can store upto billions of relations and query the graph with milliseconds latency
 - Highly available with replications across Multiple AZs
 - Great for storing knowledge graphs (Wikipedia), Fraud detection, recommendation engines, social networking

## KeySpaces 
 - Apache cassandra is an opensource NoSQL Database and Keyspaces is a managed Apache cassandra-compatible service
 - Serverless, scalable, Highly available, fully managed by AWS
 - Automatically scale table up/down based on applications traffic
 - Tables are replicated 3 times across multiple AZ
 - Using the Cassandra Query Language (CQL)
 - Single digit milliseconds latency at any scale, 1000s of request per second
 - Capacity: On-Demand mode or provisioned mode with auto scaling
 - Encryption, Backup, Point-In-Time Recovery upto 35 days
 - Use cases: Stre IoT devices info, time series data

## Quantum Ledger DB (QLDB)
 - A ledger is a book recording financial transactions
 - Fully managed, serverless, High available, Replication across 3 AZ
 - Used to review history of all the changes made to your application data over time
 - Immutable system: No entry can be removed or modified, cryptographically verifiable
 - 2-3 times better performance than common ledger blockchain frameworks, manipulate data using SQL
 - Difference with Amazon Managed Blockchain: No decentralization in accordance with the financial regulation rules in QLDB

## Timestream
 - Fully managed, Fast, Scalable, Serverless time series database
 - Automatically scales up/down to adjust capacity
 - Store and Analyze trillions of events per day
 - 1000s time faster and 1/10th the cost of relationsal database in case we have time series data
 - Scheduled queries, Mult-measure records, SQL COmpaibility
 - Data Storage tiering: recent data kept in memory and historical data kept in a cost-optimized storage data
 - Built in time series analytics function (help you identify patterns in your data in near real time)
 - Encryption in transit and at rest
 - Use cases: IoT apps, operational applications, real time analytics
 
 

 
 

   

