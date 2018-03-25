# cxf-example

## Objective
  - Develop an enterprise Java application that implements RESTful and SOAP web services that is secure
  - The RESTful service will expose two methods
     - public String push(int i1, int i2);
     - which returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue
    - public List<Integer> list();
      - which returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure
  - The SOAP service will expose the following method as operations:
    - public int gcd();
    - which returns the greatest common divisor* of the two integers at the head of the queue. These two elements will subsequently be discarded from the queue and the head replaced by the next two in line.
    - public List<Integer> gcdList();
      - which returns a list of all the computed greatest common divisors from a database. 
     - public int gcdSum();
       - which returns the sum of all computed greatest common divisors from a database.
      - The application needs to support access by up to 20 concurrent users and the code be of production quality. It needs to be deployable to a JBoss (or similar) application server as an enterprise archive [EAR] and be tolerant of server outage.

  - Greatest Common Divisor (GCD) of two whole numbers is the largest whole number that's a divisor (factor) of both of them. For instance, the largest number that divides into both 20 and 16 is 4.

## Prerequisite 
1. Java 8 or higher version
1. Tomcat
1. Apache Active MQ
## Code configuration Details
1. Checkout code
1. ```application.properties``` - Verify and update MQ Connection proerties if needed.
1. Application is configured to use H2 inmemory database. Code changes will be required if any other database needs to be configured.
1. ```scripts.sql``` - holds the basic schema details needed for application to work. This script will automatically be executed at application startup for inmemory database.
1. Current logback(```logback.xml```) configurations will log details only to STDOUT. Additional configurations can be done to output logs to file. 

Once above details are verified/configs are done, proceed to create WAR and deploy it in tomcat.

## Application URLs
After successfull deployment of app, use below steps to perform operations.
### REST Interface
   1. Push Numbers API
      - HTTP Method: PUT
      - URL: http://<IP>:<port>/cxf-example/services/rest/gcd/push/10/20
2. Push Numbers API
     - HTTP Method:GET
     - URL: http://<IP>:<port>/cxf-example/services/rest/gcd/numbers 

### SOAP Interface
GCD Service WSDL URL: ```http://<host>:<port>/cxf-example/services/gcdservice?wsdl```
For quick testing, refer to ```ClientConfig.java```. Update Host:port in line 28 if needed. And launch it as a Java Application for checks to commence.

## TODO
1. jUnit test cases
2. Remove ```ClientConfig.java```
