package com.example.android.moviedb.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Network.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shimaa on 10/16/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMoviesList = new ArrayList();

    public ImageAdapter(Context c , ArrayList<Movie> movies) {
        mContext = c;
        mMoviesList = movies;
    }

    public void addAll(ArrayList<Movie> movies){
        mMoviesList.clear();
        mMoviesList.addAll( movies);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMoviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMoviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }else {
            imageView = (ImageView) convertView;
        }

        Picasso.with(mContext)
                .load(Urls.IMAGE_BASE_URL + mMoviesList.get(position).getMoviePoster())
                .into(imageView);

        return imageView;
    }

}
