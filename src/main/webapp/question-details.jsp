<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <!-- Card for Question Details -->
            <div class="card shadow-sm mb-4">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Question Details</h2>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <label><strong>Question ID:</strong></label>
                        <p>${question.questionId}</p>
                    </div>

                    <div class="mb-3">
                        <label><strong>Question Text:</strong></label>
                        <p>${question.questionText}</p>
                    </div>
                </div>
            </div>

            <!-- Options Section -->
            <h3 class="text-center mb-4">Options</h3>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Option Text</th>
                        <th>Is Correct?</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="option" items="${options}">
                        <tr>
                            <td>${option.optionText}</td>
                            <td>${option.isCorrect ? 'Yes' : 'No'}</td>
                            <td>
                                <a href="QuizController?action=editQuestion&questionId=${question.questionId}&quizId=${quizId}" class="btn btn-sm btn-warning">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Action Buttons -->
            <div class="text-center mt-4">
                <a href="QuizController?action=editQuestion&questionId=${question.questionId}&quizId=${quizId}" class="btn btn-warning">Edit Question</a>
                <a href="QuizController?action=listQuestions&quizId=${quizId}" class="btn btn-secondary">Back to List</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
