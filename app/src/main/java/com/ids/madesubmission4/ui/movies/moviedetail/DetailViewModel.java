package com.ids.madesubmission4.ui.movies.moviedetail;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.repository.movie.MovieRepository;
import com.ids.madesubmission4.widget.FavoriteWidget;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    private MovieRepository restRepository;


    @Inject
    public DetailViewModel(@NonNull Application application, MovieRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
    }

    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }


    public void addFavorite(Movie data) {
        if (getFavorite(data.getId())) {
            getRealm().delete("id", data.getId());
        } else {
            getRealm().save(data);
        }
        getNavigator().initView(data);


        try{
            Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            getApplication().sendBroadcast(intent);
            FavoriteWidget.updateWidget(getApplication().getApplicationContext());
        }catch (Exception e){

        }
    }

    public Boolean getFavorite(int id) {
        return getRealm().getDataById("id", id) != null;
    }

    public Movie getMovie(int id) {
        return (Movie) getRealm().getDataById("id", id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (getCompositeDisposable() != null) {
            getCompositeDisposable().clear();
        }
    }
}
