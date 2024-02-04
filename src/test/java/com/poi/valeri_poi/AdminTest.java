package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class AdminTest {

    @Test
    public void testAdminConstructor() {
        Admin admin = new Admin(1, "adminUser", "adminPassword");
        assertEquals("Constructor should correctly set the id", 1, admin.getId());
        assertEquals("Constructor should correctly set the username", "adminUser", admin.getUsername());
        assertEquals("Constructor should correctly set the password", "adminPassword", admin.getPassword());
    }

    @Test
    public void testIsAdminMethodInherited() {
        Admin admin = new Admin(2, "adminUser", "adminPassword");
        admin.setUser_type("admin");
        
        assertTrue("isAdmin should return true for Admin", admin.isAdmin());
    }
}