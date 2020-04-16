package com.ids.madesubmission4.ui.movies.movie;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.adapter.movie.MovieAdapter;
import com.ids.madesubmission4.base.BaseObservableViewModel;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.model.movie.ResponseMovie;
import com.ids.madesubmission4.data.repository.movie.MovieRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 15,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public class MovieViewModel extends BaseObservableViewModel<MovieNavigator> implements MovieAdapter.OnItemClickCallback {

    public final MutableLiveData<ResponseMovie> responseMovie = new MutableLiveData<>();
    public ObservableBoolean isRefresh = new ObservableBoolean();
    public Boolean isLoading = false;
    public Integer curPage = 1;
    private MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
    private MovieRepository restRepository;
    private MovieAdapter adapter;


    @Inject
    public MovieViewModel(@NonNull Application application, MovieRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
        this.adapter = new MovieAdapter(R.layout.item_movie, this);
        this.adapter.setOnItemClickCallback(this);
    }

    public void setMovieInAdapter(ArrayList<Movie> movie) {
        this.adapter.setMovie(movie);
        this.adapter.notifyDataSetChanged();
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }

    public Single<ResponseMovie> createMovieSearch(String query) {
        if (query.isEmpty())
            return restRepository.getMovie(getApplication().getApplicationContext().getResources().getString(R.string.movie_language), curPage.toString());
        else
            return restRepository.getMovieSearch(getApplication().getApplicationContext().getResources().getString(R.string.movie_language), query, curPage.toString());
    }

    public void loadData(String query) {
        curPage = 1;
        searchData(createMovieSearch(query));
    }

    public void loadMore(String query) {
        isLoading = true;
        movies.getValue().add(null);
        this.adapter.notifyItemInserted(getItemSize() - 1);
        searchData(createMovieSearch(query));
    }

    public void searchData(Single<ResponseMovie> request) {
        getNavigator().showLoading();
        getCompositeDisposable().add(request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseMovie>() {
                    @Override
                    public void onSuccess(ResponseMovie movie) {
                        getNavigator().hideLoading();
                        isRefresh.set(false);
                        responseMovie.setValue(movie);

                        if (responseMovie.getValue().getTotalResults() > 0) {
                            if (curPage == 1) {
                                movies.setValue(responseMovie.getValue().getResults());
                            } else {
                                movies.getValue().remove(getItemSize() - 1);
                                adapter.notifyItemRemoved(getItemSize());
                                movies.getValue().addAll(responseMovie.getValue().getResults());
                            }
                        } else {
                            movies.getValue().clear();
                        }

                        setMovieInAdapter(getMovie().getValue());
                        curPage++;
                        isLoading = false;
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNavigator().hideLoading();
                        isRefresh.set(false);
                        Log.d("Error get movie", e.getMessage());
                    }
                }));
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

    public Integer getItemSize() {
        return movies.getValue().size();
    }

    @Override
    public void onItemClicked(Movie data) {
        getNavigator().showDetail(data);
    }

    public void onRefresh() {
        isRefresh.set(true);
        getNavigator().loadData();
    }
}