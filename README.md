# WebAppDB

## Project Description

**WebAppDB** is a Spring Boot-based web application for managing player data and statistics. It provides a REST API to:

- manage players and their game stats;
- persist data in a PostgreSQL database;
- validate and handle business errors;
- expose Swagger UI for API testing.

---

## Technologies

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Flyway
- Springdoc OpenAPI (Swagger UI)
- Maven
- H2 for tests

---

## Requirements

- Java 17+
- Maven
- PostgreSQL running locally
- Database `demo` and schema `my_schema` created manually

---

## Installation and Launch

### 1. Create database and schema

In PostgreSQL, run:

```sql
CREATE DATABASE demo;
CREATE SCHEMA my_schema;
```

!!!Database must be accessible at jdbc:postgresql://localhost:5432/demo?currentSchema=my_schema

### 2. Run the application

In the root directory of the project, run:
mvn spring-boot:run

The application will start on:
http://localhost:8081/WebAppDB/

### 3. Swagger UI

API documentation and testing available at:

http://localhost:8081/WebAppDB/swagger-ui/index.html

## API Usage

### Example Requests

| Method | Path                      | Description              | Entity      |
|--------|---------------------------|--------------------------|-------------|
| GET    | /api/v2/players           | List all players         | Player      |
| POST   | /api/v2/players           | Create new player        | Player      |
| PUT    | /api/v2/players/{id}     | Update player            | Player      |
| DELETE | /api/v2/players/{id}     | Delete player            | Player      |
| GET    | /api/v2/stats             | List all stats           | PlayerStats |
| POST   | /api/v2/stats             | Create stats record      | PlayerStats |
| PUT    | /api/v2/stats/{id}       | Update stats             | PlayerStats |
| DELETE | /api/v2/stats/{id}       | Delete stats             | PlayerStats |
| GET    | /api/v2/getStatus         | Check application state  | System      |


## Logging and Errors

Logs are printed to the console in the following format:

2025-06-11 12:00:00 [main] INFO  ru.bmstu.WebAppDB - Message...

### Common Errors

| Code | Description                         | Example JSON Response                                     |
|------|-------------------------------------|-----------------------------------------------------------|
| 400  | Validation failed (e.g. null fields)| `{ "status": 400, "error": "Bad Request", "message": "Username cannot be null or blank", "timestamp": "2025-06-11T14:30:00Z" }` |
| 404  | Entity not found                    | `{ "status": 404, "error": "Not Found", "message": "Player not found", "timestamp": "2025-06-11T14:31:00Z" }` |

> Localized error messages are configured in `messages.properties`.


## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Author

Last Ch
GitHub: https://github.com/LastCh

## Feedback

If you have any questions or suggestions — feel free to open an issue on GitHub.

Good luck using WebAppDB! 🚀
