package com.ids.madesubmission4.ui.tv.tvshow;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.adapter.tvshow.TvShowAdapter;
import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.model.tvshow.ResponseTvShow;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.repository.tvshow.TvRepository;

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
public class TvShowViewModel extends BaseViewModel<TvShowNavigator> implements TvShowAdapter.OnItemClickCallback {

    public final MutableLiveData<ResponseTvShow> responseTvShow = new MutableLiveData<>();
    public ObservableBoolean isRefresh = new ObservableBoolean();
    public Boolean isLoading = false;
    public Integer curPage = 1;
    private MutableLiveData<ArrayList<TvShow>> tvshows = new MutableLiveData<>();
    private TvRepository restRepository;
    private TvShowAdapter adapter;

    @Inject
    public TvShowViewModel(@NonNull Application application, TvRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
        this.adapter = new TvShowAdapter(R.layout.item_tvshow, this);
    }

    public void setTvShowInAdapter(ArrayList<TvShow> tvshow) {
        this.adapter.setTvShow(tvshow);
        this.adapter.notifyDataSetChanged();
        this.adapter.setOnItemClickCallback(this);
    }

    public TvShowAdapter getAdapter() {
        return adapter;
    }


    public Single<ResponseTvShow> createTvSearch(String query) {
        if (query.isEmpty())
            return restRepository.getTvShow(getApplication().getApplicationContext().getResources().getString(R.string.movie_language), curPage.toString());
        else
            return restRepository.getTvShowSearch(getApplication().getApplicationContext().getResources().getString(R.string.movie_language), query, curPage.toString());
    }

    public void loadData(String query) {
        curPage = 1;
        searchData(createTvSearch(query));
    }

    public void loadMore(String query) {
        isLoading = true;
        tvshows.getValue().add(null);
        this.adapter.notifyItemInserted(getItemSize() - 1);
        searchData(createTvSearch(query));
    }

    public void searchData(Single<ResponseTvShow> request) {
        getNavigator().showLoading();
        getCompositeDisposable().add(request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseTvShow>() {
                    @Override
                    public void onSuccess(ResponseTvShow movie) {
                        getNavigator().hideLoading();
                        isRefresh.set(false);
                        responseTvShow.setValue(movie);

                        if (responseTvShow.getValue().getTotalPages() > 0) {
                            if (curPage == 1) {
                                tvshows.setValue(responseTvShow.getValue().getTvShows());
                            } else {
                                tvshows.getValue().remove(getItemSize() - 1);
                                adapter.notifyItemRemoved(getItemSize());
                                tvshows.getValue().addAll(responseTvShow.getValue().getTvShows());
                            }
                        } else {
                            tvshows.getValue().clear();
                        }

                        setTvShowInAdapter(getTvShow().getValue());
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

    public MutableLiveData<ArrayList<TvShow>> getTvShow() {
        return tvshows;
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
        return tvshows.getValue().size();
    }

    @Override
    public void onItemClicked(TvShow data) {
        getNavigator().showDetail(data);
    }

    public void onRefresh() {
        isRefresh.set(true);
        getNavigator().loadData();
    }
}