package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dboce
 */
public class Review {

    private int id;
    private String comment;

    public Review(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public int getCommentId(){
        return id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}