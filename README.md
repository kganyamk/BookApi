# Book  API & CI/CD

A simple RESTful API for managing books in a library system. Built with **Spring Boot 3.4.1**, **H2 Database**, **Spring Data JPA**, **Lombok**, and **Swagger (SpringDoc OpenAPI)**.

Deployed : RENDER (Docker image)
LIVE : https://book-api-yw9t.onrender.com
---

## Features

-  CRUD operations for books
-  Borrow and return book functionality
-  In-memory or file-based H2 database
-  API documentation with Swagger UI
-  Docker support
-  CI/CD-ready with GitHub Actions

---

## Tech Stack

- Java 21
- Spring Boot 3.4.1
- H2 Database
- Spring Data JPA
- Junit5 & Mockito
- Lombok
- SpringDoc OpenAPI 2.x (Swagger)
- Docker

---

## CI/CD Pipeline

This project includes a CI/CD pipeline using GitHub Actions, which automatically builds, tests, packages, and deploys your application as a Docker image to Docker Hub whenever you push code to the main branch or open a pull request.

- Build the JAR
- Run tests
- Build Docker image
- Push Docker image

---

## API Endpoints

LIVE : https://book-api-yw9t.onrender.com/

| Method | Endpoint                 | Description         |
|--------|--------------------------|---------------------|
| GET    | `/api/books`             | Get all books       |
| GET    | `/api/books/{id}`        | Get book by ID      |
| POST   | `/api/books`             | Create new book     |
| PUT    | `/api/books/{id}`        | Update book by ID   |
| DELETE | `/api/books/{id}`        | Delete book         |
| POST   | `/api/books/{id}/borrow` | Borrow a book       |
| POST   | `/api/books/{id}/return` | Return a book       |

