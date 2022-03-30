# Task Manager API

Introduction
This is a simple RESTful API backend for the Task Manager, in which client can perform basic CRUD operations with ptojects and tasks. 
## Built With
- Springboot
- REST
- JPA
- Swagger UI
- MySql

## Features
- The project and task controllers can provide crud logic, and can also give a list of entities ordered by status (NotStarted,
    Active,
    Completed), by date

## Get started

```sh
git clone https://github.com/SamgatZ/AkvTaskManager.git
```
## DataBase Config
    server.port=8081
    spring.datasource.url=jdbc:mysql://localhost:3306/t_manager
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    
## Swagger
    http://localhost:8081/swagger-ui/index.html#/
    
![Снимок экрана 2022-03-30 в 20 15 59](https://user-images.githubusercontent.com/102664187/160857674-82475efa-a47b-409c-8c46-850eb3ec3818.png)

![Снимок экрана 2022-03-30 в 20 15 59](https://user-images.githubusercontent.com/102664187/160857707-62d25518-214d-48cb-90e7-b3170161d416.png)


