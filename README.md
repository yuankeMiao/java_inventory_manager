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
You can find details of endpoint design [here](https://github.com/yuankeMiao/fs17_java_inventory_service/tree/main/endpoints)
There are 3 main entries: 
- `api/v1/suppliers`
- `api/v1/stocks`
- `api/v1/orders`

All entries support CRUD method. For get all method, all endpoints support pagination with customized sorting and ordering, with one or two customized filters.
For the query options, please check the corresponding endpoint markdown file.

**Query examples**
```
GET /api/v1/orders?sortBy=CREATED_TIME&orderBy=DESC&status=PENDING
API_KEY: marmotte

GET /api/v1/stocks?orderBy=ASC&sortBy=QUANTITY&supplierId=1cb3c1b6-93c7-4364-baec-f321d53cf708
API_KEY: marmotte
```

All entries return well-structured and meaning full successful and faild response, check [here](https://github.com/yuankeMiao/fs17_java_inventory_service/tree/main/endpoints) for more details.

**Response Examples**
OK:
```
{
  "data": {
    "totalRecords": 20,
    "page": 1,
    "limit": 10,
    "records": [
      {
        "id": "e4e5b03a-2779-4071-a799-b210fa9c1834",
        "createdTime": "2024-06-25T10:56:37.808755",
        "updatedTime": "2024-06-25T10:56:37.808759",
        "name": "Wilkinson-Runte",
        "contactPerson": "Mickey Schuppe",
        "contactEmail": "pinkie.padberg@yahoo.com",
        "address": "Apt. 923 22119 Hauck Fort, Kirbyhaven, GA 85957-8556"
      },
      {
        "id": "f5b45035-b8af-4922-ae7e-187222f788c7",
        "createdTime": "2024-06-25T10:56:37.808707",
        "updatedTime": "2024-06-25T10:56:37.808709",
        "name": "Jakubowski-Murphy",
        "contactPerson": "Karin Lind",
        "contactEmail": "kristopher.klein@gmail.com",
        "address": "Apt. 322 46473 Thelma Shoal, New Margueritaville, PA 89163-2477"
      },
      ... 
    ]
  },
  "errors": null
}
```

Error:
```
{
  "data": null,
  "errors": [
    {
      "field": "resource",
      "message": "Stock not found with id f3454060-9376-467b-a812-548ce094cc3d"
    }
  ]
}
```

## Fuctionalities
### Logger
This project has a logger tool to monitor all the mutable methods like create, update and delete. it will log key info with timestamp in csv files in /log folder.

### Email Notification and scheduled tasks
This project has a simple Email sender with scheduled tasks. 
- Order status update: when ever an order status is updated, it will send an email to the pre-input email address:
  
  <img width="517" alt="image" src="https://github.com/yuankeMiao/fs17_java_inventory_service/assets/109540749/9e3d0166-fef0-458e-ace6-ac7498d456f4">

- Low stock alerts: The app will check stocks every half an hour, if it detects any stock quantity lower than 10, it will send an email with basic styles:
  
  <img width="526" alt="image" src="https://github.com/yuankeMiao/fs17_java_inventory_service/assets/109540749/4c59afcd-e6dd-462e-a855-10b9f5a62a09">

- Monthly report: every first day of a month, app will send a report with the most popular products and order summary of last month:
  
  <img width="886" alt="image" src="https://github.com/yuankeMiao/fs17_java_inventory_service/assets/109540749/69051b44-7483-4e55-8191-de340432204e">




