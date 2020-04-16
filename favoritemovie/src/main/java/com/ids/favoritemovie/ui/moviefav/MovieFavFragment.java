package com.ids.favoritemovie.ui.moviefav;

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

import com.ids.favoritemovie.custom.EmptyRecyclerView;
import com.ids.favoritemovie.databinding.MovieFavFragmentBinding;
import com.ids.favoritemovie.model.Movie;
import com.ids.favoritemovie.ui.detailmoviefav.DetailMovieFavActivity;

import java.util.ArrayList;

public class MovieFavFragment extends Fragment implements MovieFavNavigator{

    private MovieFavFragmentBinding dataBinding;
    private MovieFavViewModel mViewModel;
    public static MovieFavFragment newInstance() {
        return new MovieFavFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieFavViewModel.class);
        mViewModel.initViewModel(getContext(),this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dataBinding = MovieFavFragmentBinding.inflate(inflater,container,false);
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
    public void showDetail(Movie data) {
        try {
            Intent intent = new Intent(getContext(), DetailMovieFavActivity.class);
            intent.putExtra(DetailMovieFavActivity.TAG, data);
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
    public void postExecute(ArrayList<Movie> Movies) {
        mViewModel.setMovies(Movies);
    }
}
