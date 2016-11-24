package com.example.android.moviedb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;

import com.example.android.moviedb.Fragment.DetailsFragment;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.R;

/**
 * Created by Shimaa on 10/21/2016.
 */
public class DetailsActivity extends AppCompatActivity {

    Movie clickedMovie ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        clickedMovie = (Movie) bundle.getSerializable("ClickedMovie");

        if(savedInstanceState == null){

            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailsContainer,detailsFragment,"")
                    .commit();
        }

    }


}

