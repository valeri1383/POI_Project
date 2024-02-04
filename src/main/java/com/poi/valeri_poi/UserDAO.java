/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poi.valeri_poi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

    public class UserDAO {

        private DBConnection dbConnection;

        public UserDAO() {
            this.dbConnection = new DBConnection();
            System.out.println("DBConnection object: " + dbConnection); 
        }
        


        public User getUserByUsernameAndPassword(String username, String password) {
            User user = null;

            String sql = "SELECT * FROM UserAccount WHERE username = ? AND password = ?";

            sql = sql.replaceFirst("\\?", "'" + username + "'").replaceFirst("\\?", "'" + password + "'");

            ResultSet rs = dbConnection.executeSQL(sql);

            try {
                if (rs != null && rs.next()) {
                    int id = rs.getInt("User_Id");
                    String usernameResult = rs.getString("username");
                    String passwordResult = rs.getString("password");
                    String userTypeResult = rs.getString("user_type");

                    user = new User(id, usernameResult, passwordResult);
                    user.setUser_type(userTypeResult);
                    
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return user;
        }
        
        public boolean insertUser(User user) {
            if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return false;
            }
          String sql = "INSERT INTO UserAccount (username, password, user_type) VALUES (?, ?, ?)";
        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUser_type());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public boolean isUsernameTaken(String username) {
            String query = "SELECT COUNT(*) FROM UserAccount WHERE username = ?";
            try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(query)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return false;
        }

}
    

