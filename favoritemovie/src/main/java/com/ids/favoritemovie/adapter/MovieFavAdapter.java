package com.ids.favoritemovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.favoritemovie.BR;
import com.ids.favoritemovie.model.Movie;
import com.ids.favoritemovie.ui.moviefav.MovieFavViewModel;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class MovieFavAdapter extends RecyclerView.Adapter<MovieFavAdapter.GenericViewHolder> {

    private int layoutId;
    private ArrayList<Movie> movies;
    private MovieFavViewModel viewModel;
    private OnItemClickCallback onItemClickCallback;

    public MovieFavAdapter(@LayoutRes int layoutId, MovieFavViewModel viewModel) {
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
        notifyDataSetChanged();
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

        void bind(MovieFavViewModel viewModel, final Integer position) {
            binding.setVariable(BR.vmItemMovieFav, viewModel);
            binding.setVariable(BR.itemMovieFav, movies.get(position));
            binding.executePendingBindings();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(movies.get(position));
                }
            });
        }
    }

}
