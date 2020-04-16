package com.ids.madesubmission4.data.repository.tvshow;

import com.ids.madesubmission4.data.model.tvshow.ResponseTvShow;
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
public class TvRepository {

    private RestService restService;

    @Inject
    public TvRepository(RestService restService) {
        this.restService = restService;
    }


    public Single<ResponseTvShow> getTvShow(String language, String page) {
        return restService.getTv(API_KEY, language, page);
    }

    public Single<ResponseTvShow> getTvShowSearch(String language, String query, String page) {
        return restService.searchTv(API_KEY, language, query, page);
    }
}
