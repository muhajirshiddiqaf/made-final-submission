package com.ids.madesubmission4.adapter.menu;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.ui.movies.movie.MovieFragment;
import com.ids.madesubmission4.ui.tv.tvshow.TvShowFragment;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 09,November,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */
public class MenuAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_movies,
            R.string.tab_tv_show
    };

    public MenuAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFragment();
                break;
            case 1:
                fragment = new TvShowFragment();
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
