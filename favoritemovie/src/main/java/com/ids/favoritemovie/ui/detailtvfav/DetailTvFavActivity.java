package com.ids.favoritemovie.ui.detailtvfav;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.ids.favoritemovie.BR;
import com.ids.favoritemovie.R;
import com.ids.favoritemovie.databinding.DetailTvFavActivityBinding;
import com.ids.favoritemovie.model.TvShow;

public class DetailTvFavActivity extends AppCompatActivity implements DetailTvFavNavigator {

    public static String TAG = DetailTvFavActivity.class.getName();
    private TvShow tv;
    DetailTvFavViewModel mViewModel;
    DetailTvFavActivityBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this,R.layout.detail_tv_fav_activity);
        mViewModel = ViewModelProviders.of(this).get(DetailTvFavViewModel.class);
        mViewModel.initViewModel(this,this);
        dataBinding.setVariable(BR.vmFavTvDetail, mViewModel);
        dataBinding.executePendingBindings();

        initToolbar();
        initData();
        initView(tv);
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
        setSupportActionBar(dataBinding.toolbarFavTvDetail);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initData() {
        tv = getIntent().getParcelableExtra(DetailTvFavActivity.TAG);
    }


    public void initView(TvShow data) {
        tv.setFavorite(mViewModel.getFavorite(tv.getId()));
        dataBinding.setVariable(BR.tvFavDetail, tv);
    }
}
