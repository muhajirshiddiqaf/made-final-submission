package com.ids.madesubmission4.ui.movies.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.base.BaseFragment;
import com.ids.madesubmission4.data.interfaces.SearchNavigator;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.databinding.FragmentMovieBinding;
import com.ids.madesubmission4.ui.movies.moviedetail.DetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MovieFragment extends BaseFragment<FragmentMovieBinding, MovieViewModel> implements MovieNavigator {

    String searchParam = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
        loadData();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initScrollListener();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
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
    public void showDetail(Movie data) {
        Intent i = DetailActivity.newIntent(context);
        i.putExtra(DetailActivity.TAG, data);
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
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == vm.getItemSize() - 1 && vm.curPage < vm.responseMovie.getValue().getTotalPages()) {
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