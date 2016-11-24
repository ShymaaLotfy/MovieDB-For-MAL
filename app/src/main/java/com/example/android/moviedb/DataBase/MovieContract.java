package com.example.android.moviedb.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Shimaa on 11/21/2016.
 */
public class MovieContract implements  BaseColumns{

    public static final class FavouriteEntry implements BaseColumns {

        public static final String TABLE_NAME = "favourite";

        public static final String COLUMN_MOVIE_ID = "Id";

        public static final String COLUMN_MOVIE_TITLE = "Title";

        public static final String COLUMN_MOVIE_POSTERPATH = "PosterPath";

        public static final String COLUMN_MOVIE_RELEASE_DATE = "ReleaseDate";

        public static final String COLUMN_MOVIE_VOTE_AVG = "VoteAverage";

        public static final String COLUMN_MOVIE_OVERVIEW = "OverView";
    }

}
