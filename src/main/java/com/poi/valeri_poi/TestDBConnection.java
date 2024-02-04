/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poi.valeri_poi;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TestDBConnection {
    
    public static void main(String[] args) {
        DBConnection database = new DBConnection();
        
        String sql = "SELECT * FROM userAccount"; 

        ResultSet resultSet = database.executeSQL(sql);

      
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
