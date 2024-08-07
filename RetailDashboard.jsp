<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retail Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <div class="header">
        <div class="header-right">
            <a href="RetailServlet">Home</a>
            <span class="account-info">${sessionScope.email}</span>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="dashboard-container">
        <h2>Welcome, Retail User</h2>
        <p>Email: ${sessionScope.email}</p>
        <a href="${pageContext.request.contextPath}/FoodAddingServlet" class="add-food-button">Add Food Item</a>
    </div>
</body>
</html>
