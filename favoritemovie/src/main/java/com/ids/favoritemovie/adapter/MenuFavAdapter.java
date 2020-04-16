package com.ids.favoritemovie.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ids.favoritemovie.R;
import com.ids.favoritemovie.ui.moviefav.MovieFavFragment;
import com.ids.favoritemovie.ui.tvfav.TvFavFragment;


/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class MenuFavAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_movies,
            R.string.tab_tv_show
    };

    public MenuFavAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFavFragment();
                break;
            case 1:
                fragment = new TvFavFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }

}
