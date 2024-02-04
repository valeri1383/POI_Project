
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Points of Interest</title>
    <link rel="stylesheet" type="text/css" href="css/panelStyle.css">
</head>
<body>

<nav>
    <div class="nav-links">
        <a href="adminHome.jsp">Home</a>
    </div>
    <div>
        <span><a href="logout.jsp">Logout</a></span>
    </div>
</nav>
    
<div class="container">
    <div class="dark">
    <h2>Add a New Point of Interest</h2>
     <form action="" method="post" class="form-style form-inline">
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br>
        <label for="type">Type:</label>
        <select id="type" name="type" required>
            <option value="City">City</option>
            <option value="HistoricalSite">Historical Site</option>
            <option value="Restaurant">Restaurant</option>
            <option value="Pubs">Pubs</option>
        </select><br>
        
        <button type="submit" class="button">Add POI</button>
    </form>
    </div>
    <h2>Points of Interest</h2>

<%
    DBConnection dbConnection = new DBConnection(); 
    ResultSet rs = null;


    String location = request.getParameter("location");
    String type = request.getParameter("type");

    if (location != null && type != null && !location.isEmpty() && !type.isEmpty()) {
        String insertSql = "INSERT INTO PointsOfInterest (location, type, likes) VALUES (?, ?, 0)";
        // dbConnection is already initialized and should be accessible here
        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(insertSql)) {
            pstmt.setString(1, location);
            pstmt.setString(2, type);
            pstmt.executeUpdate(); // Execute the insert command
        } catch (SQLException e) {
            out.println("Error adding POI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Display existing POIs
    try {
        String sql = "SELECT * FROM PointsOfInterest";
        rs = dbConnection.executeSQL(sql);
%>
<table>
    <tr>
        <th>ID</th>
        <th>Location</th>
        <th>Type</th>
        <th>Likes</th>
    </tr>
<%
        while (rs.next()) {
%>
    <tr>
        <td><%= rs.getInt("point_of_interest_id") %></td>
        <td><%= rs.getString("location") %></td>
        <td><%= rs.getString("type") %></td>
        <td><%= rs.getInt("likes") %></td>
    </tr>
<%
        }
    } catch (SQLException e) {
        out.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        dbConnection.close();
    }
%>
</table>
</div>
</body>
</html>
