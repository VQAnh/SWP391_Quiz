<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Edit Question</h2>
                </div>
                <div class="card-body">
                    <form action="QuizController?action=updateQuestion" method="post" class="form-horizontal">
                        <input type="hidden" name="questionId" value="${question.questionId}" />
                        <input type="hidden" name="quizId" value="${quizId}" />

                        <!-- Question Text -->
                        <div class="form-group">
                            <label for="questionText" class="form-label">Question Text:</label>
                            <input type="text" class="form-control" name="questionText" value="${question.questionText}" required>
                        </div>

                        <!-- Options Section -->
                        <h4 class="text-center mt-4">Edit Options</h4>
                        <c:forEach var="option" items="${options}" varStatus="status">
                            <div class="card mb-3 shadow-sm">
                                <div class="card-body">
                                    <input type="hidden" name="optionId${status.index + 1}" value="${option.optionId}" />
                                    <div class="row">
                                        <!-- Option Text -->
                                        <div class="col-md-10">
                                            <label for="optionText${status.index + 1}" class="form-label">Option ${status.index + 1}:</label>
                                            <input type="text" class="form-control" name="optionText${status.index + 1}" value="${option.optionText}" required>
                                        </div>
                                        <!-- Correct Checkbox -->
                                        <div class="col-md-2 d-flex align-items-center">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="isCorrect${status.index + 1}" ${option.isCorrect ? 'checked' : ''}>
                                                <label class="form-check-label">Correct</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- Submit Button -->
                        <div class="form-group text-center mt-4">
                            <button type="submit" class="btn btn-success">Update Question</button>
                            <a href="QuizController?action=viewQuestionDetails&questionId=${question.questionId}&quizId=${quizId}" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
