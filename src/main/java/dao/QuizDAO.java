/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import DBConnection.DBConnection;
import model.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    // Get all quizzes
    public List<Quiz> getAllQuizzes() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM Quiz";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setQuizId(rs.getInt("quiz_id"));
                quiz.setQuizTitle(rs.getString("quiz_title"));
                quiz.setClassId(rs.getInt("class_id"));
                quiz.setCreatedBy(rs.getInt("created_by"));
                quizzes.add(quiz);
            }
        }
        return quizzes;
    }

    // Insert new quiz
    public void addQuiz(Quiz quiz) throws SQLException {
    String sql = "INSERT INTO Quiz (quiz_title, class_id, created_by) VALUES (?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, quiz.getQuizTitle());
        pstmt.setInt(2, quiz.getClassId());
        pstmt.setInt(3, quiz.getCreatedBy());
        pstmt.executeUpdate();
    }
}

    // Get quiz by ID
    public Quiz getQuizById(int quizId) throws SQLException {
    Quiz quiz = null;
    String sql = "SELECT * FROM Quiz WHERE quiz_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, quizId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            quiz = new Quiz();
            quiz.setQuizId(rs.getInt("quiz_id"));
            quiz.setQuizTitle(rs.getString("quiz_title"));
            quiz.setClassId(rs.getInt("class_id"));
            quiz.setCreatedBy(rs.getInt("created_by"));
        }
    }
    return quiz;
}


    // Update quiz
    public void updateQuiz(Quiz quiz) throws SQLException {
        String sql = "UPDATE Quiz SET quiz_title = ?, class_id = ?, created_by = ? WHERE quiz_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, quiz.getQuizTitle());
            pstmt.setInt(2, quiz.getClassId());
            pstmt.setInt(3, quiz.getCreatedBy());
            pstmt.setInt(4, quiz.getQuizId());
            pstmt.executeUpdate();
        }
    }

    // Delete quiz by ID
    public void deleteQuiz(int quizId) throws SQLException {
        String sql = "DELETE FROM Quiz WHERE quiz_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, quizId);
            pstmt.executeUpdate();
        }
    }
}
