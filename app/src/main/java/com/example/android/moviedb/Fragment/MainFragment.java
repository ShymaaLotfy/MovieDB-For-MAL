package com.example.android.moviedb.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.moviedb.Adapters.ImageAdapter;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Network.DownloadTask;
import com.example.android.moviedb.Network.MovieDownloadTask;
import com.example.android.moviedb.R;
import com.example.android.moviedb.Network.Urls;

import java.util.ArrayList;

/**
 * Created by Shimaa on 10/16/2016.
 */
public class MainFragment extends Fragment {

    ArrayList<String> posters = new ArrayList<>();
    ImageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        MovieDownloadTask task = new MovieDownloadTask(){
            @Override
            protected void onPostExecute(ArrayList<Movie> movies) {
                super.onPostExecute(movies);
                Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                for(int i=0;i<movies.size();i++){
                    posters.add(movies.get(i).getMoviePoster());
                    adapter.notifyDataSetChanged();
                }
            }
        };task.execute(Urls.NOW_PLAYING);

        //find the grid view and set the adapter
        GridView grid = (GridView) rootView.findViewById(R.id.grid);
        adapter = new ImageAdapter(getContext(),posters);
        grid.setAdapter(adapter);
//
//        //open the details fragment for the clicked item
//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //open details intent
//            }
//        });

        return rootView;
    }
}
