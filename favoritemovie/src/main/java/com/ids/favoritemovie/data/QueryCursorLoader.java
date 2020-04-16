package com.ids.favoritemovie.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.loader.content.AsyncTaskLoader;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 04,April,2020.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */
public class QueryCursorLoader extends AsyncTaskLoader<Cursor> {
    Cursor mCursor = null;
    Context mContext;
    Uri mUri;
    String mSort;

    public QueryCursorLoader(Context context, Uri uri, String sortType) {
        super(context);
        mContext = context;
        mUri = uri;
        mSort = sortType;
    }

    @Override
    public Cursor loadInBackground() {
        return mContext.getContentResolver().query(mUri, null, null, null, mSort);
    }

    @Override
    protected void onStartLoading() {
        if (mCursor != null) {
            deliverResult(mCursor);
        } else {
            forceLoad();
        }
    }

    public void deliverResult(Cursor data) {
        mCursor = data;
        super.deliverResult(data);
    }
}
