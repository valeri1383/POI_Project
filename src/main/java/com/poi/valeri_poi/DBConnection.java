/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poi.valeri_poi;

import java.sql.*;

public class DBConnection {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3307/";
    private static final String DB_NAME = "poi";
    private static final String USER_ID = "root";
    private static final String PASSWORD = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DBConnection(){
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_URL + DB_NAME, USER_ID, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Database connection established: " + connection);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed to establish database connection");
            ex.printStackTrace();
        }
    }

    public ResultSet executeSQL(String sql){
        try{
            resultSet =  statement.executeQuery(sql);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return resultSet;
    }

    public int executeInsert(String sql){
        int result = 0;
        try{
            result =  statement.executeUpdate(sql);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public int getQueryRowCount() throws SQLException {
        int size = 0;
        if (resultSet != null) {
            while (resultSet.next()) {
                size++;
            }
        }
        return size;
    }
    public Connection getConnection() {
        return this.connection;
    }
    
    public boolean updateUser(int userId, String username, String userType) {
        String sql = "UPDATE UserAccount SET username = ?, user_type = ? WHERE User_Id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, userType);
            pstmt.setInt(3, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating user: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM UserAccount WHERE User_Id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error deleting user: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
       public int executeUpdate(String sql) throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
}
       public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                
                e.printStackTrace();
            }
        }
        
}



}
