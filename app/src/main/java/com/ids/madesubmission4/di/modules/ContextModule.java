package com.ids.madesubmission4.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */

@Module
public abstract class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Binds
    abstract Context bindContext(Application application);
}
