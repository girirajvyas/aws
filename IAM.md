# 2. Identity and Access Management (IAM)

## 2.1 Basics

**Components:**  
 - It is a *global Service*
 - Root account is created by default when you create your account
 - It is recommended to **not use or share this root user and should only be used for user creation**
 - `Users:` These are the people within the organization and can be grouped based on departments, roles, etc
 - `Groups:` These contains only the users created above and no other groups
 - `Note:` 
   - It is not mandatory for Users to be assigned to a group. 
   - It can be the case that a user does not belong to any group 
   - or it can belong to multiple groups as well

**Permissions:**  
 - Users or Groups can be assigned JSON documents called `policies`
 - Policies define what users/groups are allowed or in short the permissions of the users
 - [Least privilege access](http://docs.aws.amazon.com/wellarchitected/latest/security-pillar/permissions-management.html): It is recommended to follow this principle that states dont give user more permissions that he needs 

## 2.2 How to land to IAM (Common step across this section)
 - Go to: https://aws.amazon.com -> login with root user
 - You can use the Global search -> Search IAM -> Select
 - Or go to Services -> Security, Identity and compliance -> IAM
 - You will be landed to Dashboard of IAM
 - ![Landing Page](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/IAM_landing_page.PNG)
 
## 2.3 Add User  
 - Go to Access Management -> Users -> Add Users

**Step 1: user details and access type** 
**Set User Details**  
 - User Name: johndoe/raj
 - You can have multiple users added at the same time

**Select AWS access Type**  
 - Access type 
   - Programmatic Access: check in case you want to allow this user to connect via code
   - AWS Managemenet Console access: check
     - Selecting this section will require you to provide below details
   - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_1.1.PNG)
 - Console password
   - Autogenerate Pasword: unchecked
   - Custom Password: checked
 - Require Password Reset Checkbox: unchecked
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_1.2.PNG)
 
**Step 2: Set Permissions**  
 - `Add user to group:` In case you donot have groups already, "get started with group"
 - Copy permissions from existing user
 - Add existing policies directly
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_2.1.png)

get started with group  
 - Group Name: admins
 - Create policy/ add policies
 - For starter add AdministratorAccess policy to make the users in group as admins
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_2.2_create_group.png)

**Step 3: Add tags (Optional)**  
 - for eg: Department as key and value as Enineering
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_3_tags.png)

**Step 4: Review**  
 - Review whatever you have selected till now
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_4.1_review.png)
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_4.2_review.png)

**Step 5: Success, you have created an user.**  
 - There is a one time option to download the csv that contains every information including how to login and passwords, etc
 - A link will also be generated that can be shared with the user directly.
 - ![Add user](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/add_user/add_user_step_5_Created.png)

**Detailed steps:**  
 - Step by step screenshots are also available [here](https://github.com/girirajvyas/101-series/tree/master/resources/images/aws/IAM/add_user)

`Tip:` In `IAM dashboard`, set Sign-in URL for IAM users in the account, you will have a default value by default or you can edit and see if you have an alias available

**How to differentiate a root user or IAM user?**
 - `root user:` you will directly have name 
 - `IAM user:` username @ account name

## 2.4 Policies

**Overview**  
 - If you remove the user from the group admins having AdministratorAccess policy, user will not be able to create/read groups and users
 - if you add a readonly policy, your user will only be able to read

**Basic structure of policy**  
 - On high level it has:
   - Version: Policy language version
   - Id: Identifier of policy
   - Statement: Array of statemenets (atleast 1 required)
 - Array of statements include:
   - Sid: identifier for statment
   - Effect: whether it allows or Denies access
   - Principal: account:user:role this policy is applied to
   - Action: list of actions this policy allows or denies
   - Resource: List of resources where this action will be applied


 - Go to Access Management -> Policies  
 - Here you can see all the policies and can create custom ones as well
 - you can see the policy summary and json tab of policy, where policy summary is human readable and json has the same thing in json format
 - For creating policy: you can do it via visual editor or directly write the json as well

## 2.5 Multi Factor Authentication - MFA

**Password policy Basics**  
 - Strong password means higher security for your account
 - In AWS, you can setup a password policy:
   - set a min password length
   - Require specific character types:
     - including uppercase letters
     - lowercase letters
     - numbers
     - non-alphanumeric characters
 - Allow All IAM users to change their own passwords
 - Require users to change their password after some time (password expiration)
 - prevent password re-use

**Hands on console**  
- Go to Access Management -> Account settings -> Password policy
- Default password policy:
  - Minimum password length is 8 characters
  - Include a minimum of three of the following mix of character types: uppercase, lowercase, numbers, and ! @ # $ % ^ & * ( ) _ + - = [ ] { } | '
  - Must not be identical to your AWS account name or email address
- now you can modify the options as per yor org guidelines

**MFA device options:**  
 - Virtual MFA device (Support for multiple tokens on a single device)
   - Google authenticator (phone only)
   - Authy (multi-device)
 - Universal 2nd Factor (U2F) Security Key 
   - Support for multiple root and IAM users using a single security key
   - YubiKey by Yubico (3rd party)
 - Hardware Key Fob MFA Device
   - provided by Gemalto (3rd party)
 - Hardware Key Fob MFA Device for AWS GovCloud (US)
   - Provided by Surepassid (3rd party)

**Hands on console**  
 - Activate MFA
   - Virtual MFA Device
   - U2F security key
   - Other Hardware MFA device
 - We select Virtual MFA Device
   - List of supported devices: https://aws.amazon.com/iam/features/mfa/?audit=2019q1
   - Install any of the above apps
   - Scan QR code
   - Type 2 consecutive MFA codes
   - Success
 - Next time you login via your root account you have to provide MFA token along with your password

## 2.6 Ways to access AWS

 - `AWS Management Console:`(Protected by password + MFA)
 - `AWS Command line Interface (CLI):` protected by access keys
 - `AWS Software Development Kit (SDK) for code:` protected by access keys
   - Access keys used above are generated through AWS console
   - Users Manage their own access keys
   - **Access keys are secret like you have passwords, it is recommended not to share them**

**Acess keys:**   
 - `Access Key Id` which is used like a username but it is not the same
 - `Secret Access Key` which is used like a password but it is not the same.

### 2.6.1 AWS console
 - All the hands on we did till now is on console

### 2.6.2 AWS cli on Windows

**Installation:**  
 - Google -> Download aws cli on windows -> [Link](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html)
 - Install the downloaded msi file. for me it was 28 MB.
 - After finishing installation, go to cmd -> type aws --version -> you will get the result as below
 - ![Successful installation](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_installed_successfully.PNG)
 - In case you want to upgrade, you have to download the latest version of msi and install again

**Hands On:**  
 - Do not use your root account to create security credentials
 - Login via IAM user -> IAM -> Access management -> Users -> select your name -> Security credentials tab
 - Access keys -> Create access keys (Single time download and next time you have to create a new one in case you forgot or missed)
 - Open cmd prompt -> type `aws configure` -> 
   - provide access key id: one you created just above
   - access key secret: one you created just above
   - Default region name: the region close to you
   - Default output format: nothing, just press enter
   - ![configure](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_initial_setup.PNG)
 - Verify if aws is configured correctly by: `aws iam list-users`
 - ![output](https://github.com/girirajvyas/aws/blob/main/resources/images/IAM/aws_cli/aws_cli_list_users.PNG)
 - Permissions updated via console will reflect in console as well.
 - In case you do not have permissions, there will be a blank response

**Reference documentation:** https://docs.aws.amazon.com/cli/latest/reference/
 - You get the same info via `aws help or aws ec2 help`

### 2.6.3 AWS Cloudshell (Alternative to AWS cli on Windows)


### 2.6.4 AWS SDK
 - https://aws.amazon.com/tools/
 - Here, you will find all the SDKs avaialble based on the programming language you use

## 2.7 IAM Roles for services

**Basics**  
 - Some AWS services will need to perform actions on your behalf
 - To achieve that, we will assign permissions to AWS services with IAM Roles.
 - Common roles:
   - EC2 Instance roles
   - Lambda Function roles
   - Roles for CloudFormation

**From AWS console: What are IAM Roles?**  
IAM roles are a secure way to grant permissions to entities that you trust. Examples of entities include the following:  
 - IAM user in another account
 - Application code running on an EC2 instance that needs to perform actions on AWS resources
 - An AWS service that needs to act on resources in your account to provide its features
 - Users from a corporate directory who use identity federation with SAML
 - IAM roles issue keys that are valid for short durations, making them a more secure way to grant access.

**Hands on**  
 - Go to Access Management -> Roles -> Create Roles
 - You can create role for:
    - AWS service:EC2, Lambda and others
    - Another AWS account: Belonging to you or any third party
    - Web identity: Cognito or any openId provider
    - SAML 2.0 federation: your corporate directory
 - We will do only for `AWS service` as of now, select EC2 and click `Next:Permissions`
 - Lets provide **IAMReadOnlyAccess** to this role and `Next:Tags`
 - We can skip this and do `Next:Review`
 - Provide Role name -> Create Role

## 2.8 IAM Security Tools

**Basics**  
 - IAM Credentials Report(account-level)
   - A report that lists all your account's users and the status of their various credentials
 
 - IAM Acess Advisor (user-level)
   - Access Advisor shows the service permissions granted to a user and when those services were last accessed.
   - You can use this information to revise your policies. (Enforce policy of least privilege)

**Hands On**  
Credentila Report  
 - Go to Access Reports -> Credential Report
 - you will get complete summary of all the users

Access Advisor
 - Go to Access Management -> Users -> select user -> Access Advisor
 - Gives you detailed summary of the services used and in turn helps in effectively managing the access
 - Ref: https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_access-advisor.html

## 2.9 IAM Guidelines and Best Practices

 - Don't use the root account except for AWS account setup
 - One Physical user = One AWS user
 - Assign Users to groups and assign permissions to groups to make sure security is maintained at group level
 - Create a Strong password policy/
 - Use and enforce the use of Multi Factor Authentication (MFA)
 - Create and use Roles for giving permissions to AWS services.
 - Use access keys for Programatic access (CLI/SDK)
 - Audit permissions of your account with the IAM Credentials Report and IAM Access Advisor
 - **Never share IAM users and Access Keys**

**Best practices from IAM Dashboard in AWS console**  
 - [Grant least privilege access](http://docs.aws.amazon.com/wellarchitected/latest/security-pillar/permissions-management.html) : Establishing a principle of least privilege ensures that identities are only permitted to perform the most minimal set of functions necessary to fulfill a specific task, while balancing usability and efficiency.
 - Enable Identity federation: Centrally manage users and access across multiple applications and services. For federation to multiple accounts in your AWS Organization, you can configure your identity source in [AWS Single Sign-on](https://console.aws.amazon.com/singlesignon/home).
 - Enable MFA: For extra security, we recommend that you require multi-factor authentication (MFA) for [all users](https://console.aws.amazon.com/iam/home?region=us-east-2#users).
 - [Rotate credentials regularly](https://console.aws.amazon.com/iam/home?region=us-east-2#security_credentials): Change your own passwords and access keys regularly, and make sure that all users in your account do as well.
 - Enable [IAM Access Analyzer](https://console.aws.amazon.com/access-analyzer/home): Enable IAM Access Analyzer to analyze public, cross-account, and cross-organization access.
 - Learn more about all **[security best practices](https://console.aws.amazon.com/access-analyzer/home)**.

## 2.10 Shared Responsibility Model for IAM

| AWS                                           | You                           |
| -------------                                 |:-------------:                      |
| Infrastructure (global network security)      | Users, Groups, Roles, Policies management and monitoring |
| Configuration and vulnerability analysis of services they offer     | Enable MFA on all accounts |
| Compliance Validation                         | Rotate all your keys often |
|                                               | Use IAM tools to apply appropriate permissions|
|                                               | Analyze access patterns and review permissions|

## 2.11 IAM Summary

 - Users: mapped to physical user, has a password for AWS Console
 - Groups: contains users only
 - Policies: JSON documents that outlines permissions for users or groups
 - Roles: for EC2 instances or AWS services
 - Security: MFA + password policy
 - Access keys: Access AWS using the CLI/SDK
 - Audit: IAM Credential Reports and IAM Access Advisor

## 2.12 QUIZ

What is a proper Definition of IAM roles:
 - An IAM entity that defines a set of permissions for making AWS service requests, that will be used by AWS services
 - Some AWS service will need to perform actions on your behalf. To do so, you assign permissions to aws services with IAM Roles

Which is an IAM Security Tool?
 - IAM Credential Report
 - IAM Credentials report lists all your account's users and the status of their various credentials. The other IAM security Tool is IAM Access Advisor. It shows the service permissions granted to a user and when those services were last accessed.
 
What are IAM Policies?
 - JSON Documents to define Users, Groups or Roles' permissions
 - An IAM policy is an entity that, when attached to an identity or resource, defines their permissions.

### Reference:
 - [Identity Management](https://www.aws.training/Details/eLearning?id=55148)