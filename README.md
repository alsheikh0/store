# a Spring Boot Project for a Local Store application

This repository contains the a store application built using Spring Boot and MySql related technologies

## About this Repository 

This project will be worked on gradually adding security further

Current Tech stack: Spring Boot, Spring Data JPA, Hibernate, MYSQL, JPA BUDDY, Maven, Lombok.

## Running with Docker

This project includes Docker and Docker Compose configuration for easy setup and deployment.

- **Java version:** Uses Eclipse Temurin 17 (JDK for build, JRE for runtime)
- **Database:** MySQL (latest)
- **Ports:**
  - Application: `8082` (exposed as `8082:8082`)
  - MySQL: `3306` (exposed as `3306:3306`)

### Environment Variables

The MySQL service uses the following environment variables (see `docker-compose.yml`):
- `MYSQL_ROOT_PASSWORD`: rootpassword  
- `MYSQL_DATABASE`: store
- `MYSQL_USER`: storeuser
- `MYSQL_PASSWORD`: storepass

> **Note:** Change the default MySQL passwords for production use.

If you have a `.env` file for additional configuration, you can uncomment the `env_file` line in the compose file.

### Build and Run

To build and start the application and database, run:

```sh
docker compose up --build
```

This will:
- Build the Spring Boot application jar using Maven inside a container
- Start the application on port 8082
- Start a MySQL database with persistent storage (volume `mysql-data`)

The application will be available at [http://localhost:8082](http://localhost:8082).

### Special Configuration
- The application is built and run as a non-root user for security.
- MySQL data is persisted in a Docker volume (`mysql-data`).
- The application will wait for the database to be healthy before starting.

For any custom environment variables or configuration, refer to the `application.yaml` files in `src/main/resources` and the `.env` file if present.
