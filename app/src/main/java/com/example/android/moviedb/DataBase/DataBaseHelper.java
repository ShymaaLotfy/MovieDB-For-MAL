package com.example.android.moviedb.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.moviedb.Models.Movie;

/**
 * Created by Shimaa on 11/20/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Favourite.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "CREATE TABLE " + MovieContract.FavouriteEntry.TABLE_NAME +
                " (" + MovieContract.FavouriteEntry.COLUMN_MOVIE_ID +  " INTEGER PRIMARY KEY," +
                MovieContract.FavouriteEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL," +
                MovieContract.FavouriteEntry.COLUMN_MOVIE_POSTERPATH + " TEXT NOT NULL," +
                MovieContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL," +
                MovieContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVG + " TEXT NOT NULL," +
                MovieContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL" + " );" );
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MovieContract.FavouriteEntry.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();
        long result = -2 ;

        if(!cheeckIfDataExist(MovieContract.FavouriteEntry.TABLE_NAME, movie.getMovieId(),  db)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_ID,movie.getMovieId());
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_TITLE,movie.getMovieTitle());
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_POSTERPATH,movie.getMoviePoster());
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_RELEASE_DATE,movie.getMovieReleaseDate());
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_VOTE_AVG,movie.getMovieVoteAverage());
            contentValues.put(MovieContract.FavouriteEntry.COLUMN_MOVIE_OVERVIEW,movie.getMovieOverView());
            result = db.insert(MovieContract.FavouriteEntry.TABLE_NAME,null ,contentValues);
        }

        if(result == -1)
            return false;
        else
            return true;
    }

    private boolean cheeckIfDataExist(String TableName , int movieId, SQLiteDatabase db ){

            String Query = "SELECT * FROM " + TableName + " WHERE " + MovieContract.FavouriteEntry.COLUMN_MOVIE_ID + " = " + movieId;
            Cursor cursor = db.rawQuery(Query, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                return false;
            }
            cursor.close();
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ MovieContract.FavouriteEntry.TABLE_NAME,null);
        return result;
    }


}