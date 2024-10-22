/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author HP
 */
public class TeacherDAO {

    public List<User> getAllTeachers() throws SQLException {
        List<User> teachers = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE role = 'Teacher'";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User teacher = new User();
                teacher.setUserId(rs.getInt("user_id"));
                teacher.setUsername(rs.getString("username"));
                teacher.setEmail(rs.getString("email"));
                teacher.setRole(rs.getString("role"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }
    
    public User getTeacherById(int userId) throws SQLException {
    User teacher = null;
    String sql = "SELECT * FROM [User] WHERE user_id = ? AND role = 'Teacher'";
    try (Connection conn = DBConnection.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            teacher = new User();
            teacher.setUserId(rs.getInt("user_id"));
            teacher.setUsername(rs.getString("username"));
            teacher.setEmail(rs.getString("email"));
            teacher.setRole(rs.getString("role"));
        }
    }
    return teacher;
}

}
