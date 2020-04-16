package com.ids.madesubmission4.ui.tv.tvshowdetail;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.repository.movie.MovieRepository;

import javax.inject.Inject;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

public class DetailTvViewModel extends BaseViewModel<DetailTvNavigator> {

    private MovieRepository restRepository;


    @Inject
    public DetailTvViewModel(@NonNull Application application, MovieRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
    }

    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }

    public void addFavorite(TvShow data) {
        if (getFavorite(data.getId())) {
            getRealm().delete("id", data.getId());
        } else {
            getRealm().save(data);
        }

        getNavigator().initView(data);
    }

    public Boolean getFavorite(int id) {
        return getRealm().getDataById("id", id) != null;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (getCompositeDisposable() != null) {
            getCompositeDisposable().clear();

        }
    }
}
