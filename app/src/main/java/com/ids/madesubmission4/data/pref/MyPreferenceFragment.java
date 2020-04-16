package com.ids.madesubmission4.data.pref;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.job.MovieAlarmManager;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 23,January,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static String DEFAULT_VALUE = "Tidak Ada";
    public static String DAILY_REMINDER;
    public static String RELEASE_REMINDER;

    private SwitchPreference dailyPreference;
    private SwitchPreference releasePreference;

    private MovieAlarmManager movieAlarmManager = new MovieAlarmManager();

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
        init();
        setSummaries();
    }

    private void init() {
        DAILY_REMINDER = getResources().getString(R.string.key_daily_reminder);
        RELEASE_REMINDER = getResources().getString(R.string.key_release_reminder);

        dailyPreference = findPreference(DAILY_REMINDER);
        releasePreference = findPreference(RELEASE_REMINDER);
    }

    private void setSummaries() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();

        dailyPreference.setChecked(sh.getBoolean(DAILY_REMINDER, false));
        releasePreference.setChecked(sh.getBoolean(RELEASE_REMINDER, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(DAILY_REMINDER)) {
            movieAlarmManager.stopDailyAlarmManager(getContext(), sharedPreferences.getBoolean(DAILY_REMINDER, false));
            movieAlarmManager.setDailyAlarmManager(getContext(), sharedPreferences.getBoolean(DAILY_REMINDER, false));
            dailyPreference.setChecked(sharedPreferences.getBoolean(DAILY_REMINDER, false));
        }

        if (key.equals(RELEASE_REMINDER)) {
            movieAlarmManager.stopReleaseAlarmManager(getContext(), sharedPreferences.getBoolean(RELEASE_REMINDER, false));
            movieAlarmManager.setReleaseAlarmManager(getContext(), sharedPreferences.getBoolean(RELEASE_REMINDER, false));
            releasePreference.setChecked(sharedPreferences.getBoolean(RELEASE_REMINDER, false));
        }

    }
}