# 🚑 Ambulance Booking System

A **Spring Boot-based backend** for an intelligent ambulance dispatch system with user authentication, hospital discovery, ambulance booking, and real-time tracking using the Google Maps API.

---

## 📌 Features

✅ JWT-based login & registration for Users and Admin  
✅ Admin dashboard to manage hospitals and ambulances  
✅ Users can:
- Find nearest hospitals based on current location
- View available ambulances with ETA (Estimated Time of Arrival)
- Book ambulances with patient details
- Track booking history

✅ Automatic ambulance status updates  
✅ PostgreSQL database integration  
✅ Google Maps API for distance and ETA calculation  

---

## 🧰 Tech Stack

| Layer          | Technology                        |
|----------------|-----------------------------------|
| Backend        | Spring Boot                       |
| Database       | PostgreSQL                        |
| Authentication | JWT                               |
| Maps & ETA     | Google Maps Distance Matrix API   |
| API Style      | RESTful APIs                      |

---

## ⚙️ Setup Instructions

1. **Clone the Repository**

```bash
git clone https://github.com/your-repo/ambulance-booking-system.git
cd ambulance-booking-system
````

2. **Configure `application.properties`**

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ambulance_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
google.maps.api.key=YOUR_GOOGLE_MAPS_API_KEY
```

3. **Run the Spring Boot Application**

```bash
./mvnw spring-boot:run
```

---

## 📘 ER Diagram

> 📌 Shows how entities like User, Booking, Hospital, Ambulance, and Patient are related.

<p align="center">
  <img src="https://github.com/user-attachments/assets/46c99f56-ae0a-4fd1-adc5-a07c85f4a44e" alt="ER Diagram" width="800"/>
</p>

---

## 🧩 Class Diagram

> 📌 Shows classes, fields, and service layer interactions in the application.

<p align="center">
  <img src="https://github.com/user-attachments/assets/d5248675-b329-4683-899f-08bfac8497eb" alt="Class Diagram" width="800"/>
</p>

---

## 🔁 Sequence Diagram – Booking an Ambulance

> 📌 Demonstrates step-by-step flow of the ambulance booking process.

<p align="center">
  <img src="https://github.com/user-attachments/assets/f8924dc2-0ec6-433e-ae04-68e5f84e3d55" alt="Sequence Diagram" width="800"/>
</p>

---

## 🔄 Flowchart – Backend Workflow

> 📌 High-level backend logic showing request handling and service layer processing.

<p align="center">
  <img src="https://github.com/user-attachments/assets/82342a3e-6cb9-4da6-be89-15af8305b00a" alt="Backend Flowchart" width="800"/>
</p>

---

## 🛡️ Security

* Secure endpoints using Spring Security with JWT tokens
* Role-based access for ADMIN and USER
* Passwords are hashed using BCrypt

---

## 📤 API Overview (Pluggable to Postman)

| Endpoint                | Method | Role   | Description                 |
| ----------------------- | ------ | ------ | --------------------------- |
| `/api/auth/register`    | POST   | Public | Register a user             |
| `/api/auth/login`       | POST   | Public | Login and receive JWT token |
| `/api/hospitals`        | GET    | User   | Get nearest hospitals       |
| `/api/ambulances`       | GET    | User   | Get available ambulances    |
| `/api/booking/book`     | POST   | User   | Book an ambulance           |
| `/api/booking/history`  | GET    | User   | View user’s booking history |
| `/api/admin/hospitals`  | POST   | Admin  | Add a hospital              |
| `/api/admin/ambulances` | POST   | Admin  | Add an ambulance            |
| `/api/admin/bookings`   | GET    | Admin  | View all bookings           |

---

