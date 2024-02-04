package com.poi.valeri_poi;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dboce
 */
import java.util.ArrayList;
import java.util.List;



public class Place {
    private int id;
    private String location;   
    private TypeOfPlace type;
    private int likes;
    private List<Review> comments;

    public Place(int id, String location, TypeOfPlace type, int likes) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.likes = likes;
        this.comments = new ArrayList<>();
    }


    

    // Getters
    public int getLocationId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public TypeOfPlace getType() {
        return type;
    }

    public int getLikes() {
        return likes;
    }

    // Return a copy of all comments for a place
    public List<Review> getComments() {
        return new ArrayList<>(comments);
    }

    // Find a specific comment by ID and return it or null if not found
    public Review getCommReviewById(int commentId) {
        return comments.stream()
                       .filter(item -> item.getCommentId() == commentId)
                       .findFirst()
                       .orElse(null); 
    }

    // Get the maximum ID among the current comments
    public int getCommentIdMax() {
        return comments.stream()
                       .mapToInt(Review::getCommentId)
                       .max()
                       .orElse(0);
    }

    // Increment likes
    public void likePlace() {
        this.likes++;
    }

    // Add a new comment
    public void addComment(Review comment) {
        comments.add(comment);
    }

    // Modify an existing review by ID
    public void modifyReview(int reviewId, String newComment) {
        comments.stream()
                .filter(review -> review.getCommentId() == reviewId)
                .findFirst()
                .ifPresent(review -> review.setComment(newComment));
    }

    public boolean removeComment(int commentId) {
        return comments.removeIf(review -> review.getCommentId() == commentId);
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}