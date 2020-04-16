package com.ids.favoritemovie.ui.detailtvfav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.ids.favoritemovie.data.DatabaseContract;
import com.ids.favoritemovie.data.MappingHelper;
import com.ids.favoritemovie.model.TvShow;

import static com.ids.favoritemovie.data.AppConstants.BASE_IMAGE;
import static com.ids.favoritemovie.data.DatabaseContract.TABLE_TV_SHOW;

public class DetailTvFavViewModel extends ViewModel {

    private TvShow tv;
    private Uri uriWithId;
    private Context context;

    private DetailTvFavNavigator detailTvFavNavigator;

    public void initViewModel(Context context,DetailTvFavNavigator detailTvFavNavigator){
        this.context = context;
        this.detailTvFavNavigator = detailTvFavNavigator;
    }


    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }


    public void addFavorite(TvShow data) {
        if (getFavorite(data.getId())) {
            deleteFavorite(data);
        } else {
            saveFavorite(data);
        }
        detailTvFavNavigator.initView(data);
    }

    public Boolean getFavorite(int id) {
        uriWithId = Uri.parse(DatabaseContract.getContentUri(TABLE_TV_SHOW) + "/" + id);
        if (uriWithId != null) {
            Cursor cursor = context.getContentResolver().query(uriWithId, null, null, null, null);
            if (cursor != null) {
                tv = MappingHelper.mapCursorTvToObject(cursor);
                cursor.close();
            }else{
                tv = null;
            }
        }

        return tv != null;
    }

    public void deleteFavorite(TvShow tv){
        uriWithId = Uri.parse(DatabaseContract.getContentUri(TABLE_TV_SHOW) + "/" + tv.getId());
        if (uriWithId != null) {
            context.getContentResolver().delete(uriWithId, null, null);
        }
    }

    public void saveFavorite(TvShow tv){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TvShowColumns.original_name,tv.getOriginalName());
        values.put(DatabaseContract.TvShowColumns.name,tv.getName());
        values.put(DatabaseContract.TvShowColumns.popularity,tv.getPopularity());
        values.put(DatabaseContract.TvShowColumns.vote_count,tv.getVoteCount());
        values.put(DatabaseContract.TvShowColumns.first_air_date,tv.getFirstAirDate());
        values.put(DatabaseContract.TvShowColumns.backdrop_path,tv.getBackdropPath());
        values.put(DatabaseContract.TvShowColumns.original_language,tv.getOriginalLanguage());
        values.put(DatabaseContract.TvShowColumns.id,tv.getId());
        values.put(DatabaseContract.TvShowColumns.vote_average,tv.getVoteAverage());
        values.put(DatabaseContract.TvShowColumns.overview,tv.getOverview());
        values.put(DatabaseContract.TvShowColumns.poster_path,tv.getPosterPath());
        values.put(DatabaseContract.TvShowColumns.favorite,tv.getFavorite());
        context.getContentResolver().insert(DatabaseContract.getContentUri(TABLE_TV_SHOW), values);

        detailTvFavNavigator.initView(tv);
    }

}
