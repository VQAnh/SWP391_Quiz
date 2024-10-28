package controller;

import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 * UserController Servlet class
 */
@WebServlet(urlPatterns={"/UserController"})
public class UserController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertUser(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Display list of users
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.getAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for adding a new user
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for editing an existing user
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User existingUser = userDAO.getUserById(userId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    // Insert new user into the database
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Validation
        List<String> errors = new ArrayList<>();
        if (username == null || username.trim().isEmpty()) {
            errors.add("Username is required.");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.add("Password is required.");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            errors.add("Invalid email format.");
        }
        if (role == null || role.trim().isEmpty()) {
            errors.add("Role is required.");
        }

        if (!errors.isEmpty()) {
            // Send errors and form data back to the form page
            request.setAttribute("errors", errors);
            request.setAttribute("user", new User(0,username, password, email, role, null));
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(request, response);
        } else {
            // If no errors, proceed to insert
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setRole(role);
            userDAO.addUser(newUser);
            response.sendRedirect("UserController?action=list");
        }
    }

    // Update an existing user in the database
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Validation
        List<String> errors = new ArrayList<>();
        if (username == null || username.trim().isEmpty()) {
            errors.add("Username is required.");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.add("Password is required.");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email is required.");
        } else if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
            errors.add("Invalid email format.");
        }
        if (role == null || role.trim().isEmpty()) {
            errors.add("Role is required.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", new User(userId, username, password, email, role, null));
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(request, response);
        } else {
            // If no errors, proceed to update
            User updatedUser = new User();
            updatedUser.setUserId(userId);
            updatedUser.setUsername(username);
            updatedUser.setPassword(password);
            updatedUser.setEmail(email);
            updatedUser.setRole(role);

            userDAO.updateUser(updatedUser);
            response.sendRedirect("UserController?action=list");
        }
    }

    // Delete a user from the database
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userDAO.deleteUser(userId);
        response.sendRedirect("UserController?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
