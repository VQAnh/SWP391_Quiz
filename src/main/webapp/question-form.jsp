<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">Add New Question</h2>
                </div>
                <div class="card-body">
                    <form action="QuizController?action=insertQuestion" method="post" class="form-horizontal">
                        <input type="hidden" name="quizId" value="${quizId}" />

                        <!-- Question Text -->
                        <div class="form-group mb-4">
                            <label for="questionText" class="form-label">Question Text:</label>
                            <input type="text" class="form-control" name="questionText" required>
                        </div>

                        <!-- Options Section -->
                        <h4 class="text-center mb-4">Options</h4>
                        <c:forEach var="i" begin="1" end="4">
                            <div class="card mb-3 shadow-sm">
                                <div class="card-body">
                                    <div class="row">
                                        <!-- Option Text -->
                                        <div class="col-md-10">
                                            <label for="option${i}" class="form-label">Option ${i}:</label>
                                            <input type="text" class="form-control" name="option${i}" required>
                                        </div>
                                        <!-- Correct Checkbox -->
                                        <div class="col-md-2 d-flex align-items-center">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="isCorrect${i}">
                                                <label class="form-check-label">Correct</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- Submit and Cancel Buttons -->
                        <div class="form-group text-center mt-4">
                            <button type="submit" class="btn btn-success">Add Question</button>
                            <a href="QuizController?action=viewQuestions&quizId=${quizId}" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
