# Software Requirements Specification (SRS)
## Professional Student Management System

**Version:** 1.0  
**Project Type:** Java Console Application

## 1. Introduction

### 1.1 Purpose
The purpose of the Student Management System is to provide a simple, reliable application for managing student records. It allows users to add, view, search, update, and delete student information stored in a MySQL database.

### 1.2 Scope
The application is a menu-driven console system developed using Java, JDBC, MySQL, and Maven. It follows a layered architecture to separate the user interface, business logic, and database access.

### 1.3 Intended Users
- Administrators or staff responsible for maintaining student records.
- Developers who want to understand Java database connectivity and layered application design.

## 2. Overall Description

### 2.1 Product Perspective
The system is a standalone Java application that connects to a MySQL database through JDBC. Users interact with the application through a console menu.

### 2.2 Product Functions
The system provides the following capabilities:
- Add a student record.
- Display all student records.
- Search for a student by ID.
- Update an existing student's information.
- Delete a student record after confirmation.
- Validate user input and report errors.

### 2.3 Operating Environment
- Operating system: Windows, Linux, or macOS.
- Java Development Kit (JDK): Version 17 or newer recommended.
- Database: MySQL Server.
- Build tool: Apache Maven.

### 2.4 Constraints
- A working MySQL Server connection is required.
- Each student must have a unique ID.
- Student email addresses must be unique if enforced by the database schema.
- The application must be configured with valid database connection settings.

## 3. Functional Requirements

### FR-01: Add Student
The system shall allow users to enter a student's ID, name, age, email, and course and save the record to the database.

### FR-02: Display Students
The system shall retrieve and display all student records stored in the database.

### FR-03: Search Student
The system shall allow users to search for a student using the student's ID and display the matching record, if found.

### FR-04: Update Student
The system shall allow users to update the name, age, email, and course of an existing student.

### FR-05: Delete Student
The system shall allow users to delete an existing student record after confirmation.

### FR-06: Validate Input
The system shall validate required fields, student IDs, age values, and email format before processing an operation.

### FR-07: Handle Errors
The system shall display appropriate error messages when an operation fails, a student does not exist, or database access is unsuccessful.

## 4. Non-Functional Requirements

### NFR-01: Security
The system shall use parameterized SQL queries through `PreparedStatement` to reduce SQL injection risk. Database credentials shall be configured securely and shall not be committed to a public repository.

### NFR-02: Maintainability
The system shall separate the model, service, DAO, database utility, and application layers to make the code easier to understand and modify.

### NFR-03: Reliability
The system shall close database resources appropriately and handle expected database and input errors without terminating unexpectedly.

### NFR-04: Performance
The system shall perform basic student record operations efficiently for small datasets on a local MySQL database.

### NFR-05: Portability
The application shall run on supported operating systems with a compatible JDK, Maven, MySQL Server, and correct configuration.

### NFR-06: Scalability
The layered architecture shall support future extensions, such as a graphical interface, REST API, or additional student attributes. Large-scale performance has not been established.

## 5. Data Requirements

The system shall store student information in a MySQL table named `students`.

| Field | Data Type | Description |
|---|---|---|
| id | INT | Unique student identifier; primary key |
| name | VARCHAR(100) | Student's name |
| age | INT | Student's age |
| email | VARCHAR(100) | Student's email address |
| course | VARCHAR(100) | Student's course |

## 6. Technology Stack

- **Java:** Application logic and object-oriented programming.
- **JDBC:** Connectivity between Java and MySQL.
- **MySQL:** Persistent storage for student records.
- **Apache Maven:** Dependency management and project compilation.

## 7. System Architecture

The system follows a layered architecture:

1. **Main:** Displays the console menu and receives user input.
2. **StudentService:** Performs validation and coordinates operations.
3. **StudentDAO:** Executes SQL queries and maps database results to student objects.
4. **DBConnection:** Establishes JDBC connections.
5. **MySQL Database:** Stores student records.
6. **Student Model:** Represents student data within the application.

## 8. Assumptions and Dependencies

- MySQL Server is installed and running.
- The database and required table are created before the application starts.
- Database connection settings are valid.
- Maven can download the required dependencies.
- The user can access the console and provide valid input.

## 9. Future Enhancements

- Add automated unit and integration tests.
- Add authentication and role-based access control.
- Move credentials to environment variables or external configuration.
- Add pagination and filtering for larger datasets.
- Develop a graphical interface or REST API.

## 10. Conclusion

The Professional Student Management System demonstrates basic student record management using Java, JDBC, MySQL, and Maven. Its layered design provides a foundation for future improvements in functionality, security, and maintainability.