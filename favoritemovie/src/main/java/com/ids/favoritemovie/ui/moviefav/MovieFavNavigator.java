package com.ids.favoritemovie.ui.moviefav;

import com.ids.favoritemovie.model.Movie;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public interface MovieFavNavigator {
    void showDetail(Movie data);
    void preExecute();
    void postExecute(ArrayList<Movie> Movies);
}
