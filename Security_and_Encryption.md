# AWS Security and Encryption

## Encryption

### What is Encryption?


### Types
 - In Flight
 - At rest - server side
 - client side
 
### In Flight Encryption
 - Data is encrypted before sending and dcrypted after receiving 
 - SSL certificates help with encryption (HTTPS)
 - Encryption in flight ensures no MITM (Man in the middle) can happen

### At rest Encryption - Server side 
 - Data is encrypted after being received by the server
 - Data is decrypted before being sent
 - It is stored in an encrypted form thanks to a key (usually a data key)
 - The encryption/decryption keys must be managed somewhere and the server must have access to it

### Client side
 - Data is encrypted by the client and never decrypted by the server
 - Data will be decrypted by a receiving client
 - The server should not be able to decrypt the data
 - Could leverage envelope encryption
  
## KMS

### Overview
 - Any time you hear "encryption" for an AWS service, Its most likey KMS
 - AWS manages encryption keys for us
 - Fully integrated with IAM for authorization
 - Easy way to control access to your data
 - Able to audit KMS key usage using CloudTrail
 - Seamlessly integrated into most AWS services (EBS, S3, RDS, SSM)
 - Never ever store your secrets in plaintext, especially in your code
   - KMS key encryption also available therough API calls (SDK, CLI)
   - Encrypted secrets can be stored in the code/environment variables
 
### KMS key types
 - KMS keys is the new name of "KMS customer master key"
 - Symmetric (AES-256 keys)
   - Single encryption key that is used to Encrypt and Decrypt
   - AWS services that are integrated with KMS use Symmetric CMKs
   - You may never get access to the KMS key unencrypted (must call KMS APi to use)
 - Asymmetric (RSA and ECC key pairs)
   - Public (Encrypt) and private (Decrypt) key pair
   - Used for Encrypt/decrypt or sign/verify operations
   - The public key is downloadable, but you cant access th private key unencrypted
   - Use case: encryption outside of AWS by users who cant call the KMS API
 - Three types:
   - AWS Managed key: free (aws/service-name. example: aws/rds or aws/ebs)
   - Customer Managed Keys (CMK) created in KMS: $1/month
   - Customer Managed Keys imported (must be 256 bit symmetric key): $1/month
 - Plus pay for API calls to KMS ($0.03/10,000 calls)   
 - Automatic key rotation
   - AWS Managed KMS key: automatic every 1 year
   - Customer Managed KMS key: (must be enabled) automatic every 1 year
   - Imported KMS key: only manual rotation possible using alias

### KMS key policies
 - Control access to KMS keys, "similar" to S3 bucket policies
 - Difference: You cannot control access without them
 - Default KMS key policy:
   - Created if you dont provide a specific KMS key policy
   - Complete access to the key to the root user = entire AWS account
   - Useful for cross-accout access of your KMS key
 - Copying Snapshots across accounts
   - Create a Snapshot, encrypted with your own KMS key (Customer managed key)
   - Attach a KMS key policy to authorize cross-account access
   - Share the encrypted snapshot
   - (in target) Create a copy of the snapshot, encrypt with a CMK in your account
   - Create a volume from the snapshot

### KMS Multi region keys
 
 - Identical KMS keys in different AWS Regions that can be used interchangeably
 - Multi-Region keys have the same key ID, key material, automatic rotation 
 - Encrypt in one region and decrypt in other region
 - No need to re-encrypt or making cross-region API calls
 - KMS Multi-Region are not global (primary + replicas)
 - Each Multi-Region key is managed independently
 - Use cases: Global client-side encryption, Encryption on Global DynamoDB, Global Aurora
 - DynamoDB Global tables and KMS Multi-Region keys client side encryption
   - We can encrypt specific attributes client-side in our DynamoDB Encryption client
   - Combined with global tables, the client side encrypted data is replicated to other regions
   - If we use a multi region key, replicated in the same region as the DynamoDB Global table, then clients in these regions can use low-latency API calls to KMS in their region to decrypt the data client-side
   - Using Client-side encryption we can protect specific fields and guarantee only decryption if the client has access to an API key 
 - Global Aurora and KMS Multi-Region keys client side encryption
   - We can encrypt specific attributes client-side in our AUrora table using the AWS Encryption key
   - Combined with Aurora Global tables, the client-side encrypted data is replicated to other regions
   - If we use a multi region key, replicated in the same region as the Global Aurora DB, then clients in these regions can use low-latency API calls to KMS in their region to decrypt the data client-side
   - Using client-side encryption we can protect specific fields and guarantee only decryption if the client has access to an API key, we can protect specific fiels even from database admins

### S3 replication Encryption considerations

