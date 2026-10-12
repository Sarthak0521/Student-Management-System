package com.sms.service;
import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import java.sql.SQLException;
import java.util.List;
public class StudentService {
    private final StudentDAO dao = new StudentDAO();
    public boolean registerStudent(Student s)
            throws SQLException {

        validateStudent(s);
        return dao.addStudent(s);
    }
    public List<Student> getAllStudents()
            throws SQLException {

        return dao.getAllStudents();
    }
    public Student getStudentById(int id)
            throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive."
            );
        }

        return dao.getStudentById(id);
    }
    public boolean updateStudent(Student s)
            throws SQLException {

        validateStudent(s);
        return dao.updateStudent(s);
    }
    public boolean deleteStudent(int id)
            throws SQLException {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive."
            );
        }

        return dao.deleteStudent(id);
    }
    private void validateStudent(Student s) {

        if (s == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null."
            );
        }

        if (s.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be positive."
            );
        }

        if (s.getName() == null ||
                s.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Name cannot be empty."
            );
        }

        if (s.getAge() <= 0 || s.getAge() > 150) {
            throw new IllegalArgumentException(
                    "Age must be between 1 and 150."
            );
        }

        if (s.getEmail() == null ||
                !s.getEmail().matches(
                        "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException(
                    "Enter a valid email address."
            );
        }

        if (s.getCourse() == null ||
                s.getCourse().isBlank()) {
            throw new IllegalArgumentException(
                    "Course cannot be empty."
            );
        }
    }
}
