<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center">Question List</h2>
            <div class="text-center mt-4">
                <a href="QuizController?action=add-question&quizId=${param.quizId}" class="btn btn-primary">Add New Question</a>
            </div>
            <c:forEach var="question" items="${questions}">
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-9">
                                <h5 class="card-title">Question ${question.questionId}</h5>
                                <p class="card-text">${question.questionText}</p>
                            </div>
                            <div class="col-md-3 text-end">
                                <a href="QuizController?action=editQuestion&questionId=${question.questionId}&quizId=${quizId}" class="btn btn-warning btn-sm mb-1">Edit</a>
                                <a href="QuizController?action=deleteQuestion&questionId=${question.questionId}&quizId=${quizId}" class="btn btn-danger btn-sm mb-1" onclick="return confirm('Are you sure you want to delete this question?');">Delete</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
