package com.ids.favoritemovie.ui.mainfav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ids.favoritemovie.adapter.MenuFavAdapter;
import com.ids.favoritemovie.databinding.MainFavFragmentBinding;

public class MainFavFragment extends Fragment {

    private MenuFavAdapter menuFavAdapter;

    private MainFavFragmentBinding dataBinding;
    private MainFavViewModel mViewModel;

    public static MainFavFragment newInstance() {
        return new MainFavFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = MainFavFragmentBinding.inflate(inflater,container,false);
        initData();
        initViews();
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainFavViewModel.class);
    }

    public void initData() {
        menuFavAdapter = new MenuFavAdapter(getContext(), getChildFragmentManager());
    }

    public void initViews() {
        dataBinding.viewPagerFav.setAdapter(menuFavAdapter);
        dataBinding.tabsFav.setupWithViewPager(dataBinding.viewPagerFav);
    }
}
