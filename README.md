# spring-security-authentication-authorization
Secure authentication and authorization system built with Spring Boot and Spring Security. Implements role-based access control, and user registration with encrypted passwords.

---

## 🚀 Features

- User Registration & Login
- Role-Based Authorization (ADMIN / USER)
- Encrypted Passwords using BCrypt
- Secure REST APIs
- Spring Security Configuration
- Exception Handling for Unauthorized Access

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate / JPA
- MySQL / H2 Database
- Maven

---

## 📂 Project Structure

src/main/java/com/example/springsecurity
│── config # Security Configuration
│── controller # REST Controllers
│── service # Business Logic
│── repository # JPA Repositories
│── entity # Database Entities
│── security # JWT + Auth Logic


---

## ⚙️ Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/tapan2004/spring-security-jwt-authentication.git
cd spring-security-jwt-authentication

2. Configure Database
Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password

3. Run the project
mvn spring-boot:run

Server starts at:

http://localhost:8080
