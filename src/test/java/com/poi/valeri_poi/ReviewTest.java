package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest {

    private Review review;

    @Before
    public void setUp() {
        review = new Review(1, "Initial comment");
    }

    @Test
    public void testConstructor() {
        assertEquals("Constructor should correctly set the id", 1, review.getCommentId());
        assertEquals("Constructor should correctly set the comment", "Initial comment", review.getComment());
    }

    @Test
    public void testGetCommentId() {
        int id = review.getCommentId();
        assertEquals("getCommentId should return the correct id", 1, id);
    }

    @Test
    public void testGetComment() {
        String comment = review.getComment();
        assertEquals("getComment should return the initial comment", "Initial comment", comment);
    }

    @Test
    public void testSetComment() {
        review.setComment("Updated comment");
        assertEquals("setComment should update the comment", "Updated comment", review.getComment());
    }
}