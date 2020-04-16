package com.ids.madesubmission4.ui.tv.tvshowfavorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.adapter.tvshowfavorite.TvShowFavoriteAdapter;
import com.ids.madesubmission4.base.BaseViewModel;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.repository.tvshow.TvRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 15,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public class TvShowFavoriteViewModel extends BaseViewModel<TvShowFavoriteNavigator> implements TvShowFavoriteAdapter.OnItemClickCallback {

    public ObservableBoolean isRefresh = new ObservableBoolean();
    private MutableLiveData<ArrayList<TvShow>> tvshows = new MutableLiveData<>();
    private TvRepository restRepository;
    private TvShowFavoriteAdapter adapter;


    @Inject
    public TvShowFavoriteViewModel(@NonNull Application application, TvRepository restRepository) {
        super(application);
        this.restRepository = restRepository;
        this.adapter = new TvShowFavoriteAdapter(R.layout.item_tvshow_favorite, this);
    }

    public void setTvShowInAdapter(ArrayList<TvShow> tvshow) {
        this.adapter.setTvShow(tvshow);
        this.adapter.notifyDataSetChanged();
        this.adapter.setOnItemClickCallback(this);
    }

    public TvShowFavoriteAdapter getAdapter() {
        return adapter;
    }

    public void loadData() {
        tvshows.setValue(getRealm().getAllData());
        setTvShowInAdapter(getTvShow().getValue());
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

    @Override
    public void onItemClicked(TvShow data) {
        getNavigator().showDetail(data);
    }

    public void onRefresh() {
        isRefresh.set(true);
        loadData();
    }
}