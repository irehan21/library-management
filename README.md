# Library Management System

A REST API built with Spring Boot for managing a library system.

## Tech Stack
- Java 21
- Spring Boot 3.3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL
- Lombok

## Features
- User Registration & Login with JWT Authentication
- Role Based Access Control (ADMIN / USER)
- Book & Author Management
- Issue Request System (Request, Approve, Reject, Return)
- Global Exception Handling

## API Endpoints

### Auth
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /api/auth/login | Public |
| POST | /api/members/register | Public |

### Books
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /api/books | USER, ADMIN |
| POST | /api/books | ADMIN |
| PUT | /api/books/{id} | ADMIN |
| DELETE | /api/books/{id} | ADMIN |

### Issue Requests
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /api/issue-requests/request | USER |
| PUT | /api/issue-requests/approve/{id} | ADMIN |
| PUT | /api/issue-requests/reject/{id} | ADMIN |
| PUT | /api/issue-requests/return/{id} | USER |

## Setup
1. Clone the repository
2. Create MySQL database: `CREATE DATABASE library_db;`
3. Update database credentials in application.properties
4. Run the application