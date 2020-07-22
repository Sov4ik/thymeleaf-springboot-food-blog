# Test FoodBlog with Spring Boot, Hibernate ORM, Thymeleaf

## About
This is a test application that shows how to work with:
* **[Spring Boot](https://spring.io/projects/spring-boot)**
* **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
* **[Spring Security](https://spring.io/projects/spring-security)**
* **[PostgreSQL Database](https://www.postgresql.org/)**
* **[Thymeleaf](https://www.thymeleaf.org/)**

## Requirements
This demo is build with with Maven 4.0.0 and Java 8.

## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= root

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
#(problem with timezone) spring.datasource.url=jdbc:mysql://localhost:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

#spring.datasource.url=jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

spring.datasource.username= root
spring.datasource.password= root

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

```
## Run Spring Boot application
```
mvn spring-boot:run
```

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords.
