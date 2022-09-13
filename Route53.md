## Route 53

### Overview
 - A highly available, scalable, fully managed and Authoritative DNS
   - Authoritative = customer can update the DNS records
 - Route 53 is also a Domain Registrar
 - Ability to check the health of your resources
 - Only service that provide 100% availability
 - Why Route 53?
   - 53 is a reference to the traditional DNS port

### Records
 - How you want to route traffic for a domain
 - Each Record contains
   - Domain/Sub-domain Nam: Eg: example.com
   - Record Type: eg: A or AAAA
   - Value: eg 12.34.56.78
   - Routing policy: How route 53 responds to queries
   - TTL: amount of time the record is cached at DNS resolvers
 - Route 53 supports the following DNS record types:
   - Must know: A/AAAA/CNAME/NS
   - Advanced : CAA/DS/MX/NAPTR/PTR/SOA/TXT/SPF/SRV

### Record Types
 - A: maps a hostname to IPV4
 - AAAA: maps a hostname to IPV6
 - CNAME: Maps a hostname to another hostname
   - The target is a domain name which must have an A or AAAA record
   - Can't create a CNAME record for the top node of a DNS namespace (Zone APEX)
   - Example: You cant create for example.com, but you can create for www.example.com
 - NS: Name Servers for the hosted zone
   - Controls how traffic is routed to a domain

### Hosted Zones
 - A container for records that define how to route traffic to a domain and its subdomains
 - Public Hosted Zones: contains records that specify how to route traffic on the internet (Public domain names), eg: application1.mypublicdomain.com  
 - Private Hosted Zones: contains record that specify how you route traffic within one of more VPCs (Private Domain names), eg: application1.company.internal
 - you pay $ 0.50 per month per hosted zone

### TTL


### CNAME vs Alisas


### Route 53  health checks


### Routing Policies
 - Defines how Route 53 responds to DNS queries
 - Note:
    - Its not same as the load balancer routing which routes the traffic
    - DNS does not route any traffic, it only responds to DNS queries.
 - Route 53 supports following policies:
   - Simple
   - Weighted
   - Latency based
   - Failover (Active-Passive)
   - Geolocation
   - Geoproximity (Using Route53 traffic floe feature)
   - Multi-value Answer
    
#### Routing Policies - Simple






   
 

