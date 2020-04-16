package com.ids.madesubmission4.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ids.madesubmission4.di.ViewModelFactory;
import com.ids.madesubmission4.di.ViewModelKey;
import com.ids.madesubmission4.ui.main.MainViewModel;
import com.ids.madesubmission4.ui.mainfavorite.MainFavoriteViewModel;
import com.ids.madesubmission4.ui.movies.movie.MovieViewModel;
import com.ids.madesubmission4.ui.movies.moviedetail.DetailViewModel;
import com.ids.madesubmission4.ui.movies.moviefavorite.MovieFavoriteViewModel;
import com.ids.madesubmission4.ui.tv.tvshow.TvShowViewModel;
import com.ids.madesubmission4.ui.tv.tvshowdetail.DetailTvViewModel;
import com.ids.madesubmission4.ui.tv.tvshowfavorite.TvShowFavoriteViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindViewModel(MainViewModel mainViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(MainFavoriteViewModel.class)
    abstract ViewModel bindViewModelFavorite(MainFavoriteViewModel mainFavoriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel detailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieViewModel movieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel.class)
    abstract ViewModel bindTvShowViewModel(TvShowViewModel tvShowViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieFavoriteViewModel.class)
    abstract ViewModel bindMovieFavoriteViewModel(MovieFavoriteViewModel movieFavoriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TvShowFavoriteViewModel.class)
    abstract ViewModel bindTvShowFavoriteViewModel(TvShowFavoriteViewModel tvShowFavoriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DetailTvViewModel.class)
    abstract ViewModel bindDetailTvViewModel(DetailTvViewModel detailTvViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);

}
