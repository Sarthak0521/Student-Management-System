package com.sms.app;
import com.sms.model.Student;
import com.sms.service.StudentService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentService service =
            new StudentService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewAllStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 6 -> {
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    }
                    default ->
                            System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println(
                        "Database error: " + e.getMessage()
                );
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Invalid input: " + e.getMessage()
                );
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.println("\n--- Add Student ---");

        System.out.print("Enter ID: ");
        int id = readInt();

        Student s = readStudentDetails(id);

        if (service.registerStudent(s)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student was not added.");
        }
    }

    private static void viewAllStudents()
            throws SQLException {

        System.out.println("\n--- All Students ---");

        List<Student> students = service.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent()
            throws SQLException {

        System.out.print("Enter student ID: ");
        int id = readInt();

        Student s = service.getStudentById(id);

        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(s);
        }
    }

    private static void updateStudent()
            throws SQLException {

        System.out.print("Enter ID of student to update: ");
        int id = readInt();

        Student existing = service.getStudentById(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(
                "Current details: " + existing
        );

        System.out.println("Enter the new details:");
        Student updated = readStudentDetails(id);

        if (service.updateStudent(updated)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student was not updated.");
        }
    }

    private static void deleteStudent()
            throws SQLException {

        System.out.print("Enter student ID to delete: ");
        int id = readInt();

        Student existing = service.getStudentById(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(existing);
        System.out.print("Confirm deletion? (yes/no): ");

        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            if (service.deleteStudent(id)) {
                System.out.println(
                        "Student deleted successfully!"
                );
            } else {
                System.out.println("Student was not deleted.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static Student readStudentDetails(int id) {

        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter age: ");
        int age = readInt();

        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter course: ");
        String course = sc.nextLine().trim();

        return new Student(id, name, age, email, course);
    }

    private static int readInt() {
        String input = sc.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Please enter a whole number."
            );
        }
    }
}
