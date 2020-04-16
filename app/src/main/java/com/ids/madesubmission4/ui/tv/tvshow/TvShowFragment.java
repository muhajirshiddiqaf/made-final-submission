package com.ids.madesubmission4.ui.tv.tvshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseFragment;
import com.ids.madesubmission4.data.interfaces.SearchNavigator;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.databinding.FragmentTvshowBinding;
import com.ids.madesubmission4.ui.tv.tvshowdetail.DetailTvActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TvShowFragment extends BaseFragment<FragmentTvshowBinding, TvShowViewModel> implements TvShowNavigator {

    String searchParam = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        loadData();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_tvshow;
    }

    @Override
    public int getBindingVariable() {
        return BR.vmTv;
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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }


    private void initScrollListener() {
        dataBinding.rvItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!vm.isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == vm.getItemSize() - 1 && vm.curPage < vm.responseTvShow.getValue().getTotalPages()) {
                        //bottom of list!
                        vm.loadMore(searchParam);
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearch(SearchNavigator event) {
        String query = event.getQuery();
        searchParam = query;
        loadData();
    }


    @Override
    public void loadData() {
        vm.loadData(searchParam);
    }


}
