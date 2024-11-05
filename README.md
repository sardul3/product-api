# E-Commerce API Bootcamp

This repository contains the code and documentation for the E-Commerce API Bootcamp. Each milestone builds upon the previous one, introducing new concepts and features.

## Table of Contents

1. [Milestone 1: API Development with Spring Boot](https://sardul3.com/boot-camp/api-dev-mile1.html)
2. [Milestone 2: Database Integration and More](https://sardul3.com/boot-camp/api-dev-mile2.html)
3. [Milelestone 3: Database Integration and More](https://sardul3.com/boot-camp/api-dev-mile3.html)

## Accessing Milestone Code

To access the code for a specific milestone:

1. Clone the repository:
   ```
   git clone https://github.com/sardul3/product-api.git
   ```

2. Navigate to the project directory:
   ```
   cd product-api
   ```

3. Checkout the tag for the desired milestone:
   ```
   git checkout milestone1  # For Milestone 1
   git checkout milestone2  # For Milestone 2
   ```

## Milestone 1: API Development with Spring Boot

**Tag:** `milestone-1`

This milestone covers the basics of API development with Spring Boot, including:

- Setting up a Spring Boot project
- Creating a simple RESTful web service
- Implementing CRUD operations for a `Product` entity
- Understanding HTTP methods and status codes

[View Milestone 1 Documentation](https://sardul3.com/boot-camp/api-dev-mile1.html)

## Milestone 2: Database Integration and More

**Tag:** `milestone-2`

This milestone builds upon Milestone 1 and introduces:

- Database integration with H2
- Modeling entity relationships
- Implementing repositories and services
- Input validation and DTOs
- New endpoints for extended functionality
- Using Lombok to reduce boilerplate code
- Instructions for switching to a production database

[View Milestone 2 Documentation](https://sardul3.com/boot-camp/api-dev-mile2.html)

## Running the Application

1. Ensure you have Java 21 and Maven (or Gradle) installed.
2. Navigate to the project directory for the desired milestone.
3. Run the application:
   ```
   ./mvnw spring-boot:run  # For Maven
   ```
   or
   ```
   ./gradlew bootRun  # For Gradle
   ```
4. The API will be available at `http://localhost:8080`

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
