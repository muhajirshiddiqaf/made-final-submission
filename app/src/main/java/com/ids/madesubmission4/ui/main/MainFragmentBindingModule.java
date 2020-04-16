package com.ids.madesubmission4.ui.main;

import com.ids.madesubmission4.ui.movies.movie.MovieFragment;
import com.ids.madesubmission4.ui.tv.tvshow.TvShowFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract MovieFragment provideMovieFragment();

    @ContributesAndroidInjector
    abstract TvShowFragment provideTvShowFragment();

}
