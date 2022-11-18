# Data and Analytics

## Athena
 - Serverless query service to analyze data stored in Amazon S3
 - Uses standard SQL Language to query the files (built on Presto)
 - Supports CSV, JSON, ORC, Avro, Parquet
 - Pricing: 5$ per TB of data scanned
 - Commonly used with Amazon QuickSight for reporting/dashboards
 - Use Cases: Business Intelligence/ Analytics/ Reporting Analyze and query VPC flow logs, ELB Logs, CloudTrail trails, etc
 - Examp Tip: Analyze data in S3 using serverless SQL, use Athena
 
## Athena - Improve performance while using Athena Tips
 - Use Columnar data for cost savings (less scans)
   - Apache Parquet or ORC is recommended
   - Huge Performance improvement
   - Use Glue to convert your data into Parquet or ORC
 - Compress data for smaller retrieval (bzip2, gzip, iz4, snappy, zlip, zstd)
 - Partition datasets in S3 for easy querying on virtual columns
```cmd  
  s3://yourBucket/pathToTable/
                           /<PARTITION_COLUMN_NAME>=<VALUE>
                            /<PARTITION_COLUMN_NAME>=<VALUE>                             
  
  Example: s3://athena-examples/flights/parquet/year=1991/month=10/day=1
```
 - Use Larger files  (>128MB) to minimize overhead


## RedShift

### Overview
 - RedShift is based on PostgreSql, but its not used for OLTP
 - Its OLAP - Online Analytical Processing (Analytics and data warehousing)
 - 10x better performance than other data warehouses, scale to petabytes of data
 - Columnar storage of data (instead of row based) and parallel query engine
 - Pay as you go based on the instances provisioned
 - Has a SQL interface for performing the queries
 - BI Tools such as AMazon QUickSight or Tableau integrate with it
 - Redshift vs Athena: faste queries/joins/aggregations thanks of indexes

### RedShift Cluster
 - Leader Node: for query planning, rsults aggregation
 - Compute Node: for performing the queries, send results to leader
 - You provision the node size in advance
 - You can use reserved instances for cost savings

### Snapshots and Disaster recovery
 - Redshift as no Multi-AZ mode
 - Snapshots are Point in Time backups of a cluster, stored internally in S3
 - Snapshots are incremental (Only what has changed is saved)
 - You can restore a snapshot into a new cluster
 - Automated: Every 8 hours, Every 5 GB, or on a schedule. Set Retention
 - Manual: Snapshot is retained until you delete it
 - You can configure Amazon Redshift to automatically copy snapshots (Automated or Manual) of a cluster to another AWS Region

### Loading data into Redshift: Large inserts are much better
 - Amazon Kinesis Data Firehose
   - Kinesis Data Firehose to Redshift cluster (through S3 copy)
 - S3 using COPY command
   - Through Internet without using Enhanced VPC routing
   - Through VPC with Enhanced VPC routing
 - EC2 instance JDBC driver
   - Ec2 instance to Amazon Redshift cluster (Better to write data in batches)

### Redshift Spectrum
 - Query data that is already in S3, without loading it
 - Must have a Redshift cluster available to start the query
 - The query is then submitted to thousands of Redshift spectrum nodes

## OpenSearch

### Overview
 - Amazon Opensearch is successor to ElasticSearch and name change was due to some licensing issue
 - In DynamoDB, queries only exist by primary keys or indexes
 - With OpenSearch, you can search any field, even partially matches
 - Its common to use OOpenSearch as a Complement to another database
 - OpenSearch requires a cluster of instances (not serverless)
 - Does not support SQL (It has its own query language)
 - Ingestion from Kinesis data firehose, AWS IOT, and CLoudwatch Logs
 - Security through Cognito and IAM, KMS encryption, TLS
 - Comes with OpenSearch Dashboard (Visualization)

### OpenSearch Patterns 
 - DynamoDB 
   - CRUD -> DynamoDB Table -> DynamoDB Stream -> Lambda -> Amazon Opensearch -> API to search items -> and finally the details data is fetched from DynamoDB by using the id returned from ElasticSearch
 - CloudWatch Logs
   - CloudWatch Logs -> Subscription filter -> Lambda Function -> OpenSearch (Real time)
   - CloudWatch Logs -> Subscription filter -> Kinesis data firehose -> OpenSearch (Near Real time)
 - Kinesis Data streams and data Firehose

## EMR

### Overview
 - EMR stands for Elastic MapReduce
 - EMR helps creating Hadoop clusters (Big Data) to analyze and process vast amount of data
 - The clusters can be made of hundreds of EC2 instances
 - EMR comes bundled with Apache Spark, HBase, Presto, Flink
 - EMR takes care of all the Provisioning and configuration
 - Auto-scaling and Integrated with spot instances
 - Use Cases: Data processing, Machine learning, Web Indexing, Big Data

### Node types and purchasing
 - Master Node: Manage the cluster, coordinate, manage health - long running
 - Core Node: Run Tasks and store data - long running
 - Task Node (Optional): Just to run tasks  - usually spot
 - Purchasing Options:
   - On demand: reliable, predictable, wont be terminated
   - Reserved (Minimum 1 year): Cost savings (EMR will automatically use if available)
   - Spot instances: Cheaper, can be terminated, less reliable
 - Can have long running cluster, or transient (temporary) cluster

## QuickSight

### Overview
 - Serverless machine learning powered business intelligence service to create interactive dashboards
 - Fast, automatically scalable, embeddable, with per session pricing
 - Use cases:
   - Business Analytics
   - Building Visualizations
   - Perform Ad-hoc analysis
 - Integrated with RDS, Aurora, Athena, Redshift, S3
 - In Memory computation using SPICE enhine if data is imported into QuickSight
 - Enterprise edition: Possibility to setup Column level Security (CLS)
 
### Integrations
 - AWS Services
   - RDS, Aurora, RedShift, Athena, S3, OpenSearch, TimeStream
 - Data Sources (SaaS)
   - Salesforce, Jira, etc
 - Third party databases
   - On-premises databases (JDBC)
   - Teradata, 
 - Imports via data sources
   - XLSX, CSV, JSON, .TSV, ELF and CLF (Log format)
   
### Dashboards and Analysis
 - Define users (standard versions) and Groups (enterprise versions)
   - These users and groups exist within Quicksight, not IAM !!
 - A Dashboard
   - is a readonly snapshot of an analysis that you can share
   - preserves the configuration of the analysis (Filtering, parameters, control, sort)
 - You can share the Analysis or the dashboards with Users or Groups
 - To share a dashboard, you must first publish it
 - Users who see the dashboard can also see the underlying data

## Glue

### Overview
 - Managed Extract, Transform and Load (ETL) service
 - Useful to prepare and transform data for analytics
 - Fully serverless service
 - Glue Job Bookmarks: prevent reprocessing old data
 - Glue Elastic views: 
   - Combine and replicate data across multiple data stores using SQL
   - No custom code, Glue monitors for changes in the source data, serverless 
   - Leverages a "Virtual table" (Materialized view)
 - Glue DataBrew: Clean and Normalize data using pre-built transformation
 - Glue Studio: new GUI to create, run and monitor ETL jobs in Glue
 - Glue streaming ETL (built on apache spark structured streaming): Compatible with Kinesis data streaming, Kafka, MSK (managed kafka)

### Convert dsta into Parquet format
 - 

### Glue data catalog: catalog of datasets
 - 

## Lake formation
 - Data Lake = central place to have all your data for analytics purpose
 - Fully Managed service that makes it easy to setup a data lake in days
 - Discover, Cleanse, transform and ingest data into your data lake
 - It automates many complex manual steps (Collecting, Cleansing, moving, cataloging data, etc) and de-duplicate (using ML transforms)
 - Combine structured and unstructured data in the data lake
 - Out of the box source blueprints: S3, RDS, Relational and NoSQL DB
 - Fine grained Access control for your applications (row and column level)
 - Built on top of AWS Glue
 - Use case: Centralized permissions example

## Kinesis Data analytics

### Kinesis Data analytics For SQL Applications
 - Real time analytics for Kinesis data streams and firehose using SQL
 - Add reference data from Amazon S3 to enrich data streaming data
 - Fully managed, no servers to provision
 - Automatic scaling
 - Pay for actual consumption rate
 - Output:
   - Kinesis Data streams: create streams out of the real-time analytics queries
   - Kinesis data firehose: send analytics quey results to destinations
 - Use cases:
   - Time series analytics
   - Real time dashboards
   - Real time metrics
 
### Kinesis Data analytics For Apache Flink
 - Use Flink (Java, Scala or SQL) to process and analyze streaming data
 - Run any Apache Flink application on a managed cluster on AWS
   - provisioning compute resources, parallel computation, automatic scaling
   - application backups (implemented as checkpoints and snapshots)
   - Use any apache flink programming features
   - Flink does not read from Firehose (Use Kinesis Analytics for SQL instead)   

## MSK - Managed Streaming for Apache Kafka

### Overview
 - Alternate to Amazon kinesis
 - Fully managed Apache Kafka on AWS
   - Allow you to create/update/delete clusters
   - MSK creates and manages kafka broker nodes and zookeeper nodes for you
   - Deploy the MSK cluster in your VPC, multi-AZ (up to 3 for High Availability)
   - Automatic recovery from common apache kafka failures
   - Data is tored on EBS volumes for as long as you want
 - MSK serverless
   - Run Apache kafka on MSK without Managing the capacity
   - MSK automatically provisions resources and scales compute and storage

### Kinesis data streams vs MSK
 - Kinesis data streams
   - 1 MB message size limit
   - Data streams with shards
   - shard splitting and Merging
   - TLS in-flight encryption
   - KMS at-rest encryption
 - MSK
   - 1 MB default, configure for higher (Ex: 10 MB)
   - kafka topics with partitions
   - can only add prtitions to a topic
   - PLAINTEXT or TLS in-flight encryption
   - KMS at-rest encryption

### MSK cosumers
 - Kinesis data analytics for Apache Flink
 - AWS Glue, Streaming ETL Jobs, Powered by Apache spark streaming
 - Lambda
 - Custom (Applications running on EC2, ECS, EKS)

## Big Data ingestion pipelines

### Requirements
 - We want the ingestion pipeline to be fully serverless
 - We want to collect data in real time
 - We want to transform the data
 - We want to query the transformed data using SQL
 - The reports created using the queries should be in S3
 - We want to load the data into a warehouse and create dashboards

### Solution
 - IOT core allows you to harvest data data from IoT devices
 - Kinesis is great for real-time data collection
 - Firehose helps with data delivery to S3 in near real-time (1 minute)
 - Lambda can help Firehose with data transformations
 - Amazon S3 can trigger notifications to SQS
 - Lambda can subscribe to SQS (We could have connector S3 to Lambda)
 - Athena is a serverless SQL service and results are stored in S3
 - The reporting bucket contains analyzed data and can be used by reporting tool such as AWS quicksight, redshift etc
