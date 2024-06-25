# Inventory Management System Micro-service

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.2-blue)
![pgAdmin4](https://img.shields.io/badge/pgAdmin4-8.4-blue)
![Maven](https://img.shields.io/badge/Maven-3.9.7-red)
![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5.Final-ff69b4)

## Overview
This project is a Java-based microserver designed as part of Integrify Full-stack program to demonstrate understanding of microservices architecture and RESTful APIs. It aims to provide a simple and robust  solution for managing the inventory of an e-commerce.

## Features
- Clean architecture
- RestFul API design
- API-Key based authentication
- Production-Ready
- Email notifications

## Getting Started
**Prerequisites**
- Java 17 or higher
- Maven 3.9 or higher
- PostgreSql 16 or higher

**Installation**
1. Clone the repository:
    ```bash
    git clone https://github.com/yuankeMiao/fs17_java_inventory_service.git
    ```
2. Navigate to the project `application.property`:
    ```bash
    cd inventory-manager/src/main/resources
    ```
3. Add database info, api_key, and mail host info. If you want to populate mock data, changed the value of `data.load` to `true`, after the first poppulate, changed it to `false` again.
    ```
    spring.application.name=inventory-manager
    spring.datasource.url=your database url
    spring.datasource.username=your database user name
    spring.datasource.password=your database password
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.format_sql=true
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

    SECRET_API_KEY=your secret key

    spring.mail.host=your eamil host
    spring.mail.port=587
    spring.mail.username=your email address
    spring.mail.password=your email password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true

    data.load=false
    ```
    
3. Build the project using Maven and then you are ready to run it:
    ```bash
    mvn clean install
    ```

## Inventory Management API
You can find details of endpoint design [here](
### 


