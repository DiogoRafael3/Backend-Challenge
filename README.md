# **Getting Started**

### **What this is**

Repo for backend challenge using the following specs:
* Java Spring boot
* Unit Tests
* Open Api 3
* ORM
* Docker
* Centralized logging using containers

### **Requirements**

 - Docker

### **Start-up**

Run the following commands in the command line:

    - docker pull meowtobot/challenge:latest
    - docker-compose up

**NOTE:** There is a script for creating the data described in the problem, however this program runs regardless of the script, if necessary to test the code without the script, please remove the following lines from **docker-compose.yml**
``` yaml
    volumes:
      - ./postgres/data/init.sql:/docker-entrypoint-initdb.d/init.sql
```

# Documentation

This is a brief description of challenge components and my decisions throughout the process

## Packaging

The project is packaged in order to segregate objects as much possible, having **controllers**, **domain objects**, **mappers**, **services** and the **api** in different packages

## Libraries used

### Lombok

Used in this project to generate patterns and facilitate reading code on this repo, it is used across all domain objects, as well as in the controller and service classes to avoid writing the java constructor.

### OpenApi

As per one of the requirements of the problem.

### Mapstruct

In order to facilitate mapping between database entities, domain entities and DTO entities.

## Domain Objects/Data Model

The domain objects are those mentioned in the description of the problem, including DTOs, ORM entities and request/response entities.

All database relations should be fairly standard aside from the **Pathology** entity having a **One to Many** relation with a list of **Symptoms**, this relation was used to shorten logic in the **/consultsAndSymptoms/** endpoint, however it is arguable whether or not it should be used as it creates a new table.

The **Command** entities were used to abstract unnecessary data on the **/createConsult/** endpoint.

The **Response** entities were used to obtain an output as close to the one in the problem description as possible.

Objects have **DTOs** associated.

## Code

In this repo, it was attempted that code would be at its cleanest and shortest, however due to time constraints there was specifically two things that would be done that currently aren't:
* **HospitalService** would be further segregated into many services depending on the entity used in the endpoint (as to not inject so many dependencies into the same service)
* Preferably there would be an **exception handler** in order to output more clarifying errors for the user

## Endpoints

### OpenApi

OpenApi specification is available at:

http://localhost:8080/challenge/swagger-ui/index.html

All curl descriptions are provided in this UI.

### /createConsult

Allows creation of a consult, provided the doctor and specialty exist, else there will be a runtime error and this service will output 500.

### /consultsAndSymptoms

Gets the patients symptoms and consults.

It was unclear in the instructions whether to search for a patient via id or name/age so it was defaulted to id.

### /topSpecialties

Gets top specialties, where top specialties are specialties whose consult has been requested more than twice.

### /getAllPatients

Gets all patients, filtered by name or age, and sorted by name and/or age, according to the request.

## docker-compose

I chose to change the image into a dockerhub repo, due to this being the most out-of-the-box option i could think of.

Additionally, i tried to use a grafana/loki/promtail stack to capture logging, however i couldn't get it fully working (comments on the loki configuration might be the answer).

In this instance, Promtail "attaches" itself to the container, sending out its logs, Loki stores the logs in a database (i used Loki because it was suggested by an SRE friend), and then Grafana queries for these logs.

I bridged these containers via 
```yaml
networks:
stack:
driver: bridge
```

# Conclusion

This challenge was extremely fun and helped me evolve, i had never used pagination or log capturing before and it was a fun challenge to sort those things out in real time.

As said before, there was some things i'd have liked to integrate that i didn't get the time to (including diagrams for this readme), however i still believe this is a good implementation of the problem.