
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ page import="com.poi.valeri_poi.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/adminMenuStyle.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-links">
            <a href="adminHome.jsp">Home</a>
        </div>
        <div class="user-info">
            <span>Logged in as <%= ((User)session.getAttribute("user")).getUsername() %></span>
            <a href="logout.jsp">Logout</a>
        </div>
    </nav>

    <div class="sidebar">
        <ul>
            <li><a href="viewUsersAdmin.jsp">View Users</a></li>
            <li><a href="changeDetailsAdmin.jsp">Manage Users</a></li>
            <li><a href="listPOIadmin.jsp">Points of Interest</a></li>
            <li><a href="addPOIadmin.jsp">Add Points of Interest</a></li>
            <li><a href="CommentsAdmin.jsp">Manage Comments</a></li>
            <li><a href="logout.jsp">Log Out</a></li>
        </ul>
    </div>

    <div class="main-content">
        <div class="welcome-box">
            <h2>Welcome to the Admin Dashboard</h2>
            <p>Welcome, <%= ((User)session.getAttribute("user")).getUsername() %>! Here you can manage users, view and add points of interest, and moderate comments.</p>
            <img src="images/admin.jpg" alt="Welcome Image">
        </div>
    </div>

</body>
</html>
