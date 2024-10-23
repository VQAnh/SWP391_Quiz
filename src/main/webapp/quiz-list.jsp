<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center mb-4">Quiz List</h2>

            <!-- Button to add new quiz -->
            <div class="mb-3 text-end">
                <a href="QuizController?action=new" class="btn btn-primary">Add New Quiz</a>
            </div>

            <!-- Quiz List Table -->
            <table id="quizTable" class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="quiz" items="${listQuiz}">
                        <tr>
                            <td>${quiz.quizId}</td>
                            <td>${quiz.quizTitle}</td>
                            <td>
                                <a href="QuizController?action=viewQuestions&quizId=${quiz.quizId}" class="btn btn-sm btn-info">View Questions</a>
                                <a href="QuizController?action=edit&quizId=${quiz.quizId}" class="btn btn-sm btn-warning">Edit</a>
                                <a href="QuizController?action=add-question&quizId=${quiz.quizId}" class="btn btn-sm btn-success">Add Question</a>
                                <a href="QuizController?action=delete&quizId=${quiz.quizId}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this quiz?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- DataTables JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

<!-- Initialize DataTables -->
<script>
    $(document).ready(function() {
        $('#quizTable').DataTable({
            "pagingType": "simple_numbers",
            "language": {
                "search": "Search:",
                "lengthMenu": "Show _MENU_ entries",
                "zeroRecords": "No matching records found",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries available",
                "infoFiltered": "(filtered from _MAX_ total entries)",
                "paginate": {
                    "first": "First",
                    "last": "Last",
                    "next": "Next",
                    "previous": "Previous"
                }
            }
        });
    });
</script>

<%@ include file="footer.jsp" %>
