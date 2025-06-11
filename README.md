# Pikupiku Service API

## Overview

This project is a Spring Boot application that provides various API endpoints for managing users, maps, and scores.

## Testing API Requests

All API requests can be tested using the HTTP file located in the following path:

### How to Use

1. Open the file `api-tests.http` in your IDE (e.g., IntelliJ IDEA).
2. Use the built-in HTTP client to execute the requests directly from the file.
3. Ensure the application is running locally on `http://localhost:8080` before testing.

### Available Endpoints

The `api-tests.http` file contains examples for the following operations:
- User registration and login
- Map creation and retrieval
- Score creation and retrieval
- Highscore retrieval

## Requirements

- Java 17+
- Maven
- IntelliJ IDEA (recommended for testing HTTP requests)

## Running the Application

1. Clone the repository.
2. Build the project using Maven:
   ```bash
   mvn clean install