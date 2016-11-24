package com.example.android.moviedb.Network;

/**
 * Created by Shimaa on 10/17/2016.
 */
public class Urls {
    public static final String API_KEY = "?api_key=";
    public static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780";
    public static final String NOW_PLAYING = BASE_URL + "now_playing" + API_KEY;
    public static final String TOP_RATED = BASE_URL + "top_rated" + API_KEY;
    public static final String MOST_POPULAR = BASE_URL + "popular" + API_KEY;
    public static final String REVIEW_URL = "/reviews" + API_KEY;
    public  static final String TRAIlER_URl = "/videos" + API_KEY;
    public static final String VEDIO_URL = "https://www.youtube.com/watch?v=";
}
