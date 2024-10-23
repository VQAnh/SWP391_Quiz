/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author HP
 */
public class StudentQuizResult {
    private int resultId;
    private int studentId;
    private int quizId;
    private BigDecimal score;
    private Timestamp submittedAt;

    public StudentQuizResult() {
    }

    public StudentQuizResult(int resultId, int studentId, int quizId, BigDecimal score, Timestamp submittedAt) {
        this.resultId = resultId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }

    
}
