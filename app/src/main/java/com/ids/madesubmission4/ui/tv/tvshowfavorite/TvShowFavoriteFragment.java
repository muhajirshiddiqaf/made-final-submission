package com.ids.madesubmission4.ui.tv.tvshowfavorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseFragment;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.realm.RealmHelper;
import com.ids.madesubmission4.databinding.FragmentTvshowFavoriteBinding;
import com.ids.madesubmission4.ui.tv.tvshowdetail.DetailTvActivity;

public class TvShowFavoriteFragment extends BaseFragment<FragmentTvshowFavoriteBinding, TvShowFavoriteViewModel> implements TvShowFavoriteNavigator {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        vm.setRealm(new RealmHelper<>(realm, TvShow.class));
        vm.loadData();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tvshow_favorite;
    }

    @Override
    public int getBindingVariable() {
        return BR.vmTvFavorite;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showDetail(TvShow data) {
        Intent i = DetailTvActivity.newIntent(context);
        i.putExtra(DetailTvActivity.TAG, data);
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        vm.loadData();
    }
}
