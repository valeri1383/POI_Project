<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.poi.valeri_poi.User" %>
<%@page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
    <title>Points of Interest</title>
    <link rel="stylesheet" type="text/css" href="css/homeStyle.css">
</head>


    <body>
    <% if (session.getAttribute("user") != null) { %>
        <nav>
            <div class="nav-links">
                <a href="home.jsp">Home</a>
                <a href="searchPOI.jsp">Search and Like POI</a>
                <a href="listPOI.jsp">List of POI</a>
                <a href="commentPOI.jsp">Comment on POI</a>

            </div>
            <div>
            <span>Logged in as <%= ((User)session.getAttribute("user")).getUsername() %></span>
            <span><a href="logout.jsp">/Logout</a></span>
            </div>
        </nav>
                

        <h2>Welcome to Points of Interest!</h2>
            <div class="container">
                <div class="box">
                    <h3>Search Points of Interests</h3>
                    <div action="searchPoints.jsp" method="get">
                        <button onclick="window.location.href='searchPOI.jsp'"";>Search/Like/Comment</button>
                    </div>
                </div>
                <div class="box">
                    <h3>List Points of Interests</h3>
                    <div action="listPoints.jsp" method="get">
                        <button onclick="window.location.href='listPOI.jsp';">View List</button>
                    </div>
                </div>
                <div class="box">
                    <h3>Comment on Points of Interests</h3>
                    <div action="commentPoint.jsp" method="get">
                        <button onclick="window.location.href='commentPOI.jsp';">Add Comment</button>
                    </div>
                </div>
            </div>
        
            <nav>

      
        <% } else { 
            response.sendRedirect("index.jsp");
        } %>

    </body>
    <script>
<% if (session.getAttribute("message") != null) { %>
    alert('<%=session.getAttribute("message")%>');
    <%session.removeAttribute("message");%>
<% } %>
</script>
</html>

