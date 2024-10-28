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
    
    private void viewQuestions(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
        request.setAttribute("questions", questions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("quiz-questions.jsp");
        dispatcher.forward(request, response);
    }

    private void viewQuestionDetails(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Question question = questionDAO.getQuestionById(questionId);
        List<Option> options = optionDAO.getOptionsByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("options", options);
        request.setAttribute("quizId", request.getParameter("quizId"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("question-details.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewQuestionForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("quizId", request.getParameter("quizId"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("question-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String questionText = request.getParameter("questionText");

        Question newQuestion = new Question();
        newQuestion.setQuizId(quizId);
        newQuestion.setQuestionText(questionText);
        int questionId = questionDAO.addQuestion(newQuestion);

        if (questionId > 0) {
            List<Option> options = new ArrayList<>();
            for (int i = 1; i <= 4; i++) {
                String optionText = request.getParameter("option" + i);
                boolean isCorrect = "on".equals(request.getParameter("isCorrect" + i));

                Option option = new Option();
                option.setQuestionId(questionId);
                option.setOptionText(optionText);
                option.setIsCorrect(isCorrect);
                options.add(option);
            }

            for (Option option : options) {
                optionDAO.addOption(option);
            }

            response.sendRedirect("QuizController?action=viewQuestions&quizId=" + quizId);
        } else {
            request.setAttribute("errorMessage", "Failed to add the question.");
            showNewQuestionForm(request, response);
        }
    }

    private void showEditQuestionForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Question question = questionDAO.getQuestionById(questionId);
        List<Option> options = optionDAO.getOptionsByQuestionId(questionId);

        request.setAttribute("question", question);
        request.setAttribute("options", options);
        request.setAttribute("quizId", request.getParameter("quizId"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("question-edit.jsp");
        dispatcher.forward(request, response);
    }

    private void updateQuestion(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String questionText = request.getParameter("questionText");

        Question updatedQuestion = new Question();
        updatedQuestion.setQuestionId(questionId);
        updatedQuestion.setQuestionText(questionText);
        questionDAO.updateQuestion(updatedQuestion);

        for (int i = 1; i <= 4; i++) {
            int optionId = Integer.parseInt(request.getParameter("optionId" + i));
            String optionText = request.getParameter("optionText" + i);
            boolean isCorrect = "on".equals(request.getParameter("isCorrect" + i));

            Option option = new Option();
            option.setOptionId(optionId);
            option.setOptionText(optionText);
            option.setIsCorrect(isCorrect);
            optionDAO.updateOption(option);
        }
        response.sendRedirect("QuizController?action=viewQuestionDetails&questionId=" + questionId);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

    
