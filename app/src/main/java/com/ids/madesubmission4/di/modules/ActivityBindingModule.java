package com.ids.madesubmission4.di.modules;

import com.ids.madesubmission4.ui.main.MainFragment;
import com.ids.madesubmission4.ui.main.MainFragmentBindingModule;
import com.ids.madesubmission4.ui.mainfavorite.MainFavoriteFragment;
import com.ids.madesubmission4.ui.mainfavorite.MainFavoriteFragmentBindingModule;
import com.ids.madesubmission4.ui.movies.moviedetail.DetailActivity;
import com.ids.madesubmission4.ui.tv.tvshowdetail.DetailTvActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainFragment bindMainActivity();

    @ContributesAndroidInjector(modules = {MainFavoriteFragmentBindingModule.class})
    abstract MainFavoriteFragment bindMainFavoriteActivity();

    @ContributesAndroidInjector(modules = {})
    abstract DetailActivity bindDetailActivity();

    @ContributesAndroidInjector(modules = {})
    abstract DetailTvActivity bindDetailTvActivity();

}
