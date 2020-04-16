package com.ids.madesubmission4.ui.movies.moviefavorite;

import com.ids.madesubmission4.base.BaseNavigator;
import com.ids.madesubmission4.data.model.movie.Movie;

public interface MovieFavoriteNavigator extends BaseNavigator {
    void showDetail(Movie data);
}
