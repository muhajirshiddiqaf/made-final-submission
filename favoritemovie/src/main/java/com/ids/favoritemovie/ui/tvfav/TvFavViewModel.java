package com.ids.favoritemovie.ui.tvfav;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.ViewModel;

import com.ids.favoritemovie.R;
import com.ids.favoritemovie.adapter.TvFavAdapter;
import com.ids.favoritemovie.data.DatabaseContract;
import com.ids.favoritemovie.data.MappingHelper;
import com.ids.favoritemovie.model.TvShow;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.ids.favoritemovie.data.AppConstants.BASE_IMAGE;
import static com.ids.favoritemovie.data.DatabaseContract.TABLE_TV_SHOW;

public class TvFavViewModel extends ViewModel implements TvFavAdapter.OnItemClickCallback{
    private TvFavAdapter adapter;
    private Context context;
    private TvFavNavigator tvFavNavigator;

    public void initViewModel(Context context,TvFavNavigator tvFavNavigator){
        this.context = context;
        this.tvFavNavigator = tvFavNavigator;
        this.adapter = new TvFavAdapter(R.layout.item_fav_tv, this);
        this.adapter.setOnItemClickCallback(this);
    }

    public void setTvs(ArrayList<TvShow> tvs){
        setTvInAdapter(tvs);
    }

    public void loadData(Context context){
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, context);
        context.getContentResolver().registerContentObserver(DatabaseContract.getContentUri(TABLE_TV_SHOW), true, myObserver);
        new LoadTvAsync(context, tvFavNavigator).execute();
    }


    @Override
    public void onItemClicked(TvShow data) {
        tvFavNavigator.showDetail(data);
    }

    public static class DataObserver extends ContentObserver {
        final Context context;
        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadTvAsync(context, (TvFavNavigator) context).execute();
        }
    }

    private static class LoadTvAsync extends AsyncTask<Void, Void, ArrayList<TvShow>> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<TvFavNavigator> weakCallback;

        private LoadTvAsync(Context context, TvFavNavigator callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<TvShow> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(DatabaseContract.getContentUri(TABLE_TV_SHOW), null, null, null, null);
            return MappingHelper.mapCursorTvToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<TvShow> Tvs) {
            super.onPostExecute(Tvs);
            weakCallback.get().postExecute(Tvs);
        }
    }

    public void setTvInAdapter(ArrayList<TvShow> tv) {
        this.adapter.setTvShow(tv);
    }

    public TvFavAdapter getAdapter() {
        return adapter;
    }

    public String getPoster(String imageUrl) {
        return BASE_IMAGE + imageUrl;
    }

    public String getOverview(String overview) {
        try {
            return overview.substring(0, 100) + context.getString(R.string.more);
        } catch (Exception e) {
            return context.getString(R.string.more);
        }
    }
}
