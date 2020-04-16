package com.ids.madesubmission4.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.repository.movie.MovieRepository;

import javax.inject.Inject;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private MovieRepository restRepository;

    @Inject
    public MainViewModel(@NonNull Application application, MovieRepository restRepository) {
        super(application);
        this.restRepository = restRepository;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (getCompositeDisposable() != null) {
            getCompositeDisposable().clear();

        }
    }
}
