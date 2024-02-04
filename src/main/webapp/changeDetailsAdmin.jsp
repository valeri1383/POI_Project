
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change/Delete User Details</title>
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
    <h2>Change/Delete User Details</h2>

    <!-- form submission -->
    <%
        String action = request.getParameter("action");
        String userId = request.getParameter("user_id");
        DBConnection dbConnection = new DBConnection();
        int rowsAffected = 0;

        if ("change".equals(action) && userId != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String userType = request.getParameter("user_type");
            String updateSql = "UPDATE UserAccount SET username='" + username + "', password='" + password + "', user_type='" + userType + "' WHERE User_Id=" + userId;

            try {
                rowsAffected = dbConnection.executeUpdate(updateSql);
                if (rowsAffected > 0) {
                    out.println("<p>User details updated successfully.</p>");
                } else {
                    out.println("<p>No user found with ID: " + userId + "</p>");
                }
            } catch (SQLException e) {
                out.println("<p>Error updating user details: " + e.getMessage() + "</p>");
            }
        } else if ("delete".equals(action) && userId != null) {
            String deleteSql = "DELETE FROM UserAccount WHERE User_Id=" + userId;
            try {
                rowsAffected = dbConnection.executeUpdate(deleteSql);
                if (rowsAffected > 0) {
                    out.println("<p>User deleted successfully.</p>");
                } else {
                    out.println("<p>No user found with ID: " + userId + "</p>");
                }
            } catch (SQLException e) {
                out.println("<p>Error deleting user: " + e.getMessage() + "</p>");
            }
        }
    %>

 <!-- Change User Details Form -->
<div class="dark">
    <h3>Update User Details</h3>
    <form action="" method="post" class="form-style form-inline">
        <input type="hidden" name="action" value="change" />
        <div class="form-group">
            <label for="user_id">User ID:</label>
            <input type="text" name="user_id" required class="form-control" />
        </div>
        <div class="form-group">
            <label for="username">Change Username:</label>
            <input type="text" name="username" class="form-control" />
        </div>
        <div class="form-group">
            <label for="password">Change Password:</label>
            <input type="password" name="password" class="form-control" />
        </div>
        <div class="form-group">
            <label for="user_type">Change User Type:</label>
            <select name="user_type" class="form-control">
                <option value="admin">Admin</option>
                <option value="user">User</option>
            </select>
        </div>
        <div class="form-group">
            <button type="submit" class="button">Change User Details</button>
        </div>
    </form>
</div>

    <!-- Delete User Form -->
<div class="dark">
    <h3>Update User Details</h3>
    <form action="" method="post">
        <input type="hidden" name="action" value="delete" />
        User ID (for deletion): <input type="text" name="user_id" required />
        <button type="submit" class="button" value="Delete User">Delete User</button>
    </form>
</div>

    <!-- Existing User List for Reference -->
   <h2>User List</h2>
<%
    try {
        Connection conn = dbConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM UserAccount";
        ResultSet rs = stmt.executeQuery(query);
%>
<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>User Type</th>
    </tr>
    <%
        while (rs.next()) {
            int listUserId = rs.getInt("User_Id"); // Renamed variable
            String username = rs.getString("username");
            String password = rs.getString("password");
            String userType = rs.getString("user_type");
    %>
    <tr>
        <td><%= listUserId %></td> 
        <td><%= username %></td>
        <td><%= password %></td>
        <td><%= userType %></td>
    </tr>
    <%
        }
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        out.println("Error retrieving user list: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (dbConnection != null) {
            dbConnection.closeConnection();
        }
    }
%>
</div>

</body>
</html>