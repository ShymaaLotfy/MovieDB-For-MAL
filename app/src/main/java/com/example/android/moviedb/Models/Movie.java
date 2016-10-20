package com.example.android.moviedb.Models;

import com.example.android.moviedb.Network.Urls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shimaa on 10/17/2016.
 */
public class Movie {
    private String  title, releaseDate, moviePoster, voteAverage, plotSynopsis;

    public Movie(JSONObject movieObject) throws JSONException {
        title = movieObject.getString("title");
        releaseDate = movieObject.getString("release_date");
        moviePoster = movieObject.getString("poster_path");
        voteAverage = movieObject.getString("vote_average");

    }

    public String getMoviePoster(){return Urls.IMAGE_BASE_URL + moviePoster;}
    public String getTitle(){return title;}
    public String getReleaseDate(){return releaseDate;}
    public String getVoteAverage(){return voteAverage;}


}
