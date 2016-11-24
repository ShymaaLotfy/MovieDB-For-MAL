package com.example.android.moviedb.Parser;

import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Shimaa on 11/19/2016.
 */
public class MovieParser extends Parser {

    @Override
    public ArrayList< Model> parse(JSONObject object) throws JSONException {

        ArrayList<Model> result = new ArrayList<Model>() ;
        //parse the movie jsonobject
        JSONArray resultArray = object.getJSONArray("results");
        for(int i=0 ; i<resultArray.length() ; i++){

            JSONObject movieObject = resultArray.getJSONObject(i);

            Movie movie = new Movie();
            movie.setMovieId(movieObject.getInt("id"));
            movie.setMovieTitle(movieObject.getString("title"));
            movie.setMoviePoster( movieObject.getString("poster_path"));
            movie.setMovieReleaseDate(movieObject.getString("release_date"));
            movie.setMovieVoteAverage(movieObject.getString("vote_average"));
            movie.setMovieOverView(movieObject.getString("overview"));

            result.add( movie );
        }

        return result;
    }
}
