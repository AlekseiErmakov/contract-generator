# Simple Generator

A TypeSpec-based API contract generator that compiles TypeSpec definitions into OpenAPI specifications and generates Java client libraries.

## Overview

This project streamlines the API development workflow by:

1. Defining API contracts using TypeSpec - a language for describing APIs
2. Generating OpenAPI 3.0 specifications from TypeSpec definitions
3. Generating Java client code from the OpenAPI specifications
4. Packaging and publishing the generated code for consumption by client applications

## Project Structure

```
simple-generator/
├── specs/                 # TypeSpec source files
│   └── main.tsp           # Main TypeSpec API definition
├── yaml-specs/            # Generated OpenAPI specifications (generated)
│   └── @typespec/
│       └── openapi3/      # OpenAPI 3.0 YAML files
├── generated/             # Generated Java client code (generated)
├── build/                 # Build outputs
│   └── libs/              # Generated JAR files
├── build.gradle           # Gradle build configuration
├── package.json           # Node.js dependencies for TypeSpec
└── Makefile               # Convenience commands
```

## Current API

The current API defined in TypeSpec includes:

- **Models**:
  - `ProductDto` - Product data with ID, name, description, price, and availability
  - `ProductCreateRequest` - Data needed to create a new product
  - `ProductUpdateRequest` - Data needed to update an existing product

- **Endpoints**:
  - `GET /products` - List all products
  - `GET /products/{productId}` - Get a product by ID
  - `POST /products` - Create a new product
  - `PUT /products/{productId}` - Update a product
  - `DELETE /products/{productId}` - Delete a product

## Prerequisites

- Java 21 or higher
- Node.js (for TypeSpec compilation)
- Gradle
- Make (optional, for convenience commands)

## Getting Started

### 1. Install Dependencies

```bash
# Install TypeSpec dependencies
npm install
```

### 2. Generate API Client

```bash
# Using Make
make buildAll

# Or using Gradle directly
./gradlew buildAll
```

This will:
- Clean previous generated files
- Compile TypeSpec to OpenAPI
- Generate Java code from OpenAPI
- Build the product-api JAR

### 3. Publish to Maven Local

```bash
# Using Make
make publishLocally

# Or using Gradle directly
./gradlew publishToMavenLocal
```

## Using the Generated Client

After publishing, client applications can use the generated API by adding this dependency:

```groovy
dependencies {
    implementation 'com.example.contractgenerator:product-api:1.0.0'
}
```

The client provides:
- Strongly-typed model classes for all DTOs
- Interface definitions for all API endpoints
- Spring-compatible annotations for easy integration

See the `simple-generator-usage` project for a complete example of using the generated client.

## Development Workflow

1. **Edit API Definition**: Modify the TypeSpec file in `specs/main.tsp`
2. **Generate & Build**: Run `make buildAll` to regenerate all artifacts
3. **Publish**: Run `make publishLocally` to publish to Maven Local
4. **Integrate**: Use the updated client in your application

## Available Commands

| Command | Description |
|---------|-------------|
| `make clean` | Clean generated files |
| `make compile` | Compile TypeSpec to OpenAPI |
| `make build` | Build the complete pipeline |
| `make generateJar` | Clean, compile, and build |
| `make publishLocally` | Publish to Maven Local |

## Configuration

The code generation is configured in `build.gradle` with these key settings:

- **Group**: `com.example.contractgenerator`
- **Artifact**: `product-api`
- **Version**: `1.0.0`
- **Generator**: Spring Boot 3-compatible client
- **Package**: `com.example.contractgenerator.products`

## How It Works

1. **TypeSpec Compilation**: `tsp compile` converts the TypeSpec definition to an OpenAPI YAML specification
2. **OpenAPI Generation**: The OpenAPI Generator plugin creates Java code from the YAML specification
3. **Java Compilation**: The generated Java code is compiled into class files
4. **JAR Packaging**: The compiled classes are packaged into a JAR file
5. **Publishing**: The JAR is published to Maven Local for use by other projects

## License

MIT
