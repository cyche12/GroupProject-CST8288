<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="database.FoodItemDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Available Items</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>

<div class="available-items-container">
    <h2>Available Items</h2>
    <table>
        <thead>
            <tr>
                <th>Food ID</th>
                <th>Food Name</th>
                <th>Quantity</th>
                <th>Cost</th>
                <th>Expiry Date</th>
                <th>Retailer ID</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
            	@SuppressWarnings("unchecked")
                List<FoodItemDTO> foodItems = (List<FoodItemDTO>) request.getAttribute("foodItems");
                if (foodItems != null) {
                    for (FoodItemDTO item : foodItems) {
            %>
            <tr>
                <td><%= item.getFoodId() %></td>
                <td><%= item.getFoodName() %></td>
                <td><%= item.getFoodQuantity() %></td>
                <td><%= item.getFoodCost() %></td>
                <td><%= item.getFoodExpiry() %></td>
                <td><%= item.getRetailerId() %></td>
                <td><a href="purchaseFoodItem.jsp?foodId=<%= item.getFoodId() %>">Purchase</a></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No items available.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
