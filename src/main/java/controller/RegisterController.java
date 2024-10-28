/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import model.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String role = "Student";

        boolean hasError = false;

        if (userDAO.isUsernameExists(username)) {
            request.setAttribute("usernameError", "Username already exists.");
            hasError = true;
        }

        if (userDAO.isEmailExists(email)) {
            request.setAttribute("emailError", "Email already exists.");
            hasError = true;
        }
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailPattern)) {
            request.setAttribute("emailError", "Invalid email format.");
            hasError = true;
        }

        if (password.length() < 8) {
            request.setAttribute("passwordError", "Password must be at least 8 characters long.");
            hasError = true;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("confirmPasswordError", "Passwords do not match.");
            hasError = true;
        }

        if (hasError) {
            request.setAttribute("username", username); // Retain form data
            request.setAttribute("email", email); // Retain form data
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); 
        newUser.setEmail(email);
        newUser.setRole(role);

        boolean isRegistered = userDAO.registerUser(newUser);

        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("generalError", "Registration failed, please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
                
    }
    
    
}
