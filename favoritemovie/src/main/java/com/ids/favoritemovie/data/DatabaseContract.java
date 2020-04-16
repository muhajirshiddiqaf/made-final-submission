package com.ids.favoritemovie.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 21,February,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class DatabaseContract {

    public static final String AUTHORITY = "com.ids.madesubmission4";
    private static final String SCHEME = "content";

    //Database schema information
    public static final String TABLE_MOVIE = "Movie";

    public static final String TABLE_TV_SHOW = "TvShow";

    public static final class MovieColumns implements BaseColumns {
        public static final String popularity = "popularity";
        public static final String vote_count = "vote_count";
        public static final String video = "video";
        public static final String poster_path = "poster_path";
        public static final String id = "id";
        public static final String adult = "adult";
        public static final String backdrop_path = "backdrop_path";
        public static final String original_language = "original_language";
        public static final String original_title = "original_title";
        public static final String title = "title";
        public static final String vote_average = "vote_average";
        public static final String overview = "overview";
        public static final String release_date = "release_date";
        public static final String favorite = "favorite";
    }


    public static final class TvShowColumns implements BaseColumns {
        public static final String original_name = "original_name";
        public static final String name = "name";
        public static final String popularity = "popularity";
        public static final String vote_count = "vote_count";
        public static final String first_air_date = "first_air_date";
        public static final String backdrop_path = "backdrop_path";
        public static final String original_language = "original_language";
        public static final String id = "id";
        public static final String vote_average = "vote_average";
        public static final String overview = "overview";
        public static final String poster_path = "poster_path";
        public static final String favorite = "favorite";
    }

    //Unique authority string for the content provider
    public static final String CONTENT_AUTHORITY = "com.ids.madesubmission4";


    public static Uri getContentUri(String segment){
        return new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(segment)
                .build();
    }
}
