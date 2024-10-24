<%-- 
    Document   : welcome
    Created on : Oct 23, 2024, 1:21:22 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <h1 class="mb-4">Welcome, ${sessionScope.user.username}</h1>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Student Information</h5>
                    <p class="card-text">
                        <strong>Email:</strong> ${sessionScope.user.email}<br>
                        <strong>Role:</strong> ${sessionScope.user.role}<br>
                        <strong>Created At:</strong> ${sessionScope.user.createdAt} 
                    </p>
                    <a href="logout" class="btn btn-danger">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
