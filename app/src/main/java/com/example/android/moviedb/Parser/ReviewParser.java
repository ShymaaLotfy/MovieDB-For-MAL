package com.example.android.moviedb.Parser;

import com.example.android.moviedb.Models.Header;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Models.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shimaa on 11/19/2016.
 */
public class ReviewParser extends Parser {

    @Override
    public ArrayList< Model> parse(JSONObject object) throws JSONException {

        ArrayList<Model> result = new ArrayList<Model>() ;

        JSONArray resultArray = object.getJSONArray("results");
        for(int i=0 ; i<resultArray.length() ; i++){

            Review review = new Review();
            JSONObject reviewObject = resultArray.getJSONObject(i);
            review.setReviewerName(reviewObject.getString("author"));
            review.setReviewContent(reviewObject.getString("content"));
            review.setReviewContent(review.getReviewContent().replaceAll("(\\r|\\n||\\t)", ""));
            result.add( review );
        }

        if(result.size() > 0){
            Header reviewHeader = new Header();
            reviewHeader.setHeaderTextValue("Reviews :");
            result.add(0,reviewHeader);
        }

        return result;
    }
}
