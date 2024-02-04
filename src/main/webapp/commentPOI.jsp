
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Comments on Points of Interest</title>
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
    <h2>Comments on Points of Interest</h2>

    <div class="dark">
        <form method="POST">
            <label for="poiId">Point of Interest ID:</label>
            <input type="text" name="poiId" required>
            <label for="comment">Add your Comment:</label>
            <input type="text" name="comment" required>
            <button type="submit">Submit Comment</button>
        </form>
    </div>

    <h2>View Comments for a Point of Interest</h2>
    <div class="dark">
        
        <form method="GET"> <!-- Using GET since we're retrieving data -->
            <label for="viewPoiId">Point of Interest ID:</label>
            <input type="text" name="viewPoiId" required>
            <button type="submit">View Comments</button>
        </form>
    </div>


<%
    DBConnection dbConnection = new DBConnection();
    if ("POST".equalsIgnoreCase(request.getMethod())) {
        try {
            String poiId = request.getParameter("poiId");
            String commentText = request.getParameter("comment");

            String insertSql = "INSERT INTO Comments (point_of_interest_id, comment) VALUES (?, ?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(insertSql);
            preparedStatement.setInt(1, Integer.parseInt(poiId));
            preparedStatement.setString(2, commentText);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            out.println("Error adding comment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    String viewPoiId = request.getParameter("viewPoiId");
    if (viewPoiId != null && !viewPoiId.trim().isEmpty()) {
        try {
            String fetchCommentsSql = "SELECT comment FROM Comments WHERE point_of_interest_id = ?";
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(fetchCommentsSql);
            ps.setInt(1, Integer.parseInt(viewPoiId));
            ResultSet commentsRs = ps.executeQuery();

            out.println("<h3>Comments for Point of Interest with ID: " + viewPoiId + "</h3>");
            out.println("<ul>");
            while (commentsRs.next()) {
                out.println("<li>" + commentsRs.getString("comment") + "</li>");
            }
            out.println("</ul>");

            commentsRs.close();
            ps.close();
        } catch (Exception e) {
            out.println("Error fetching comments: " + e.getMessage());
            e.printStackTrace();
        }
    }

    try {
        String sql = "SELECT * FROM PointsOfInterest";
        ResultSet rs = dbConnection.executeSQL(sql);
%>
<h3>All Points of Interest</h3>
<table>
    <tr>
        <th>POI ID</th>
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
        rs.close();
    } catch (SQLException e) {
        out.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        out.println("General Exception: " + e.getMessage());
        e.printStackTrace();
    } finally {
        dbConnection.close();
    }
%>
</table>
</div>
</body>
</html>
