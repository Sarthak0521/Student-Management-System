
package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE
    public boolean addStudent(Student s)
            throws SQLException {

        String sql = """
                INSERT INTO students
                (id, name, age, email, course)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getEmail());
            ps.setString(5, s.getCourse());

            return ps.executeUpdate() == 1;
        }
    }

    // READ ALL
    public List<Student> getAllStudents()
            throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT id, name, age, email, course
                FROM students
                ORDER BY id
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("course")
                );

                students.add(s);
            }
        }

        return students;
    }

    // READ ONE
    public Student getStudentById(int id)
            throws SQLException {

        String sql = """
                SELECT id, name, age, email, course
                FROM students
                WHERE id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("email"),
                            rs.getString("course")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE
    public boolean updateStudent(Student s)
            throws SQLException {

        String sql = """
                UPDATE students
                SET name = ?, age = ?, email = ?, course = ?
                WHERE id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getCourse());
            ps.setInt(5, s.getId());

            return ps.executeUpdate() == 1;
        }
    }

    // DELETE
    public boolean deleteStudent(int id)
            throws SQLException {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }
}
