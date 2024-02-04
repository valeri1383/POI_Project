<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="css/loginStyle.css">
</head>

<body>
    <div class="container">
        <h2>Login</h2>
        <form action="login.jsp" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login" class="login-button">
        </form>
        <% if (request.getParameter("success") != null && request.getParameter("success").equals("true")) { %>
            <p>Account successfully created.</p>
        <% } %>
        <a href="/register.jsp" class="register-link">Register</a>
    </div>
</body>
</html>
