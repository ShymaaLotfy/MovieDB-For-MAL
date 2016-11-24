package com.example.android.moviedb.Network;

import android.os.AsyncTask;

import com.example.android.moviedb.Interfaces.DataIsReady;
import com.example.android.moviedb.Models.Model;
import com.example.android.moviedb.Parser.Parser;

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
public class DownloadTask extends AsyncTask<String,Void,ArrayList<? extends Model>> {

    public DataIsReady dataIsReady = null;
    public Parser parser ;

    @Override
    protected ArrayList<? extends Model> doInBackground(String... params) {

        ArrayList<Model> result = new ArrayList<>();
        //check if i have a null url
        if(params[0] == null) return null;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                JSONObject jsonObject = new JSONObject(jsonString);
                result = parser.parse(jsonObject);
                return result;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

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

    @Override
    protected void onPostExecute(ArrayList<? extends Model> result) {
        super.onPostExecute( result );
        dataIsReady.Display( result);
    }
}
