# Student Course Management System

A Spring Boot REST API application for managing students, courses, and enrollments in an educational institution.

## Features

- **Student Management**: Create, read, update, and delete students
- **Course Management**: Manage course catalog with titles and descriptions
- **Enrollment System**: Handle student enrollments in courses
- **Advanced Queries**: Find students/courses with multiple enrollments
- **Search Functionality**: Search by name, email, title
- **Validation**: Input validation using Bean Validation
- **Exception Handling**: Global exception handling with custom exceptions
- **DTO Pattern**: Clean separation using Data Transfer Objects

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Bean Validation**

## Project Structure

```
src/main/java/com/example/studentcourse/
├── controller/          # REST Controllers
├── domain/             # Entity classes (Student, Course, Enrollment)
├── dto/                # Data Transfer Objects
├── exceptions/         # Custom exceptions & global handler
├── mapper/             # Entity-DTO mappers
├── repository/         # JPA repositories
└── service/            # Business logic layer
```

## API Endpoints

### Student Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

### Student Search & Analytics

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students/search/email?email={email}` | Find student by email |
| GET | `/api/students/search/name?name={name}` | Find student by name |
| GET | `/api/students/more-than-three-enrollments` | Students with 3+ enrollments |

### Course Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{id}` | Get course by ID |
| POST | `/api/courses` | Create new course |
| PUT | `/api/courses/{id}` | Update course |
| DELETE | `/api/courses/{id}` | Delete course |

### Course Search & Analytics

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/courses/search/title?title={title}` | Find course by title |
| GET | `/api/courses/more-than-three-enrollments` | Courses with 3+ enrollments |

## Data Models

### Student Request DTO
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### Student Response DTO
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### Course Request DTO
```json
{
  "title": "Spring Boot Fundamentals",
  "description": "Learn the basics of Spring Boot framework"
}
```

### Course Response DTO
```json
{
  "id": 1,
  "title": "Spring Boot Fundamentals",
  "description": "Learn the basics of Spring Boot framework"
}
```

## Database Schema

### Students Table
- `id` (Primary Key)
- `name` (Not Null)
- `email` (Unique, Not Null)

### Courses Table
- `id` (Primary Key)
- `title` (Unique, Not Null)
- `description` (Not Null)

### Enrollments Table
- `id` (Primary Key)
- `student_id` (Foreign Key)
- `course_id` (Foreign Key)
- `enrollment_date`

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
   cd studentcourse
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
   - Base URL: `http://localhost:8091`
   - Student API: `http://localhost:8091/api/students`
   - Course API: `http://localhost:8091/api/courses`

## Configuration

### Application Properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=your_password

# Server
server.port=8091

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

## Exception Handling

The application uses custom exceptions with global handling:

- **ResourceNotFoundException**: When requested resource is not found (404)
- **InvalidRequestException**: For invalid request data (400)
- **DuplicateResourceException**: When trying to create duplicate entries (409)
- **EnrollmentException**: For enrollment-specific business rule violations (400)

## Validation

Input validation using Bean Validation annotations:
- `@NotBlank`: For required string fields
- `@Email`: For email format validation
- `@Positive`: For positive ID numbers
- `@Valid`: For request body validation

## Business Rules

- **Unique Constraints**: Student emails and course titles must be unique
- **Enrollment Tracking**: System tracks which students are enrolled in which courses
- **Analytics**: Provides insights on students/courses with high enrollment counts

## API Usage Examples

### Create a Student
```bash
curl -X POST http://localhost:8091/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com"
  }'
```

### Create a Course
```bash
curl -X POST http://localhost:8091/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spring Boot Fundamentals",
    "description": "Learn the basics of Spring Boot framework"
  }'
```

### Get All Students
```bash
curl http://localhost:8091/api/students
```

### Search Student by Email
```bash
curl "http://localhost:8091/api/students/search/email?email=john.doe@example.com"
```

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