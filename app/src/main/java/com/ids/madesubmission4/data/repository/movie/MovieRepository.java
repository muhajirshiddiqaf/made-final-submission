package com.ids.madesubmission4.data.repository.movie;

import com.ids.madesubmission4.data.model.movie.ResponseMovie;
import com.ids.madesubmission4.data.rest.RestService;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ids.madesubmission4.util.AppConstants.API_KEY;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public class MovieRepository {

    private RestService restService;

    @Inject
    public MovieRepository(RestService restService) {
        this.restService = restService;
    }

    public Single<ResponseMovie> getMovie(String language, String page) {
        return restService.getMovie(API_KEY, language, page);
    }

    public Single<ResponseMovie> getMovieSearch(String language, String query, String page) {
        return restService.searchMovie(API_KEY, language, query, page);
    }
}
