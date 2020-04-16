package com.ids.madesubmission4.base;

import android.app.Application;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.AndroidViewModel;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 19,December,2019
 * Email : muhajir.shiddiq.af@gmail.com
 * Phone Number : 0895411149046
 * Company : Fujitsu Indonesia
 */
public class BaseObservableViewModel<N> extends AndroidViewModel implements Observable {

    /*
     * BaseViewModel
     * extends ViewModel
     */

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);

    private final CompositeDisposable disposable;

    private WeakReference<N> mNavigator;
    /**
     * A convenience class that implements {@link Observable} interface and provides
     * {@link #notifyPropertyChanged(int)} and {@link #notifyChange} methods.
     */

    private transient PropertyChangeRegistry mCallbacks;

    public BaseObservableViewModel(@androidx.annotation.NonNull Application application) {
        super(application);
        this.disposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        disposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return disposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    /*
     * BaseObservable
     * implement Observable
     */

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    @Override
    public void addOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }
}

