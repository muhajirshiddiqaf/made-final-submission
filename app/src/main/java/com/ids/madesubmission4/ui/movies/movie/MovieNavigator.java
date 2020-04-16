package com.ids.madesubmission4.ui.movies.movie;

import com.ids.madesubmission4.base.BaseNavigator;
import com.ids.madesubmission4.data.model.movie.Movie;

public interface MovieNavigator extends BaseNavigator {
    void showDetail(Movie data);

    void loadData();
}
