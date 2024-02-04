/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poi.valeri_poi;
import com.poi.valeri_poi.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User(1, "testUser", "testPassword");
    }

    @Test
    public void getId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void getUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void setUsername() {
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void setPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void getUserType() {
        assertEquals("user", user.getUser_type());
    }

    @Test
    public void setUserType() {
        user.setUser_type("admin");
        assertEquals("admin", user.getUser_type());
        assertTrue(user.isAdmin()); // Verify that isAdmin reflects this change
    }

    @Test
    public void isAdminShouldReturnTrueForAdminUser() {
        user.setUser_type("admin");
        assertTrue(user.isAdmin());
    }

    @Test
    public void isAdminShouldReturnFalseForRegularUser() {
        user.setUser_type("user");
        assertFalse(user.isAdmin());
    }
}
