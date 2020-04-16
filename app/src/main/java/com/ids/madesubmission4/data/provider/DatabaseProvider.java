package com.ids.madesubmission4.data.provider;

import android.appwidget.AppWidgetManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.provider.DatabaseContract.MovieColumns;
import com.ids.madesubmission4.data.provider.DatabaseContract.TvShowColumns;
import com.ids.madesubmission4.data.realm.RealmHelper;
import com.ids.madesubmission4.widget.FavoriteWidget;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 21,February,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class DatabaseProvider extends ContentProvider {
    private static final String TAG = DatabaseProvider.class.getSimpleName();

    private static final int CLEANUP_JOB_ID = 43;
    private static final int MOVIE = 100;
    private static final int MOVIE_WITH_ID = 101;

    private static final int TVSHOW = 200;
    private static final int TVSHOW_WITH_ID = 201;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private RealmHelper realmHelper;

    static {

        // content://com.ids.madesubmission4/tasks
        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_MOVIE,
                MOVIE);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_MOVIE + "/#",
                MOVIE_WITH_ID);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_MOVIE + "/#",
                MOVIE_WITH_ID);


        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_TV_SHOW,
                TVSHOW);

        sUriMatcher.addURI(DatabaseContract.CONTENT_AUTHORITY,
                DatabaseContract.TABLE_TV_SHOW + "/#",
                TVSHOW_WITH_ID);
    }


    @Override
    public boolean onCreate() {
        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.getInstance(configuration);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        int match = sUriMatcher.match(uri);
        Realm realm = getRealm();

        MatrixCursor movieCursor = new MatrixCursor(new String[]{
                MovieColumns.popularity,
                MovieColumns.vote_count,
                MovieColumns.video,
                MovieColumns.poster_path,
                MovieColumns.id,
                MovieColumns.adult,
                MovieColumns.backdrop_path,
                MovieColumns.original_language,
                MovieColumns.original_title,
                MovieColumns.title,
                MovieColumns.vote_average,
                MovieColumns.overview,
                MovieColumns.release_date,
                MovieColumns.favorite
        });

        MatrixCursor tvShowCursor = new MatrixCursor(new String[]{
                TvShowColumns.original_name,
                TvShowColumns.name,
                TvShowColumns.popularity,
                TvShowColumns.vote_count,
                TvShowColumns.first_air_date,
                TvShowColumns.backdrop_path,
                TvShowColumns.original_language,
                TvShowColumns.id,
                TvShowColumns.vote_average,
                TvShowColumns.overview,
                TvShowColumns.poster_path,
                TvShowColumns.favorite
        });


        try {
            switch (match) {
                //Expected "query all" Uri: content://com.ids.madesubmission4/movie
                case MOVIE:
                    realmHelper = new RealmHelper<>(realm, Movie.class);
                    ArrayList<Movie> moviesRealmResults = realmHelper.getAllData(); //realm.where(Movie.class).findAll();
                    for (Movie movie : moviesRealmResults) {
                        Object[] rowData = new Object[]{
                                movie.getPopularity(),
                                movie.getVoteCount(),
                                movie.getVideo(),
                                movie.getPosterPath(),
                                movie.getId(),
                                movie.getAdult(),
                                movie.getBackdropPath(),
                                movie.getOriginalLanguage(),
                                movie.getOriginalTitle(),
                                movie.getTitle(),
                                movie.getVoteAverage(),
                                movie.getOverview(),
                                movie.getReleaseDate(),
                                movie.getFavorite()};
                        movieCursor.addRow(rowData);
                    }
                    break;

                //Expected "query one" Uri: content://com.ids.madesubmission4/movie/{id}
                case MOVIE_WITH_ID:
                    realmHelper = new RealmHelper<>(realm, Movie.class);
                    Integer id = Integer.parseInt(uri.getPathSegments().get(1));
                    Movie movie = realm.where(Movie.class).equalTo("id", id).findFirst();
                    if(movie!=null){
                        movieCursor.addRow(new Object[]{
                                movie.getPopularity(),
                                movie.getVoteCount(),
                                movie.getVideo(),
                                movie.getPosterPath(),
                                movie.getId(),
                                movie.getAdult(),
                                movie.getBackdropPath(),
                                movie.getOriginalLanguage(),
                                movie.getOriginalTitle(),
                                movie.getTitle(),
                                movie.getVoteAverage(),
                                movie.getOverview(),
                                movie.getReleaseDate(),
                                movie.getFavorite()}
                        );
                    }else{
                        return null;
                    }
                    break;

                //Expected "query all" Uri: content://com.ids.madesubmission4/tvShow
                case TVSHOW:
                    realmHelper = new RealmHelper<>(realm, TvShow.class);
                    ArrayList<TvShow> tvShowsRealmResults = realmHelper.getAllData(); //realm.where(Movie.class).findAll();
                    for (TvShow tvShow : tvShowsRealmResults) {
                        Object[] rowData = new Object[]{
                                tvShow.getOriginalName(),
                                tvShow.getName(),
                                tvShow.getPopularity(),
                                tvShow.getVoteCount(),
                                tvShow.getFirstAirDate(),
                                tvShow.getBackdropPath(),
                                tvShow.getOriginalLanguage(),
                                tvShow.getId(),
                                tvShow.getVoteAverage(),
                                tvShow.getOverview(),
                                tvShow.getPosterPath(),
                                tvShow.getFavorite()};
                        tvShowCursor.addRow(rowData);
                    }
                    break;

                //Expected "query one" Uri: content://com.ids.madesubmission4/tvShow/{id}
                case TVSHOW_WITH_ID:
                    realmHelper = new RealmHelper<>(realm, TvShow.class);
                    Integer idTvShow = Integer.parseInt(uri.getPathSegments().get(1));
                    TvShow tvShow = realm.where(TvShow.class).equalTo("id", idTvShow).findFirst();
                    if(tvShow!=null){
                        tvShowCursor.addRow(new Object[]{
                                tvShow.getOriginalName(),
                                tvShow.getName(),
                                tvShow.getPopularity(),
                                tvShow.getVoteCount(),
                                tvShow.getFirstAirDate(),
                                tvShow.getBackdropPath(),
                                tvShow.getOriginalLanguage(),
                                tvShow.getId(),
                                tvShow.getVoteAverage(),
                                tvShow.getOverview(),
                                tvShow.getPosterPath(),
                                tvShow.getFavorite()}
                        );
                    }else{
                        return null;
                    }
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);
            }



        } finally {
            realm.close();
        }

        if(match == MOVIE || match == MOVIE_WITH_ID){
            movieCursor.setNotificationUri(getContext().getContentResolver(), uri);
            return movieCursor;
        }else if(match == TVSHOW || match == TVSHOW_WITH_ID){
            tvShowCursor.setNotificationUri(getContext().getContentResolver(), uri);
            return tvShowCursor;
        }else{
            return null;
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (sUriMatcher.match(uri)){
            case MOVIE:
                realmHelper = new RealmHelper<>(getRealm(), Movie.class);
                Movie movie = new Movie();
                movie.setPopularity(contentValues.getAsDouble(MovieColumns.popularity));
                movie.setVoteCount(contentValues.getAsInteger(MovieColumns.vote_count));
                movie.setVideo(contentValues.getAsBoolean(MovieColumns.video));
                movie.setPosterPath(contentValues.get(MovieColumns.poster_path).toString());
                movie.setId(contentValues.getAsInteger(MovieColumns.id));
                movie.setAdult(contentValues.getAsBoolean(MovieColumns.adult));
                movie.setBackdropPath(contentValues.get(MovieColumns.backdrop_path).toString());
                movie.setOriginalLanguage(contentValues.get(MovieColumns.original_language).toString());
                movie.setOriginalTitle(contentValues.get(MovieColumns.original_title).toString());
                movie.setTitle(contentValues.get(MovieColumns.title).toString());
                movie.setVoteAverage(contentValues.getAsDouble(MovieColumns.vote_average));
                movie.setOverview(contentValues.get(MovieColumns.overview).toString());
                movie.setReleaseDate(contentValues.get(MovieColumns.release_date).toString());
                movie.setFavorite(true);
                realmHelper.save(movie);
                break;
            case TVSHOW:
                realmHelper = new RealmHelper<>(getRealm(), TvShow.class);
                TvShow tvShow = new TvShow();
                tvShow.setOriginalName(contentValues.getAsString(TvShowColumns.original_name));
                tvShow.setName(contentValues.getAsString(TvShowColumns.name));
                tvShow.setPopularity(contentValues.getAsDouble(TvShowColumns.popularity));
                tvShow.setVoteCount(contentValues.getAsInteger(TvShowColumns.vote_count));
                tvShow.setFirstAirDate(contentValues.getAsString(TvShowColumns.first_air_date));
                tvShow.setBackdropPath(contentValues.getAsString(TvShowColumns.backdrop_path));
                tvShow.setOriginalLanguage(contentValues.getAsString(TvShowColumns.original_language));
                tvShow.setId(contentValues.getAsInteger(TvShowColumns.id));
                tvShow.setVoteAverage(contentValues.getAsDouble(TvShowColumns.vote_average));
                tvShow.setOverview(contentValues.getAsString(TvShowColumns.overview));
                tvShow.setPosterPath(contentValues.getAsString(TvShowColumns.poster_path));
                tvShow.setFavorite(true);
                realmHelper.save(tvShow);
                break;
            default:
                break;
        }

        updateWidget();
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        Integer id = 0;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_WITH_ID:
                realmHelper = new RealmHelper<>(getRealm(), Movie.class);
                id = Integer.parseInt(String.valueOf(ContentUris.parseId(uri)));
                realmHelper.delete("id",id);
                break;
            case TVSHOW_WITH_ID:
                realmHelper = new RealmHelper<>(getRealm(), TvShow.class);
                id = Integer.parseInt(String.valueOf(ContentUris.parseId(uri)));
                realmHelper.delete("id",id);
                break;
        }

        updateWidget();
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    public Realm getRealm(){
        Realm.init(getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        return Realm.getInstance(configuration);
    }

    public void updateWidget(){
        try{
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            getContext().sendBroadcast(intent);
            FavoriteWidget.updateWidget(getContext().getApplicationContext());
        }catch (Exception e){

        }
    }
}
