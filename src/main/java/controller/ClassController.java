/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.ClassDAO;
import dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ClassInfo;
import model.User;

/**
 * ClassController Servlet class
 */
@WebServlet(urlPatterns={"/ClassController"})
public class ClassController extends HttpServlet {

    private ClassDAO classDAO = new ClassDAO();
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
                    insertClass(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateClass(request, response);
                    break;
                case "delete":
                    deleteClass(request, response);
                    break;
                default:
                    listClass(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Display list of classes
    private void listClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<ClassInfo> listClass = classDAO.getAllClasses();
        request.setAttribute("listClass", listClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for adding a new class
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<User> listTeachers = userDAO.getAllTeachers();
        request.setAttribute("listTeachers", listTeachers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
        dispatcher.forward(request, response);
    }

    // Show form for editing an existing class
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        ClassInfo existingClass = classDAO.getClassById(classId);
        List<User> listTeachers = userDAO.getAllTeachers();
        request.setAttribute("listTeachers", listTeachers);
        request.setAttribute("classI", existingClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
        dispatcher.forward(request, response);
    }

    // Insert new class into the database
    private void insertClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String className = request.getParameter("className");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        ClassInfo newClass = new ClassInfo();
        newClass.setClassName(className);
        newClass.setTeacherId(teacherId);
        classDAO.addClass(newClass);
        response.sendRedirect("ClassController?action=list");
    }

    // Update an existing class in the database
    private void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        String className = request.getParameter("className");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));

        ClassInfo updatedClass = new ClassInfo();
        updatedClass.setClassId(classId);
        updatedClass.setClassName(className);
        updatedClass.setTeacherId(teacherId);
        classDAO.updateClass(updatedClass);
        response.sendRedirect("ClassController?action=list");
    }

    // Delete a class from the database
    private void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        classDAO.deleteClass(classId);
        response.sendRedirect("ClassController?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
