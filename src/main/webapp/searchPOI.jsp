
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Search and Like Points of Interest</title>
    <link rel="stylesheet" type="text/css" href="css/panelStyle.css">
</head>
<body>
    

<nav>
    <div class="nav-links">
        <a href="home.jsp">Home</a>
        <a href="searchPOI.jsp">Search and Like POI</a>
        <a href="listPOI.jsp">List of POI</a>
        <a href="commentPOI.jsp">Comment on POI</a>
    </div>
    <div>
    <span><a href="logout.jsp">Logout</a></span>
    </div>
</nav>


<div class="container">
    <h2>Points of Interest</h2>

    <!-- Search Form -->
<div class="dark"> 
    <div class="search-container">
        <!-- Search Form -->
        <form method="GET" class="search-form">
            <input type="text" name="searchQuery" placeholder="Search by location or type" class="search-input">
            <button type="submit" class="button">Search</button>
        </form>
    </div>
</div>
<%
    DBConnection dbConnection = new DBConnection();
    ResultSet rs = null;

    // Handling likes
    if ("post".equalsIgnoreCase(request.getMethod())) {
        String likePoiId = request.getParameter("likePoiId");
        if (likePoiId != null && !likePoiId.trim().isEmpty()) {
            try {
                String updateSql = "UPDATE PointsOfInterest SET likes = likes + 1 WHERE point_of_interest_id = ?";
                PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(updateSql);
                preparedStatement.setInt(1, Integer.parseInt(likePoiId));
                preparedStatement.executeUpdate();
                preparedStatement.close();

                // Refresh the page to show updated likes
     String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/listPOI.jsp");
            return;
            } catch (Exception e) {
                out.println("Error processing like: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Display Points of Interest Table
    String searchQuery = request.getParameter("searchQuery");
    try {
        String sql;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            
            sql = "SELECT * FROM PointsOfInterest WHERE location LIKE ? OR type LIKE ?";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchQuery + "%");
            preparedStatement.setString(2, "%" + searchQuery + "%");
            rs = preparedStatement.executeQuery();
        } else {
            sql = "SELECT * FROM PointsOfInterest";
            rs = dbConnection.executeSQL(sql);
        }
%>

<table>
    <tr>
        <th>ID</th>
        <th>Location</th>
        <th>Type</th>
        <th>Likes</th>
        <th>Action</th>
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
        <td><form method="POST"><input type="hidden" name="likePoiId" value="<%= poiId %>"><input type="submit" value="Like"></form></td>
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