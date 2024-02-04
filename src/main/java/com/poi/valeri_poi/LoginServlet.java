/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.poi.valeri_poi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Verify the user
            User user = userDAO.getUserByUsernameAndPassword(username, password); 

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("User_Id", user.getId()); 
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Login failed.", e);
        }
    }
}
