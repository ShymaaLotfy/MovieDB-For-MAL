package com.example.android.moviedb.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.moviedb.Fragment.DetailsFragment;
import com.example.android.moviedb.Fragment.MainFragment;
import com.example.android.moviedb.Interfaces.MovieListener;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.R;

public class MainActivity extends AppCompatActivity implements MovieListener{

    boolean isTwoPane = false;
    MainFragment mainFragment = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            mainFragment.setMovieListener(this);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContainer, mainFragment,"")
                    .commit();
        }else{
            mainFragment = (MainFragment) getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
            mainFragment.setMovieListener(this);
        }

        if(findViewById(R.id.detailsContainer) != null){
            isTwoPane = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "mContent", mainFragment);
    }

    @Override
    public void setSelecteedMovie(Movie movie) {

        if(!isTwoPane){
            //case one Pane
            Intent intent = new Intent(this, DetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ClickedMovie", movie );
            intent.putExtras(bundle);
            startActivity(intent);

        }else{
            //case two pane
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ClickedMovie",movie);
            detailsFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailsContainer,detailsFragment)
                    .commit();
        }
    }
}
