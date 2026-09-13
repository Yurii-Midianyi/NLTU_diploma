# 📊 RESTful Web Service for Polling & Voting System

> A robust backend RESTful service built with **Spring Boot** designed for creating, participating in, and analyzing online polls and surveys.

---

## 📌 About the Project

This project implements a scalable and secure REST API for an online polling platform developed as an academic thesis project at the Ukrainian National Forestry University (Department of Computer Science).

The service manages the full poll lifecycle: user registration and role-based authentication using **JWT**, creating public and private polls, voting record processing, response pagination, and calculating real-time aggregated survey results.

---

## 🚀 Key Features

* **Authentication & Authorization:**
  * User registration and authentication powered by **JWT** (HS256) and Spring Security.
  * Role-Based Access Control (RBAC): `USER` and `ADMIN` roles.
  * Account moderation capabilities (e.g., suspending users via `/users/suspend`).
* **Poll Management:**
  * Full CRUD operations for questions and answer choices.
  * Support for single and **multiple-choice** questions (`hasMultipleAnswers: true/false`).
  * **Private polls** with restricted access enforcement.
  * Strict request validation with custom and built-in bean constraints (e.g., `@NotBlank`, `@Future`, `@NotEmptyAnswers`, `@ListMaxSize`).
* **Voting & Analytics:**
  * Submit and cancel votes (`/vote`, `/vote/cancel`).
  * Detailed vote metrics: participant counts and individual vote tallies per answer choice.
* **Database & Performance Optimization:**
  * Efficient **pagination** (`Pageable`) for large data sets (`?page=X&size=Y`).
  * Optimized JPQL queries leveraging `JOIN FETCH` to eliminate N+1 lazy loading issues.
* **API Documentation:**
  * Dynamic, interactive OpenAPI documentation via **Swagger / SpringFox**.

---

## 🛠 Tech Stack

* **Language:** Java 
* **Framework:** Spring Boot (Spring Web, Spring Security, Spring Data JPA) 
* **Database:** MySQL 
* **Database Migrations:** Liquibase 
* **ORM:** Hibernate / JPA 
* **Object Mapping:** ModelMapper 
* **Build Tool:** Apache Maven 
* **Documentation:** OpenAPI / Swagger UI (SpringFox) 
* **Version Control:** Git 

---

## 🔌 API Reference

### 🔐 Authentication (`/auth`)
* `POST /auth/signup` — Register a new user
* `POST /auth/signin` — Authenticate credentials and receive a JWT token

### ❓ Questions & Polling (`/questions`)
* `GET /questions?page=0&size=10` — Retrieve paginated list of questions
* `GET /questions/{id}` — Fetch details for a specific question
* `POST /questions` — Create a new question with answer choices
* `POST /questions/{id}/vote?answerIds=17,19` — Cast votes on a question
* `POST /questions/{id}/vote/cancel` — Cancel a previously cast vote
* `GET /questions/results/{id}` — View calculated voting results

### 👤 User Moderation (`/users`)
* `PATCH /users/suspend` — Suspend a user account (Admin role required)

---

## 📋 Example Payloads

### Create a Question
**POST** `/questions`
```json
{
  "questionText": "What food do you like?",
  "answers": [
    { "answerText": "Pizza" },
    { "answerText": "Potato soup" },
    { "answerText": "Pasta" }
  ],
  "isPrivate": false,
  "hasMultipleAnswers": true,
  "endDateTime": "2026-12-28T23:59:00"
}
