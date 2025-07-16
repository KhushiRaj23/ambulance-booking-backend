# 🚑 Ambulance Booking System

A Spring Boot-based backend for an intelligent ambulance dispatch system with user authentication, hospital discovery, ambulance booking, and real-time tracking using Google Maps API.

## 📌 Features

- JWT-based login & registration for users and admin
- Admin can manage hospitals and ambulances
- Users can:
  - Find nearest hospitals
  - View available ambulances with ETA (Estimated time of arrival)
  - Book ambulance with patient details
  - Track booking history
- Automatic ambulance status updates
- PostgreSQL database
- Google Maps API integration for distance and ETA calculation

---

## 🧰 Tech Stack

| Layer        | Tech                            |
|--------------|----------------------------------|
| Backend      | Spring Boot                      |
| Database     | PostgreSQL                       |
| Auth         | JWT                              |
| Maps         | Google Maps Distance Matrix API  |
| API Style    | RESTful                          |

---

## ⚙️ Setup Instructions

1. Clone the Repository:

```bash
git clone https://github.com/your-repo/ambulance-booking-system.git
cd ambulance-booking-system
2. Configure application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ambulance_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
google.maps.api.key=YOUR_GOOGLE_MAPS_API_KEY
```

3. Run the Spring Boot app:

```bash
./mvnw spring-boot:run
```

---

## 🧩 Class Diagram

<img width="768" height="894" alt="image" src="https://github.com/user-attachments/assets/7ab3a45c-332e-4ab4-b191-7f7179608df3" />

## 🔁 Sequence Diagram

<img width="1183" height="797" alt="image" src="https://github.com/user-attachments/assets/14fb7555-4071-4628-bc9b-4ef256bb6840" />

## 🔄 Flowchart (Backend Workflow)

![WhatsApp Image 2025-07-16 at 10 07 51_ef3e7ea6](https://github.com/user-attachments/assets/df4e0c19-6c70-4b2c-aa99-3de858a45023)
