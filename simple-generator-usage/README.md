# Simple Generator Usage

A Spring Boot application demonstrating how to use the API client generated from the TypeSpec definition in the `simple-generator` project.

## Overview

This project showcases:
- Integration with a generated API client from TypeSpec
- Implementing a RESTful Product API with CRUD operations
- Swagger UI for API documentation and testing

## Prerequisites

- Java 21+
- Gradle
- Maven Local repository (for accessing the generated client)

## Setup

1. **Generate and publish the API client to Maven Local**
   ```bash
   cd ../simple-generator
   ./gradlew buildAll publishToMavenLocal
   ```

2. **Build and run this project**
   ```bash
   ./gradlew bootRun
   ```

3. **Access the API**
   - API base URL: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui/index.html

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /products | List all products |
| GET    | /products/{productId} | Get a product by ID |
| POST   | /products | Create a new product |
| PUT    | /products/{productId} | Update a product |
| DELETE | /products/{productId} | Delete a product |

## Sample Request (HTTP Client)

You can use the included `ProductApi.http` file with HTTP Client in your IDE:

```http
### List all products
GET http://localhost:8080/products
Accept: application/json

### Create a new product
POST http://localhost:8080/products
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-performance gaming laptop",
  "price": 1299.99
}
```

## How It Works

1. **TypeSpec Definition**: The API contract is defined in TypeSpec in the `simple-generator` project
2. **Code Generation**: TypeSpec is compiled to OpenAPI, then Java code is generated
3. **Published Artifact**: The generated code is published as `product-api:1.0.0`
4. **Implementation**: This project implements the API using the published client interfaces

## Development Workflow

1. Modify the TypeSpec definition in `../simple-generator/specs/main.tsp`
2. Regenerate and republish the client:
   ```bash
   cd ../simple-generator
   ./gradlew buildAll publishToMavenLocal
   ```
3. Restart this application to use the updated client

## Project Structure

- `src/main/java` - Spring Boot application code
  - Controllers that implement the generated API interfaces
  - Service layer for business logic
  - Repository layer for data access
- `ProductApi.http` - Sample HTTP requests for testing

## Dependencies

- `product-api:1.0.0` - Generated API client
- Spring Boot Web - RESTful API framework
- Spring Boot Validation - Request validation
- SpringDoc OpenAPI - Swagger UI documentation
