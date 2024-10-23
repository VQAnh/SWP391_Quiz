/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.List;

/**
 *
 * @author HP
 */
public class Quiz {
    private int quizId;
    private String quizTitle;
    private int classId;
    private int createdBy;
    private List<Question> question;

    public Quiz() {
    }

    public Quiz(int quizId, String quizTitle, int classId, int createdBy) {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.classId = classId;
        this.createdBy = createdBy;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    
}
