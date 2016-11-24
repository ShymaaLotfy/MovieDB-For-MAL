package com.example.android.moviedb.Parser;

import com.example.android.moviedb.Models.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shimaa on 11/19/2016.
 */
public abstract class Parser {

   public abstract ArrayList< Model > parse(JSONObject object) throws JSONException;
}
