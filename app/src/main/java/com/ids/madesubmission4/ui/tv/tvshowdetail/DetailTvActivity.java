package com.ids.madesubmission4.ui.tv.tvshowdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseActivity;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.data.realm.RealmHelper;
import com.ids.madesubmission4.databinding.ActivityDetailTvshowBinding;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 23,December,2019.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class DetailTvActivity extends BaseActivity<ActivityDetailTvshowBinding, DetailTvViewModel> implements DetailTvNavigator {

    public static String TAG = DetailTvActivity.class.getName();

    public TvShow tvShow;

    public static Intent newIntent(Context context) {
        return new Intent(context, DetailTvActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        vm.setRealm(new RealmHelper<>(realm, TvShow.class));
        initToolbar();
        initData();
        initView(tvShow);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail_tvshow;
    }

    @Override
    public int getBindingVariable() {
        return BR.vmTvDetail;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void initToolbar() {
        setSupportActionBar(dataBinding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initData() {
        tvShow = getIntent().getParcelableExtra(TAG);
    }

    public void initView(TvShow tvShow) {
        tvShow.setFavorite(vm.getFavorite(tvShow.getId()));
        dataBinding.setVariable(BR.tvDetail, tvShow);
    }


}

