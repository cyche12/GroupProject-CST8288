<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Food Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <div class="header">
        <div class="header-right">
            <a href="${pageContext.request.contextPath}/RetailServlet">Home</a>
            <span class="account-info">${sessionScope.email}</span>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="add-food-item-container">
        <h2>Add Food Item</h2>
        <form action="FoodAddingServlet" method="post">
            <input type="text" name="food_id" placeholder="Food ID" required>
            <input type="text" name="food_name" placeholder="Food Name" required>
            <input type="number" name="food_quantity" placeholder="Quantity" required>
            <input type="number" step="0.01" name="food_cost" placeholder="Cost" required>
            <input type="date" name="food_expiry" placeholder="Expiry Date" required>
            <input type="text" name="retailer_id" placeholder="Retailer ID" required>
            <input type="submit" value="Add Food Item">
        </form>
    </div>
</body>
</html>
