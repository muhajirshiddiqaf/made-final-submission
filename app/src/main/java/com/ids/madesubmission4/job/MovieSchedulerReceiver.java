package com.ids.madesubmission4.job;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.ids.madesubmission4.R;
import com.ids.madesubmission4.data.model.movie.ResponseMovie;
import com.ids.madesubmission4.data.rest.RestService;
import com.ids.madesubmission4.di.modules.NetworkModule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ids.madesubmission4.job.MovieAlarmManager.NOTIF_TYPE;
import static com.ids.madesubmission4.util.AppConstants.API_KEY;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 21,February,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class MovieSchedulerReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String notifType = intent.getStringExtra(NOTIF_TYPE);
        if (notifType.equals("release"))
            getMovieRelease(context);
        else
            showNotification(context, "Daily Reminder", "Don't forget open Movie Apps", 0);
    }


    private void showNotification(Context context, String title, String message, int notifId) {

        String CHANNEL_ID = "Channel_1";
        String CHANNEL_NAME = "Job scheduler channel";

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_favorite_24dp)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();
        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }
    }

    public void getMovieRelease(Context context) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        RestService data = NetworkModule.provideUserService(NetworkModule.provideRetrofit());
        Single<ResponseMovie> dataMovie = data.searchRealeaseToday(API_KEY, context.getString(R.string.movie_language), currentDate, currentDate);

        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(dataMovie.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseMovie>() {
                    @Override
                    public void onSuccess(ResponseMovie responseMovie) {
                        if (responseMovie.getTotalResults() > 0) {
                            for (int i = 0; i < responseMovie.getResults().size(); i++) {
                                showNotification(context, responseMovie.getResults().get(i).getTitle(), responseMovie.getResults().get(i).getOverview(), i);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

}
