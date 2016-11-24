package com.example.android.moviedb.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moviedb.Interfaces.DataIsReady;
import com.example.android.moviedb.Adapters.DetailsAdapter;
import com.example.android.moviedb.DataBase.DataBaseHelper;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Models.Review;
import com.example.android.moviedb.Models.Trailer;
import com.example.android.moviedb.Network.DownloadTask;
import com.example.android.moviedb.Network.Urls;
import com.example.android.moviedb.Parser.MovieParser;
import com.example.android.moviedb.Parser.Parser;
import com.example.android.moviedb.Parser.ReviewParser;
import com.example.android.moviedb.Parser.TrailerParser;
import com.example.android.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shimaa on 10/21/2016.
 */
public class DetailsFragment extends android.support.v4.app.Fragment implements DataIsReady {

    private Movie clickedMovie ;
    ListView list ;
    DetailsAdapter adapter;
    Button favButton ;


    @Override
    public void onStart() {
        super.onStart();
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHelper myDb = new DataBaseHelper(getContext());
                boolean isInserted = myDb.insertData(clickedMovie);
                if(isInserted == true)
                    Toast.makeText(getContext(),"This Movie is Inserted into Your Favourite List",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getContext(),"Movie is not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Movie Details");
        final View view = inflater.inflate(R.layout.details_fragment, null);
        Bundle bundle = getArguments();
        clickedMovie = (Movie) bundle.getSerializable("ClickedMovie");

        //inflate the header
  ;      View headerView = inflater.inflate(R.layout.details_header_items, null);
        favButton = (Button)headerView.findViewById(R.id.addToFav);
        ImageView imageView = (ImageView)headerView.findViewById(R.id.poster);
        TextView title = (TextView)headerView.findViewById(R.id.title);
        TextView releaseDate = (TextView)headerView.findViewById(R.id.releaseDate);
        TextView voteAverage = (TextView)headerView.findViewById(R.id.voteAverage);
        TextView overview = (TextView)headerView.findViewById(R.id.overview);

        Picasso.with(getContext()).load(Urls.IMAGE_BASE_URL + clickedMovie.getMoviePoster()).into(imageView);
        title.setText(clickedMovie.getMovieTitle());
        releaseDate.setText(clickedMovie.getMovieReleaseDate());
        voteAverage.setText(clickedMovie.getMovieVoteAverage() + "/10");
        overview.setText(clickedMovie.getMovieOverView());

        //Trailers Download Task
        String trailerUrl = Urls.BASE_URL + clickedMovie.getMovieId() + Urls.TRAIlER_URl;
        DownloadTask(new TrailerParser(),trailerUrl);

        //Review Download task
        String reviewUrl = Urls.BASE_URL + clickedMovie.getMovieId() + Urls.REVIEW_URL;
        DownloadTask(new ReviewParser(),reviewUrl);

        list = (ListView)view.findViewById(R.id.list);
        adapter = new DetailsAdapter(this.getContext(),clickedMovie);
        list.addHeaderView(headerView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(adapter.getItem(position) instanceof Trailer){

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(((Trailer) adapter.getItem(position)).getTrailerLink()));
                    startActivity(browserIntent);

                }
            }
        });

        return view;
    }

    private void DownloadTask(Parser parser,String url){

        if(isNetworkAvailable()){
            DownloadTask trailerDownloadTask = new DownloadTask();
            trailerDownloadTask.dataIsReady = this ;
            trailerDownloadTask.parser = parser;
            trailerDownloadTask.execute(url);
        }else{
            Toast.makeText(getContext(),"There is No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void Display(ArrayList<? extends Model> result) {
        adapter.addAll((ArrayList<Model>) result);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
