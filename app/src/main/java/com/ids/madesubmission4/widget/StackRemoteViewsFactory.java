package com.ids.madesubmission4.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.ids.madesubmission4.R;
import com.ids.madesubmission4.data.model.movie.Movie;
import com.ids.madesubmission4.data.realm.RealmHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.ids.madesubmission4.util.AppConstants.BASE_IMAGE;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 21,February,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<Movie> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private RealmHelper realmHelper;
    private Realm realm;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    public void initRealm() {
        try {
            Realm.init(mContext);
            RealmConfiguration configuration = new RealmConfiguration.Builder().build();
            realm = Realm.getInstance(configuration);
        }catch (Exception e){

        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        try{
            //if (realm == null)
            initRealm();
            realmHelper = new RealmHelper(realm, Movie.class);
            mWidgetItems.clear();
            mWidgetItems.addAll(realmHelper.getWidgetData());

        }catch (Exception e){

        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        try{
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
            if (mWidgetItems.get(i).getPosterPath().isEmpty())
                rv.setImageViewBitmap(R.id.imageView, BitmapFactory.decodeResource(mContext.getResources(), R.drawable.round_gradient));
            else
                try {
                    Bitmap bp = Glide.with(mContext).asBitmap().load(BASE_IMAGE + mWidgetItems.get(i).getPosterPath()).submit().get();
                    rv.setImageViewBitmap(R.id.imageView, bp);
                } catch (Exception e) {
                    e.printStackTrace();
                    rv.setImageViewBitmap(R.id.imageView, BitmapFactory.decodeResource(mContext.getResources(), R.drawable.round_gradient));
                }
            Bundle extras = new Bundle();
            extras.putInt(FavoriteWidget.EXTRA_ITEM, i);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);

            return rv;
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

