# Restaurant API

[![OpenAPI](https://img.shields.io/badge/CLEAN%20ARCHITECTURE-8A2BE2)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/JAVA-FC6A31)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/DOCKER-2496ED)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/SPRING%20BOOT-FC6A31)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/OPEN%20API-EF0092)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/SPRING%20DATA%20JPA-FC6A31)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/IN%20MEMORY%20H2%20DATABASE-00EA64)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/TESTS-8A2BE2)](https://www.openapis.org/)
[![OpenAPI](https://img.shields.io/badge/CUCUMBER-00EA64)](https://www.openapis.org/)

### DESCRIPTION

 

#### review call example
![Booking Call](booking.png)



#### Swagger UI

- Open Swagger UI at `localhost:8080/swagger-ui` after running the application.



#### Tests

- to execute unit tests
```sh
mvn test
```

- to execute integrated tests
```sh
mvn test -P integration-test
```

- to execute system tests and BDD
```sh
mvn spring-boot:start
mvn test -P system-test
```


- to execute performance test (need make utility installed on your computer)
```sh
make performance-test
```



