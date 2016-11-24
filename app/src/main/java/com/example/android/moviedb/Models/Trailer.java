package com.example.android.moviedb.Models;

import com.example.android.moviedb.Network.Urls;

/**
 * Created by Shimaa on 11/19/2016.
 */
public class Trailer extends Model {

    private String trailerKey;

    public void setTrailerKey(String key){trailerKey = key;}
    public String getTrailerLink(){return Urls.VEDIO_URL + trailerKey;}

}
