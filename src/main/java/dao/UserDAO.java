package dao;

import DBConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(user);
            }
        }
        return users;
    }

    // Insert a new user
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO [User] (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.executeUpdate();
        }
    }

    // Get user by ID
    public User getUserById(int userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
            }
        }
        return user;
    }

    // Update an existing user
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE [User] SET username = ?, password = ?, email = ?, role = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getUserId());
            pstmt.executeUpdate();
        }
    }

    // Delete a user by ID
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM [User] WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
    
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
}
