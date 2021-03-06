package com.ids.favoritemovie.ui;

import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ids.favoritemovie.R;
import com.ids.favoritemovie.custom.EmptyRecyclerView;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class CustomViewBindings {

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(EmptyRecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("imageUrl")
    public static void bindImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }


    @BindingAdapter("android:rating")
    public static void bindRating(RatingBar view, double rating) {
        if (view.getRating() != rating) {
            view.setRating((float) (rating / 2));
        }
    }

    @BindingAdapter("setFavorite")
    public static void bindFavorite(ImageView view, boolean favorite) {
        if (favorite)
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.redAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
        else
            view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.colorGrey), android.graphics.PorterDuff.Mode.MULTIPLY);

    }
}
