package com.ids.favoritemovie.ui.moviefav;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.ViewModel;

import com.ids.favoritemovie.R;
import com.ids.favoritemovie.adapter.MovieFavAdapter;
import com.ids.favoritemovie.data.DatabaseContract;
import com.ids.favoritemovie.data.MappingHelper;
import com.ids.favoritemovie.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.ids.favoritemovie.data.AppConstants.BASE_IMAGE;
import static com.ids.favoritemovie.data.DatabaseContract.TABLE_MOVIE;

public class MovieFavViewModel extends ViewModel implements MovieFavAdapter.OnItemClickCallback{
    private MovieFavAdapter adapter;
    private Context context;
    private MovieFavNavigator movieFavNavigator;

    public void initViewModel(Context context,MovieFavNavigator movieFavNavigator){
        this.context = context;
        this.movieFavNavigator = movieFavNavigator;
        this.adapter = new MovieFavAdapter(R.layout.item_fav_movie, this);
        this.adapter.setOnItemClickCallback(this);
    }

    public void setMovies(ArrayList<Movie> movies){
        setMovieInAdapter(movies);
    }

    public void loadData(Context context){
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, context);
        context.getContentResolver().registerContentObserver(DatabaseContract.getContentUri(TABLE_MOVIE), true, myObserver);
        new LoadMovieAsync(context, movieFavNavigator).execute();
    }


    @Override
    public void onItemClicked(Movie data) {
        movieFavNavigator.showDetail(data);
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
            new LoadMovieAsync(context, (MovieFavNavigator) context).execute();
        }
    }

    private static class LoadMovieAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<MovieFavNavigator> weakCallback;

        private LoadMovieAsync(Context context, MovieFavNavigator callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(DatabaseContract.getContentUri(TABLE_MOVIE), null, null, null, null);
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> Movies) {
            super.onPostExecute(Movies);
            weakCallback.get().postExecute(Movies);
        }
    }

    public void setMovieInAdapter(ArrayList<Movie> movie) {
        this.adapter.setMovie(movie);
    }

    public MovieFavAdapter getAdapter() {
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
