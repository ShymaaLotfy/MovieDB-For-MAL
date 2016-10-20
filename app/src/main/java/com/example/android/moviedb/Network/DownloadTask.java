package com.example.android.moviedb.Network;

import android.os.AsyncTask;

import com.example.android.moviedb.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Shimaa on 10/17/2016.
 */
public abstract class DownloadTask<V> extends AsyncTask<String,Void,ArrayList<V>> {

    @Override
    protected ArrayList<V> doInBackground(String... params) {

        ArrayList<V> result = new ArrayList<>();
        //check if i have a null url
        if(params[0] == null) return null;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray resultArray = jsonObject.getJSONArray("results");
                for(int i=0 ; i<resultArray.length() ; i++){
                    JSONObject object = resultArray.getJSONObject(i);
                    result.add((V) GetElementFromJson(object));
                }
                return result;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    abstract protected V GetElementFromJson(JSONObject object) throws JSONException;

    private String readResponse(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();

        while (true){
            if(line == null) break;
            builder.append(line);
            line = reader.readLine();
        }

        return builder.toString();
    }
}
