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


## Milestone 3: Exception Handling and Best Practices
**Tag:** `milestone-3`

In this milestone, we enhance our API by implementing robust exception handling and adhering to best practices:

- **Custom Exceptions:** Define specific exceptions like `ProductNotFoundException` to provide clear error messages.
- **Global Exception Handling:** Utilize `@ControllerAdvice` and `@ExceptionHandler` to manage exceptions across the application.
- **Meaningful Error Responses:** Return informative HTTP responses with appropriate status codes and messages.
- **Logging:** Implement logging to capture and monitor exceptions for debugging and auditing purposes.

[View Milestone 3 Documentation](https://sardul3.com/boot-camp/api-dev-mile3.html)

## Milestone 4: Content Negotiation and HATEOAS
**Tag:** `milestone-4`

This milestone focuses on enhancing the flexibility and discoverability of our API:

- **Content Negotiation:** Implement mechanisms to serve responses in different formats (e.g., JSON, XML) based on client requests.
- **HATEOAS (Hypermedia as the Engine of Application State):** Integrate HATEOAS principles to include hyperlinks in responses, aiding clients in navigating the API.
- **Object Mapping with MapStruct:** Use MapStruct to efficiently map between entity classes and Data Transfer Objects (DTOs), reducing boilerplate code.

[View Milestone 4 Documentation](https://sardul3.com/boot-camp/api-dev-mile4.html)

## Milestone 5: API Documentation and Versioning
**Tag:** `milestone-5`

In this milestone, we focus on making our API more maintainable and user-friendly:

- **API Documentation:** Utilize Swagger and OpenAPI to generate interactive API documentation, facilitating easier integration for consumers.
- **API Versioning:** Implement strategies to manage different versions of the API, ensuring backward compatibility and smooth transitions for clients.

[View Milestone 5 Documentation](https://sardul3.com/boot-camp/api-dev-mile5.html)


## Milestone 6: Filtering, Pagination, and Sorting
**Tag:** `milestone-6`

This milestone aims to improve the efficiency and usability of our API when handling large datasets:

- **Filtering:** Allow clients to retrieve data based on specific criteria, such as product attributes.
- **Pagination:** Implement mechanisms to divide large sets of data into manageable pages, reducing load times and improving performance.
- **Sorting:** Enable clients to receive data ordered by specified fields, enhancing the flexibility of data retrieval.

[View Milestone 6 Documentation](https://sardul3.com/boot-camp/api-dev-mile6.html)

---

By completing these milestones, our API becomes more robust, user-friendly, and efficient, adhering to industry best practices and providing a solid foundation for future development. 

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
