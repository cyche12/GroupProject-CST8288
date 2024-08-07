//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.UserDAO;
import database.UserDTO;
import enums.UserType;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class.getName());

    public RegistrationServlet() {
        super();
        userDao = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        String userType = request.getParameter("user_type");
        String joinDate = request.getParameter("join_date");

        if (firstName == null || lastName == null || email == null || password == null || userType == null || joinDate == null) {
            LOGGER.severe("One or more parameters are missing");
            request.setAttribute("error", "All fields are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        userType = userType.toUpperCase();

        LOGGER.info("Received form data:");
        LOGGER.info("First Name: " + firstName);
        LOGGER.info("Last Name: " + lastName);
        LOGGER.info("Email: " + email);
        LOGGER.info("Password: " + password);
        LOGGER.info("User Type: " + userType);
        LOGGER.info("Join Date: " + joinDate);

        UserDTO user = new UserDTO();
        user.setUserId(UUID.randomUUID().toString());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserEmail(email);
        user.setUserPassword(password);

        try {
            user.setUserType(UserType.valueOf(userType));
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Invalid user type: " + userType);
            request.setAttribute("error", "Invalid user type. Please select a valid user type.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            Date sqlJoinDate = Date.valueOf(joinDate);
            user.setJoinDate(sqlJoinDate);
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Invalid date format: " + joinDate);
            request.setAttribute("error", "Invalid date format. Please use the date picker.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        LOGGER.info("Attempting to add user to the database");
        boolean isUserAdded = userDao.addUser(user);
        if (isUserAdded) {
            LOGGER.info("User registered successfully: " + user.getUserId());
            response.sendRedirect("index.jsp");
        } else {
            LOGGER.severe("Failed to register user");
            request.setAttribute("error", "Failed to register user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
