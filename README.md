# Professional Student Management System

A menu-driven Java console application for managing student records in MySQL. The project demonstrates object-oriented programming, JDBC database connectivity, layered architecture, and CRUD operations.

## Features

- **Create:** Add new student records.
- **Read:** View all students or search by student ID.
- **Update:** Edit an existing student's details.
- **Delete:** Delete a student record after confirmation.
- **Input validation:** Validate required fields, age, email format, and student ID.
- **Database access:** Use JDBC and `PreparedStatement` to execute parameterized SQL queries.
- **Layered architecture:** Separate the model, DAO, service, database connection, and console interface.
- **Resource management:** Use try-with-resources in database operations to close JDBC resources.
- **Documentation:** Includes a Software Requirements Specification (SRS) and UML documentation.

## Technology Stack

- Java (JDK 17 or newer recommended)
- Maven
- MySQL
- JDBC
- MySQL Connector/J

## Project Structure

```text
Student_Management_System/
├── pom.xml
├── database.sql
├── README.md
├── docs/
│   ├── SRS_Document.md
│   └── UML_Diagram.txt
└── src/
    └── main/
        └── java/
            └── com/
                └── sms/
                    ├── app/
                    │   └── Main.java
                    ├── model/
                    │   └── Student.java
                    ├── dao/
                    │   └── StudentDAO.java
                    ├── service/
                    │   └── StudentService.java
                    └── util/
                        └── DBConnection.java
```

## Prerequisites

Before running the application, install:

1. JDK 17 or newer.
2. Apache Maven.
3. MySQL Server.

Check that Java, Maven, and MySQL are available from your terminal:

```powershell
java -version
mvn -version
mysql --version
```

## Database Setup

1. Start the MySQL Server.
2. From the project root, import the provided SQL script:

   ```powershell
   Get-Content .\database.sql -Raw | mysql -u root -p
   ```

3. Verify the database and table:

   ```sql
   USE studentdb;
   SHOW TABLES;
   DESCRIBE students;
   ```

The application expects a `studentdb` database and a `students` table with these columns: `id`, `name`, `age`, `email`, and `course`.

## Configure the Database Connection

Open:

`src/main/java/com/sms/util/DBConnection.java`

Update the connection settings to match your local MySQL setup:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/studentdb";
private static final String USER = "root";
private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password. Do not commit real passwords or other secrets to a public repository.

## Build and Run

Run these commands from the directory containing `pom.xml`.

Compile the project:

```powershell
mvn clean compile
```

Run the console application:

```powershell
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java "-Dexec.mainClass=com.sms.app.Main"
```

If `mvn` is not recognized on Windows, configure Maven's `bin` directory in your `Path` environment variable, or invoke Maven using its full path.

## Using the Application

Use the console menu to choose an operation:

1. Add Student
2. View All Students
3. Search Student by ID
4. Update Student
5. Delete Student
6. Exit

Enter the requested details when prompted. Use a student ID that does not already exist when adding a record. Student email addresses must also be unique if the database schema defines `email` as a unique column.

## Architecture

The application separates responsibilities across layers:

- **Model (`Student.java`):** Represents student data.
- **DAO (`StudentDAO.java`):** Contains SQL and database access logic.
- **Service (`StudentService.java`):** Applies validation and coordinates operations.
- **Utility (`DBConnection.java`):** Creates MySQL connections.
- **Application (`Main.java`):** Presents the console menu and accepts user input.

## Documentation

- `docs/SRS_Document.md` — Software Requirements Specification.
- `docs/UML_Diagram.txt` — UML documentation.

## Resume Description

**Student Management System | Java, JDBC, MySQL, Maven**

- Developed a menu-driven Java application to manage student records through create, read, update, and delete operations.
- Implemented JDBC-based MySQL connectivity using parameterized SQL queries and the DAO design pattern.
- Organized the code into model, DAO, service, utility, and application layers.
- Added input validation and exception handling, and documented the system with SRS and UML artifacts.

## Future Improvements

- Add automated unit and integration tests.
- Improve user-facing handling of duplicate IDs and email addresses.
- Move database credentials to environment variables or an external configuration file.
- Add a graphical interface or REST API if required.
