package com.example.android.moviedb.Network;

import com.example.android.moviedb.Models.Movie;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shimaa on 10/20/2016.
 */
public class MovieDownloadTask extends DownloadTask<Movie> {
    @Override
    protected Movie GetElementFromJson(JSONObject object) throws JSONException {
        return new Movie(object);
    }
}
