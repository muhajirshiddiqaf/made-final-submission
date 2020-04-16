package com.ids.favoritemovie.ui.detailmoviefav;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.ids.favoritemovie.BR;
import com.ids.favoritemovie.R;
import com.ids.favoritemovie.databinding.DetailMovieFavActivityBinding;
import com.ids.favoritemovie.model.Movie;

public class DetailMovieFavActivity extends AppCompatActivity implements DetailMovieFavNavigator
{

    public static String TAG = DetailMovieFavActivity.class.getName();
    private Movie movie;
    DetailMovieFavViewModel mViewModel;
    DetailMovieFavActivityBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this,R.layout.detail_movie_fav_activity);
        mViewModel = ViewModelProviders.of(this).get(DetailMovieFavViewModel.class);
        mViewModel.initViewModel(this,this);
        dataBinding.setVariable(BR.vmFavDetail, mViewModel);
        dataBinding.executePendingBindings();

        initToolbar();
        initData();
        initView(movie);
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
        setSupportActionBar(dataBinding.toolbarFavDetail);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initData() {
        movie = getIntent().getParcelableExtra(DetailMovieFavActivity.TAG);
    }


    public void initView(Movie data) {
        movie.setFavorite(mViewModel.getFavorite(movie.getId()));
        dataBinding.setVariable(BR.movieFavDetail, movie);
    }
}
