package com.ids.madesubmission4.ui.movies.moviefavorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.adapter.moviefavorite.MovieFavoriteAdapter;
import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.repository.movie.MovieRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 15,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public class MovieFavoriteViewModel extends BaseViewModel<MovieFavoriteNavigator> implements MovieFavoriteAdapter.OnItemClickCallback {

    public ObservableBoolean isRefresh = new ObservableBoolean();
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    private MovieRepository restRepository;
    private MovieFavoriteAdapter adapter;


    @Inject
    public MovieFavoriteViewModel(@NonNull Application application, MovieRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
        this.adapter = new MovieFavoriteAdapter(R.layout.item_movie_favorite, this);
        this.adapter.setOnItemClickCallback(this);
    }

    public void setMovieInAdapter(ArrayList<Movie> movie) {
        this.adapter.setMovie(movie);
        this.adapter.notifyDataSetChanged();
    }

    public MovieFavoriteAdapter getAdapter() {
        return adapter;
    }

    public void loadData() {
        movies.setValue(getRealm().getAllData());
        setMovieInAdapter(getMovie().getValue());
    }

    public MutableLiveData<ArrayList<Movie>> getMovie() {
        return movies;
    }

    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }

    public String getOverview(String overview) {
        try {
            return overview.substring(0, 100) + getApplication().getString(R.string.more);
        } catch (Exception e) {
            return getApplication().getString(R.string.more);
        }
    }

    @Override
    public void onItemClicked(Movie data) {
        getNavigator().showDetail(data);
    }

    public void onRefresh() {
        isRefresh.set(true);
        loadData();
    }
}