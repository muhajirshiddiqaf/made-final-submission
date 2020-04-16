package com.ids.madesubmission4.data.interfaces;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 19,January,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class SearchNavigator {
    String data;

    public SearchNavigator(String query) {
        this.data = query;
    }

    public String getQuery() {
        return data;
    }
}
