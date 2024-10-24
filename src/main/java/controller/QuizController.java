package controller;


import dao.ClassDAO;
import dao.OptionDAO;
import dao.QuestionDAO;
import dao.QuizDAO;
import model.Question;
import model.Option;
import model.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;     
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/QuizController"})
public class QuizController extends HttpServlet {

    private QuestionDAO questionDAO = new QuestionDAO();
    private OptionDAO optionDAO = new OptionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                    insertQuiz(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateQuiz(request, response);
                    break;
                case "delete":
                    deleteQuiz(request, response);
                    break;
                case "viewQuestions":
                    viewQuestions(request, response);
                    break;
                case "add-question":
                    showNewQuestionForm(request, response);
                    break;
                case "insertQuestion":
                    insertQuestion(request, response);
                    break;
                case "viewQuestionDetails":
                    viewQuestionDetails(request, response);
                    break;
                case "editQuestion":
                    showEditQuestionForm(request, response);
                    break;
                case "updateQuestion":
                    updateQuestion(request, response);
                    break;
                default:
                    listQuiz(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listQuiz(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Quiz> listQuiz = quizDAO.getAllQuizzes();
        request.setAttribute("listQuiz", listQuiz);
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz-list.jsp");
        dispatcher.forward(request, response);
    }


    private void insertQuiz(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String quizTitle = request.getParameter("quizTitle");
        int classId = Integer.parseInt(request.getParameter("classId"));
        int createdBy = 1;

        Quiz newQuiz = new Quiz();
        newQuiz.setQuizTitle(quizTitle);
        newQuiz.setClassId(classId);
        newQuiz.setCreatedBy(createdBy);
        quizDAO.addQuiz(newQuiz);
        response.sendRedirect("QuizController?action=list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ClassDAO classDAO = new ClassDAO();
        List<ClassInfo> listClass = classDAO.getAllClasses();
        request.setAttribute("listClass", listClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        ClassDAO classDAO = new ClassDAO();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Quiz existingQuiz = quizDAO.getQuizById(quizId);
        List<ClassInfo> listClass = classDAO.getAllClasses();
        request.setAttribute("quiz", existingQuiz);
        request.setAttribute("listClass", listClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateQuiz(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String quizTitle = request.getParameter("quizTitle");
        int classId = Integer.parseInt(request.getParameter("classId"));
        int createdBy = 1;

        Quiz updatedQuiz = new Quiz(quizId, quizTitle, classId, createdBy);
        quizDAO.updateQuiz(updatedQuiz);
        response.sendRedirect("QuizController?action=list");
    }

    private void deleteQuiz(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        quizDAO.deleteQuiz(quizId);
        response.sendRedirect("QuizController?action=list");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
