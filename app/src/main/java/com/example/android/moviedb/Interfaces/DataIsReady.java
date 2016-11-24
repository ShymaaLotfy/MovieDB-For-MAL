package com.example.android.moviedb.Interfaces;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.example.android.moviedb.Models.Model;

import java.util.ArrayList;

/**
 * Created by Shimaa on 11/19/2016.
 */
public interface DataIsReady  {

    public void Display( ArrayList<? extends Model>  result);
}
