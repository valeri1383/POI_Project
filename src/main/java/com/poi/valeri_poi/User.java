/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poi.valeri_poi;
//import java.util.*;



public class User {
    public int id;
    public String username;
    public String password;
    public String user_type;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user_type = "user";
    }

    public int getId() {
        return this.id;
    }
    
    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isAdmin() {
        return "admin".equals(this.user_type);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
