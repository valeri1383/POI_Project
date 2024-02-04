
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    
    <title>Comments on Points of Interest</title>
    <link rel="stylesheet" type="text/css" href="css/panelStyle.css">
    <script>
        function confirmDeletion(commentId) {
            var confirmAction = confirm("Are you sure you want to delete this comment?");
            if (confirmAction) {
                window.location.href = "DeleteComment.jsp?commentId=" + commentId;
            } else {
                // If canceled, do nothing
            }
        }
    </script>
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
    <h2>Delete Comments on Points of Interest</h2>
    <%--
     <div class="dark">
        <form method="POST">
            <label for="poiId">Point of Interest ID:</label>
            <input type="text" name="poiId" required>
            <label for="comment">Comment:</label>
            <input type="text" name="comment" required>
            <input type="submit" value="Submit Comment" class="button">
        </form>
    </div>
    --%>

    <%
        DBConnection dbConnection = new DBConnection();
        ResultSet rs = null;
        try {
            String sql = "SELECT PointsOfInterest.point_of_interest_id, PointsOfInterest.location, PointsOfInterest.type, PointsOfInterest.likes, Comments.comment, Comments.Comment_Id " +
                         "FROM Comments " +
                         "JOIN PointsOfInterest ON Comments.point_of_interest_id = PointsOfInterest.point_of_interest_id";
            rs = dbConnection.executeSQL(sql);
    %>

    <table>
        <tr>
            <th>POI ID</th>
            <th>Location</th>
            <th>Type</th>
            <th>Likes</th>
            <th>Comment</th>
            <th>Action</th>
        </tr>
        <%
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("point_of_interest_id") %></td>
            <td><%= rs.getString("location") %></td>
            <td><%= rs.getString("type") %></td>
            <td><%= rs.getInt("likes") %></td>
            <td><%= rs.getString("comment") %></td>
            <td>
                <button onclick="confirmDeletion(<%= rs.getInt("Comment_Id") %>);">Delete</button>
            </td>
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
