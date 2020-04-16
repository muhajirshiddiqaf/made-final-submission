package com.ids.madesubmission4.ui.movies.moviedetail;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseActivity;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.realm.RealmHelper;
import com.ids.madesubmission4.databinding.ActivityDetailMovieBinding;
import com.ids.madesubmission4.widget.FavoriteWidget;
import com.ids.madesubmission4.widget.StackWidgetService;

public class DetailActivity extends BaseActivity<ActivityDetailMovieBinding, DetailViewModel> implements DetailNavigator {

    public static String TAG = DetailActivity.class.getName();
    private Movie movie;

    public static Intent newIntent(Context context) {
        return new Intent(context, DetailActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        vm.setRealm(new RealmHelper<>(realm, Movie.class));
        initToolbar();
        initData();
        initView(movie);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail_movie;
    }

    @Override
    public int getBindingVariable() {
        return BR.vmDetail;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    public void initToolbar() {
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initData() {
        movie = getIntent().getParcelableExtra(TAG);
    }

    @Override
    public void initView(Movie data) {
        movie.setFavorite(vm.getFavorite(movie.getId()));
        dataBinding.setVariable(BR.movieDetail, movie);
    }
}
