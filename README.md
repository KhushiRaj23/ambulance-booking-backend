
# 🚑 Ambulance Booking System - Backend

A robust Spring Boot backend system for managing ambulance bookings, hospital operations, and emergency medical services coordination.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing](#testing)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## 🎯 Overview

The Ambulance Booking System is a comprehensive backend solution designed to streamline emergency medical services. It provides real-time ambulance availability tracking, efficient booking management, and seamless coordination between hospitals, ambulances, and patients.

### Key Benefits
- **Real-time Tracking**: Monitor ambulance status and location in real-time
- **Efficient Booking**: Streamlined booking process with instant confirmation
- **Smart Routing**: Google Maps integration for optimal route calculation
- **Role-based Access**: Secure access control for different user types
- **Scalable Architecture**: Built with Spring Boot for enterprise-grade performance

## ✨ Features

### 🔐 Authentication & Authorization
- JWT-based authentication system
- Role-based access control (ADMIN/USER)
- Secure password encryption
- Session management

### 🚑 Ambulance Management
- Real-time ambulance status tracking
- Location-based ambulance assignment
- Maintenance scheduling
- Status updates (Available, On Duty, Maintenance)

### 🏥 Hospital Integration
- Multi-hospital support
- Hospital-specific ambulance fleets
- Capacity management
- Emergency response coordination

### 📱 Booking System
- Instant ambulance booking
- Patient information management
- Booking status tracking
- History and analytics

### 🗺️ Location Services
- Google Maps API integration
- Distance calculation
- Route optimization
- Geographic data management

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot 3.5.3** - Main application framework
- **Spring Security 6.2.8** - Security and authentication
- **Spring Data JPA** - Data persistence
- **Spring Web MVC** - REST API endpoints

### Database
- **PostgreSQL** - Primary production database
- **H2 Database** - In-memory database for testing

### Security
- **JWT (JSON Web Tokens)** - Stateless authentication
- **BCrypt** - Password hashing
- **Spring Security** - Authorization and access control

### Build & Testing
- **Maven** - Dependency management and build tool
- **JUnit 5** - Unit testing framework
- **Spring Boot Test** - Integration testing

### Additional Libraries
- **Lombok** - Code generation and boilerplate reduction
- **Google Maps API** - Location and distance services
- **HikariCP** - Database connection pooling

## 📁 Project Structure

```
ambulance-backend/
├── src/
│   ├── main/
│   │   ├── java/com/ambulancebooking/ambulance_backend/
│   │   │   ├── config/                 # Configuration classes
│   │   │   ├── controller/             # REST API controllers
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   ├── enums/                  # Enumeration classes
│   │   │   ├── exception/              # Custom exception handlers
│   │   │   ├── model/                  # Entity classes
│   │   │   ├── repository/             # Data access layer
│   │   │   ├── security/               # Security configuration
│   │   │   ├── service/                # Business logic layer
│   │   │   └── util/                   # Utility classes
│   │   └── resources/
│   │       ├── application.properties  # Main configuration
│   │       ├── application-test.properties # Test configuration
│   │       └── static/                 # Static resources
│   └── test/                           # Test classes
├── pom.xml                             # Maven configuration
└── README.md                           # This file
```

## ⚙️ Prerequisites

Before running this application, ensure you have:

- **Java 21+** - Latest LTS version recommended
- **Maven 3.6+** - Build and dependency management
- **PostgreSQL 12+** - Database server
- **Google Maps API Key** - For location services (optional)

### System Requirements
- **RAM**: Minimum 2GB, Recommended 4GB+
- **Storage**: Minimum 1GB free space
- **OS**: Windows, macOS, or Linux

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd ambulance-backend
```

### 2. Database Setup
```sql
-- Create PostgreSQL database
CREATE DATABASE ambulance_db;
CREATE USER postgres WITH PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE ambulance_db TO postgres;
```

### 3. Environment Variables
Set the following environment variables:

#### Windows (PowerShell)
```powershell
$env:DATASOURCE_URL="jdbc:postgresql://localhost:5432/ambulance_db"
$env:DATASOURCE_USER="postgres"
$env:DATASOURCE_PASSWORD="12345"
$env:JWT_SECRET="your-secure-jwt-secret-key-here"
$env:GOOGLE_DISTANCE_MATRIX_API="your-google-api-key"
$env:FRONTEND_URL="http://localhost:3000"
```

#### Linux/macOS
```bash
export DATASOURCE_URL=jdbc:postgresql://localhost:5432/ambulance_db
export DATASOURCE_USER=postgres
export DATASOURCE_PASSWORD=12345
export JWT_SECRET=your-secure-jwt-secret-key-here
export GOOGLE_DISTANCE_MATRIX_API=your-google-api-key
export FRONTEND_URL=http://localhost:3000
```

### 4. Build the Project
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package the application
./mvnw package
```

### 5. Run the Application
```bash
# Run with Maven
./mvnw spring-boot:run

# Or run the JAR file
java -jar target/ambulance-backend-0.0.1-SNAPSHOT.jar
```

## ⚙️ Configuration

### Application Properties
The main configuration file is `src/main/resources/application.properties`:

```properties
# Application Name
spring.application.name=ambulance-booking-system

# Database Configuration
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Configuration
spring.jwt.secret=${JWT_SECRET}

# Server Configuration
server.port=8080

# Logging
logging.level.com.ambulancebooking=INFO
logging.level.root=INFO
```

### Test Configuration
For testing, the application uses H2 in-memory database:

```properties
# Test Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

# Test JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "password": "secure_password",
  "email": "john@example.com",
  "phone": "+1234567890",
  "latitude": 40.7128,
  "longitude": -74.0060
}
```

#### Login User
```http
POST /auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "secure_password"
}
```

### Ambulance Endpoints

#### Get Available Ambulances
```http
GET /ambulances/available?hospitalId=1
Authorization: Bearer <jwt-token>
```

#### Get All Ambulances
```http
GET /ambulances/all?hospitalId=1
Authorization: Bearer <jwt-token>
```

#### Get Ambulance Counts
```http
GET /ambulances/counts?hospitalId=1
Authorization: Bearer <jwt-token>
```

Response:
```json
{
  "total": 5,
  "available": 2,
  "onDuty": 2,
  "maintenance": 1
}
```

#### Get Ambulances by Status
```http
GET /ambulances/status/ON_DUTY?hospitalId=1
Authorization: Bearer <jwt-token>
```

### Booking Endpoints

#### Book Ambulance
```http
POST /booking/book
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "ambulanceId": 1,
  "hospitalId": 1,
  "bookingType": "EMERGENCY",
  "patient": {
    "name": "Jane Smith",
    "age": 35,
    "gender": "FEMALE",
    "condition": "Chest pain"
  }
}
```

#### Get Booking History
```http
GET /booking/history
Authorization: Bearer <jwt-token>
```

#### Get Active Bookings
```http
GET /booking/active
Authorization: Bearer <jwt-token>
```

#### Complete Booking
```http
POST /booking/{bookingId}/complete
Authorization: Bearer <jwt-token>
```

#### Cancel Booking
```http
POST /booking/{bookingId}/cancel
Authorization: Bearer <jwt-token>
```

### User Endpoints

#### Get User Profile
```http
GET /users/profile
Authorization: Bearer <jwt-token>
```

#### Update User Profile
```http
PUT /users/profile
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "email": "newemail@example.com",
  "phone": "+1987654321"
}
```

### Admin Endpoints

#### Get All Users
```http
GET /admin/users
Authorization: Bearer <jwt-token>
```

#### Get All Bookings
```http
GET /admin/bookings
Authorization: Bearer <jwt-token>
```

## 🗄️ Database Schema

### Core Entities

#### User
```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Ambulance
```sql
CREATE TABLE ambulances (
    id BIGSERIAL PRIMARY KEY,
    vehicle_number VARCHAR(20) UNIQUE NOT NULL,
    status VARCHAR(20) DEFAULT 'AVAILABLE',
    hospital_id BIGINT REFERENCES hospitals(id),
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Hospital
```sql
CREATE TABLE hospitals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address TEXT,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Booking
```sql
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    ambulance_id BIGINT REFERENCES ambulances(id),
    hospital_id BIGINT REFERENCES hospitals(id),
    booking_type VARCHAR(20) DEFAULT 'NORMAL',
    booking_status VARCHAR(20) DEFAULT 'ACTIVE',
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    completion_time TIMESTAMP,
    cancellation_time TIMESTAMP
);
```

#### Patient
```sql
CREATE TABLE patients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INTEGER,
    gender VARCHAR(10),
    condition TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 🔒 Security

### Authentication Flow
1. User registers/logs in with credentials
2. System validates credentials and generates JWT token
3. Token contains user information and expiration time
4. Client includes token in Authorization header for subsequent requests

### JWT Token Structure
```json
{
  "header": {
    "alg": "HS256",
    "typ": "JWT"
  },
  "payload": {
    "sub": "username",
    "iat": 1640995200,
    "exp": 1641081600,
    "role": "USER"
  }
}
```

### Security Headers
- **CORS**: Configured for frontend integration
- **CSRF**: Disabled for API usage
- **Session Management**: Stateless (JWT-based)

### Role-based Access Control
- **USER**: Can book ambulances, view own bookings
- **ADMIN**: Can manage all users, bookings, and system data

## 🧪 Testing

### Running Tests
```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=AmbulanceBookingSystemApplicationTests

# Run with coverage
./mvnw test jacoco:report
```

### Test Configuration
- Uses H2 in-memory database
- Separate test profile
- Mock external services where appropriate

### Test Structure
```
src/test/java/
└── com/ambulancebooking/ambulance_backend/
    ├── AmbulanceBookingSystemApplicationTests.java
    ├── controller/          # Controller tests
    ├── service/            # Service layer tests
    └── repository/         # Repository tests
```

## 🚀 Deployment

### Production Build
```bash
# Create production JAR
./mvnw clean package -Pprod

# Run production JAR
java -jar target/ambulance-backend-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:21-jdk-slim
COPY target/ambulance-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables for Production
```bash
export DATASOURCE_URL=jdbc:postgresql://prod-db-host:5432/ambulance_db
export DATASOURCE_USERNAME=prod_user
export DATASOURCE_PASSWORD=secure_prod_password
export JWT_SECRET=very-long-secure-jwt-secret-key
export GOOGLE_DISTANCE_MATRIX_API=prod_google_api_key
export FRONTEND_URL=https://your-frontend-domain.com
```

## 🔧 Troubleshooting

### Common Issues

#### 1. Database Connection Error
```
Error: 'url' must start with "jdbc"
```
**Solution**: Ensure environment variables are set correctly and database is running.

#### 2. JWT Secret Error
```
Error: WeakKeyException: The specified key byte array is not secure enough
```
**Solution**: Use a longer JWT secret (minimum 256 bits).

#### 3. Port Already in Use
```
Error: Port 8080 is already in use
```
**Solution**: Change port in `application.properties` or stop conflicting service.

#### 4. Compilation Errors
```
Error: method does not override or implement a method from a supertype
```
**Solution**: Check method signatures and ensure proper inheritance.

### Debug Mode
Enable debug logging by adding to `application.properties`:
```properties
logging.level.com.ambulancebooking=DEBUG
logging.level.org.springframework.security=DEBUG
```

### Health Checks
```bash
# Check application health
curl http://localhost:8080/actuator/health

# Check database connectivity
curl http://localhost:8080/actuator/health/db
```

## 🤝 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes
4. Add tests for new functionality
5. Commit your changes: `git commit -m 'Add feature'`
6. Push to the branch: `git push origin feature-name`
7. Submit a pull request

### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Ensure proper exception handling
- Write unit tests for new features

### Testing Requirements
- All new features must have corresponding tests
- Maintain minimum 80% code coverage
- Integration tests for API endpoints
- Unit tests for business logic

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

### Getting Help
- **Issues**: Create an issue on GitHub
- **Documentation**: Check this README and inline code comments
- **Community**: Join our discussion forum

### Contact Information
- **Project Maintainer**: [Your Name]
- **Email**: [your.email@example.com]
- **GitHub**: [your-github-username]

## 🔄 Changelog

### Version 1.0.0 (Current)
- Initial release with core ambulance booking functionality
- JWT-based authentication system
- Role-based access control
- Google Maps integration
- Comprehensive API endpoints
- PostgreSQL database support
- Unit and integration testing

### Planned Features
- Real-time notifications
- Mobile app API endpoints
- Advanced analytics dashboard
- Multi-language support
- Payment integration
- Emergency contact management

---

**Made with ❤️ for better emergency medical services**

*Last updated: August 2024*


