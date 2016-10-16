package com.example.android.moviedb.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.moviedb.Adapters.ImageAdapter;
import com.example.android.moviedb.R;

/**
 * Created by Shimaa on 10/16/2016.
 */
public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

//        //find the grid view and set the adapter
//        GridView grid = (GridView) rootView.findViewById(R.id.grid);
//        ImageAdapter adapter = new ImageAdapter();
//        grid.setAdapter(adapter);
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
