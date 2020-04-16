package com.ids.madesubmission4.ui.movies.moviefavorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseFragment;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.realm.RealmHelper;
import com.ids.madesubmission4.databinding.FragmentMovieFavoriteBinding;
import com.ids.madesubmission4.ui.movies.moviedetail.DetailActivity;


public class MovieFavoriteFragment extends BaseFragment<FragmentMovieFavoriteBinding, MovieFavoriteViewModel> implements MovieFavoriteNavigator {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        vm.setRealm(new RealmHelper<>(realm, Movie.class));
        vm.loadData();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movie_favorite;
    }

    @Override
    public int getBindingVariable() {
        return BR.vmFavorite;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showDetail(Movie data) {
        Intent i = DetailActivity.newIntent(context);
        i.putExtra(DetailActivity.TAG, data);
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.loadData();
    }
}