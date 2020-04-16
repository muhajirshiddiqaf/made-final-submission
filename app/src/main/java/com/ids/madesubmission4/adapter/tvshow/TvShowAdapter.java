package com.ids.madesubmission4.adapter.tvshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.ids.madesubmission4.BR;
import com.ids.madesubmission4.data.model.tvshow.TvShow;
import com.ids.madesubmission4.ui.tv.tvshow.TvShowViewModel;

import java.util.ArrayList;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,November,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */


public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.GenericViewHolder> {

    private int layoutId;
    private ArrayList<TvShow> tvshows;
    private TvShowViewModel viewModel;
    private OnItemClickCallback onItemClickCallback;

    public TvShowAdapter(@LayoutRes int layoutId, TvShowViewModel viewModel) {
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
        return tvshows == null ? 0 : tvshows.size();
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

    public void setTvShow(ArrayList<TvShow> tvshows) {
        this.tvshows = tvshows;
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvShow data);
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TvShowViewModel viewModel, Integer position) {
            binding.setVariable(BR.vmTv, viewModel);
            binding.setVariable(BR.tvshow, tvshows.get(position));
            binding.executePendingBindings();

            itemView.setOnClickListener(v -> {
                onItemClickCallback.onItemClicked(tvshows.get(position));
            });
        }
    }

}