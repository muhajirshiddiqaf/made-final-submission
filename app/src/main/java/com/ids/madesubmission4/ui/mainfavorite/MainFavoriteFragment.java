package com.ids.madesubmission4.ui.mainfavorite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.adapter.menufavorite.MenuFavoriteAdapter;
import com.ids.madesubmission4.base.BaseFragment;
import com.ids.madesubmission4.databinding.ActivityMainFavoriteBinding;

public class MainFavoriteFragment extends BaseFragment<ActivityMainFavoriteBinding, MainFavoriteViewModel> implements MainFavoriteNavigator {

    private MenuFavoriteAdapter menuFavoriteAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm.setNavigator(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initData();
        initViews();
        return view;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main_favorite;
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

    public void initData() {
        menuFavoriteAdapter = new MenuFavoriteAdapter(context, getChildFragmentManager());
    }

    public void initViews() {
        dataBinding.viewPager.setAdapter(menuFavoriteAdapter);
        dataBinding.tabs.setupWithViewPager(dataBinding.viewPager);
    }
}
