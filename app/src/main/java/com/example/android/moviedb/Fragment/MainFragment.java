package com.example.android.moviedb.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.moviedb.Interfaces.DataIsReady;
import com.example.android.moviedb.Activities.DetailsActivity;
import com.example.android.moviedb.Adapters.ImageAdapter;
import com.example.android.moviedb.DataBase.DataBaseHelper;
import com.example.android.moviedb.Interfaces.MovieListener;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Network.DownloadTask;
import com.example.android.moviedb.Parser.MovieParser;
import com.example.android.moviedb.R;
import com.example.android.moviedb.Network.Urls;

import java.util.ArrayList;

/**
 * Created by Shimaa on 10/16/2016.
 */
public class MainFragment extends android.support.v4.app.Fragment implements DataIsReady{

    private ArrayList<Movie> moviesList = new ArrayList<>();
    private ImageAdapter adapter;
    private GridView grid ;
    private MovieListener movieListener;

    public void setMovieListener(MovieListener movieListener){
        this.movieListener = movieListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(isNetworkAvailable()){
            OperateDownloadTask(Urls.NOW_PLAYING);
            getActivity().setTitle("Now Playing");
        }else{
            Toast.makeText(getContext(),"There is No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate the fragment xml
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //find the grid view and set the adapter
        grid = (GridView) rootView.findViewById(R.id.grid);
        adapter = new ImageAdapter(getContext(),moviesList);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieListener.setSelecteedMovie((Movie) adapter.getItem(position));
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu
        inflater.inflate(R.menu.movie_list_choice_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        String whichMovies = "" ;
        if (id == R.id.top_rated) {
            whichMovies = "Top Rated";
            getActivity().setTitle(whichMovies);
            DownloadAndUpdateTheList(whichMovies);
            return true;
        }else if (id == R.id.most_popular) {
            whichMovies = "Most Popular";
            getActivity().setTitle(whichMovies);
            DownloadAndUpdateTheList(whichMovies);
            return true;
        }else if (id == R.id.favourite) {
            whichMovies = "Favourites" ;
            getActivity().setTitle(whichMovies);
            UpdateListWithFavourites();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DownloadAndUpdateTheList(String whichList){
        String url = null ;
        if(whichList == "Top Rated"){
            url = Urls.TOP_RATED;
        }else if (whichList == "Most Popular"){
            url = Urls.MOST_POPULAR;
        }
        OperateDownloadTask(url);
    }

    public void OperateDownloadTask(String url){
        if(isNetworkAvailable()){

            DownloadTask task = new DownloadTask();
            task.dataIsReady = this;
            task.parser = new MovieParser();
            task.execute(url);
        }else{
            Toast.makeText(getContext(),"There is No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void UpdateListWithFavourites(){

        ArrayList<Movie> favList = new ArrayList<Movie>();
        DataBaseHelper myDb = new DataBaseHelper(getContext());
        Cursor cursor = myDb.getAllData();
        if (cursor.moveToFirst()){
            do{
                Movie movie = new Movie();
                movie.setMovieId(cursor.getInt(cursor.getColumnIndex("Id")));
                movie.setMovieTitle(cursor.getString(cursor.getColumnIndex("Title")));
                movie.setMoviePoster(cursor.getString(cursor.getColumnIndex("PosterPath")));
                movie.setMovieReleaseDate(cursor.getString(cursor.getColumnIndex("ReleaseDate")));
                movie.setMovieVoteAverage(cursor.getString(cursor.getColumnIndex("VoteAverage")));
                movie.setMovieOverView(cursor.getString(cursor.getColumnIndex("OverView")));
                favList.add(movie);
            }while(cursor.moveToNext());
        }
        cursor.close();
        adapter.addAll(favList);
    }

    @Override
    public void Display(ArrayList<? extends Model> result) {
        adapter.addAll((ArrayList<Movie>)result);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
