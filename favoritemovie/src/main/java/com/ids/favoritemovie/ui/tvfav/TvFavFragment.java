package com.ids.favoritemovie.ui.tvfav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ids.favoritemovie.databinding.TvFavFragmentBinding;
import com.ids.favoritemovie.model.TvShow;
import com.ids.favoritemovie.ui.detailtvfav.DetailTvFavActivity;
import com.ids.favoritemovie.custom.EmptyRecyclerView;

import java.util.ArrayList;

public class TvFavFragment extends Fragment implements TvFavNavigator{

    private TvFavFragmentBinding dataBinding;
    private TvFavViewModel mViewModel;
    public static TvFavFragment newInstance() {
        return new TvFavFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TvFavViewModel.class);
        mViewModel.initViewModel(getContext(),this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dataBinding = TvFavFragmentBinding.inflate(inflater,container,false);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData(){
        EmptyRecyclerView recyclerView = dataBinding.rvItem;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mViewModel.getAdapter());
        mViewModel.loadData(getContext());
    }

    @Override
    public void showDetail(TvShow data) {
        try {
            Intent intent = new Intent(getContext(), DetailTvFavActivity.class);
            intent.putExtra(DetailTvFavActivity.TAG, data);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getContext(), "Activity not found", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<TvShow> Tvs) {
        mViewModel.setTvs(Tvs);
    }

}
