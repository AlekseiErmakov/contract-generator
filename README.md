# Contract Generator

A TypeSpec-based contract generator that compiles TypeSpec specifications into OpenAPI YAML and generates Java client libraries.

## Overview

This project provides a complete pipeline for generating API contracts from TypeSpec specifications. It automatically:

1. Compiles TypeSpec files to OpenAPI 3.0 specifications
2. Generates Java client code from the OpenAPI specifications
3. Packages the generated code into JAR files for distribution
4. Publishes artifacts to Maven repositories

## Project Structure

```
contract-generator/
├── simple-generator/          # Main generator module
│   ├── specs/                 # TypeSpec source files
│   │   └── main.tsp          # Main TypeSpec specification
│   ├── yaml-specs/           # Generated OpenAPI YAML files (generated)
│   ├── generated/            # Generated Java code (generated)
│   ├── build.gradle          # Gradle build configuration
│   ├── package.json          # Node.js dependencies for TypeSpec
│   └── Makefile              # Make targets for common tasks
├── build.gradle              # Root Gradle configuration
├── settings.gradle           # Gradle multi-project settings
└── README.md                 # This file
```

## Prerequisites

- **Java 21** or higher
- **Node.js 18** or higher (for TypeSpec compilation)
- **npm** (for TypeSpec dependencies)
- **Make** (for build automation)

## Getting Started

### 1. Install Dependencies

```bash
# Install Node.js dependencies for TypeSpec
cd simple-generator
npm install
```

### 2. Build the Project

You can build the project using either Gradle or Make:

#### Using Gradle (Recommended)
```bash
# Build everything (clean, compile TypeSpec, generate code, build JAR)
./gradlew :simple-generator:buildAll

# Individual tasks
./gradlew :simple-generator:cleanGeneratedYaml     # Clean YAML specs
./gradlew :simple-generator:cleanGeneratedClasses # Clean generated Java code
./gradlew :simple-generator:compileTypeSpec        # Compile TypeSpec to OpenAPI
./gradlew :simple-generator:openApiGenerate        # Generate Java code
./gradlew :simple-generator:buildProductApiJar     # Build JAR file
```

#### Using Make
```bash
cd simple-generator

# Individual tasks
make clean     # Clean generated YAML specs
make compile   # Compile TypeSpec to OpenAPI
make build     # Run complete Gradle build pipeline
```

### 3. Publish Artifacts

```bash
# Publish to local Maven repository
./gradlew :simple-generator:publishToMavenLocal
```

## Generated Artifacts

The build process generates the following artifacts:

- **OpenAPI Specification**: `simple-generator/yaml-specs/@typespec/openapi3/openapi.yaml`
- **Java Client Code**: `simple-generator/generated/src/main/java/com/example/contractgenerator/products/`
- **Main JAR**: `simple-generator/build/libs/simple-generator-1.0.0.jar`
- **API Contract JAR**: `simple-generator/build/libs/simple-generator-1.0.0-productApiJar.jar`

## Using the Generated Client

Once published, other projects can use the generated client by adding this dependency:

```gradle
dependencies {
    implementation 'com.example.contractgenerator:simple-generator:1.0.0'
    implementation 'com.example.contractgenerator:simple-generator:1.0.0:productApiJar'
}
```

The generated client includes:
- **API Interfaces**: `com.example.contractgenerator.products.api.ProductsApi`
- **Model Classes**: `com.example.contractgenerator.products.model.*`
- **Utility Classes**: `com.example.contractgenerator.products.api.ApiUtil`

## Development Workflow

### 1. Edit TypeSpec Files
Modify the TypeSpec specifications in `simple-generator/specs/main.tsp`:

```typescript
import "@typespec/http";
import "@typespec/openapi3";

using TypeSpec.Http;

@service({
  title: "Products API",
})
namespace ProductService;

// Your API definitions here
```

### 2. Generate and Build
```bash
# Complete build pipeline
./gradlew :simple-generator:buildAll
```

### 3. Test Integration
```bash
# Publish to local Maven for testing
./gradlew :simple-generator:publishToMavenLocal
```

## Configuration

### Gradle Configuration

The project uses these key configurations:

- **Group**: `com.example.contractgenerator`
- **Version**: `1.0.0`
- **Java Version**: 21
- **OpenAPI Generator**: Spring Boot 3 compatible
- **Package Structure**: 
  - API: `com.example.contractgenerator.products.api`
  - Models: `com.example.contractgenerator.products.model`

### TypeSpec Configuration

TypeSpec dependencies are managed in `simple-generator/package.json`:

```json
{
  "dependencies": {
    "@typespec/compiler": "~0.63.0",
    "@typespec/http": "^0.63.0",
    "@typespec/openapi3": "^0.63.0"
  }
}
```

## Build Tasks

### Gradle Tasks

| Task | Description |
|------|-------------|
| `buildAll` | Complete build pipeline (clean → compile → generate → package) |
| `cleanGeneratedYaml` | Remove generated YAML specifications |
| `cleanGeneratedClasses` | Remove generated Java code |
| `compileTypeSpec` | Compile TypeSpec files to OpenAPI YAML |
| `openApiGenerate` | Generate Java code from OpenAPI specification |
| `buildProductApiJar` | Build JAR with generated API contracts |
| `publishToMavenLocal` | Publish artifacts to local Maven repository |

### Make Tasks

| Task | Description |
|------|-------------|
| `make clean` | Clean generated YAML specs |
| `make compile` | Compile TypeSpec to OpenAPI |
| `make build` | Run complete Gradle build pipeline |

## Troubleshooting

### Node.js Version Issues
If you encounter TypeSpec compilation issues, ensure you have Node.js 18 or higher:

```bash
node --version  # Should be 18.0.0 or higher
```

### Generated Files Not Found
If the build fails with "file not found" errors, ensure the complete pipeline runs:

```bash
./gradlew :simple-generator:buildAll
```

### Clean Build
For a completely fresh build:

```bash
# Clean everything
./gradlew clean
cd simple-generator && make clean
cd ..

# Rebuild everything
./gradlew :simple-generator:buildAll
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Edit TypeSpec files in `simple-generator/specs/`
4. Test your changes with `./gradlew :simple-generator:buildAll`
5. Submit a pull request

## License

This project is licensed under the MIT License.

