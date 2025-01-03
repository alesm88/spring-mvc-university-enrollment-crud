[![Java CI with Maven](https://github.com/alesm88/spring-mvc-university-enrollment-crud/actions/workflows/maven.yml/badge.svg)](https://github.com/alesm88/spring-mvc-university-enrollment-crud/actions/workflows/maven.yml)

# Spring Project MVC with a CRUD and enrollment for university

## Description
This is a simple Spring Boot project that demonstrates an Hibernate/JPA API for managing the enrolments for subjects of a student who has logged in. It includes basic CRUD operations, along with error handling and validation.

## Table of Contents

- [Spring Project MVC with a CRUD and enrollment for university](#spring-project-mvc-with-a-crud-and-enrollment-for-university)
  - [Description](#description)
  - [Table of Contents](#table-of-contents)
  - [Technologies](#technologies)
  - [Features](#features)
  - [Setup](#setup)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Database Configuration](#database-configuration)
    - [Running the Application](#running-the-application)
    - [Running the Application using Docker:](#running-the-application-using-docker)

## Technologies

- **Java**: Java 8 or higher
- **Spring Framework**: Spring MVC, Spring ORM (for Hibernate/JPA integration)
- **Hibernate/JPA**: For ORM (Object-Relational Mapping)
- **MySQL**: Database for data persistence
- **Maven**: Build tool to manage dependencies
- **Tomcat**: Web server (optional for deployment)

## Features

- Basic CRUD operations for entities: students, subjects and professors.
- Follows the MVC pattern to decouple business logic, view, and data access layers.
- Data is persisted using Hibernate ORM with JPA.
- MySQL database connectivity for data persistence.
- Form validation and exception handling.

## Setup

### Prerequisites

- **Java JDK 8** or above.
- **Apache Maven** (to manage dependencies).
- **MySQL Workbench 8.0** (for database setup).
- **IDE**: IntelliJ IDEA, Eclipse, or any other Java IDE.
- **Git**: To clone this repository.

### Installation

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/alesm88/spring-mvc-university-enrollment-crud.git
    cd spring-mvc-university-enrollment-crud
    ```

2. Open the project in your preferred IDE. Personal opinion, Spring Tools 4 for Eclipse

3. Let Maven resolve the dependencies by refreshing the project or running:

    ```bash
    mvn clean install
    ```

### Database Configuration

1. Install and configure MySQL Workbench 8.0 if you haven't already.

2. Create a database:

    ```sql
    CREATE DATABASE db_universidad;
    ```

3. Update the `src/main/resources/application.properties` file with your MySQL database configurations:

    ```properties
    # Database Configuration
    spring.datasource.url=jdbc:mysql://localhost/db_universidad?serverTimezone=
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=create-drop
    logging.level.org.hibernate.SQL=debug
    ```
4. There is a `src/main/resources/import.sql` file with some data of students, professors, subjects and users (specially for the login) that you can use as soon as you start the program
   
### Running the Application

1. Run the project using Maven:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
    
2. To run the tests:

    ```
    ./mvnw test
    ```

3. Access the application by visiting:

    ```
    http://localhost:8080
    ```
    
    If you use a different port, adjust accordingly.
   
### Running the Application using Docker:

    ```
    docker-compose up --build
    ```
