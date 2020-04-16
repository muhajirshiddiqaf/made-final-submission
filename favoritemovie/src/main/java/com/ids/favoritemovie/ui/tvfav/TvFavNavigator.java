package com.ids.favoritemovie.ui.tvfav;


import com.ids.favoritemovie.model.TvShow;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public interface TvFavNavigator {
    void showDetail(TvShow data);
    void preExecute();
    void postExecute(ArrayList<TvShow> TvShows);
}
