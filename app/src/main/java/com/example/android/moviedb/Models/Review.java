package com.example.android.moviedb.Models;

/**
 * Created by Shimaa on 11/19/2016.
 */
public class Review extends Model {

    private String reviewerName , reviewContent;

    public void setReviewerName(String name){reviewerName = name;}
    public void setReviewContent(String content){reviewContent = content;}
    public  String getReviewerName(){return reviewerName;}
    public  String getReviewContent(){return  reviewContent;}
}
