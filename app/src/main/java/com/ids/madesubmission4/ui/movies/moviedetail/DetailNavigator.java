package com.ids.madesubmission4.ui.movies.moviedetail;

import com.ids.madesubmission4.base.BaseNavigator;
import com.ids.madesubmission4.data.model.movie.Movie;

public interface DetailNavigator extends BaseNavigator {
    void initView(Movie data);
}
