# Docker Deployment Guide

This guide explains how to run the Ambulance Booking System using Docker.

## Prerequisites

- Docker (version 20.10 or later)
- Docker Compose (version 2.0 or later)

## Quick Start with Docker Compose

1. **Clone the repository** (if not already done):
   ```bash
   git clone <repository-url>
   cd ambulance-backend
   ```

2. **Set up environment variables** (optional):
   Create a `.env` file in the project root:
   ```env
   # Database Configuration
   POSTGRES_DB=ambulance_booking
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=your_secure_password

   # Application Configuration
   JWT_SECRET=your-super-secret-jwt-key-minimum-32-characters
   GOOGLE_DISTANCE_MATRIX_API=your-google-maps-api-key
   FRONTEND_URL=http://localhost:3000
   ```

3. **Run the application**:
   ```bash
   docker-compose up -d
   ```

4. **Check the application**:
   - API: http://localhost:8080
   - Health Check: http://localhost:8080/actuator/health
   - Database: localhost:5432

## Building the Docker Image Manually

1. **Build the image**:
   ```bash
   docker build -t ambulance-backend:latest .
   ```

2. **Run with external database**:
   ```bash
   docker run -d \
     --name ambulance-backend \
     -p 8080:8080 \
     -e DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/ambulance_booking \
     -e DATASOURCE_USERNAME=postgres \
     -e DATASOURCE_PASSWORD=password \
     -e JWT_SECRET=your-jwt-secret \
     -e GOOGLE_DISTANCE_MATRIX_API=your-api-key \
     -e FRONTEND_URL=http://localhost:3000 \
     ambulance-backend:latest
   ```

## Environment Variables

| Variable | Description | Default | Required |
|----------|-------------|---------|----------|
| `DATASOURCE_URL` | PostgreSQL database URL | `jdbc:postgresql://localhost:5432/ambulance_booking` | Yes |
| `DATASOURCE_USERNAME` | Database username | `postgres` | Yes |
| `DATASOURCE_PASSWORD` | Database password | `password` | Yes |
| `JWT_SECRET` | JWT signing secret (min 32 chars) | - | Yes |
| `GOOGLE_DISTANCE_MATRIX_API` | Google Maps API key | - | Yes |
| `FRONTEND_URL` | Frontend application URL for CORS | `http://localhost:3000` | Yes |
| `SPRING_PROFILES_ACTIVE` | Spring profile to use | `prod` | No |

## Production Deployment

### Security Considerations

1. **Change default passwords**:
   - Update database password
   - Use a strong JWT secret (minimum 32 characters)

2. **Use secrets management**:
   ```bash
   # Using Docker secrets
   echo "your-jwt-secret" | docker secret create jwt_secret -
   echo "your-db-password" | docker secret create db_password -
   ```

3. **Enable HTTPS**:
   - Configure reverse proxy (nginx, traefik)
   - Use SSL certificates

### Health Checks

The application includes health checks at:
- `/actuator/health` - Basic health status
- `/actuator/health/liveness` - Liveness probe
- `/actuator/health/readiness` - Readiness probe

### Monitoring

Access application metrics at:
- `/actuator/metrics` - Application metrics
- `/actuator/info` - Application information

## Development

### Hot Reload with Docker

For development with hot reload:

```bash
# Build development image
docker build -f Dockerfile.dev -t ambulance-backend:dev .

# Run with volume mounting
docker run -d \
  --name ambulance-backend-dev \
  -p 8080:8080 \
  -v $(pwd)/src:/app/src \
  ambulance-backend:dev
```

### Running Tests in Docker

```bash
# Run tests
docker run --rm \
  -v $(pwd):/app \
  -w /app \
  openjdk:24-jdk-slim \
  ./mvnw test

# Or using docker-compose
docker-compose -f docker-compose.test.yml up --abort-on-container-exit
```

## Troubleshooting

### Common Issues

1. **Application not starting**:
   ```bash
   # Check logs
   docker-compose logs app
   
   # Check if database is ready
   docker-compose logs postgres
   ```

2. **Database connection issues**:
   ```bash
   # Verify database is running
   docker-compose ps postgres
   
   # Test database connection
   docker-compose exec postgres psql -U postgres -d ambulance_booking -c "\dt"
   ```

3. **Port conflicts**:
   ```bash
   # Check if ports are in use
   netstat -tulpn | grep :8080
   netstat -tulpn | grep :5432
   ```

### Logs

```bash
# View application logs
docker-compose logs -f app

# View database logs
docker-compose logs -f postgres

# View all logs
docker-compose logs -f
```

## Cleanup

```bash
# Stop and remove containers
docker-compose down

# Remove volumes (WARNING: This will delete database data)
docker-compose down -v

# Remove images
docker rmi ambulance-backend:latest postgres:15-alpine
```
