package com.ids.madesubmission4.data.rest;

import com.ids.madesubmission4.data.model.movie.ResponseMovie;
import com.ids.madesubmission4.data.model.tvshow.ResponseTvShow;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public interface RestService {

    @GET("discover/movie")
    Single<ResponseMovie> getMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );

    @GET("search/movie")
    Single<ResponseMovie> searchMovie(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") String page
    );

    @GET("discover/tv")
    Single<ResponseTvShow> getTv(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page);


    @GET("search/tv")
    Single<ResponseTvShow> searchTv(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") String page);


    @GET("discover/movie")
    Single<ResponseMovie> searchRealeaseToday(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("primary_release_date.gte") String gte,
            @Query("primary_release_date.lte") String lte);

}
