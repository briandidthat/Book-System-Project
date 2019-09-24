## Book System

The Book System keeps track of books and associated notes. The system consists of the following components:

1. Spring Cloud Config Server
2. Eureka Service Registry
3. Book Service
4. Note Service
5. Note Queue Consumer

### General Requirements

1. All system components must get their configuration from the Config Server.
2. All system components must register with Eureka.
3. All web service to web service communication must be done using Feign clients.
4. All DAO and Service layer components must have unit/integration tests.


![Book System diagram](https://github.com/Octothorp6/Book-System-Project/blob/development/Book%20System%20Project%20Diagram.png)

