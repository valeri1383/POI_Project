
<%@ page import="java.sql.*" %>
<%@ page import="com.poi.valeri_poi.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
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
    <h2>User List</h2>

    <%
    DBConnection dbConnection = new DBConnection();
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
            <th>User Type</th>
        </tr>
        <%
        while (rs.next()) {
            int userId = rs.getInt("User_Id");
            String username = rs.getString("username");
            String userType = rs.getString("user_type");
        %>
        <tr>
            <td><%= userId %></td>
            <td><%= username %></td>
            <td><%= userType %></td>
        </tr>
        <%
        }
        %>
    </table>

    <%
    } catch (SQLException e) {
        out.println("Database connection problem: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
    %>
</div>

</body>
</html>