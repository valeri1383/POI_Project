package com.poi.valeri_poi;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class PointsOfInterestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       
        PlaceDAO dao = new PlaceDAO();

    
        List<Place> pointsOfInterest = dao.getAllPlaces();

     
        request.setAttribute("pointsOfInterest", pointsOfInterest);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/listPOI.jsp");
        dispatcher.forward(request, response);
    }
}