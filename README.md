
# 📚 Library Management System (Backend Only)

A simple backend system for a library application built using **Spring Boot**, **JWT Authentication**, and **MySQL** database.

---

## 🚀 Features

- User registration & login using JWT
- Role-based access control (USER & AUTHOR)
- CRUD operations for books (only by AUTHORS)
- USERS can only view books
- Secure API endpoints with Spring Security & JWT

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
- Maven

---

## 🧠 Software Engineering Principle Used

**Single Responsibility Principle (SRP)**  
Each class in the project is responsible for a single functionality, such as:
- `UserService`: handling user logic
- `BookService`: managing book-related operations
- `JwtService`: handling token generation and validation

---

## 🗄️ Database

Using **MySQL** database with JPA Entities:
- `User` Entity
- `Book` Entity
- `Role` (Enum-based)

---

## 🔐 Authentication & Authorization

- JWT Token is returned after login
- Token must be included in the `Authorization` header:  
  `Bearer <token>`
- `AUTHOR` role can access `/api/book/**` endpoints for CRUD
- `USER` role can access only GET requests

---

## 📬 API Endpoints

### Auth
- `POST /api/auth/register` — register a user
- `POST /api/auth/login` — login and receive JWT

### Book
- `GET /api/book` — list all books
- `POST /api/book` — add a book (AUTHOR only)
- `PUT /api/book/{id}` — update book (AUTHOR only)
- `DELETE /api/book/{id}` — delete book (AUTHOR only)

---

## 📦 Getting Started

### Prerequisites
- Java 17
- Maven
- MySQL running locally

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/En-Ibrahim/library-security.git
   cd library-security
