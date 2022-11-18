# CloudFront

## Overview
 - Content Delivery Network (CDN)
 - Improves read performance, content is cached at the edge
 - 216 Points of Presence globally (edge locations)
 - DDoS protection, Integration with Shield, AWS Web application Firewall (WAF)
 - Can expose external HTTPS and can talk to internal HTTPS backends

## Origins
 - S3 Bucket
   - For distributed files and caching them at the edge
   - Enhanced Security with CloudFront Origin Access Identity
   - CloudFront can be used as an ingress (to upload files to S3)
 - Custom Origin (HTTP)
   - Application Load Balancer
   - EC2 Instance
   - S3 Website (must first enable the bucket as a static S3 website)
   - Any HTTP backend you want

## Security (how to access)
  - S3
  - EC2
  - ELB

## Geo Location

## Price Classes

## Cache Invalidations

## AWS Global Accelerator




 
