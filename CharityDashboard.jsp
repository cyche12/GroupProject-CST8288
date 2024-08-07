<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Charity Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
</head>
<body>
    <div class="header">
        <div class="header-right">
            <a href="CharityServlet">Home</a>
            <span class="account-info">${sessionScope.email}</span>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="dashboard-container">
        <h2>Welcome, Charity User</h2>
        <p>Email: ${sessionScope.email}</p>
    </div>
</body>
</html>
