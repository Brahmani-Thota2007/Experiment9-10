# Library Microservices System (Without Docker)

A four-service Spring Boot starter project for Skill Experiment 11. It runs locally in STS/IDEA without Docker.

## Services
- `eureka-server` — Eureka registry, port 8761
- `auth-service` — demo login endpoint, port 8081
- `book-service` — in-memory Book CRUD REST API, port 8082
- `api-gateway` — routes `/auth/**` and `/books/**`, port 8080

## Requirements
- JDK 21
- Maven 3.9+ (or import each Maven project into STS)
- Internet access on first build to download Maven dependencies

## Import into Spring Tool Suite (STS)
1. Extract this ZIP to a normal folder (not inside the ZIP preview).
2. In STS, choose **File → Import → Maven → Existing Maven Projects**.
3. Browse to the extracted root folder. Select all four projects and click **Finish**.
4. Wait for Maven dependencies to finish downloading.

## Run order
Run each project's main class with **Run As → Spring Boot App**:
1. `EurekaServerApplication`
2. `AuthServiceApplication`
3. `BookServiceApplication`
4. `ApiGatewayApplication`

Check Eureka at http://localhost:8761 and Gateway at http://localhost:8080.

## Test with Postman
### Demo login
POST `http://localhost:8080/auth/login`
Content-Type: `application/json`
```json
{"username":"student","password":"library123"}
```
This is a classroom demo only; it returns a simple demo token, not production-grade JWT authentication.

### Book CRUD
- GET `http://localhost:8080/books`
- GET `http://localhost:8080/books/1`
- POST `http://localhost:8080/books` with JSON body:
```json
{"title":"Clean Code","author":"Robert C. Martin"}
```
- PUT `http://localhost:8080/books/1` with JSON body:
```json
{"title":"Effective Java","author":"Joshua Bloch"}
```
- DELETE `http://localhost:8080/books/1`

Books are stored in memory, so data resets when Book Service restarts. This starter does not include PostgreSQL or production security.

## Important
This package intentionally contains NO Dockerfile and NO docker-compose.yml. It is a runnable local microservices demonstration, but it does not fulfill Docker-specific containerization steps unless Docker configuration is added later.
