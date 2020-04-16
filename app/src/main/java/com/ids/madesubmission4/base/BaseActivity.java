package com.ids.madesubmission4.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ids.madesubmission4.util.CommonUtils;
import com.ids.madesubmission4.util.NetworkUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created with love by Muhajir Shiddiq Al Faruqi on 05,November,2019.
 * Email : infinitydsolution@gmail.com
 * Company : Infinity Digital Solution
 */

public abstract class BaseActivity<U extends ViewDataBinding, T extends ViewModel> extends DaggerAppCompatActivity {

    public Context context;

    public T vm;
    public U dataBinding;
    public View view;
    public Realm realm;
    @Inject
    protected T viewModel;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ProgressDialog mProgressDialog;
    private BaseActivity mActivity;

    public abstract
    @LayoutRes
    int getLayout();


    public abstract int getBindingVariable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayout());
        context = this;
        vm = (T) ViewModelProviders.of(this, viewModelFactory).get(viewModel.getClass());
        dataBinding.setVariable(getBindingVariable(), vm);
        dataBinding.executePendingBindings();
        isNetworkConnected();
        initRealm();
    }


    public void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(context);
    }


    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void isNetworkConnected() {
        if (!NetworkUtils.isNetworkConnected(getApplicationContext()))
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
    }
}
