<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h2 class="text-center">${quiz != null ? "Edit Quiz" : "New Quiz"}</h2>
                </div>
                <div class="card-body">
                    <form action="QuizController?action=${quiz != null ? 'update' : 'insert'}" method="post" class="form-horizontal">
                        <input type="hidden" name="quizId" value="${quiz != null ? quiz.quizId : ''}" />

                        <!-- Quiz Title -->
                        <div class="form-group mb-4">
                            <label for="quizTitle" class="form-label">Quiz Title:</label>
                            <input type="text" class="form-control" name="quizTitle" value="${quiz != null ? quiz.quizTitle : ''}" required>
                        </div>

                        <!-- Class Dropdown -->
                        <div class="form-group mb-4">
                            <label for="classId" class="form-label">Class:</label>
                            <select class="form-control" name="classId" required>
                                <c:forEach var="cls" items="${listClass}">
                                    <option value="${cls.classId}" ${quiz != null && quiz.classId == cls.classId ? 'selected' : ''}>${cls.className}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- Buttons -->
                        <div class="form-group text-center mt-4">
                            <button type="submit" class="btn btn-success">${quiz != null ? "Update" : "Create"}</button>
                            <a href="QuizController?action=list" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
