
<%@page import="com.poi.valeri_poi.UserDAO"%>
<%@page import="com.poi.valeri_poi.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Register</title>
         <link rel="stylesheet" type="text/css" href="css/registerStyle.css">
    </head>
    
    <body>
    <div class="container">
        <h2>Register</h2>
        <form action="register.jsp" method="post">
            <input type="text" name="username" placeholder="Username" required>
          
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Register">

            <a href="/login.jsp" class="login-link">Already have an account? Log in</a>
   

   

                <%
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String error = "";
                    UserDAO userDAO = new UserDAO();

                    if (username != null && password != null) {
                        if (userDAO.isUsernameTaken(username)) {
                            error = "Username is already taken!";
                        } else {
                            User user = new User(-1, username, password);
                            user.setUser_type("user"); 
                            boolean result = userDAO.insertUser(user);

                            if (result) {
                                response.sendRedirect("index.jsp?success=true");
                            } else {
                                error = "Error while registering.";
                            }
                        }
                    }
                %>



                <% if (!error.equals("")) { %>
                    <p class="mt-2 mb-3 text-danger"><%= error %></p>
                <% } else if (request.getParameter("success") != null && request.getParameter("success").equals("true")) { %>
                    <p class="mt-2 mb-3 text-success">Account successfully created. Please log in.</p>
                <% } %>

               
            </form>
    </body>
