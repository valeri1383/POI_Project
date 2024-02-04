
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
    <h2>Points of Interest</h2>

<%
    DBConnection dbConnection = new DBConnection();
    ResultSet rs = null;

  
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
            int poiId = rs.getInt("point_of_interest_id");
            String location = rs.getString("location");
            String type = rs.getString("type");
            int likes = rs.getInt("likes");
%>
    <tr>
        <td><%= poiId %></td>
        <td><%= location %></td>
        <td><%= type %></td>
        <td><%= likes %></td>
    </tr>
<%
        }
    } catch (SQLException e) {
        out.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        out.println("General Exception: " + e.getMessage());
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
