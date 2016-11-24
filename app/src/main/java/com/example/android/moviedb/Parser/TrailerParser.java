package com.example.android.moviedb.Parser;

import com.example.android.moviedb.Models.Header;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Models.Review;
import com.example.android.moviedb.Models.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Shimaa on 11/20/2016.
 */
public class TrailerParser extends Parser {

    @Override
    public ArrayList< Model> parse(JSONObject object) throws JSONException {

        ArrayList<Model> result = new ArrayList<Model>() ;

        JSONArray resultArray = object.getJSONArray("results");
        for(int i=0 ; i<resultArray.length() ; i++){

            Trailer trailer = new Trailer();
            JSONObject trailerObject = resultArray.getJSONObject(i);

            String type = trailerObject.getString("type");
            if(type.equals("Trailer")){
                trailer.setTrailerKey(trailerObject.getString("key"));
                result.add( trailer );
            }

        }

        if (result.size() > 0){
            Header trailerHeader = new Header();
            trailerHeader.setHeaderTextValue("Trailers :");
            result.add(0,trailerHeader);
        }

        return result;

    }
}
