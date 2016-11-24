package com.example.android.moviedb.Models;

import com.example.android.moviedb.Network.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shimaa on 10/17/2016.
 */
public class Movie extends Model implements Serializable {

    private String  movieTitle, movieReleaseDate, moviePoster, movieVoteAverage, movieOverView;
    private int movieId;
    private ArrayList<Trailer> movieTrailers = new ArrayList<Trailer>();
    private ArrayList<Review> movieReviews = new ArrayList<Review>();

    public Movie(){}

    //Setters
    public void setMovieId(int Id){movieId = Id;}
    public void setMovieTitle(String Title) { movieTitle = Title;}
    public void setMoviePoster(String poster){moviePoster =   poster;}
    public void setMovieReleaseDate(String date) {movieReleaseDate = date;}
    public void setMovieOverView(String overView) {movieOverView = overView;}
    public void setMovieVoteAverage(String voteAverage) {movieVoteAverage = voteAverage;}
    public void setMovieTrailers(ArrayList<Trailer> trailers){movieTrailers = trailers;}
    public void setMovieReviews(ArrayList<Review> reviews){movieReviews = reviews;}
    //Getters
    public  int getMovieId(){return movieId;}
    public String getMovieTitle(){return movieTitle;}
    public String getMoviePoster(){return moviePoster;}
    public String getMovieReleaseDate(){return movieReleaseDate;}
    public String getMovieVoteAverage(){return movieVoteAverage;}
    public String getMovieOverView() {return movieOverView;}
    public ArrayList<Trailer> getMovieTrailers(){return  movieTrailers;}
    public ArrayList<Review> getMovieReviews() {return  movieReviews;}

}
