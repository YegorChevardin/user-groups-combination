# Spring Boot User Combinations Application

This is an open-source Spring Boot application that generates user combinations from groups based on previously
generated groups and optimizes the generation process to achieve uniqueness. It's a versatile tool that can be used in
various scenarios where you need to create unique user combinations from existing groups.

## Getting Started

These instructions will help you set up and run the project on your local machine.

### Prerequisites

Before you get started, make sure you have the following prerequisites:

- Java Development Kit (JDK) 11 or higher
- Apache Maven
- MySQL Database

### Configuration

To configure the application to connect to your database, you need to set the following environment variables in
the `application.properties` or `application.yml` file:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${DB_URL}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Replace `${DB_URL}`, `${DB_PORT}`, `${DB_NAME}`, `${DB_USER}`, and `${DB_PASS}` with your database connection details.

### Build and Run

1. Clone this repository to your local machine.

2. Navigate to the project directory:

   ```bash
   cd spring-boot-user-combinations
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The application will start, and you can access it at `http://localhost:8080`. Make sure your database is up and running
with the appropriate configuration.