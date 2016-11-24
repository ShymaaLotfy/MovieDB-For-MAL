package com.example.android.moviedb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.moviedb.Models.Header;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Movie;
import com.example.android.moviedb.Models.Review;
import com.example.android.moviedb.Models.Trailer;
import com.example.android.moviedb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Shimaa on 11/19/2016.
 */
public class DetailsAdapter extends BaseAdapter {

    ArrayList<Model> itemsList = new ArrayList<Model>();
    Movie clickedMovie;
    Context context;

    public DetailsAdapter(Context c , Movie  mov){
        context=c;
        clickedMovie = mov;
    }

    public void addAll(ArrayList<Model> list){
        itemsList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if(itemsList.get(position) instanceof Header)
            return 0;
        else if(itemsList.get(position) instanceof Trailer)
            return 1;
        else if(itemsList.get(position) instanceof Review)
            return 2;
        return super.getItemViewType(position);
    }

    TextView header;
    TextView trailerNum;
    TextView name;
    TextView content;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        int type = getItemViewType(position);

        if(convertView == null){
            switch(type){
                case 0 : {
                    convertView = inflater.inflate(R.layout.header_text_view, null);
                    header = (TextView)convertView.findViewById(R.id.header);
                    break ;
                }
                case 1 : {
                    convertView = inflater.inflate(R.layout.trailer_items, null);
                    trailerNum = (TextView)convertView.findViewById(R.id.TrailerNumber);
                    break;
                }
                case 2 : {
                    convertView = inflater.inflate(R.layout.review_items, null);
                    name = (TextView)convertView.findViewById(R.id.reviewerName);
                    content = (TextView)convertView.findViewById(R.id.reviewContent);
                    break;
                }
            }
        }

        if(itemsList.get(position) instanceof Header) {
            header.setText(((Header) itemsList.get(position)).getHeaderTextValue());

        }else if(itemsList.get(position) instanceof Trailer) {
            trailerNum.setText("Trailer" + position);

        }else if(itemsList.get(position) instanceof Review) {
            name.setText(((Review) itemsList.get(position)).getReviewerName());
            content.setText(((Review) itemsList.get(position)).getReviewContent());

        }
        return convertView;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
