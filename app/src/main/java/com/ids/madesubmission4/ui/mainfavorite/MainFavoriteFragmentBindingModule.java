package com.ids.madesubmission4.ui.mainfavorite;

import com.ids.madesubmission4.ui.movies.moviefavorite.MovieFavoriteFragment;
import com.ids.madesubmission4.ui.tv.tvshowfavorite.TvShowFavoriteFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainFavoriteFragmentBindingModule {

    @ContributesAndroidInjector
    abstract MovieFavoriteFragment provideMovieFavoriteFragment();

    @ContributesAndroidInjector
    abstract TvShowFavoriteFragment provideTvShowFavoriteFragment();

}
