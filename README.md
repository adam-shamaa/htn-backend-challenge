# HTN Backend Challenge

## Table of Contents
1. [Challenge Reference](#Challenge-Reference)
2. [Languages, Tools & Frameworks](#Languages,-Tools-&-Frameworks)
3. [Getting Started](#Getting-Started)
4. [Development](#Development)
5. [Directory Structure](#Directory-Structure)

## Challenge Reference
https://gist.github.com/faizaanmadhani/bc4c5ec754d47b460c0564f4b84cfa66

## Languages, Tools, Frameworks & Databases
Languages:
- Java
- OpenAPI 3.0
- SQL

Frameworks:
- Spring

Tools:
- Hibernate (ORM)
- Docker

Databases:
- MySQL

## Getting Started

### Build & run locally
**Using Docker**
1. Ensure Docker is installed & running, verify it's running using the command `docker info`
2. In the root directory start the services using `docker-compose up`

Note: To stop & clean up all volumes, networks and images, use `docker-compose down -v --rmi`

### Developement

#### API Specifications and Contract
This project utilizes OpenAPI 3.0 to define generate a contract that the backendAPI conforms to. Generated artifacts include [Controller Interfaces, DTO Models] which are built locally during the build lifecycle.

#### DB Initialization
On first start up, the MySQL server is initialized with the JSON data by:
1. Flattening the JSON data into separate lists/tables
2. Converting to CSV format
3. Importing into the MySQL server through an SQL init script

#### Database Architecture
There are three separate tables:
- User table
- Hacker table
- HackerSkill table

![Database Architecture][https://user-images.githubusercontent.com/61364811/220806332-6852859d-c0fd-43d3-9894-bf49b1c786e5.png]

The user and hacker tables are seperated in order to allow generalization with different roles such as staff, volunteers, judges, sponsors who may use the same backend but have different views. With this
architecture, common data across all roles exist in the user table while unique attributes live in their own role tables. 

#### API Client
Feel free to use the [pre-made API collection](https://pastebin.com/mWRTTUr9) by importing into [Insomnia API client](https://insomnia.rest).

#### Improvements
Some improvements to the current implementation include: 
- Pagination
- Tests
- Better use of environment variables
- Enum definitions (ex. for skill names)
- More comprehensive form field validation (ex. regex patterns for phone & email)


## Directory Structure
```
/ root
| -> / db
|    | -> / seed
|    |      Contains scripts to flatten the JSON data file and convert into CSV format
|    |          in order to initialize the DB
|    | -> / seed_data
|           Contains a initialization script for the DB, dependent CSV files are created in the 
|               above directory
| -> / src
|    | -> / main
|       | -> / java / com / htn / backendchallenge
|          .... self explanatory layered architecture
|       | -> / resources / openapi
|          | -> / openapi
|               Contains the OpenAPI 3.0 API spec         

```