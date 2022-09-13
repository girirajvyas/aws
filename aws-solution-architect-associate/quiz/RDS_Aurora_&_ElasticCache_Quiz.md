1. Amazon RDS supports the following databases, EXCEPT: 
a. MongoDB
b. MySQL 
c. MariaDB
d. Microsoft SQL server
Ans: A, RDS supports MySQL, PostgreSQL, MariaDB, Oracle, MS SQL Server, and Amazon Aurora.

2. You're planning for a new solution that requires a MySQL database that must be available even in case of a disaster in one of the Availability Zones. What should you use?
a. create Read replicas
b. Enable Encryption
c. Enable Multi-AZ
Ans: C, Multi-AZ helps when you plan a disaster recovery for an entire AZ going down. If you plan against an entire AWS Region going down, you should use backups and replication across AWS Regions.

3. We have an RDS database that struggles to keep up with the demand of requests from our website. Our million users mostly read news, and we don't post news very often. Which solution is NOT adapted to this problem?
a. An ElasticCache server
b. RDS Multi-AZ
c. RDS read replicas
Ans: b, Be very careful with the way you read questions at the exam. Here, the question is asking which solution is NOT adapted to this problem. ElastiCache and RDS Read Replicas do indeed help with scaling reads.

4. You have set up read replicas on your RDS database, but users are complaining that upon updating their social media posts, they do not see their updated posts right away. What is a possible cause for this?
a. there must be a bug in your application
b. Read replicas have asynchronous replication, therefore its likely your users will only read Eventual Consistency 
c. You should have setup multi-AZ instead
Ans: b

5. Which RDS (NOT Aurora) feature when used does not require you to change the SQL connection string?
a. Multi-AZ
b. Read Replicas
Ans: a, Multi-AZ keeps the same connection string regardless of which database is up.
b,is incorrect because Read Replicas add new endpoints with their own DNS name. We need to change our application to reference them individually to balance the read load

6. Your application running on a fleet of EC2 instances managed by an Auto Scaling Group behind an Application Load Balancer. Users have to constantly log back in and you don't want to enable Sticky Sessions on your ALB as you fear it will overload some EC2 instances. What should you do?
a. use your own custom load balancer on EC2 instances instead of using ALB
b. store session data in RDS
c. store session data in elastiCache
d. store session data in shared EBS volume
Ans: c, Storing Session Data in ElastiCache is a common pattern to ensuring different EC2 instances can retrieve your user's state if needed.

7. An analytics application is currently performing its queries against your main production RDS database. These queries run at any time of the day and slow down the RDS database which impacts your users' experience. What should you do to improve the users' experience?
a. Setup a read replicas
b. Setup Multi-AZ
c. Run the analytics query at night
Ans: a, Read Replicas will help as your analytics application can now perform queries against it, and these queries won't impact the main production RDS database.

8. You would like to ensure you have a replica of your database available in another AWS Region if a disaster happens to your main AWS Region. Which database do you recommend to implement this easily?
a. RDS read replicas
b. RDS Multi-AZ
c. Aurora Read Replicas
d. Aurora Global Databases
Ans: d, Aurora Global Databases allows you to have an Aurora Replica in another AWS Region, with up to 5 secondary regions.

9. How can you enhance the security of your ElastiCache Redis Cluster by forcing users to enter a password when they connect?
a. Use Redis Auth
b. Use IAM Auth
c. Use security groups
Ans: a

10. Your company has a production Node.js application that is using RDS MySQL 5.6 as its database. A new application programmed in Java will perform some heavy analytics workload to create a dashboard on a regular hourly basis. What is the most cost-effective solution you can implement to minimize disruption for the main application?
a. Enable Multi-AZ for the RDS database and run the analytics workload on the standby database
b. Create a Read replica in a different AZ and run the analytics workload on the replica database
c. Create a Read replica in a different AZ and run the analytics workload on the source database
Ans: b

11. You would like to create a disaster recovery strategy for your RDS PostgreSQL database so that in case of a regional outage the database can be quickly made available for both read and write workloads in another AWS Region. The DR database must be highly available. What do you recommend?
a. Create a read replica in the same region and enable multi-AZ on the main database
b. Create a Read replica in different region and enable multi-AZ on the read replica
c. Create a Read Replica in the same region and enable multi-AZ on the read replica
d. Enable multi region option on the main database
Ans: b

12. You have migrated the MySQL database from on-premises to RDS. You have a lot of applications and developers interacting with your database. Each developer has an IAM user in the company's AWS account. What is a suitable approach to give access to developers to the MySQL RDS DB instance instead of creating a DB user for each one?
a. By Default IAM users have access to your RDS database
b. Use Amazon Cognito
c. Enable IAM database Authentication
Ans: c

13. Which of the following statement is true regarding replication in both RDS Read Replicas and Multi-AZ?
a. Read replica uses Asysnchronous Replication and Multi-AZ uses Asynchronous replication
b. Read replica uses Asysnchronous Replication and Multi-AZ uses Synchronous replication
c. Read replica uses Sysnchronous Replication and Multi-AZ uses Synchronous replication
d. Read replica uses Sysnchronous Replication and Multi-AZ uses Asynchronous replication
Ans: b

14. How do you encrypt an unencrypted RDS DB instance?
a. do it straight from AWS console, Select your RDS DB instance, choose Actions then Encrypt using KMS
b. Do it straight from AWS console, after stopping the RDS DB instance
c. Create a snapshot of the unencrypted RDS DB Instance, copy the snapshotand tick "Enable encryption", then restore the RDS DB instance from the encrypted snapshot
Ans: c

15. For your RDS database, you can have up to ............ Read Replicas.
a. 3
b. 5
c. 7
Ans: b

16. Which RDS database technology does NOT support IAM Database Authentication?
a. Oracle
b. PostgreSQL
c. MySQL
Ans: a

17. You have an un-encrypted RDS DB instance and you want to create Read Replicas. Can you configure the RDS Read Replicas to be encrypted?
a. no
b. Yes
Ans: a, You can not create encrypted Read Replicas from an unencrypted RDS DB instance.

18. An application running in production is using an Aurora Cluster as its database. Your development team would like to run a version of the application in a scaled-down application with the ability to perform some heavy workload on a need-basis. Most of the time, the application will be unused. Your CIO has tasked you with helping the team to achieve this while minimizing costs. What do you suggest?
a. Use an Aurora Global database
b. Use an RDS database
c. Use Aurora Serverless
d. Run Aurora on EC2, and write a script to shutdown the EC2 instance at Night

19. How many Aurora Read Replicas can you have in a single Aurora DB Cluster?
a. 5
b. 10
c. 15
Ans: c

20. Amazon Aurora supports both .......................... databases.
a. MySQL and MariaDB
b. MySQL and PostgreSQL
c. Oracle and MariaDB
d. Oracle and MS SQL Server
Ans: b

21. You work as a Solutions Architect for a gaming company. One of the games mandates that players are ranked in real-time based on their score. Your boss asked you to design then implement an effective and highly available solution to create a gaming leaderboard. What should you use?
a. use RDS for MySQL
b. Use an Amazon Aurora
c. Use ElastiCache for Memcached
d. Use ElastiCache for Redis - Sorted Set
Ans: d

 



