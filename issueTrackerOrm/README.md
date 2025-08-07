# Issue Tracker ORM

A Spring Boot REST API application for managing bugs and issues in software projects.

## Features

- **Bug Management**: Create, read, update, and delete bugs
- **Project Association**: Link bugs to specific projects
- **User Assignment**: Assign bugs to users
- **Priority System**: Set bug priorities (High, Medium, Low)
- **Status Tracking**: Track bug status (Open, In Progress, Resolved, etc.)
- **Advanced Queries**: Filter bugs by status, project, assigned user
- **Validation**: Input validation using Bean Validation
- **Exception Handling**: Global exception handling with custom exceptions

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Bean Validation**

## Project Structure

```
src/main/java/com/example/issueTrackerOrm/
├── controller/          # REST Controllers
├── domain/             # Entity classes
├── dto/                # Data Transfer Objects
├── exception/          # Custom exceptions
├── mapper/             # Entity-DTO mappers
├── repository/         # JPA repositories
└── service/            # Business logic
```

## API Endpoints

### Bug Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/bugs` | Get all bugs |
| GET | `/api/bugs/{id}` | Get bug by ID |
| POST | `/api/bugs/create` | Create new bug |
| PUT | `/api/bugs/update/{id}` | Update bug |
| DELETE | `/api/bugs/delete/{id}` | Delete bug |

### Bug Filtering

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/bugs/status/{status}` | Get bugs by status |
| GET | `/api/bugs/project/{projectId}` | Get bugs by project |
| GET | `/api/bugs/assigned-to/{userId}` | Get bugs by assigned user |
| GET | `/api/bugs/unresolved/user/{userId}` | Get unresolved bugs by user |
| GET | `/api/bugs/count/project/{projectId}` | Count bugs in project |

## Data Models

### Bug Entity
```json
{
  "bugid": 1,
  "title": "Login page not loading",
  "description": "The login page shows a blank screen",
  "status": "Open",
  "priority": "High",
  "projectName": "Web Application"
}
```

### Bug Request DTO
```json
{
  "title": "Login page not loading",
  "description": "The login page shows a blank screen",
  "status": "Open",
  "priority": "High",
  "projectId": 1
}
```

## Setup Instructions

### Prerequisites
- Java 17 or higher
- PostgreSQL database
- Maven 3.6+

### Database Setup
1. Install PostgreSQL
2. Create a database named `postgres`
3. Update `application.properties` with your database credentials

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd issueTrackerOrm
   ```

2. **Configure database**
   ```properties
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8090`
   - API endpoints: `http://localhost:8090/api/bugs`

## Configuration

### Application Properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=your_password

# Server
server.port=8090

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

## Exception Handling

The application uses custom exceptions:

- **ResourceNotFoundException**: When requested resource is not found (404)
- **InvalidRequestException**: For invalid request data (400)
- **EmptyResultException**: When query returns no results (404)

## Validation

Input validation using Bean Validation annotations:
- `@NotBlank`: For required string fields
- `@NotNull`: For required fields
- `@Positive`: For positive numbers
- `@Valid`: For request body validation

## Testing

Run tests using Maven:
```bash
mvn test
```

## Development

### Adding New Features
1. Create entity in `domain` package
2. Add repository interface in `repository` package
3. Create DTOs in `dto` package
4. Implement mapper in `mapper` package
5. Add service logic in `service` package
6. Create controller in `controller` package

### Code Style
- Follow Spring Boot conventions
- Use DTOs for API communication
- Implement proper exception handling
- Add validation annotations
- Use meaningful variable names

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests
5. Submit a pull request

## Author
- Lokeshwaran M

## License

This project is licensed under the MIT License.