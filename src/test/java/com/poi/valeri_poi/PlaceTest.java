package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlaceTest {

    private Place place;

    @Before
    public void setUp() {
        place = new Place(1, "Eiffel Tower", TypeOfPlace.HistoricalSite, 0);
    }

    @Test
    public void testAddComment() {
        Review review = new Review(1, "Amazing view!");
        place.addComment(review);

        assertEquals("Comments list should contain 1 comment after addition", 1, place.getComments().size());
    }

    @Test
    public void testGetCommentById() {
        Review review = new Review(1, "Amazing view!");
        place.addComment(review);

        assertNotNull("Should find a comment by its ID", place.getCommReviewById(1));
        assertNull("Should not find a comment for non-existing ID", place.getCommReviewById(2));
    }

    @Test
    public void testLikePlace() {
        place.likePlace(); // Increment likes by 1
        assertEquals("Likes should be incremented to 1", 1, place.getLikes());
    }

    @Test
    public void testModifyReview() {
        Review review = new Review(1, "Great place!");
        place.addComment(review);

        place.modifyReview(1, "Even better with the new lighting!");
        Review modifiedReview = place.getCommReviewById(1);

        assertEquals("Comment should be modified", "Even better with the new lighting!", modifiedReview.getComment());
    }

    @Test
    public void testRemoveComment() {
        Review review = new Review(1, "Must visit!");
        place.addComment(review);

        boolean result = place.removeComment(1);
        assertTrue("Comment should be successfully removed", result);
        assertEquals("Comments list should be empty after deletion", 0, place.getComments().size());
    }


    @Test
    public void testGetCommentIdMax() {
        place.addComment(new Review(1, "Incredible!"));
        place.addComment(new Review(2, "A must-see."));

        assertEquals("Max comment ID should be 2", 2, place.getCommentIdMax());
    }
}