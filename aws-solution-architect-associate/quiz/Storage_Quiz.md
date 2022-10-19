1. You need to move hundreds of Terabytes into Amazon S3, then process the data using a fleet of EC2 instances. You have a 1 Gbit/s broadband. You would like to move the data faster and possibly processing it while in transit. What do you recommend?
a. Use your network
b. Use snowcone
c. Use AWS datamigration
d. Use Snowball edge
Ans:
d, Correct, Snowball Edge is the right answer as it comes with computing capabilities and allows you to pre-process the data while it's being moved into Snowball.

2. You want to expose virtually infinite storage for your tape backups. You want to keep the same software you're using and want an iSCSI compatible interface. What do you use?
a. AWS SNowball
b. AWS Storage Gateway - Tape Gateway
c. AWS Storage Gateway - Volume Gateway
d. AWS Storage Gateway - File Gateway
Ans:
b, Correct

3. Your EC2 Windows Servers need to share some data by having a Network File System mounted on them which respects the Windows security mechanisms and has integration with Microsoft Active Directory. What do you recommend?
a. Amazon FSx for windows (FIle Server)
b. Amazon EFS
c. Amazon FSx for Lustre
d. Amazon S3 with File gateway
Ans: 
a, Correct

4. You have hundreds of Terabytes that you want to migrate to AWS S3 as soon as possible. You tried to use your network bandwidth and it will take around 3 weeks to complete the upload process. What is the recommended approach to using in this situation?
a. AWS Storage Gateway - Volume Gateway
b. S3 Multi-part Upload
c. AWS Snowball Edge
d. AWS Data Migration Service
Ans:
c, Correct

5. You have a large dataset stored in S3 that you want to access from on-premises servers using the NFS or SMB protocol. Also, you want to authenticate access to these files through on-premises Microsoft AD. What would you use?
a. AWS Storage Gateway - Volume Gateway
b. AWS Storage Gateway - File Gateway
c. AWS Storage Gateway - Tape Gateway
d. AWS Data Migration Service
Ans: 
b, Correct

6. You are planning to migrate your company's infrastructure from on-premises to AWS Cloud. You have an on-premises Microsoft Windows File Server that you want to migrate. What is the most suitable AWS service you can use?
a. Amazon Fsx for Windows (File Server)
b. AWS STorage Gateway - File Gateway
c. AWS managed Microsoft AD
Ans:
a, Correct

7. You would like to have a distributed POSIX compliant file system that will allow you to maximize the IOPS in order to perform some High-Performance Computing (HPC) and genomics computational research. This file system has to easily scale to millions of IOPS. What do you recommend?
a. EFS with MAX IO enabled
b. Amazon FSx with Lustre
c. Amazon S3 mounted on the EC2 instances
d. EC2 instance store
Ans: 
b, correct

8. Which deployment option in the FSx file system provides you with long-term storage that's replicated within AZ?
a. Scratch file system
b. persistent file system
Ans:
b, Correct, Provides long-term storage where data is replicated within the same AZ. Failed files were replaced within minutes.

9. Which of the following protocols is NOT supported by AWS Transfer Family?
a. File Transfer Protocol (FTP)
b. File Transfer Protocol over SSL (FTPs)
c. Transport Layer Security (TLS)
d. Secure File Transfer Protocol (SFTP)
Ans:
c, Incorrect, AWS Transfer Family is a managed service for file transfers into and out of S3 or EFS using the FTP protocol, thus TLS is not supported.

10. 

