<%-- 
    Document   : login
    Created on : Oct 23, 2024, 1:20:59 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FPT University Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: fafafa;
        }
        .form-container {
            margin-top: 50px;
            border: 1px solid #e3e3e3;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-container h3 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .form-container label {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .help-text {
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
        }

        .help-text a {
            color: #007bff;
            text-decoration: none;
        }

        .help-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4 form-container">
            <h3 class="text-center">FPT University Login</h3>
            <form action="login" method="post">
                <h5 class="text-center">Login with your username and password</h5>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Your username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Login</button>
            </form>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">
                    ${errorMessage}
                </div>
            </c:if>
            <div class="help-text" style="display: flex; justify-content: space-between">
                <a href="#">Forgot password, reset password?</a>
                <a href="register">Register</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
