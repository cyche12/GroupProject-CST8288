//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package servlets;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import database.FoodItemDAO;
import database.FoodItemDTO;

public class FoodAddingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FoodAddingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && "RETAIL".equals(session.getAttribute("userType"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("AddFoodItem.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("RetailServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && "RETAIL".equals(session.getAttribute("userType"))) {
            String foodId = request.getParameter("food_id");
            String foodName = request.getParameter("food_name");
            int foodQuantity = Integer.parseInt(request.getParameter("food_quantity"));
            double foodCost = Double.parseDouble(request.getParameter("food_cost"));
            String foodExpiryString = request.getParameter("food_expiry");
            String retailerId = request.getParameter("retailer_id");

            Date foodExpiry = null;
            try {
                foodExpiry = Date.valueOf(foodExpiryString);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Invalid date format.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("AddFoodItem.jsp");
                dispatcher.forward(request, response);
                return;
            }

            FoodItemDTO foodItem = new FoodItemDTO(foodId, foodName, foodQuantity, foodCost, foodExpiry, retailerId);
            FoodItemDAO foodItemDAO = new FoodItemDAO();

            boolean isAdded = foodItemDAO.addFoodItem(foodItem);
            if (isAdded) {
                request.setAttribute("message", "Food item added successfully!");
            } else {
                request.setAttribute("error", "Failed to add food item.");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("AddFoodItem.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("RetailServlet");
        }
    }
}
