package com.ids.madesubmission4.adapter.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.ui.movies.movie.MovieViewModel;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,November,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */


public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private int layoutId;
    private ArrayList<Movie> movies;
    private MovieViewModel viewModel;
    private OnItemClickCallback onItemClickCallback;

    public MovieAdapter(@LayoutRes int layoutId, MovieViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
            return new GenericViewHolder(binding);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GenericViewHolder) {
            ((GenericViewHolder) holder).bind(viewModel, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return movies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setMovie(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(MovieViewModel viewModel, Integer position) {
            binding.setVariable(BR.vm, viewModel);
            binding.setVariable(BR.movie, movies.get(position));
            binding.executePendingBindings();

            itemView.setOnClickListener(v -> {
                onItemClickCallback.onItemClicked(movies.get(position));
            });
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }


}