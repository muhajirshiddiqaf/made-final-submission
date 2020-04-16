package com.ids.madesubmission4.adapter.moviefavorite;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.ui.movies.moviefavorite.MovieFavoriteViewModel;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,November,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */


public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.GenericViewHolder> {

    private int layoutId;
    private ArrayList<Movie> movies;
    private MovieFavoriteViewModel viewModel;
    private OnItemClickCallback onItemClickCallback;

    public MovieFavoriteAdapter(@LayoutRes int layoutId, MovieFavoriteViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
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

        void bind(MovieFavoriteViewModel viewModel, Integer position) {
            binding.setVariable(BR.vm, viewModel);
            binding.setVariable(BR.movie, movies.get(position));
            binding.executePendingBindings();

            itemView.setOnClickListener(v -> {
                onItemClickCallback.onItemClicked(movies.get(position));
            });
        }
    }

}