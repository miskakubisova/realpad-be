# Realpad Backend (realpad-be)

## Introduction
This backend service is part of the interview process for Realpad and is designed to provide weather forecasts for various cities. It is intended to work in tandem with a frontend application.

## Prerequisites
Before you begin, ensure you have the following installed:
- Java Development Kit (JDK), version 11 or higher recommended
- IntelliJ IDEA or another IDE with Spring Boot support
- Git for repository cloning

## Getting Started

### Cloning the Repository
Clone the repository and navigate to the project directory with the following commands:
```shell
git clone https://github.com/your-username/realpad-be.git
cd realpad-be
```

### Running the Application
You can run the project using one of the following methods:

- In IntelliJ IDEA or your preferred IDE:
    1. Open the project.
    2. Find the main application file [RealpadInterviewApplication](src/main/java/cz/interview/realpad/realpadinterview/RealpadInterviewApplication.java).
    3. Click the "play" button or right-click and select "Run" to start the application.

- Using Maven from the command line:
```shell
./mvnw spring-boot:run
```

### Accessing the Service
The backend service will be available at `http://localhost:8090`. The following endpoints are provided:

- `/cities` - Retrieves a list of available cities for weather forecasts.
- `/forecast/{cityName}` - Provides a 5-day weather forecast for the specified city with three measurement intervals per day.

## Frontend Application
The frontend application can be found in a separate repository and should be run alongside this service for full functionality. Access it here: [realpad-fe](https://github.com/miskakubisova/realpad-fe). The frontend serves on `http://localhost:8080`.

## Technologies Used
- Spring Boot
- Maven
- Java
