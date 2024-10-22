/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author HP
 */
import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ClassInfo;

public class ClassDAO {

    // Get all classes
    public List<ClassInfo> getAllClasses() throws SQLException {
        List<ClassInfo> classes = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM Class c "
                + "JOIN [User] u ON c.teacher_id = u.user_id";
        TeacherDAO teacherDao = new TeacherDAO();
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ClassInfo cls = new ClassInfo();
                cls.setClassId(rs.getInt("class_id"));
                cls.setClassName(rs.getString("class_name"));
                cls.setTeacherId(rs.getInt("teacher_id"));
                cls.setTeacher(teacherDao.getTeacherById(rs.getInt("teacher_id")));
                classes.add(cls);
            }
        }
        return classes;
    }

    // Insert a new class
    public void addClass(ClassInfo cls) throws SQLException {
        String sql = "INSERT INTO [Class] (class_name, teacher_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cls.getClassName());
            pstmt.setInt(2, cls.getTeacherId());
            pstmt.executeUpdate();
        }
    }

    // Get class by ID
    public ClassInfo getClassById(int classId) throws SQLException {
        ClassInfo cls = null;
        String sql = "SELECT * FROM Class WHERE class_id = ?";
        TeacherDAO teacherDao = new TeacherDAO();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cls = new ClassInfo();
                cls.setClassId(rs.getInt("class_id"));
                cls.setClassName(rs.getString("class_name"));
                cls.setTeacherId(rs.getInt("teacher_id"));
                cls.setTeacher(teacherDao.getTeacherById(rs.getInt("teacher_id")));
            }
        }
        return cls;
    }

    // Update an existing class
    public void updateClass(ClassInfo cls) throws SQLException {
        String sql = "UPDATE Class SET class_name = ?, teacher_id = ? WHERE class_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cls.getClassName());
            pstmt.setInt(2, cls.getTeacherId());
            pstmt.setInt(3, cls.getClassId());
            pstmt.executeUpdate();
        }
    }

    // Delete a class by ID
    public void deleteClass(int classId) throws SQLException {
        String sql = "DELETE FROM Class WHERE class_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classId);
            pstmt.executeUpdate();
        }
    }
}
