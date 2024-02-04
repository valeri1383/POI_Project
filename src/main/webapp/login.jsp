
<%@page import="com.poi.valeri_poi.UserDAO" %>
<%@page import="com.poi.valeri_poi.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            if(request.getParameter("success") != null && request.getParameter("success").equals("true")) {
                out.println("<p>Account successfully created. Please log in.</p>");
            }
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByUsernameAndPassword(username, password);

            if(user != null) {
                session.setAttribute("user", user);
                if("admin".equals(user.getUser_type())){
                    response.sendRedirect("adminHome.jsp");
                } else {
                    response.sendRedirect("home.jsp");
                }
            } else {
                response.sendRedirect("index.jsp?msg=username or password mismatched");
            }
        %>

    </body>
</html>
