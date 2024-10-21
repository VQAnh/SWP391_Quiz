/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import DBConnection.DBConnection;
import model.Option;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDAO {

    // Get all options for a question
    public List<Option> getOptionsByQuestionId(int questionId) throws SQLException {
        List<Option> options = new ArrayList<>();
        String sql = "SELECT * FROM [Option] WHERE question_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, questionId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Option option = new Option();
                option.setOptionId(rs.getInt("option_id"));
                option.setQuestionId(rs.getInt("question_id"));
                option.setOptionText(rs.getString("option_text"));
                option.setIsCorrect(rs.getBoolean("is_correct"));
                options.add(option);
            }
        }
        return options;
    }

    // Insert new option
    public void addOption(Option option) throws SQLException {
        String sql = "INSERT INTO [Option] (question_id, option_text, is_correct) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, option.getQuestionId());
            pstmt.setString(2, option.getOptionText());
            pstmt.setBoolean(3, option.isIsCorrect());
            pstmt.executeUpdate();
        }
    }

    // Get option by ID
    public Option getOptionById(int optionId) throws SQLException {
        Option option = null;
        String sql = "SELECT * FROM [Option] WHERE option_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, optionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                option = new Option();
                option.setOptionId(rs.getInt("option_id"));
                option.setQuestionId(rs.getInt("question_id"));
                option.setOptionText(rs.getString("option_text"));
                option.setIsCorrect(rs.getBoolean("is_correct"));
            }
        }
        return option;
    }

    // Update option
    public void updateOption(Option option) throws SQLException {
        String sql = "UPDATE [Option] SET option_text = ?, is_correct = ? WHERE option_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, option.getOptionText());
            pstmt.setBoolean(2, option.isIsCorrect());
            pstmt.setInt(3, option.getOptionId());
            pstmt.executeUpdate();
        }
    }

    // Delete option by ID
    public void deleteOption(int optionId) throws SQLException {
        String sql = "DELETE FROM [Option] WHERE option_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, optionId);
            pstmt.executeUpdate();
        }
    }
}
