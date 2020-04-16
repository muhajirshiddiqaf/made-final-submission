package com.ids.favoritemovie.ui.detailmoviefav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.ids.favoritemovie.data.DatabaseContract;
import com.ids.favoritemovie.data.MappingHelper;
import com.ids.favoritemovie.model.Movie;

import static com.ids.favoritemovie.data.AppConstants.BASE_IMAGE;
import static com.ids.favoritemovie.data.DatabaseContract.TABLE_MOVIE;

public class DetailMovieFavViewModel extends ViewModel {

    private Movie movie;
    private Uri uriWithId;
    private Context context;

    private DetailMovieFavNavigator detailMovieFavNavigator;

    public void initViewModel(Context context,DetailMovieFavNavigator detailMovieFavNavigator){
        this.context = context;
        this.detailMovieFavNavigator = detailMovieFavNavigator;
    }


    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }


    public void addFavorite(Movie data) {
        if (getFavorite(data.getId())) {
            deleteFavorite(data);
        } else {
            saveFavorite(data);
        }
        detailMovieFavNavigator.initView(data);
    }

    public Boolean getFavorite(int id) {
        uriWithId = Uri.parse(DatabaseContract.getContentUri(TABLE_MOVIE) + "/" + id);
        if (uriWithId != null) {
            Cursor cursor = context.getContentResolver().query(uriWithId, null, null, null, null);
            if (cursor != null) {
                movie = MappingHelper.mapCursorToObject(cursor);
                cursor.close();
            }else{
                movie = null;
            }
        }

        return movie != null;
    }

    public void deleteFavorite(Movie movie){
        uriWithId = Uri.parse(DatabaseContract.getContentUri(TABLE_MOVIE) + "/" + movie.getId());
        if (uriWithId != null) {
            context.getContentResolver().delete(uriWithId, null, null);
        }
    }

    public void saveFavorite(Movie movie){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MovieColumns.popularity,movie.getPopularity());
        values.put(DatabaseContract.MovieColumns.vote_count,movie.getVoteCount());
        values.put(DatabaseContract.MovieColumns.video,movie.getVideo());
        values.put(DatabaseContract.MovieColumns.poster_path,movie.getPosterPath());
        values.put(DatabaseContract.MovieColumns.id,movie.getId());
        values.put(DatabaseContract.MovieColumns.adult,movie.getAdult());
        values.put(DatabaseContract.MovieColumns.backdrop_path,movie.getBackdropPath());
        values.put(DatabaseContract.MovieColumns.original_language,movie.getOriginalLanguage());
        values.put(DatabaseContract.MovieColumns.original_title,movie.getOriginalTitle());
        values.put(DatabaseContract.MovieColumns.title,movie.getTitle());
        values.put(DatabaseContract.MovieColumns.vote_average,movie.getVoteAverage());
        values.put(DatabaseContract.MovieColumns.overview,movie.getOverview());
        values.put(DatabaseContract.MovieColumns.release_date,movie.getReleaseDate());
        values.put(DatabaseContract.MovieColumns.favorite,movie.getFavorite());
        context.getContentResolver().insert(DatabaseContract.getContentUri(TABLE_MOVIE), values);

        detailMovieFavNavigator.initView(movie);
    }

}
