package com.example.android.moviedb.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shimaa on 10/16/2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mImagespath = new ArrayList();

    public ImageAdapter(Context c , ArrayList<String> imagesPath) {
        mContext = c;
        mImagespath = imagesPath;
    }

    @Override
    public int getCount() {
        return mImagespath.size();
    }

    @Override
    public Object getItem(int position) {
        return mImagespath.get(position);
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
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }else {
            imageView = (ImageView) convertView;
        }
        imageView.getLayoutParams().height +=150;
        imageView.getLayoutParams().width+= 150;

        Picasso.with(mContext)
                .load(mImagespath.get(position))
                .into(imageView);

        return imageView;
    }

}
