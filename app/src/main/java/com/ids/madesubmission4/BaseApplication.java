package com.ids.madesubmission4;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.preference.PreferenceManager;

import com.developer.crashx.config.CrashConfig;
import com.ids.madesubmission4.di.component.AppComponent;
import com.ids.madesubmission4.di.component.DaggerAppComponent;
import com.ids.madesubmission4.job.MovieAlarmManager;
import com.ids.madesubmission4.widget.FavoriteWidget;
import com.ids.madesubmission4.widget.StackWidgetService;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.ids.madesubmission4.data.pref.MyPreferenceFragment.DAILY_REMINDER;
import static com.ids.madesubmission4.data.pref.MyPreferenceFragment.RELEASE_REMINDER;
import static com.ids.madesubmission4.util.AppConstants.DB_NAME;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 14,December,2019.
 * Email : infinitydsolution@gmail.com
 * Phone : 0895411149046
 * Company : Infinity Digital Solution
 */


public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);

        CrashConfig.Builder.create()
                .backgroundMode(CrashConfig.BACKGROUND_MODE_CRASH) //default: CrashConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .apply();

        initService();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }


    private void initService() {
        PreferenceManager preferenceManager = new PreferenceManager(this);
        SharedPreferences sh = preferenceManager.getSharedPreferences();

        MovieAlarmManager baseJob = new MovieAlarmManager();

        baseJob.setReleaseAlarmManager(this, sh.getBoolean(RELEASE_REMINDER, false));
        baseJob.setDailyAlarmManager(this, sh.getBoolean(DAILY_REMINDER, false));
    }


}






















